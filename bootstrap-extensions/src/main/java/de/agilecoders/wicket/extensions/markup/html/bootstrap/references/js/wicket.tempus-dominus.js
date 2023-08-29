function createTempusDominus(elementId, config) {
	const el = document.getElementById(`${elementId}`);
	el.datetimepicker = new tempusDominus.TempusDominus(el, config);
}

function destroyTempusDominus(elementId) {
	const el = document.getElementById(`${elementId}`);
	if (el && el.datetimepicker) {
		el.datetimepicker.dispose();
		delete el.datetimepicker;
	}
}
