#!/bin/bash

file=$1

print_usage() {
    echo "Usage: $0 <file>"
}

if [ -z $file ]; then
    echo "Argument <file> not specified."
    print_usage
    exit 1
elif [ -nf $file ]; then
    echo "File $file does not exist."
    print_usage
    exit 1
fi

cpt=1
cat $file | while read line
do
    echo -e $cpt $line
    cpt=$((cpt + 1))
done

