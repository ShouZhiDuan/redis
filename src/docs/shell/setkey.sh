#!/bin/bash
for ((i=0;i<20000;i++))
do
        echo -en "helloworld" | redis-cli -h 192.168.10.24 -p 7291 -c -x set name$i >>redis.log
done