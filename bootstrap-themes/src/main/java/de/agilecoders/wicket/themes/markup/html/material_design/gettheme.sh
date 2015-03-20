#!/bin/bash 

downloadTheme() {
    file=$1

    wget "https://raw.githubusercontent.com/FezVrasta/bootstrap-material-design/master/dist/$file" -O $file
}

for file in css/material-fullpalette.css css/material-fullpalette.css.map css/material-fullpalette.min.css css/material-fullpalette.min.css.map \
	css/material.css css/material.css.map css/material.min.css css/material.min.css.map \
	css/ripples.css css/ripples.css.map css/ripples.min.css css/ripples.min.css.map \
	css/roboto.css css/roboto.css.map css/roboto.min.css css/roboto.min.css.map \
	fonts/LICENSE.txt \
	fonts/Material-Design-Icons.eot fonts/Material-Design-Icons.svg fonts/Material-Design-Icons.ttf fonts/Material-Design-Icons.woff \
	fonts/RobotoDraftBold.woff fonts/RobotoDraftBold.woff2 \
	fonts/RobotoDraftItalic.woff fonts/RobotoDraftItalic.woff2 \
	fonts/RobotoDraftMedium.woff fonts/RobotoDraftMedium.woff2 \
	fonts/RobotoDraftRegular.woff fonts/RobotoDraftRegular.woff2 \
	js/material.js js/material.min.js js/material.min.js.map \
	js/ripples.js js/ripples.min.js js/ripples.min.js.map
do
    downloadTheme $file 2>&1 & >/dev/null
done

