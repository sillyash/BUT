#!/bin/bash

# Execute inside Zephyr container

cd /workspace/zephyrproject

mkdir -p lib
cd lib

# Download lib
git clone https://github.com/DaveGamble/cJSON.git
