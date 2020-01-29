(function(root, factory) {
    if (typeof exports === "object") {
        module.exports = factory();
    } else if (typeof define === "function" && define.amd) {
        define([ "./spin" ], factory);
    } else {
        root.Ladda = factory(root.Spinner);
    }
})(this, function(Spinner) {
    "use strict";
    var ALL_INSTANCES = [];
    function create(button) {
        if (typeof button === "undefined") {
            console.warn("Ladda button target must be defined.");
            return;
        }
        if (!button.querySelector(".ladda-label")) {
            button.innerHTML = '<span class="ladda-label">' + button.innerHTML + "</span>";
        }
        var spinner = createSpinner(button);
        var timer;
        var instance = {
            start: function() {
                button.setAttribute("disabled", "");
                button.setAttribute("data-loading", "");
                clearTimeout(timer);
                spinner.classList.remove('d-none');
                return this;
            },
            startAfter: function(delay) {
                clearTimeout(timer);
                timer = setTimeout(function() {
                    instance.start();
                }, delay);
                return this;
            },
            stop: function() {
                button.removeAttribute("disabled");
                button.removeAttribute("data-loading");
                clearTimeout(timer);
                timer = setTimeout(function() {
                    spinner.classList.add('d-none');
                }, 1e3);
                return this;
            },
            toggle: function() {
                if (this.isLoading()) {
                    this.stop();
                } else {
                    this.start();
                }
                return this;
            },
            enable: function() {
                this.stop();
                return this;
            },
            disable: function() {
                this.stop();
                button.setAttribute("disabled", "");
                return this;
            },
            isLoading: function() {
                return button.hasAttribute("data-loading");
            },
            getTarget: function() {
                return button;
            }
        };
        ALL_INSTANCES.push(instance);
        return instance;
    }
    function bind(target, options) {
        options = options || {};
        var targets = [];
        if (typeof target === "string") {
            targets = toArray(document.querySelectorAll(target));
        } else if (typeof target === "object" && typeof target.nodeName === "string") {
            targets = [ target ];
        }
        for (var i = 0, len = targets.length; i < len; i++) {
            (function() {
                var element = targets[i];
                if (typeof element.addEventListener === "function") {
                    var instance = create(element);
                    var timeout = -1;
                    element.addEventListener("click", function() {
                        instance.startAfter(1);
                        if (typeof options.timeout === "number") {
                            clearTimeout(timeout);
                            timeout = setTimeout(instance.stop, options.timeout);
                        }
                        if (typeof options.callback === "function") {
                            options.callback.apply(null, [ instance ]);
                        }
                    }, false);
                }
            })();
        }
    }
    function stopAll() {
        for (var i = 0, len = ALL_INSTANCES.length; i < len; i++) {
            ALL_INSTANCES[i].stop();
        }
    }
    function createSpinner(button) {
        var spinner = button.querySelector('.ladda-spinner');
        if (spinner) {
            return spinner;
        }
        var clazz = ['ladda-spinner', button.getAttribute('data-style')];
        if (button.getAttribute('data-spinner-small')) {
            clazz.push(button.getAttribute('data-style') + '-sm');
        }
        clazz.push('d-none');
        if (button.hasAttribute('data-spinner-color')) {
            clazz.push(button.getAttribute('data-spinner-color'));
        }
        button.insertAdjacentHTML('afterbegin', '<span class="' + clazz.join(' ') + '" role="status" aria-hidden="true"></span>');
        return button.firstChild;
    }
    function toArray(nodes) {
        var a = [];
        for (var i = 0; i < nodes.length; i++) {
            a.push(nodes[i]);
        }
        return a;
    }
    return {
        bind: bind,
        create: create,
        stopAll: stopAll
    };
});
