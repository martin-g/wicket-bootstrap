/**
 * custom js, some kind of jQuery plugin with 2 not implemented functions: 'showError' and 'hideError'
 *
 * any extension of $.wb_validation should correctly implement 'showError' and 'hideError' functions.
 *
 * 'showError' should add validation message artifacts to DOM
 * 'hideError' should remove from DOM all generated validation message artifacts
 */
(function ($) {
    $.wb_validation = {
        config: null,
        defaultConfig: {
            appendToParent: false,
            errorClass: "has-error"
        },

        updateConfig: function (config) {
            this.config = $.extend(this.defaultConfig, config);
        },

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
                that.addErrorClass(notValid, $element);
                notValid.removeAttr('wb-validation-message');

                notValid.one('click keydown change blur', function () {
                    that.hideError(notValid, $element);
                    that.removeErrorClass(notValid, $element);
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
            // override this method
        },

        /**
         * Hide error message.
         *
         * @param $element form element
         * @param $messageTarget message target
         */
        hideError: function ($element, $messageTarget) {
            // override this method
        },

        /**
         * add error class to element
         *
         * @param $element
         * @param $messageTarget
         */
        addErrorClass: function ($element, $messageTarget) {
            if (this.config.errorClass != null) {
                $messageTarget.parent().addClass(this.config.errorClass);
            }
        },

        /**
         * removes error class from element
         *
         * @param $element
         * @param $messageTarget
         */
        removeErrorClass: function ($element, $messageTarget) {
            if (this.config.errorClass != null) {
                $messageTarget.parent().removeClass(this.config.errorClass);
            }
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
