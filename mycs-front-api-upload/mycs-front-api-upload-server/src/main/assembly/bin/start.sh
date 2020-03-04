#!/bin/sh
. `dirname $0`/env.sh

# 根据配置文件路径去查找当前是否已有应用启动起来
APP_PID=`ps -ef -o pid -o args | grep -v "grep" | grep "java" | grep "$MAIN_JAR" | awk '{print $1}' | awk '{sub(/\D+/, "");print $1}'`

# APP_PID不为空，说明应用已启动，直接退出
if [ -n "$APP_PID" ]; then
    if [ "$1" = "restart" ]; then
        echo -e "Killing the $SERVER_NAME [PID: $APP_PID] ...\c"
        kill $APP_PID
        echo "OK"
        echo -e "The $SERVER_NAME is being destroyed ...\c"
        while (true)
        do
            sleep 1
            APP_PID=`ps -ef -o pid -o args | grep -v "grep" | grep "java" | grep "$MAIN_JAR" | awk '{print $1}' | awk '{sub(/\D+/, "");print $1}'`
            if [ -n "$APP_PID" ]; then
                echo -e ".\c"
            else
                echo "OK"
                break
            fi
        done
    else
        echo "ERROR: The $SERVER_NAME had bean started"
        echo "exit"
        exit 1
    fi
fi

# 检查端口是否被占用
if [ -n "$SERVER_PORT" ]; then
    ss=/usr/sbin/ss
    if [ ! -f "$ss" ]; then
        ss=/bin/netstat
    fi
    SERVER_PORT_COUNT=`$ss -tln | grep $SERVER_PORT | wc -l`
    if [ $SERVER_PORT_COUNT -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME's port [$SERVER_PORT] is already in use !"
        exit 1
    fi
fi


# 如果logs目录不存在，就创建一个
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

# 将上面的jar文件名称，拼接上lib的路径然后输出
LIB_JARS=`ls $LIB_DIR|grep .jar|grep -v .*-exec.jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

# -DappName 指定应用名
#JAVA_OPTS="-DappName=$SERVER_NAME -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Ddubbo.shutdown.hook=true"
#JAVA_OPTS="-DappName=$SERVER_NAME"

# 调试模式
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

# JVM启动基本参数
JAVA_MEM_SIZE_OPTS="-Xmx128m -Xms64m -Xmn128m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128M -Xss256k"

# 引入启动参数配置
if [ -f "$BIN_DIR/include/start.conf" ]; then
    . $BIN_DIR/include/start.conf
    echo "Include $BIN_DIR/include/start.conf"
else
    echo "Include $BIN_DIR/include/start.conf fail, use the default settings"
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


RUN_CMD="java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS -jar $MAIN_JAR"
# 通过java命令启动服务，同时将其作为后台任务执行。（使用 Docker 启动时注释下面相关内容）
# ================================== Java App ==================================
echo "$RUN_CMD" >> $SHELL_OUTPUT_FILE
echo -e "Run the $SERVER_NAME with the command below:"
echo "$RUN_CMD"
nohup $RUN_CMD > $SHELL_OUTPUT_FILE 2>&1 &

# 睡眠一下再检查应用是否启动
sleep 1
APP_PID=`ps -ef -o pid -o args | grep -v "grep" | grep "java" | grep "$MAIN_JAR" | awk '{print $1}' | awk '{sub(/\D+/, "");print $1}'`

if [ -z "$APP_PID" ]; then
    echo -e "The $SERVER_NAME started FAILED \n"
    exit 1
else
    echo -e "The $SERVER_NAME has started . [PID: $APP_PID]\n"
    echo $APP_PID > $SERVER_NAME.pid
fi
# ==================================== End =====================================

# 使用 Docker 运行时启用该命令，并注释上面的脚本
# ============================== Docker Container ==============================
#$RUN_CMD
# ==================================== End =====================================
