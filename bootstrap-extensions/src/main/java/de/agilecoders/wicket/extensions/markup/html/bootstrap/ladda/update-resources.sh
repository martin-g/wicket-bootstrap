#!/bin/bash 

downloadResource() {
    wget "https://raw.githubusercontent.com/msurguy/ladda-bootstrap/master/dist/$1" -O $2
}

declare -A CONFIG
CONFIG["ladda-themeless.css"]="css/ladda-themeless.css"
CONFIG["ladda-themeless.min.css"]="css/ladda-themeless.min.css"
CONFIG["ladda.js"]="js/ladda.js"
CONFIG["ladda.min.js"]="js/ladda.min.js"

for key in "${!CONFIG[@]}"
do
    downloadResource $key ${CONFIG[$key]} 2>&1 & >/dev/null
done

