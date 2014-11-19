#!/bin/bash 

downloadTheme() {
    file=$1

    wget "https://raw.githubusercontent.com/FezVrasta/bootstrap-material-design/master/dist/$file" -O $file
}

for file in css/material-wfont.css css/material-wfont.min.css css/ripples.min.css fonts/LICENSE.txt fonts/Material-Design-Icons.eot fonts/Material-Design-Icons.svg fonts/Material-Design-Icons.ttf fonts/Material-Design-Icons.woff js/material.js js/material.min.js js/ripples.js js/ripples.min.js
do
    downloadTheme $file 2>&1 & >/dev/null
done

