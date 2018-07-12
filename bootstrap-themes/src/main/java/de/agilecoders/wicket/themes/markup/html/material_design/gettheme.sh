#!/bin/bash

downloadTheme() {
    file=$1

    wget "https://unpkg.com/bootstrap-material-design/dist/$file" -O $file
}

for file in css/bootstrap-material-design.css css/bootstrap-material-design.min.css \
	js/bootstrap-material-design.js js/bootstrap-material-design.min.js js/bootstrap-material-design.js.map
do
    downloadTheme $file 2>&1 & >/dev/null
done

