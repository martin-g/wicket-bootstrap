/*!
 * @copyright &copy; Kartik Visweswaran, Krajee.com, 2014
 * @version 1.1.0
 *
 * An extended checkbox plugin for bootstrap with three states and additional styles.
 *
 * For more JQuery/Bootstrap plugins and demos visit http://plugins.krajee.com
 * For more Yii related demos visit http://demos.krajee.com
 */
!function ($) {

    var CheckboxX = function (element, options) {
        var self = this;
        self.$element = $(element);
        self.disabled = self.$element.attr('disabled') || self.$element.attr('readonly');
        self.initialValue = self.$element.val();
        self.init(options);
    }

    CheckboxX.prototype = {
        constructor: CheckboxX,
        init: function (options) {
            var self = this,
                css = options.inline ? 'cbx-container' : 'cbx-container cbx-block';
            self.options = options;
            if (typeof self.$container == 'undefined') {
                self.$container = $(document.createElement("div")).addClass(css).html(self.render());
                self.$element.before(self.$container);
                self.$container.append(self.$element);
                self.$element.hide();
            }
            else {
                self.$container.before(self.$element);
                self.$container.addClass(css).html(self.render());
                self.$container.append(self.$element);
            }
            self.$element.removeClass('cbx-loading');
            self.$cbx = self.$container.find('.cbx');
            self.$element.closest('form').on('reset', function (e) {
                self.reset();
            });
            self.$cbx.on('click', function(e) {
                self.change()
            });
            self.$cbx.on('keyup', function(e) {
                e.which == 32 && self.change();
            });
            self.$element.on('click', function(e) {
                self.change(options.labelClickEvent)
            });
        },
        change: function () {
            var self = this, flag = (arguments.length == 0) ? true : arguments[0];
            if (self.disabled) {
                return;
            }
            var options = self.options, val = parseInt(self.$element.val()), newVal, threeState = options.threeState;
            if (val === 1) {
                newVal = threeState ? null : 0;
            } else {
                newVal = (val === 0) ? 1 : (threeState ? 0 : 1);
            }
            self.$element.val(newVal);
            if (flag) {
                self.$element.trigger('change');
            }
            self.$cbx.html(self.getIndicator());
        },
        reset: function () {
            var self = this;
            self.$element.val(self.initialValue);
            self.refresh();
            self.$element.trigger('reset');
        },
        refresh: function (options) {
            var self = this;
            if (arguments.length) {
                self.init($.extend(self.options, options));
            }
            else {
                self.disabled = self.$element.attr('disabled') || self.$element.attr('readonly');
                self.init(self.options);
            }
            self.initialValue = self.$element.val();
        },
        getIndicator: function () {
            var self = this,
                options = self.options,
                icon = options.iconUnchecked,
                val = parseInt(self.$element.val());
            if (val === 1) {
                icon = options.iconChecked;
            }
            else if (val !== 0 && self.options.threeState == true) {
                icon = options.iconNull;
            }
            return icon;
        },
        render: function () {
            var self = this,
                icon = self.getIndicator(),
                size = self.options.size,
                tab = self.disabled ? '' : ' tabindex="1000"',
                css = 'cbx cbx-' + size + (self.disabled ? ' cbx-disabled' : ' cbx-active');
            return '<div class="' + css + '"' + tab + '>' + icon + '</div>';
        }
    };

    $.fn.checkboxX = function (option) {
        var args = Array.apply(null, arguments);
        args.shift();
        return this.each(function () {
            var $this = $(this),
                data = $this.data('checkboxX'),
                options = typeof option === 'object' && option;

            if (!data) {
                $this.data('checkboxX', (data = new CheckboxX(this, $.extend({}, $.fn.checkboxX.defaults, options, $(this).data()))));
            }

            if (typeof option === 'string') {
                data[option].apply(data, args);
            }
        });
    };

    $.fn.checkboxX.defaults = {
        threeState: true,
        inline: true,
        iconChecked: '<i class="glyphicon glyphicon-ok"></i>',
        iconUnchecked: ' ',
        iconNull: '<i class="glyphicon glyphicon-stop"></i>',
        size: 'md',
        labelClickEvent: true
    };

    $('input[data-toggle="checkbox-x"]').addClass('cbx-loading');

    $(document).ready(function () {
        $('input[data-toggle="checkbox-x"]').checkboxX();
    });
}(window.jQuery);