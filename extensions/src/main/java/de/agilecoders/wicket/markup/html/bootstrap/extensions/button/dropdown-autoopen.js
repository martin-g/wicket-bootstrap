/**
 * This code comes from http://cameronspear.com/blog/twitter-bootstrap-dropdown-on-hover-plugin/
 */
;
!(function ($) {

    "use strict"; // jshint ;_;

    // outside the scope of the jQuery plugin to
    // keep track of all dropdowns
    var $allDropdowns = $();

    // if instantlyCloseOthers is true, then it will instantly
    // shut other nav items when a new one is hovered over
    $.fn.dropdownHover = function (options) {

        // the element we really care about
        // is the dropdown-toggle's parent
        $allDropdowns = $allDropdowns.add(this.parent());

        return this.each(function () {
            var $this = $(this).parent(),
            defaults = {
                delay: 500,
                instantlyCloseOthers: true
            },
            data = {
                delay: $(this).data('delay'),
                instantlyCloseOthers: $(this).data('close-others')
            },
            options = $.extend(true, {}, defaults, options, data),
            timeout;

            $this.hover(function () {
                if (options.instantlyCloseOthers === true) {
                    $allDropdowns.removeClass('open');
                }

                window.clearTimeout(timeout);
                $(this).addClass('open');
            }, function () {
                timeout = window.setTimeout(function () {
                    $this.removeClass('open');
                }, options.delay);
            });
        });
    };

}(window.jQuery));