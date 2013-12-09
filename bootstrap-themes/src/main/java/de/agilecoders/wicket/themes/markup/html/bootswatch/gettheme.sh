#!/bin/bash 

downloadTheme() {
    theme=$1
    mkdir -p css

    wget "http://bootswatch.com/$theme/variables.less" -O css/bootstrap.$theme.variables.less
    wget "http://bootswatch.com/$theme/bootswatch.less" -O css/bootstrap.$theme.less
    wget "http://bootswatch.com/$theme/bootstrap.min.css" -O css/bootstrap.$theme.min.css
    wget "http://bootswatch.com/$theme/bootstrap.css" -O css/bootstrap.$theme.css

    echo -e "@import \"../../bootstrap/css/bootstrap.less\";\n@import \"bootstrap.$theme.variables.less\";\n\n" | cat - css/bootstrap.$theme.less > tmp.$theme.out && mv tmp.$theme.out css/bootstrap.$theme.less
}

for theme in amelia cerulean cosmo cyborg flatly journal readable simplex slate spacelab united yeti 
do
    downloadTheme $theme 2>&1 & >/dev/null
done

