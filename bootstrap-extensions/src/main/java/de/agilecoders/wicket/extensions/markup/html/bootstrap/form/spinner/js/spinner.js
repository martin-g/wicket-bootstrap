(function($) {
	jQuery.fn.extend({

		spinner : function(options) {

			var defaults = {
				minimum : -1000000,
				maximum : 1000000,
				step : 1
			};
			var options = $.extend(defaults, options);
			$('.spinner .btn:first-of-type').on(
					'click',
					function() {
						if ($('.spinner input').val() < options.maximum) {
							$('.spinner input')
									.val(
											parseInt($('.spinner input').val(),
													10) + options.step);
						}
					});
			$('.spinner .btn:last-of-type').on(
					'click',
					function() {
						if ($('.spinner input').val() > options.minimum) {
							$('.spinner input')
									.val(
											parseInt($('.spinner input').val(),
													10) - options.step);
						}
					});
		}

	});
})(jQuery);