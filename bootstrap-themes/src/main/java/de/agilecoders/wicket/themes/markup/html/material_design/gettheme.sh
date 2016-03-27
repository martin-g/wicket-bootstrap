#!/bin/bash 

downloadTheme() {
    file=$1

    wget "https://raw.githubusercontent.com/FezVrasta/bootstrap-material-design/master/dist/$file" -O $file
}

for file in css/bootstrap-material-design.css css/bootstrap-material-design.css.map css/bootstrap-material-design.min.css css/bootstrap-material-design.min.css.map \
	css/ripples.css css/ripples.css.map css/ripples.min.css css/ripples.min.css.map \
	js/material.js js/material.min.js js/material.min.js.map \
	js/ripples.js js/ripples.min.js js/ripples.min.js.map
do
    downloadTheme $file 2>&1 & >/dev/null
done

