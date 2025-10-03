/*!
 * Bootstrap Confirmation (v5.0.1)
 * @copyright 2013 Nimit Suwannagate <ethaizone@hotmail.com>
 * @copyright 2014-2021 Damien "Mistic" Sorel <contact@git.strangeplanet.fr>
 * @licence Apache License, Version 2.0
 */
// monkey patched version by solomax
(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? factory(require('jquery'), require('bootstrap')) :
        typeof define === 'function' && define.amd ? define(['jquery', 'bootstrap'], factory) :
            (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.jQuery));
}(this, (function ($) {
    'use strict';
    /**
     * ------------------------------------------------------------------------
     * Constants
     * ------------------------------------------------------------------------
     */

    const NAME = 'confirmation';
    const VERSION = '4.2.1';
    const DATA_KEY = `bs.${NAME}`;
    const EVENT_KEY = `.${DATA_KEY}`;
    const JQUERY_NO_CONFLICT = $.fn[NAME];
    const BTN_CLASS_BASE = 'h-100 d-flex align-items-center';
    const BTN_CLASS_DEFAULT = 'btn btn-sm';

    const EVENT_SHOWN = `shown${EVENT_KEY}`;
    const EVENT_CLICK = `click${EVENT_KEY}`;
    const EVENT_CONFIRMED = `confirmed${EVENT_KEY}`;
    const EVENT_CANCELED = `canceled${EVENT_KEY}`;
    const EVENT_KEYUP = `keyup${EVENT_KEY}`;

    const DefaultType = {
        ...bootstrap.Popover.DefaultType,
        singleton: 'boolean',
        popout: 'boolean',
        copyAttributes: '(string|array)',
        onConfirm: 'function',
        onCancel: 'function',
        btnOkClass: 'string',
        btnOkLabel: 'string',
        btnOkIconClass: 'string',
        btnOkIconContent: 'string',
        btnCancelClass: 'string',
        btnCancelLabel: 'string',
        btnCancelIconClass: 'string',
        btnCancelIconContent: 'string',
        buttons: 'array'
    };

    const Default = {
        ...bootstrap.Popover.Default,
        _attributes: {},
        _selector: null,
        placement: 'top',
        title: 'Are you sure?',
        trigger: 'click',
        confirmationEvent: undefined,
        content: '',
        singleton: false,
        popout: false,
        copyAttributes: 'href target',
        onConfirm: $.noop,
        onCancel: $.noop,
        btnOkClass: `${BTN_CLASS_DEFAULT} btn-primary`,
        btnOkLabel: 'Yes',
        btnOkIconClass: '',
        btnOkIconContent: '',
        btnCancelClass: `${BTN_CLASS_DEFAULT} btn-secondary`,
        btnCancelLabel: 'No',
        btnCancelIconClass: '',
        btnCancelIconContent: '',
        buttons: [],
        // @formatter:off
        template: `
      <div class="popover confirmation" role="tooltip">
        <div class="popover-arrow confirmation-arrow"></div>
        <h3 class="popover-header"></h3>
        <div class="popover-body"></div>
        <div class="confirmation-buttons text-center">
          <div class="btn-group"></div>
        </div>
      </div>` // @formatter:on

    };

    if (Default.whiteList) {
        Default.whiteList['*'].push('data-apply', 'data-dismiss');
    }

    const Selector = {
        BUTTONS: '.confirmation-buttons .btn-group'
    };
    const Keymap = {
        13: 'Enter',
        27: 'Escape',
        39: 'ArrowRight',
        40: 'ArrowDown'
    };
    // taken from bootsrtap 5.3 (converted to class with static-only methods)
    class EventHandler {
        static hydrateObj(obj, meta = {}) {
            for (const [key, value] of Object.entries(meta)) {
                try {
                    obj[key] = value
                } catch {
                    Object.defineProperty(obj, key, {
                        configurable: true,
                        get() {
                            return value
                        }
                    })
                }
            }
            return obj
        }

        // monkey-patched to work with Apache Wicket
        static trigger(element, event, args) {
            if (typeof event !== 'string' || !element) {
                return null
            }

            const jQueryEvent = $.Event(event, args)
            const bubbles = !jQueryEvent.isPropagationStopped();
            const defaultPrevented = jQueryEvent.isDefaultPrevented()
            const evt = EventHandler.hydrateObj(new Event(event, { bubbles, cancelable: true }), args);
            if (defaultPrevented) {
                evt.preventDefault()
            }
            if (!jQueryEvent.isImmediatePropagationStopped()) {
                element.dispatchEvent(evt);
                $(element).trigger(event, args);
            }
            if (evt.defaultPrevented) {
                jQueryEvent.preventDefault()
            }
            return evt
        }
    }
    /**
     * ------------------------------------------------------------------------
     * Class Definition
     * ------------------------------------------------------------------------
     */
    // keep track of the last openned confirmation for keyboard navigation

    let activeConfirmation;

    class Confirmation extends bootstrap.Popover {
        constructor(element, config) {
            super(element, config);

            if ((this.config.popout || this.config.singleton) && !this.config.rootSelector) {
                throw new Error('The rootSelector option is required to use popout and singleton features since jQuery 3.');
            } // keep trace of selectors
            const origTitle = element.getAttribute('data-bs-original-title');
            if (origTitle) {
                element.setAttribute('title', origTitle);
            }
            element.setAttribute('data-bs-original-title', '');


            this._isDelegate = false;

            if (config.selector) {
                // container of buttons
                config._selector = config.rootSelector + ' ' + config.selector;
                this.config._selector = config._selector;
            } else if (config._selector) {
                // children of container
                this.config._selector = config._selector;
                this._isDelegate = true;
            } else {
                // standalone
                this.config._selector = config.rootSelector;
            }

            if (this.config.confirmationEvent === undefined) {
                this.config.confirmationEvent = this.config.trigger;
            }

            if (!this.config.selector) {
                this._copyAttributes();
            }

            this._setConfirmationListeners();
        }

        // Overrides
        _isWithContent() {
            return true;
        }

        _getTipElement() {
            const createNew = !this.tip;
            super._getTipElement();
            if (createNew) {
                this.tip.classList.add('bs-popover-auto'); // required to display arrow

                const $tip = $(this.tip);
                if (this.config.buttons.length > 0) {
                    this._setButtons($tip, this.config.buttons);
                } else {
                    this._setStandardButtons($tip);
                }
                const hasBody = $tip.find('.popover-body').length > 0;
                $tip.find('.confirmation-buttons').addClass(hasBody ? 'mb-2 ms-2 me-2' : 'm-2');
                this._setupKeyupEvent();
            }
            return this.tip;
        }

        dispose() {
            $('body').off(`${EVENT_CLICK}.${this.uid}`);
            this.eventBody = false;

            this._cleanKeyupEvent();

            if (this._element) {
                const $e = $(this._element);
                super.dispose();
                $e.data(DATA_KEY, null);
            }
        }

        hide(callback) {
            this._cleanKeyupEvent();

            super.hide(callback);
        }

        // Private
        /**
         * Build configuration object
         * Bootstrap standard is to give priority to JS config over data attributes,
         * but for Confirmation we prefer data attributes
         * @param config
         * @return {*}
         * @private
         */
        _getConfig(_config) {
            const config = super._getConfig(_config);

            const dataAttrs = $(this._element).data();
            Object.keys(dataAttrs).forEach(function (dataAttr) {
                if (dataAttr.indexOf('btn') !== 0) {
                    delete dataAttrs[dataAttr];
                }
            });
            this.config = { ...config, ...dataAttrs };
            return this.config;
        }

        /**
         * Copy the value of `copyAttributes` on the config object
         * @private
         */
        _copyAttributes() {
            const self = this;

            this.config._attributes = {};

            if (this.config.copyAttributes) {
                if (typeof this.config.copyAttributes === 'string') {
                    this.config.copyAttributes = this.config.copyAttributes.split(' ');
                }
            } else {
                this.config.copyAttributes = [];
            }

            this.config.copyAttributes.forEach(function (attr) {
                self.config._attributes[attr] = $(self._element).attr(attr);
            });
        }

        /**
         * Custom event listeners for popouts and singletons
         * @private
         */
        _setConfirmationListeners() {
            const self = this;
            // preserve original jQuery listeners
            const allListeners = $._data(this._element, 'events') || {};
            const listeners = allListeners[this.config.trigger] ? [...allListeners[this.config.trigger]] : []

            if (!this.config.selector) {
                // cancel original event
                $(this._element).off().on(this.config.trigger, function (e, ack) {
                    if (ack === true) {
                        listeners.forEach(l => l.handler(e, ack));
                    } else {
                        e.preventDefault();
                        e.stopPropagation();
                        e.stopImmediatePropagation();
                    }
                }); // manage singleton

                $(this._element).on(EVENT_SHOWN, function () {
                    if (self.config.singleton) {
                        // close all other popover already initialized
                        $(self.config._selector).not($(this)).filter(function () {
                            return $(this).data(DATA_KEY) !== undefined;
                        }).confirmation('hide');
                    }
                });
            } else {
                // cancel original event
                $(this._element).on(this.config.trigger, this.config.selector, function (e, ack) {
                    if (ack === true) {
                        listeners.forEach(l => l.handler(e, ack));
                    } else {
                        e.preventDefault();
                        e.stopPropagation();
                        e.stopImmediatePropagation();
                    }
                });
            }

            if (!this._isDelegate) {
                // manage popout
                this.eventBody = false;
                this.uid = this._element.id || Confirmation.getUID(`${NAME}_group`);
                $(this._element).on(EVENT_SHOWN, function () {
                    if (self.config.popout && !self.eventBody) {
                        self.eventBody = $('body').on(`${EVENT_CLICK}.${self.uid}`, function (e) {
                            if ($(self.config._selector).is(e.target) || $(self.config._selector).has(e.target).length > 0) {
                                return;
                            } // close all popover already initialized


                            $(self.config._selector).filter(function () {
                                return $(this).data(DATA_KEY) !== undefined;
                            }).confirmation('hide');
                            $('body').off(`${EVENT_CLICK}.${self.uid}`);
                            self.eventBody = false;
                        });
                    }
                });
            }
        }

        /**
         * Init the standard ok/cancel buttons
         * @param $tip
         * @private
         */
        _setStandardButtons($tip) {
            const buttons = [{
                'class': this.config.btnOkClass,
                label: this.config.btnOkLabel,
                iconClass: this.config.btnOkIconClass,
                iconContent: this.config.btnOkIconContent,
                attr: this.config._attributes
            }, {
                'class': this.config.btnCancelClass,
                label: this.config.btnCancelLabel,
                iconClass: this.config.btnCancelIconClass,
                iconContent: this.config.btnCancelIconContent,
                cancel: true
            }];

            this._setButtons($tip, buttons);
        }

        /**
         * Init the buttons
         * @param $tip
         * @param buttons
         * @private
         */
        _setButtons($tip, buttons) {
            const self = this
                , $group = $tip.find(Selector.BUTTONS).empty();
            buttons.forEach(function (button) {
                const btn = $('<a href="#"></a>')
                    .addClass(BTN_CLASS_BASE)
                    .addClass(button['class'] || `${BTN_CLASS_DEFAULT} btn-secondary`)
                    .html(button.label || '')
                    .data('buttons-set', true)
                    .attr(button.attr || (button.cancel ? {} : self.config._attributes));

                if (button.iconClass || button.iconContent) {
                    btn.prepend($('<i></i>').addClass(button.iconClass || '').text(button.iconContent || ''));
                }

                btn.one('click', function (e) {
                    if ($(this).attr('href') === '#') {
                        e.preventDefault();
                    }

                    if (button.onClick) {
                        button.onClick.call($(self._element));
                    }

                    if (button.cancel) {
                        self.config.onCancel.call(self._element, button.value);
                        EventHandler.trigger(self._element, EVENT_CANCELED, [button.value]);
                    } else {
                        self.config.onConfirm.call(self._element, button.value);
                        EventHandler.trigger(self._element, EVENT_CONFIRMED, [button.value]);
                        EventHandler.trigger(self._element, self.config.confirmationEvent, [true]);
                    }

                    self.hide();
                });
                $group.append(btn);
            });
        }

        /**
         * Install the keyboatd event handler
         * @private
         */
        _setupKeyupEvent() {
            activeConfirmation = this;
            $(window).off(EVENT_KEYUP).on(EVENT_KEYUP, this._onKeyup.bind(this));
        }

        /**
         * Remove the keyboard event handler
         * @private
         */
        _cleanKeyupEvent() {
            if (activeConfirmation === this) {
                activeConfirmation = undefined;
                $(window).off(EVENT_KEYUP);
            }
        }

        /**
         * Event handler for keyboard navigation
         * @param event
         * @private
         */
        _onKeyup(event) {
            if (!this.tip) {
                this._cleanKeyupEvent();

                return;
            }

            const $tip = $(this._getTipElement());
            const key = event.key || Keymap[event.keyCode || event.which];
            const $group = $tip.find(Selector.BUTTONS);
            const $active = $group.find('.active');
            let $next;

            switch (key) {
                case 'Escape':
                    this.hide();
                    break;

                case 'ArrowRight':
                    if ($active.length && $active.next().length) {
                        $next = $active.next();
                    } else {
                        $next = $group.children().first();
                    }

                    $active.removeClass('active');
                    $next.addClass('active').focus();
                    break;

                case 'ArrowLeft':
                    if ($active.length && $active.prev().length) {
                        $next = $active.prev();
                    } else {
                        $next = $group.children().last();
                    }

                    $active.removeClass('active');
                    $next.addClass('active').focus();
                    break;
            }
        }

        // Static
        /**
         * Generates an uui, copied from Bootrap's utils
         * @param {string} prefix
         * @returns {string}
         */
        static getUID(prefix) {
            let uid = prefix;

            do {
                // eslint-disable-next-line no-bitwise
                uid += ~~(Math.random() * 1000000); // `~~` acts like a faster Math.floor() here
            } while (document.getElementById(uid));

            return uid;
        }

        static _jQueryInterface(config) {
            return this.each(function () {
                let data = $(this).data(DATA_KEY);

                const _config = typeof config === 'object' ? config : {};

                _config.rootSelector = $(this).selector || _config.rootSelector; // this.selector removed in jQuery > 3

                if (!data && /dispose|hide/.test(config)) {
                    return;
                }

                if (!data) {
                    data = new Confirmation(this, _config);
                    $(this).data(DATA_KEY, data);
                }

                if (typeof config === 'string') {
                    if (typeof data[config] === 'undefined') {
                        throw new TypeError(`No method named "${config}"`);
                    }

                    data[config]();
                }
            });
        }

        static get VERSION() {
            return VERSION;
        }

        static get Default() {
            return Default;
        }

        static get NAME() {
            return NAME;
        }

        static get DATA_KEY() {
            return DATA_KEY;
        }

        static get Event() {
            return Event;
        }

        static get EVENT_KEY() {
            return EVENT_KEY;
        }

        static get DefaultType() {
            return DefaultType;
        }
    }

    /**
     * ------------------------------------------------------------------------
     * jQuery
     * ------------------------------------------------------------------------
     */


    $.fn[NAME] = Confirmation._jQueryInterface;
    $.fn[NAME].Constructor = Confirmation;

    $.fn[NAME].noConflict = function () {
        $.fn[NAME] = JQUERY_NO_CONFLICT;
        return Confirmation._jQueryInterface;
    };

})));
