$(function() {
    var summernoteConfig = ${summernoteconfig};
    var summernote = $('#'+summernoteConfig.summernoteEditorId);

    var toolbar = new Array();
    $.each(summernoteConfig.ToolbarOptions, function(key, value){
        var category = new Array();
        category.push(key);
        category.push(value);
        toolbar.push(category);
    });

    var summernoteConfigDefault = {
        toolbar : toolbar,
        onImageUpload : function(files) {
		var files = $(files);
		var filesSize = files.length;
		var overlay;

		// Show Overlay
		var overlayTimeout = setTimeout(function(){
			overlay = $("<div class='summernoteOverlay'></div>").appendTo("body");
			new Spinner({color:'#fff'}).spin(overlay[0]);
		},summernoteConfig.overlayTimeout);

            files.each(function(){
                var file = this;
                var data = new FormData();
                data.append("file", file);
                url = summernoteConfig.imageUploadUrl;
                $.ajax({
                    data : data,
                    headers : {
                        "Wicket-Ajax" : "true",
                        "Wicket-Ajax-BaseURL" : Wicket.Ajax.baseUrl
                    },
                    type : "POST",
                    url : url,
                    cache : false,
                    contentType : false,
                    processData : false,
                    success : function(res, status, xhr) {
			// Insert image
                        var imageUrl = xhr.getResponseHeader("imageUrl");
                        var decodedImageUrl = window.atob(/(image=)(.*)[^&]*/.exec(imageUrl)[2]);
                        imageUrl = imageUrl.replace(/(image=)[^&]*/, '$1' + decodedImageUrl);
                        $('#'+summernoteConfig.summernoteEditorId).summernote('insertImage',imageUrl);

                        // Hide Overlay
                        filesSize -= 1;
                        if(!filesSize){
				clearTimeout(overlayTimeout);
				if(overlay){
					overlay.remove();
				}
                        }
                    }
                });
            });
        },
        onBlur: function(e) {
            // Create a hidden field so that if the form has been submitted the content will be available in the backend
            var form = summernote.closest("form");
            if($('#'+summernoteConfig.summernoteEditorId+"_content",form).length <= 0){
                $(form).append("<input type='hidden' id='"+summernoteConfig.summernoteEditorId+"_content' name='"+summernoteConfig.summernoteEditorId+"_content' value='' />");
            }
            $('#'+summernoteConfig.summernoteEditorId+"_content",form).val(window.btoa(summernote.code()));
        }
    };

    $.extend(summernoteConfigDefault, summernoteConfig);
    summernote.summernote(summernoteConfigDefault);
    summernote.code(summernoteConfig.content);
});
