;(function (undefined) {

    'use strict';

    jQuery('#${markupId}').fileinput({
        showCaption: ${showCaption},
        showPreview: ${showPreview},
        showRemove: ${showRemove},
        showUpload: ${showUpload},
        uploadClass: 'btn btn-default fileinput-upload-button',
        browseLabel: '${browseLabel}',
        removeLabel: '${removeLabel}',
        uploadLabel: '${uploadLabel}',
        msgLoading:  '${msgLoading}',
        msgProgress: '${msgProgress}',
        msgSelected: '${msgSelected}'
    });

    // bind ajax submit behavior on upload button click
    jQuery('.file-input:has(#${markupId}) .fileinput-upload-button').click(function(e){
        e.preventDefault();
        jQuery('#${markupId}').triggerHandler('${eventName}');
    });
})();
