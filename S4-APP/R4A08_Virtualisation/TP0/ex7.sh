#!/bin/bash

while true; do
    read -p "Entrez le nom du répertoire: " dirname
    
    if [ -d "$dirname" ]; then
        if [ ! -r "$dirname" ]; then
            file_type=$(file "$dirname")
            echo "Type de fichier: $file_type"
        else
            echo "Le répertoire $dirname est accessible en lecture et ne sera pas traité."
        fi
    else
        echo "$dirname n'est pas un répertoire ou n'existe pas."
    fi
    
    # Demander si on veut continuer
    read -p "Voulez-vous continuer? (N/n/NON/NO) pour arrêter: " reponse
    
    reponse_lower=$(echo "$reponse" | tr '[:upper:]' '[:lower:]')
    
    # Vérifier les conditions d'arrêt
    case "$reponse_lower" in
        non|no|n) 
            echo "Fin du script."
            exit 0
            ;;
    esac
done