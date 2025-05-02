#!/bin/bash

dir="$1"

if [ -z $dir ]; then
    echo "No directory specified."
    echo "Usage: $0 <dirname>"
    exit 1
elif [ -d $dir ]; then
    echo "Directory $dir already exists."
    exit 2
elif [ -f $dir ]; then
    echo "$dir is a file."
    exit 2
fi 

mkdir -p $dir
echo "Directory $dir created !"

sleep 2

echo "Deleting $dir..."
rm -rf $dir
