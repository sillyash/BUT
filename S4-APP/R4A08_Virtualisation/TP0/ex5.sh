#!/bin/bash

echo -n "Entrez le mois : "
read month

echo -n "Entrez l'année : "
read year

echo ""
cal -m $month $year
