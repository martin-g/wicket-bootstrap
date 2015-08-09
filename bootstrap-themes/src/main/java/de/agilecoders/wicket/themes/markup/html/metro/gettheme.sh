#!/bin/bash 

downloadTheme() {
    file=$1

    wget "https://raw.githubusercontent.com/idleberg/m8tro-bootstrap/master/dist/$file" -O $file
}

downloadFonts() {
	file=$1

    wget "https://github.com/twbs/bootstrap/raw/master/dist/$file" -O $file	
}

for file in css/m8tro.min.css css/m8tro.css
do
    downloadTheme $file 2>&1 & >/dev/null
done

for file in fonts/glyphicons-halflings-regular.eot fonts/glyphicons-halflings-regular.svg fonts/glyphicons-halflings-regular.ttf fonts/glyphicons-halflings-regular.woff fonts/glyphicons-halflings-regular.woff2
do
    downloadFonts $file 2>&1 & >/dev/null
done
