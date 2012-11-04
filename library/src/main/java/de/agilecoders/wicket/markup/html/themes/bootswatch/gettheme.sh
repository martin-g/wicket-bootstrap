#!/bin/bash 

downloadTheme() {
    theme=$1
    mkdir -p css
    mkdir -p less/$theme

    wget "http://bootswatch.com/$theme/variables.less" -O less/$theme/variables.less
    wget "http://bootswatch.com/$theme/bootswatch.less" -O less/$theme/bootswatch.less
    wget "http://bootswatch.com/$theme/bootstrap.min.css" -O css/bootstrap.$theme.min.css
    wget "http://bootswatch.com/$theme/bootstrap.css" -O css/bootstrap.$theme.css
}

for theme in amelia cerulean cyborg journal readable simplex slate spacelab spruce superhero united
do
    downloadTheme $theme
done

