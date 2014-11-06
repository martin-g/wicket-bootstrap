(function ($) {
	$.wb_validation = {
		config: {},

		validation: function ($container) {
			$container.find("input, select, textarea").filter(':eq(0)').focus();
			this.showErrors($container);
		},

		showErrors: function ($container) {
			var that = this;
			$container.find('[wb-validation-message]').each(function (i) {
				if (0 == i) {
					$(this).focus();
				}
				that.showError($(this), $(this).attr('wb-validation-message'));
				$(this).removeAttr('wb-validation-message');

				$(this).one('click keydown change blur', function () {
					that.hideError($(this));
				});
			});
		},

		/**
		 * Show error message.
		 *
		 * @param $element form element
		 * @param message error message
		 */
		showError: function ($element, message) {
			// need override this method
		},

		/**
		 * Hide error message.
		 *
		 * @param $element form element
		 */
		hideError: function ($element) {
			// need override this method
		}
	};

	$(function () {
		$.fn.wb_validation = function (type) {
			$(this).each(function () {
				'validate' == type ? $.wb_validation.validation($(this)) : $.wb_validation.showErrors($(this));
			});
		};
	});
})(jQuery);
