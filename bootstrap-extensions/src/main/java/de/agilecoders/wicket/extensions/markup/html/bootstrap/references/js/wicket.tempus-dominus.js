if (typeof(Wicket) === 'undefined') {
    window.Wicket = {};
}

if (typeof(Wicket.Bootstrap) === 'undefined') {
    Wicket.Bootstrap = {};
}

Wicket.Bootstrap.createTempusDominus = function (elementId, config, localization, lang) {
    const el = document.getElementById(`${elementId}`);
    let defLocalization = {};
    if (tempusDominus.locales) {
        const fullName = localization.locale.replace('-', '_');
        const locale = tempusDominus.locales[fullName] || tempusDominus.locales[lang];
        defLocalization = locale ? locale.localization : {};
    }
    config.localization = Object.assign({}, defLocalization, localization);
    el.datetimepicker = new tempusDominus.TempusDominus(el, config);
}

Wicket.Bootstrap.destroyTempusDominus = function (elementId) {
    const el = document.getElementById(`${elementId}`);
    if (el && el.datetimepicker) {
        el.datetimepicker.dispose();
        delete el.datetimepicker;
    }
}
