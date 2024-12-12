#!/bin/bash

get() {
    curl --silent --fail $@
    exit_code=$?
    if [[ $exit_code > 0 ]]; then
        echo "A problem occurred while downloading the theme!"
        exit $exit_code
    fi
}

downloadTheme() {
    theme=$1
    version=$2
    mkdir -p css

    echo "Downloading $theme@$version"
    get "https://cdn.jsdelivr.net/npm/bootswatch@$version/dist/$theme/_variables.scss" -o css/bootstrap.$theme.variables.scss
    get "https://cdn.jsdelivr.net/npm/bootswatch@$version/dist/$theme/_bootswatch.scss" -o css/bootstrap.$theme.scss
    get "https://cdn.jsdelivr.net/npm/bootswatch@$version/dist/$theme/bootstrap.min.css" -o css/bootstrap.$theme.min.css
    get "https://cdn.jsdelivr.net/npm/bootswatch@$version/dist/$theme/bootstrap.css" -o css/bootstrap.$theme.css
    get "https://cdn.jsdelivr.net/npm/bootswatch@$version/dist/$theme/bootstrap.rtl.min.css" -o css/bootstrap.rtl.$theme.min.css
    get "https://cdn.jsdelivr.net/npm/bootswatch@$version/dist/$theme/bootstrap.rtl.css" -o css/bootstrap.rtl.$theme.css

    echo -e "@import \"../../bootstrap/css/bootstrap.scss\";\n@import \"bootstrap.$theme.variables.scss\";\n\n" | cat - css/bootstrap.$theme.scss > tmp.$theme.out && mv tmp.$theme.out css/bootstrap.$theme.scss
}

for theme in cerulean cosmo cyborg darkly flatly journal litera lumen lux materia minty morph pulse quartz sandstone simplex sketchy slate solar spacelab superhero united vapor yeti zephyr
do
    downloadTheme $theme 'v5.3.3'
done

