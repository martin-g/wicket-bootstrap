#!/bin/bash

# test that sass compiler exists on path otherwise exit.
if ! [ -x "$(command -v sass)" ];
then
   echo "Sass compiler was not found"
   exit 1
fi

# Download bootstrap source files
wget https://github.com/twbs/bootstrap/archive/v4.1.0.zip -O bootstrap.zip
unzip bootstrap.zip
mv bootstrap-4.1.0 bootstrap

# Compile bootstrap-theme to bootstrap-theme.css file
mkdir -p css/
touch css/bootstrap-theme.scss

echo -e '$enable-gradients: true;\n$enable-shadows: true;\n\n@import "../bootstrap/scss/bootstrap.scss";\n\n' > css/bootstrap-theme.scss

sass css/bootstrap-theme.scss > css/bootstrap-theme.css

# Cleanup source files
rm -rf bootstrap
rm bootstrap.zip
rm css/bootstrap-theme.scss