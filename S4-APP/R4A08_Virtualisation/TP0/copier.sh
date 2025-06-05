#!/bin/bash

set -e

if [ ! $# -eq 2 ]; then
    echo "Usage: copier <file1> <file2>"
    exit 1
fi

fileSource="$1"
fileDest="$2"

if [ -f "$fileDest" ]; then
    echo "Destination file already exists : $fileDest"
    exit 2
elif [ ! -f "$fileSource" ]; then
    echo "Source file does not exist : $fileSource"
    exit 2
elif [ ! -r "$fileSource" ]; then
    echo "Source file is not readable."
    exit 1
fi

dirDest=$(dirname "$fileDest")

if [ ! -w "$dirDest" ]; then
    echo "Destination directory is not writeable."
    exit 1
fi

cat "$fileSource" > "$fileDest"
