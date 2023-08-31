function createTempusDominus(elementId, config, localization, lng) {
	const el = document.getElementById(`${elementId}`);
	let defLocalization = {};
	if (tempusDominus.locales) {
		const fullName = localization.locale.replace('-', '_');
		const locale = tempusDominus.locales[fullName] || tempusDominus.locales[lng];
		defLocalization = locale ? locale.localization : {};
	}
	config.localization = Object.assign({}, defLocalization, localization);
	el.datetimepicker = new tempusDominus.TempusDominus(el, config);
}

function destroyTempusDominus(elementId) {
	const el = document.getElementById(`${elementId}`);
	if (el && el.datetimepicker) {
		el.datetimepicker.dispose();
		delete el.datetimepicker;
	}
}
