/**
 * 'showError' and 'hideError' functions implementation of $.wb_validation (@see jquery.wb.validation.js)
 *
 * 'showError'-function shows tooltip with validation message.
 * 'hideError'-function destroys tooltip with validation message.
 */
(function ($) {
    /**
     * Show error message.
     *
     * @param $element form element
     * @param $messageTarget element for message
     * @param message error message
     */
    $.wb_validation.showError = function ($element, $messageTarget, message) {
        $messageTarget.tooltip($.extend(this.config, {
            title: message,
            trigger: 'manual'
        })).tooltip('show');
    };

    /**
     * @param $element form element
     * @param $messageTarget element for message
     */
    $.wb_validation.hideError = function ($element, $messageTarget) {
        $messageTarget.tooltip('destroy');
    };
})(jQuery);
