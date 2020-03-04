#!/bin/sh
. `dirname $0`/env.sh

# 获取 pid
PIDS=`ps -ef -o pid -o args | grep -v "grep" | grep "java" | grep "$MAIN_JAR" | awk '{print $1}' | awk '{sub(/\D+/, "");print $1}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME is not running!"
    exit 1
fi

if [ "$1" != "skip" ]; then
    $BIN_DIR/dump.sh
fi

# kill 线程
echo -e "Stopping the $SERVER_NAME [$PIDS] ...\c"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
        PID_EXIST=`ps -ef -o pid | grep $PID | grep -v "grep"`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done

echo "OK"
