#!/bin/bash 

downloadTheme() {
    file=$1

    wget "https://raw.githubusercontent.com/todc/todc-bootstrap/master/dist/$file" -O $file
}

for file in css/todc-bootstrap.css css/todc-bootstrap.min.css css/todc-bootstrap.css.map img/checkmark.png
do
    downloadTheme $file 2>&1 & >/dev/null
    rename -f 's/todc/google/' $file
done

