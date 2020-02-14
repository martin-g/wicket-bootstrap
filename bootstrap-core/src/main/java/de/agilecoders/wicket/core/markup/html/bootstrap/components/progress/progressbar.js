;(function ($, undefined) {

    'use strict';

    Wicket.WUPB.prototype.start = function() {
        var fileupload = Wicket.$(this.fileid);

        if (fileupload && fileupload.value) {
            this.scheduleUpdate();
        }
    };

    Wicket.WUPB.prototype.setStatus = function(status) {
//        console.log('setStatus', status);
    }

    Wicket.WUPB.prototype.setPercent = function(percent, customId) {
        var tab = $('#' + (customId || this.barid));
        tab.css({width: percent + '%'});
    }

    Wicket.WUPB.prototype.update = function() {
        var responseAsText = this.iframe.contentDocument.body.innerHTML;

        var update = responseAsText.split('|');

        var progressPercent = update[1];
        var status = update[2];

        this.setPercent(progressPercent);
        this.setStatus( status );

        this.iframe.parentNode.removeChild(this.iframe);
        this.iframe = null;

        if (progressPercent !== '100') {
            this.scheduleUpdate();
        }
    }

})(jQuery);
