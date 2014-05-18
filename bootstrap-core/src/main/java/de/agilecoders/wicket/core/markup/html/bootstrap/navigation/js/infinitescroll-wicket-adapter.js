/*
 * simulates Wicket.Ajax.process(data)
 */

;
(function () {

  'use strict';

  if (typeof(WicketInfinitescrollAdapter) !== 'undefined') {
    return;
  }

  // plain copy from wicket-ajax-jquery.js
  var FunctionsExecuter = function (functions) {

    this.functions = functions;

    this.current = 0;

    this.depth = 0; // we need to limit call stack depth

    this.processNext = function () {
      if (this.current < this.functions.length) {
        var f, run;

        f = this.functions[this.current];
        run = function () {
          try {
            var n = jQuery.proxy(this.notify, this);
            f(n);
          }
          catch (e) {
            Wicket.Log.error("FunctionsExecuter.processNext: " + e);
          }
        };
        run = jQuery.proxy(run, this);
        this.current++;

        if (this.depth > 1000) {
          // to prevent stack overflow (see WICKET-4675)
          this.depth = 0;
          window.setTimeout(run, 1);
        }
        else {
          this.depth++;
          run();
        }
      }
    };

    this.start = function () {
      this.processNext();
    };

    this.notify = function () {
      this.processNext();
    };
  };

  /**
   * Simulates a call like Wicket.Ajax.process(data), but without parsing data to a XMLDocument.
   *
   * You should remove any children from the <ajax-response> element inside data,
   * which already has been processed by the infinitescroll extension.
   * Otherwise Wicket will replace the existing components instead of appending them to your container.
   */
  // modified copy from wicket-ajax-jquery.js
  var process = function (data) {
    var context = {
      attrs: {},
      steps: []
    };
    var call = new Wicket.Ajax.Call();
    call.loadedCallback(data, context);
    var executer = new FunctionsExecuter(context.steps);
    executer.start();
  };

  window.WicketInfinitescrollAdapter = function (ajaxResponse, componentToBeIgnored) {
    ajaxResponse.removeChild(componentToBeIgnored);

    var ajaxResponseToBeProcessedByWicket;
    ajaxResponseToBeProcessedByWicket = document.implementation.createDocument(null, null);
    ajaxResponseToBeProcessedByWicket.appendChild(ajaxResponse);

    return {
      processInfiniteScrollResponse: function () {
        process(ajaxResponseToBeProcessedByWicket);
      }
    }
  };
})();
