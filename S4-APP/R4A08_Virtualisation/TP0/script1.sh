#!/bin/bash

ls > fich1
date >> fich1
echo "salut" >> fich1

pwd

n=5
((a=77+$n))
mot="salut"
let var1=3*$a

echo $var1
read nom
echo $nom

echo "mon premier argument est: $1"
echo "mon second argument est: $2"
