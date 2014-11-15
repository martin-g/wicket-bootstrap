(function ($) {
    /**
     * Show error message.
     *
     * @param $element form element
     * @param $messageTarget element for message
     * @param message error message
     */
    $.wb_validation.showError = function ($element, $messageTarget, message) {
        $messageTarget.after('<p class="validation validation-message">' + message + '</p>');
    };

    /**
     * @param $element form element
     * @param $messageTarget message target
     */
    $.wb_validation.hideError = function ($element, $messageTarget) {
        $messageTarget.next().remove();
    };
})(jQuery);
