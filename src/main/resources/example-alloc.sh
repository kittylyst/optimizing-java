#!/bin/bash

# Simple script for running the model toy allocator

CP=./target/optimizing-java-1.0.0-SNAPSHOT.jar

GC_LOG_OPTS="-Xloggc:gc-alloc-`date +%s`.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution"

MEM_OPTS="-Xmx1G"

JAVA_BIN=`which java`

if [ $JAVA_HOME ]; then
    JAVA_CMD=$JAVA_HOME/bin/java
elif [ $JAVA_BIN ]; then
    JAVA_CMD=$JAVA_BIN
else
    echo "For this command to run, either $JAVA_HOME must be set, or java must be in the path."
    exit 1
fi

exec $JAVA_CMD -cp $CP $GC_LOG_OPTS $MEM_OPTS optjava.ModelAllocator