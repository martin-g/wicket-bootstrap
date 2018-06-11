/**
 * 'showError' and 'hideError' functions implementation of $.wb_validation (@see jquery.wb.validation.js)
 *
 * 'showError'-function appends p-tag with validation message.
 * 'hideError'-function removes appended p-tag with validation message.
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
        $messageTarget.after('<p class="validation validation-message invalid-feedback">' + message + '</p>');
    };

    /**
     * @param $element form element
     * @param $messageTarget message target
     */
    $.wb_validation.hideError = function ($element, $messageTarget) {
        $messageTarget.next().remove();
    };
})(jQuery);
