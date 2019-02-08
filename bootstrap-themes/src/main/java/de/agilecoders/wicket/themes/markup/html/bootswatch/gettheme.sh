#!/bin/bash 

downloadTheme() {
    theme=$1
    mkdir -p css

    wget "http://bootswatch.com/3/$theme/variables.less" -O css/bootstrap.$theme.variables.less
    wget "http://bootswatch.com/3/$theme/bootswatch.less" -O css/bootstrap.$theme.less
    wget "http://bootswatch.com/3/$theme/bootstrap.min.css" -O css/bootstrap.$theme.min.css
    wget "http://bootswatch.com/3/$theme/bootstrap.css" -O css/bootstrap.$theme.css

    echo -e "@import \"../../bootstrap/css/bootstrap.less\";\n@import \"bootstrap.$theme.variables.less\";\n\n" | cat - css/bootstrap.$theme.less > tmp.$theme.out && mv tmp.$theme.out css/bootstrap.$theme.less
}

for theme in cerulean cosmo cyborg darkly flatly journal lumen readable simplex slate spacelab superhero united yeti paper sandstone
do
    downloadTheme $theme 2>&1 & >/dev/null
done

