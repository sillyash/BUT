#!/usr/bin/bash

# Make a directory tree for the French corpus data.
# Its root is corpus/, it has 2 sub-directories: bin/ for scripts and French-data/
# The French-data/ directory should contain
# - a README.md file with a unique sentence: "This is the README file from the multiword expressions (MWEs) corpus for French, version 1.4."
# - s symbolic link to the bin/ directory
# Input parameters: none

echo "Creating a directory tree for the corpus data in French"

# Creating the root directory executable and readable by all
mkdir corpus
chmod 755 corpus

# Creating the global README file
cd corpus
echo "Creating a global README file"
echo "This is the README file from the multiword expressions (MWEs)" \
			"corpus for all languages, edition 1.4." > README.md
chmod 644 README.md

# Creating sub-directories with the same rights
mkdir bin French-data
chmod 755 bin French-data

# Creating the README file
cd French-data
echo "This is the README file from the multiword expressions (MWEs)" \
			"corpus for French, edition 1.4." > README.md
chmod 755 README.md

# Creating an empty .cuptfile
echo "Creating an empty .cupt file"
touch train.cupt
chmod 644 train.cupt

# Creating a symbolic link
echo "Creating a symbolic link from French-data/ to bin/."
ln -s ../bin scripts

# Creating a hard link
echo "Creating a hard link from French-data/ to the global README.md."
ln ../README.md README-all.md

# Returning to the root directory
cd ../../

echo "DONE!"
