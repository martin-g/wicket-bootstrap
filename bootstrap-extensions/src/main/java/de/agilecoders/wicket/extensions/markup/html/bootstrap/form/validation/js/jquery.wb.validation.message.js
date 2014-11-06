(function ($) {
	/**
	 * @Override
	 * @param $element form element
	 * @param message error message
	 */
	$.wb_validation.showError = function ($element, message) {
		$element.css('border-color', '#8A370C');
		$element.after('<p class="validation validation-message">' + message + '</p>');
	};

	/**
	 * @Override
	 * @param $element form element
	 */
	$.wb_validation.hideError = function ($element) {
		$element.css('border-color', '');
		$element.next().remove();
	};
})(jQuery);
