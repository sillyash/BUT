#!/bin/bash

dir="$1"
files=$2
filename="$3"

print_usage() {
    echo "Usage: $0 <dirname> <files> <filename>"
}

# Check args
if [ $# -lt 3 ]; then
    echo "Expected 3 arguments, got $#."
    print_usage
    exit 1
elif [ -z $dir ]; then
    echo "No directory specified."
    print_usage
    exit 1
elif [ -d $dir ]; then
    echo "Directory $dir already exists."
    print_usage
    exit 2
elif [ -f $dir ]; then
    echo "$dir is a file."
    print_usage
    exit 2
elif [ -z $files ]; then
    echo "Number of files not specified."
    print_usage
    exit 1
elif [ $files -le 0 ]; then
    echo "Number of files $files is not correct."
    print_usage
    exit 1
fi

# Dir
mkdir -p $dir
echo "Directory $dir created !"

# Files
echo -n "Creating $files $filename in $dir..."

for cpt in `seq 1 $files`
do
    touch "$dir/$filename$cpt"
done

echo " Done!"

# Cleanup
sleep 2
echo "Deleting $dir..."
rm -rf $dir
