;(function (undefined) {
    'use strict';

    // bind ajax submit behavior on upload button click
    jQuery('.file-input:has(#${markupId}) .fileinput-upload-button').click(function(e){
        e.preventDefault();
        jQuery('#${markupId}').triggerHandler('${eventName}');
    });
})();
