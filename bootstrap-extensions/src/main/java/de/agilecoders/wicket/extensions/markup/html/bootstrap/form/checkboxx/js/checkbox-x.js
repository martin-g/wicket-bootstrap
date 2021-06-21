/*!
 * @copyright &copy; Kartik Visweswaran, Krajee.com, 2014 - 2021
 * @version 1.5.6
 *
 * An extended checkbox plugin for bootstrap with three states and additional styles.
 *
 * For more JQuery/Bootstrap plugins and demos visit http://plugins.krajee.com
 * For more Yii related demos visit http://demos.krajee.com
 */
(function ($) {
    "use strict";
    var CheckboxX = function (element, options) {
        var self = this;
        self.$element = $(element);
        self.disabled = self.$element.attr('disabled') || self.$element.attr('readonly');
        self.initialValue = self.$element.val();
        self.init(options);
    };

    CheckboxX.prototype = {
        constructor: CheckboxX,
        init: function (options) {
            var self = this, $el = self.$element, isCbx = $el.is(':checkbox'), isSelect = $el.is('select'),
                isText = !isCbx && !isSelect, enclosedSelect = isSelect && !options.enclosedLabel,
                css = options.inline ? 'cbx-container' : 'cbx-container cbx-block';
            if (options.theme) {
                css += ' cbx-' + options.theme;
            }
            self.options = options;
            self.clearEvents();
            $el.removeClass('cbx-loading');
            if (!self.initCheckbox()) {
                return;
            }
            if (self.$container === undefined) {
                self.$container = $(document.createElement("div")).addClass(css).html(self.render());
                self.$container.insertBefore($el).append($el);
                $el.hide();
            }
            else {
                self.$container.before($el).addClass(css).html(self.render()).append($el);
            }
            self.$cbx = self.$container.find('.cbx');
            $el.closest('form').on('reset.checkbox', function () {
                self.reset();
            });
            self.$cbx.off('click.checkbox keyup.checkbox').on('click.checkbox', function () {
                if (isCbx && !options.enclosedLabel && !options.useNative && !self.disabled) {
                    $el.trigger('change');
                    return;
                }
                if (isText && !options.enclosedLabel || enclosedSelect) {
                    self.change();
                }
            }).on('keyup.checkbox', function (e) {
                if (e.which === 32) {
                    self.change();
                    e.preventDefault();
                }
            });
            if (isText || isSelect) {
                $el.on('click.checkbox', function () {
                    self.change();
                });
            }
        },
        initCheckbox: function () {
            var self = this, $el = self.$element, val, options = self.options;
            if (!$el.is(':checkbox')) {
                return true;
            }
            if (!$el.prop('checked')) {
                val = $el.prop('indeterminate') ? options.valueNull : options.valueUnchecked;
                $el.val(val);
            }
            $el.on('change.checkbox', function () {
                self.change();
            });
            if (options.useNative) {
                $el.removeClass('cbx-loading');
                return false;
            }
            return true;
        },
        change: function () {
            var self = this, $el = self.$element, newVal;
            if (self.disabled) {
                return;
            }
            newVal = self.getValue();
            $el.val(newVal);
            self.validateCheckbox(newVal);
            if (!self.options.useNative) {
                self.$cbx.html(self.getIndicator());
            }
        },
        getValue: function () {
            var self = this, val = self.$element.val(), options = self.options;
            switch (val) {
                case options.valueUnchecked:
                    return self.options.threeState ? options.valueNull : options.valueChecked;
                case options.valueChecked:
                    return options.valueUnchecked;
                default:
                    return options.valueChecked;
            }
        },
        setCheckboxProp: function (newVal) {
            var self = this, $el = self.$element, options = self.options;
            $el.prop('indeterminate', false).prop('checked', false);
            switch (newVal) {
                case options.valueChecked:
                    $el.prop('checked', true);
                    break;
                case options.valueUnchecked:
                    break;
                default:
                    if (options.threeState) {
                        $el.prop('indeterminate', true);
                    }
                    break;
            }
        },
        validateCheckbox: function (newVal) {
            var self = this, $el = self.$element, isCbx = $el.is(':checkbox');
            if (!isCbx) {
                $el.trigger('change');
                return;
            }
            self.setCheckboxProp(newVal);
        },
        clearEvents: function () {
            var self = this, $el = self.$element, $cbx = self.$container ? self.$container.find('.cbx') : null;
            $el.off('.checkbox');
            if ($cbx) {
                $cbx.off('.checkbox');
            }
        },
        destroy: function () {
            var self = this, $el = self.$element;
            self.clearEvents();
            self.$container.before($el).remove();
            $el.removeData().show();
        },
        reset: function () {
            var self = this, $el = self.$element;
            $el.val(self.initialValue);
            self.refresh();
            $el.trigger('checkbox:resetinput');
        },
        refresh: function (options) {
            var self = this;
            if (options) {
                self.init($.extend(self.options, options));
            } else {
                self.disabled = self.$element.attr('disabled') || self.$element.attr('readonly');
                self.init(self.options);
            }
            self.initialValue = self.$element.val();
        },
        getIndicator: function (hasThreeState) {
            var self = this, options = self.options, icon = options.iconUnchecked, $el = self.$element, val = $el.val(),
                isInd = $el.is(':checkbox') ? $el.prop('indeterminate') :
                    (val !== options.valueUnchecked && (self.options.threeState || hasThreeState));
            return '<span class="cbx-icon">' +
                (val === options.valueChecked ? options.iconChecked : (isInd ? options.iconNull : icon)) +
                '</span>';
        },
        render: function () {
            var self = this, options = self.options, icon = self.getIndicator(options.allowThreeValOnInit),
                tab = self.disabled || !options.tabindex ? '' : ' tabindex="' + options.tabindex + '"',
                size = options.size, css = 'cbx cbx-' + size + (self.disabled ? ' cbx-disabled' : ' cbx-active');
            return '<div class="' + css + '"' + tab + '>' + icon + '</div>';
        }
    };

    $.fn.checkboxX = function (option) {
        var args = Array.apply(null, arguments), retvals = [];
        args.shift();
        this.each(function () {
            var $this = $(this), data = $this.data('checkboxX'), options = typeof option === 'object' && option;
            if (!data) {
                data = new CheckboxX(this, $.extend(true, {}, $.fn.checkboxX.defaults, options, $this.data()));
                $this.data('checkboxX', data);
            }
            if (typeof option === 'string') {
                retvals.push(data[option].apply(data, args));
            }
        });
        switch (retvals.length) {
            case 0:
                return this;
            case 1:
                return retvals[0];
            default:
                return retvals;
        }
    };

    $.fn.checkboxX.defaults = {
        theme: '',
        threeState: true,
        inline: true,
        iconChecked: '<i class="glyphicon glyphicon-ok"></i>',
        iconUnchecked: ' ',
        iconNull: '<div class="cbx-icon-null"></div>',
        valueChecked: '1',
        valueUnchecked: '0',
        valueNull: '',
        size: 'md',
        enclosedLabel: false,
        useNative: false,
        allowThreeValOnInit: false,
        tabindex: "1000"
    };

    $.fn.checkboxX.Constructor = CheckboxX;

    $('input[data-toggle="checkbox-x"]').addClass('cbx-loading');

    $(document).ready(function () {
        $('input[data-toggle="checkbox-x"]').checkboxX();
    });
})(window.jQuery);
