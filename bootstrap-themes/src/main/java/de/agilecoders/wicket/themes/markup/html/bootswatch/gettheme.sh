#!/bin/bash 

downloadTheme() {
    theme=$1
    mkdir -p css

    wget "http://bootswatch.com/4/$theme/_variables.scss" -O css/bootstrap.$theme.variables.scss
    wget "http://bootswatch.com/4/$theme/_bootswatch.scss" -O css/bootstrap.$theme.scss
    wget "http://bootswatch.com/4/$theme/bootstrap.min.css" -O css/bootstrap.$theme.min.css
    wget "http://bootswatch.com/4/$theme/bootstrap.css" -O css/bootstrap.$theme.css

    echo -e "@import \"../../bootstrap/css/bootstrap.scss\";\n@import \"bootstrap.$theme.variables.scss\";\n\n" | cat - css/bootstrap.$theme.scss > tmp.$theme.out && mv tmp.$theme.out css/bootstrap.$theme.scss
}

for theme in cerulean cosmo cyborg darkly flatly journal litera lumen lux materia minty pulse sandstone simplex sketchy slate spacelab superhero united yeti
do
    downloadTheme $theme 2>&1 & >/dev/null
done

