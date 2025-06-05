#!/bin/bash

while true; do
    read -p "Entrez le nom du fichier: " filename

    if [ -e "$filename" ]; then
        file_type=$(file "$filename")
        echo "Type de fichier: $file_type"
    else
        echo "Le fichier $filname n'existe pas."
    fi

    read -p "Voulez vous continuer? (N/n/NON/NO) pour arrêter: " response

    response_lower=$(echo "$response" | tr '[:upper:]' '[:lower:]')

    case "$response_lower" in
        non|no|n)
            exit 0
            ;;
    esac
done
