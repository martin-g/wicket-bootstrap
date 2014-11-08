(function ($) {
    $.wb_validation = {
        config: {appendToParent: false},

        validation: function ($container) {
            $container.find("input, select, textarea").filter(':eq(0)').focus();
            this.showErrors($container);
        },

        showErrors: function ($container) {
            var that = this;
            $container.find('[wb-validation-message]').each(function (i) {
                var notValid = $(this);
                if (0 == i) {
                    notValid.focus();
                }
                var $element = notValid;
                if (that.config.appendToParent) {
                    $element = notValid.parent();
                }
                that.showError(notValid, $element, notValid.attr('wb-validation-message'));
                notValid.removeAttr('wb-validation-message');

                notValid.one('click keydown change blur', function () {
                    that.hideError(notValid, $element);
                });
            });
        },

        /**
         * Show error message.
         *
         * @param $element form element
         * @param $messageTarget element for message
         * @param message error message
         */
        showError: function ($element, $messageTarget, message) {
            // need override this method
        },

        /**
         * Hide error message.
         *
         * @param $element form element
         * @param $messageTarget message target
         * @param $messageTarget message target
         */
        hideError: function ($element, $messageTarget) {
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
