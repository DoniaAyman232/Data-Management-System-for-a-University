#!/bin/bash

space=90

disk_use=$(df -h /c | grep '^C:' | awk '{ print $5 }' | cut -d'%' -f1)

if [ "$disk_use" -gt "$space" ]; then 
    echo "Disk space usage is above $space% at $(date)" >> /h/casestudy/bash/alert.txt
else 
    echo "Disk space is within acceptable limits  at $(date)" >> /h/casestudy/bash/alert.txt
fi
