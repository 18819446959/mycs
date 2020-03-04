#!/bin/sh
cd `dirname $0`

# 当前路径
BIN_DIR=`pwd`

# 向上一层路径
cd ..
DEPLOY_DIR=`pwd`
# 依赖jar包目录
LIB_DIR=$DEPLOY_DIR/lib
# 配置文件路径
TMP_DIR=$BIN_DIR/tmp
CONF_DIR=$DEPLOY_DIR/conf
CONFIG_FILE=$TMP_DIR/application.yml
LOGS_FILE=$TMP_DIR/application-log.yml
# 日志输出路径

LOGS_DIR=`sed -r '0,/^logging:/d;/^ +file:/!d;s/.*://' $LOGS_FILE | head -n1`
LOGS_DIR=${LOGS_DIR%#*}
LOGS_DIR="`echo $LOGS_DIR`"
if [ -n "$LOGS_DIR" ]; then
    echo "$LOGS_DIR" | grep -q "/"
    if [ $? -eq 0 ]; then
        LOGS_DIR=${LOGS_DIR%/*}
        echo $LOGS_DIR
        echo "$LOGS_DIR" | grep -q "^\s*$"
        if [ $? -eq 0 ]; then
            LOGS_DIR="/";
        fi
    else
        LOGS_DIR=""
    fi
else
    LOGS_DIR=`sed -r '0,/^logging:/d;/^ +path:/!d;s/.*://' $LOGS_FILE | head -n1`
    LOGS_DIR=${LOGS_DIR%#*}
    LOGS_DIR="`echo $LOGS_DIR`"
fi

if [ ! -n "$LOGS_DIR" ]; then
	LOGS_DIR="."
fi

# 主启动类
MAIN_JAR=`ls $LIB_DIR|grep .*-exec.jar|awk '{print "'$LIB_DIR'/"$0}'`
# 控制台日志输出收集位置
SHELL_OUTPUT_FILE=/dev/null

# 如果JDK环境变量没有写到全局要添加如下几行
JAVA_HOME=/usr/local/jdk1.8.0_181
PATH=$JAVA_HOME/bin:$PATH
export JAVA_HOME
export PATH

# 从配置文件取得应用名、端口号，端口名
SERVER_NAME=`sed -r '0,/^spring:/d;0,/^ +application:/d;/^ +name:/!d;s/.*://' $CONFIG_FILE | head -n1`
SERVER_NAME=${SERVER_NAME%#*}
SERVER_NAME=`echo $SERVER_NAME`

SERVER_PORT=`sed -r '0,/^server:/d;/^ +port:/!d;s/.*://' $CONFIG_FILE | head -n1`
SERVER_PORT=${SERVER_PORT%#*}
SERVER_PORT=`echo $SERVER_PORT`

# 应用名为空的话就取当前系统名
if [ -z "$SERVER_NAME" ]; then
    echo "SERVER_NAME is empty"
    SERVER_NAME=`hostname`
fi
echo
echo "-------------------------------------- $SERVER_NAME 启 动 信 息 --------------------------------------"
echo "部署目录：$DEPLOY_DIR"
echo "日志目录：$LOGS_DIR"
echo "启动 JAR：$MAIN_JAR"
echo "LIB 目录：$LIB_DIR"
echo "CONF目录：$CONF_DIR"
echo "服 务 名：$SERVER_NAME"
echo "端 口 号：$SERVER_PORT"
echo
