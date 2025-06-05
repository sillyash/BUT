#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Usage: $0 nom_du_script"
    exit 1
fi

script_name=$1

shift

if [ -f "$script_name" ] && [ -x "$script_name" ]; then
    source "$script_name" "$@"
else
    echo "Le script $script_name n'existe pas ou n'est pas exécutable."
    exit 1
fi