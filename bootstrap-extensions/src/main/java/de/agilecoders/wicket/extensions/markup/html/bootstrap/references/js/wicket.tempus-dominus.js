function createTempusDominus(elementId, config, localization, lng, events) {
	const el = document.getElementById(`${elementId}`);
	let defLocalization = {};
	if (tempusDominus.locales) {
		const fullName = localization.locale.replace('-', '_');
		const locale = tempusDominus.locales[fullName] || tempusDominus.locales[lng];
		defLocalization = locale ? locale.localization : {};
	}
	config.localization = Object.assign({}, defLocalization, localization);
	el.datetimepicker = new tempusDominus.TempusDominus(el, config);

	el.tdsubscriptions = {};
	events.split(',').forEach(evt => {
		if ('change' === evt) {
			el.tdsubscriptions.change = el.datetimepicker.subscribe(
				tempusDominus.Namespace.events.change, (e) => {
					el.dispatchEvent(new Event('change'));
				});
		}
	});

}

function destroyTempusDominus(elementId) {
	const el = document.getElementById(`${elementId}`);
	if (el && el.datetimepicker) {
		if (el.tdsubscriptions.change) {
			el.tdsubscriptions.change.unsubscribe();
			delete el.tdsubscriptions;
		}
		el.datetimepicker.dispose();
		delete el.datetimepicker;
	}
}
