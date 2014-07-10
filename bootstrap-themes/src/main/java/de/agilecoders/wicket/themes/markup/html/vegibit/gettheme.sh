#!/bin/bash 

downloadTheme() {
    theme=$1
    mkdir -p css

    wget "http://vegibit.com/themes/$theme/bootstrap.min.css" -O css/bootstrap.$theme.css
    #wget "http://bootswatch.com/$theme/bootswatch.less" -O css/bootstrap.$theme.less
    #wget "http://bootswatch.com/$theme/bootstrap.min.css" -O css/bootstrap.$theme.min.css
    #wget "http://bootswatch.com/$theme/bootstrap.css" -O css/bootstrap.$theme.css

    #echo -e "@import \"../../bootstrap/css/bootstrap.less\";\n@import \"bootstrap.$theme.variables.less\";\n\n" | cat - css/bootstrap.$theme.less > tmp.$theme.out && mv tmp.$theme.out css/bootstrap.$theme.less
}

for theme in vegiflat vegistone vegitalian vegication vegisweet vegiretro vegimelon vegipooh vegisea vegilibrium vegibuntu vegitapenade vegimin vegilime vegitini vegicalm vegificial vegisail vegicasso vegimoon
do
    downloadTheme $theme 2>&1 & >/dev/null
done
