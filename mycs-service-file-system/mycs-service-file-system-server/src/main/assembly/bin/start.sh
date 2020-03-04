#!/bin/sh
cd `dirname $0`

# 当前路径
BIN_DIR=`pwd`

# 向上一层路径
cd ..
DEPLOY_DIR=`pwd`

# 配置文件路径
CONF_DIR=$DEPLOY_DIR/conf
CONFIG_FILE=$CONF_DIR/application.yml
# 主启动类
MAIN_CLASS=cn.mycs.service.file.system.server.FileSystemServerApp

# 如果JDK环境变量没有写到全局要添加如下几行
JAVA_HOME=/usr/local/jdk1.8.0_181
PATH=$JAVA_HOME/bin:$PATH
export JAVA_HOME
export PATH

# 从配置文件取得应用名、端口号，端口名
SERVER_NAME=`sed '/[[:space:]]name:/!d;s/.*:[[:space:]]//' $CONFIG_FILE | sed 's/[[:space:]]#.*$//g' | tr -d '\r'`
SERVER_NAME=${SERVER_NAME%#*}
SERVER_PORT=`sed '/[[:port:]]name:/!d;s/.*:[[:space:]]//' $CONFIG_FILE | sed 's/[[:space:]]#.*$//g' | tr -d '\r'`
SERVER_PORT=${SERVER_PORT%#*}

# 应用名为空的话就取当前系统名
if [ -z "$SERVER_NAME" ]; then
    echo "SERVER_NAME is empty"
    SERVER_NAME=`hostname`
fi
echo "====================================== Starting the $SERVER_NAME ================================================="
# 根据配置文件路径去查找当前是否已有应用启动起来
APP_PID=`ps -ef -ww | grep "java" |  grep $CONF_DIR | grep $MAIN_CLASS  | awk '{print $2}'`
echo "SERVER_NAME: $SERVER_NAME"
echo "SERVER_PORT: $SERVER_PORT"
echo "APP_PID: $APP_PID"

# APP_PID不为空，说明应用已启动，直接退出
if [ -n "$APP_PID" ]; then
    echo "PID: $APP_PID"
    if [ "$1" = "restart" ]; then
        echo "The $SERVER_NAME is restarting ..."
        kill $APP_PID
        sleep 3
    else
        echo "ERROR: The $SERVER_NAME already started!"
        echo "exit"
        exit 1
    fi
fi

# 检查端口是否被占用
if [ -n "$SERVER_PORT" ]; then
    SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`
    if [ $SERVER_PORT_COUNT -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
        exit 1
    fi
fi

# 日志输出路径
LOGS_DIR=/wdata/weblog/$SERVER_NAME
# 如果logs目录不存在，就创建一个
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

echo "LOGS_DIR :$LOGS_DIR"

# 控制台日志输出收集位置
STDOUT_FILE=$LOGS_DIR/stdout.log

# 依赖jar包目录
LIB_DIR=$DEPLOY_DIR/lib

# 将上面的jar文件名称，拼接上lib的路径然后输出
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

# -DappName 指定应用名
#JAVA_OPTS="-DappName=$SERVER_NAME"

# 调试模式
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=18540,server=y,suspend=n "
fi
JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

# JVM启动基本参数
JAVA_MEM_SIZE_OPTS="-Xmx128m -Xms64m -Xmn128m -XX:PermSize=64m -XX:MaxPermSize=128M -Xss256k"

# 引入启动参数配置
if [ -f "$BIN_DIR/include/start.conf" ]; then
    . $BIN_DIR/include/start.conf
    echo "读取配置文件成功"
else
    echo "路径 $BIN_DIR/include/start.conf 中找不到配置文件，使用默认值"
fi

JAVA_MEM_OPTS=" -server $JAVA_MEM_SIZE_OPTS"

# 首先将java版本号信息输出到标准输出，然后查找’64-bit’信息，目的就是判断jdk版本是否为64位
BITS=`java -version 2>&1 | grep -i 64-bit`

# 根据32位和64位配置不同的启动java垃圾回收参数，根据应用自行调整
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS="$JAVA_MEM_OPTS -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS="$JAVA_MEM_OPTS -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

echo -e "Starting the $SERVER_NAME ...\c"
echo "启动参数：java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS $MAIN_CLASS" >> $STDOUT_FILE

# 通过java命令启动服务，同时将其作为后台任务执行。（使用 Docker 启动时注释下面相关内容）
# ================================== Java App ==================================
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS $MAIN_CLASS > $STDOUT_FILE 2>&1 &
# 睡眠一下再检查应用是否启动
sleep 1
APP_PID=`ps -f | grep java | grep "$MAIN_CLASS" |awk '{print $2}'`

if [ -z "$APP_PID" ]; then
    echo "START APP FAIL!"
    echo "STDOUT: $STDOUT_FILE"
    exit 1
fi

echo "START SUCCESSED APP_PID: $APP_PID"
echo "STDOUT: $STDOUT_FILE"
# ==================================== End =====================================

# 使用 Docker 运行时启用该命令，并注释上面的脚本
# ============================== Docker Container ==============================
#java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS $MAIN_CLASS > $STDOUT_FILE 2>&1
# ==================================== End =====================================