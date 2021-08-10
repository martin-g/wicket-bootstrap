/*!
 * Bootstrap Confirmation (v4.2.1)
 * @copyright 2013 Nimit Suwannagate <ethaizone@hotmail.com>
 * @copyright 2014-2021 Damien "Mistic" Sorel <contact@git.strangeplanet.fr>
 * @licence Apache License, Version 2.0
 */
// monkey patched version by solomax
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(require('jquery'), require('bootstrap')) :
  typeof define === 'function' && define.amd ? define(['jquery', 'bootstrap'], factory) :
  (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.jQuery));
}(this, (function ($) { 'use strict';

/**
 * --------------------------------------------------------------------------
 * Bootstrap (v5.0.2): dom/manipulator.js
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/main/LICENSE)
 * --------------------------------------------------------------------------
 */
	const DISALLOWED_ATTRIBUTES = new Set(['sanitize', 'allowList', 'sanitizeFn'])
	const Manipulator = (function() {
		function normalizeData(val) {
			if (val === 'true') {
				return true
			}

			if (val === 'false') {
				return false
			}

			if (val === Number(val).toString()) {
				return Number(val)
			}

			if (val === '' || val === 'null') {
				return null
			}

			return val
		}

		function normalizeDataKey(key) {
			return key.replace(/[A-Z]/g, chr => `-${chr.toLowerCase()}`)
		}

		const Manipulator = {
			setDataAttribute(element, key, value) {
				element.setAttribute(`data-bs-${normalizeDataKey(key)}`, value)
			},

			removeDataAttribute(element, key) {
				element.removeAttribute(`data-bs-${normalizeDataKey(key)}`)
			},

			getDataAttributes(element) {
				if (!element) {
					return {}
				}

				const attributes = {}

				Object.keys(element.dataset)
					.filter(key => key.startsWith('bs'))
					.forEach(key => {
						let pureKey = key.replace(/^bs/, '')
						pureKey = pureKey.charAt(0).toLowerCase() + pureKey.slice(1, pureKey.length)
						attributes[pureKey] = normalizeData(element.dataset[key])
					})

				return attributes
			},

			getDataAttribute(element, key) {
				return normalizeData(element.getAttribute(`data-bs-${normalizeDataKey(key)}`))
			},

			offset(element) {
				const rect = element.getBoundingClientRect()

				return {
					top: rect.top + document.body.scrollTop,
					left: rect.left + document.body.scrollLeft
				}
			},

			position(element) {
				return {
					top: element.offsetTop,
					left: element.offsetLeft
				}
			}
		}
		return Manipulator;
	})();
	// Shoutout AngusCroll (https://goo.gl/pxwQGp)
	const toType = obj => {
		if (obj === null || obj === undefined) {
			return `${obj}`
		}

		return {}.toString.call(obj).match(/\s([a-z]+)/i)[1].toLowerCase()
	};
	const typeCheckConfig = (componentName, config, configTypes) => {
		Object.keys(configTypes).forEach(property => {
			const expectedTypes = configTypes[property]
			const value = config[property]
			const valueType = value && isElement(value) ? 'element' : toType(value)

			if (!new RegExp(expectedTypes).test(valueType)) {
				throw new TypeError(
					`${componentName.toUpperCase()}: Option "${property}" provided type "${valueType}" but expected type "${expectedTypes}".`
				)
			}
		})
	};
	const isElement = obj => {
		if (!obj || typeof obj !== 'object') {
			return false
		}

		if (typeof obj.jquery !== 'undefined') {
			obj = obj[0]
		}

		return typeof obj.nodeType !== 'undefined'
	};
const uriAttrs = new Set([
  'background',
  'cite',
  'href',
  'itemtype',
  'longdesc',
  'poster',
  'src',
  'xlink:href'
])
/**
 * A pattern that recognizes a commonly useful subset of URLs that are safe.
 *
 * Shoutout to Angular 7 https://github.com/angular/angular/blob/7.2.4/packages/core/src/sanitization/url_sanitizer.ts
 */
const SAFE_URL_PATTERN = /^(?:(?:https?|mailto|ftp|tel|file):|[^#&/:?]*(?:[#/?]|$))/i

/**
 * A pattern that matches safe data URLs. Only matches image, video and audio types.
 *
 * Shoutout to Angular 7 https://github.com/angular/angular/blob/7.2.4/packages/core/src/sanitization/url_sanitizer.ts
 */
const DATA_URL_PATTERN = /^data:(?:image\/(?:bmp|gif|jpeg|jpg|png|tiff|webp)|video\/(?:mpeg|mp4|ogg|webm)|audio\/(?:mp3|oga|ogg|opus));base64,[\d+/a-z]+=*$/i

const allowedAttribute = (attr, allowedAttributeList) => {
  const attrName = attr.nodeName.toLowerCase()

  if (allowedAttributeList.includes(attrName)) {
    if (uriAttrs.has(attrName)) {
      return Boolean(SAFE_URL_PATTERN.test(attr.nodeValue) || DATA_URL_PATTERN.test(attr.nodeValue))
    }

    return true
  }

  const regExp = allowedAttributeList.filter(attrRegex => attrRegex instanceof RegExp)

  // Check if a regular expression validates the attribute.
  for (let i = 0, len = regExp.length; i < len; i++) {
    if (regExp[i].test(attrName)) {
      return true
    }
  }

  return false
}
function sanitizeHtml(unsafeHtml, allowList, sanitizeFn) {
  if (!unsafeHtml.length) {
    return unsafeHtml
  }

  if (sanitizeFn && typeof sanitizeFn === 'function') {
    return sanitizeFn(unsafeHtml)
  }

  const domParser = new window.DOMParser()
  const createdDocument = domParser.parseFromString(unsafeHtml, 'text/html')
  const allowlistKeys = Object.keys(allowList)
  const elements = [].concat(...createdDocument.body.querySelectorAll('*'))

  for (let i = 0, len = elements.length; i < len; i++) {
    const el = elements[i]
    const elName = el.nodeName.toLowerCase()

    if (!allowlistKeys.includes(elName)) {
      el.remove()

      continue
    }

    const attributeList = [].concat(...el.attributes)
    const allowedAttributes = [].concat(allowList['*'] || [], allowList[elName] || [])

    attributeList.forEach(attr => {
      if (!allowedAttribute(attr, allowedAttributes)) {
        el.removeAttribute(attr.nodeName)
      }
    })
  }

  return createdDocument.body.innerHTML
}

  function _extends() {
    _extends = Object.assign || function (target) {
      for (var i = 1; i < arguments.length; i++) {
        var source = arguments[i];

        for (var key in source) {
          if (Object.prototype.hasOwnProperty.call(source, key)) {
            target[key] = source[key];
          }
        }
      }

      return target;
    };

    return _extends.apply(this, arguments);
  }

  /**
   * ------------------------------------------------------------------------
   * Constants
   * ------------------------------------------------------------------------
   */

  var NAME = 'confirmation';
  var VERSION = '4.2.1';
  var DATA_KEY = "bs." + NAME;
  var EVENT_KEY = "." + DATA_KEY;
  var JQUERY_NO_CONFLICT = $.fn[NAME];
  var BTN_CLASS_BASE = 'h-100 d-flex align-items-center';
  var BTN_CLASS_DEFAULT = 'btn btn-sm';

  var DefaultType = _extends({}, bootstrap.Popover.DefaultType, {
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
  });

  var Default = _extends({}, bootstrap.Popover.Default, {
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
    btnOkClass: BTN_CLASS_DEFAULT + " btn-primary",
    btnOkLabel: 'Yes',
    btnOkIconClass: '',
    btnOkIconContent: '',
    btnCancelClass: BTN_CLASS_DEFAULT + " btn-secondary",
    btnCancelLabel: 'No',
    btnCancelIconClass: '',
    btnCancelIconContent: '',
    buttons: [],
    // @formatter:off
    template: "\n<div class=\"popover confirmation\">\n  <div class=\"arrow\"></div>\n  <h3 class=\"popover-header\"></h3>\n  <div class=\"popover-body\">\n    <p class=\"confirmation-content\"></p>\n    <div class=\"confirmation-buttons text-center\">\n      <div class=\"btn-group\"></div>\n    </div>\n  </div>\n</div>" // @formatter:on

  });

  if (Default.whiteList) {
    Default.whiteList['*'].push('data-apply', 'data-dismiss');
  }

  var ClassName = {
    FADE: 'fade',
    SHOW: 'show'
  };
  var Selector = {
    TITLE: '.popover-header',
    CONTENT: '.confirmation-content',
    BUTTONS: '.confirmation-buttons .btn-group'
  };
  var Keymap = {
    13: 'Enter',
    27: 'Escape',
    39: 'ArrowRight',
    40: 'ArrowDown'
  };
  var Event = {
    HIDE: "hide" + EVENT_KEY,
    HIDDEN: "hidden" + EVENT_KEY,
    SHOW: "show" + EVENT_KEY,
    SHOWN: "shown" + EVENT_KEY,
    INSERTED: "inserted" + EVENT_KEY,
    CLICK: "click" + EVENT_KEY,
    FOCUSIN: "focusin" + EVENT_KEY,
    FOCUSOUT: "focusout" + EVENT_KEY,
    MOUSEENTER: "mouseenter" + EVENT_KEY,
    MOUSELEAVE: "mouseleave" + EVENT_KEY,
    CONFIRMED: "confirmed" + EVENT_KEY,
    CANCELED: "canceled" + EVENT_KEY,
    KEYUP: "keyup" + EVENT_KEY
  };
  /**
   * ------------------------------------------------------------------------
   * Class Definition
   * ------------------------------------------------------------------------
   */
  // keep track of the last openned confirmation for keyboard navigation

  var activeConfirmation;

  class Confirmation extends bootstrap.Popover {
    constructor(element, config) {
      super(element, config);

      if ((this.config.popout || this.config.singleton) && !this.config.rootSelector) {
        throw new Error('The rootSelector option is required to use popout and singleton features since jQuery 3.');
      } // keep trace of selectors
      element.setAttribute('title', element.getAttribute('data-bs-original-title'));
      element.setAttribute('data-bs-original-title', '');


      this._isDelegate = false;

      if (config.selector) {
        // container of buttons
        config._selector = config.rootSelector + " " + config.selector;
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
    isWithContent() {
      return true;
    }

    setContent() {
      var $tip = $(this.getTipElement());

      var content = this._getContent();

      if (typeof content === 'function') {
        content = content.call(this._element);
      }

      this.setElementContent($tip.find(Selector.TITLE).get(0), this.getTitle());
      $tip.find(Selector.CONTENT).toggle(!!content);

      if (content) {
        this.setElementContent($tip.find(Selector.CONTENT).get(0), content);
      }

      if (this.config.buttons.length > 0) {
        this._setButtons($tip, this.config.buttons);
      } else {
        this._setStandardButtons($tip);
      }

      $tip.removeClass(ClassName.FADE + " " + ClassName.SHOW);

      this._setupKeyupEvent();
    }

    dispose() {
      $('body').off(Event.CLICK + "." + this.uid);
      this.eventBody = false;

      this._cleanKeyupEvent();

      super.dispose();
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
    _getConfig(config) {
      const dataAttributes = Manipulator.getDataAttributes(this._element)

      Object.keys(dataAttributes).forEach(dataAttr => {
        if (DISALLOWED_ATTRIBUTES.has(dataAttr)) {
          delete dataAttributes[dataAttr]
        }
      })

      config = {
        ...this.constructor.Default,
        ...dataAttributes,
        ...(typeof config === 'object' && config ? config : {})
      }

      config.container = config.container === false ? document.body : getElement(config.container)

      if (typeof config.delay === 'number') {
        config.delay = {
          show: config.delay,
          hide: config.delay
        }
      }

      if (typeof config.title === 'number') {
        config.title = config.title.toString()
      }

      if (typeof config.content === 'number') {
        config.content = config.content.toString()
      }

      typeCheckConfig(NAME, config, this.constructor.DefaultType)

      if (config.sanitize) {
        config.template = sanitizeHtml(config.template, config.allowList, config.sanitizeFn)
      }

      const dataAttrs = $(this._element).data();
      Object.keys(dataAttrs).forEach(function (dataAttr) {
        if (dataAttr.indexOf('btn') !== 0) {
          delete dataAttrs[dataAttr];
        }
      });
      this.config = _extends({}, config, dataAttrs);
      return this.config;
    }

    /**
     * Copy the value of `copyAttributes` on the config object
     * @private
     */
     _copyAttributes() {
      var _this2 = this;

      this.config._attributes = {};

      if (this.config.copyAttributes) {
        if (typeof this.config.copyAttributes === 'string') {
          this.config.copyAttributes = this.config.copyAttributes.split(' ');
        }
      } else {
        this.config.copyAttributes = [];
      }

      this.config.copyAttributes.forEach(function (attr) {
        _this2.config._attributes[attr] = $(_this2._element).attr(attr);
      });
    }

    /**
     * Custom event listeners for popouts and singletons
     * @private
     */
    _setConfirmationListeners() {
      var self = this;

      if (!this.config.selector) {
        // cancel original event
        $(this._element).on(this.config.trigger, function (e, ack) {
          if (!ack) {
            e.preventDefault();
            e.stopPropagation();
            e.stopImmediatePropagation();
          }
        }); // manage singleton

        $(this._element).on(Event.SHOWN, function () {
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
          if (!ack) {
            e.preventDefault();
            e.stopPropagation();
            e.stopImmediatePropagation();
          }
        });
      }

      if (!this._isDelegate) {
        // manage popout
        this.eventBody = false;
        this.uid = this._element.id || Confirmation.getUID(NAME + "_group");
        $(this._element).on(Event.SHOWN, function () {
          if (self.config.popout && !self.eventBody) {
            self.eventBody = $('body').on(Event.CLICK + "." + self.uid, function (e) {
              if ($(self.config._selector).is(e.target) || $(self.config._selector).has(e.target).length > 0) {
                return;
              } // close all popover already initialized


              $(self.config._selector).filter(function () {
                return $(this).data(DATA_KEY) !== undefined;
              }).confirmation('hide');
              $('body').off(Event.CLICK + "." + self.uid);
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
      var buttons = [{
        "class": this.config.btnOkClass,
        label: this.config.btnOkLabel,
        iconClass: this.config.btnOkIconClass,
        iconContent: this.config.btnOkIconContent,
        attr: this.config._attributes
      }, {
        "class": this.config.btnCancelClass,
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
      var self = this;
      var $group = $tip.find(Selector.BUTTONS).empty();
      buttons.forEach(function (button) {
        var btn = $('<a href="#"></a>').addClass(BTN_CLASS_BASE).addClass(button["class"] || BTN_CLASS_DEFAULT + " btn-secondary").html(button.label || '').attr(button.attr || (button.cancel ? {} : self.config._attributes));

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
            $(self._element).trigger(Event.CANCELED, [button.value]);
          } else {
            self.config.onConfirm.call(self._element, button.value);
            $(self._element).trigger(Event.CONFIRMED, [button.value]);
            $(self._element).trigger(self.config.confirmationEvent, [true]);
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
      $(window).off(Event.KEYUP).on(Event.KEYUP, this._onKeyup.bind(this));
    }

    /**
     * Remove the keyboard event handler
     * @private
     */
    _cleanKeyupEvent() {
      if (activeConfirmation === this) {
        activeConfirmation = undefined;
        $(window).off(Event.KEYUP);
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

      var $tip = $(this.getTipElement());
      var key = event.key || Keymap[event.keyCode || event.which];
      var $group = $tip.find(Selector.BUTTONS);
      var $active = $group.find('.active');
      var $next;

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
      var uid = prefix;

      do {
        // eslint-disable-next-line no-bitwise
        uid += ~~(Math.random() * 1000000); // "~~" acts like a faster Math.floor() here
      } while (document.getElementById(uid));

      return uid;
    }

    static _jQueryInterface(config) {
      return this.each(function () {
        var data = $(this).data(DATA_KEY);

        var _config = typeof config === 'object' ? config : {};

        _config.rootSelector = $(this).selector || _config.rootSelector; // this.selector removed in jQuery > 3

        if (!data && /destroy|hide/.test(config)) {
          return;
        }

        if (!data) {
          data = new Confirmation(this, _config);
          $(this).data(DATA_KEY, data);
        }

        if (typeof config === 'string') {
          if (typeof data[config] === 'undefined') {
            throw new TypeError("No method named \"" + config + "\"");
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
