(function ($) {
	/**
	 * @Override
	 * @param $element form element
	 * @param message error message
	 */
	$.wb_validation.showError = function ($element, message) {
		$element.tooltip($.extend(this.config, {
			title: message,
			trigger: 'manual'
		})).tooltip('show');
	};

	/**
	 * @Override
	 * @param $element form element
	 */
	$.wb_validation.hideError = function ($element) {
		$element.tooltip('destroy');
	};
})(jQuery);
