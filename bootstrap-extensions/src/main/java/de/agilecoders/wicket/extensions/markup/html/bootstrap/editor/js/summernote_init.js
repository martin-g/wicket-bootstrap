$(function() {
	var summernoteconfig = ${summernoteconfig};
	var summernote = $('#'+summernoteconfig.summernoteEditorId);
	
	var toolbar = new Array();
	$.each(summernoteconfig.ToolbarOptions, function(key, value){
		var category = new Array();
		category.push(key);
		category.push(value);
		toolbar.push(category);
	});
	
	var summernoteconfigdefault = {
		toolbar : toolbar,
		onImageUpload : function(files) {
			$(files).each(function(){
				var file = this;
				var data = new FormData();
				data.append("file", file);
				url = summernoteconfig.imageUploadUrl;
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
						var imageUrl = xhr.getResponseHeader("imageUrl");
						var decodedImageUrl = window.atob(/(image=)(.*)[^&]*/.exec(imageUrl)[2]);
						imageUrl = imageUrl.replace(/(image=)[^&]*/, '$1' + decodedImageUrl);
						$('#'+summernoteconfig.summernoteEditorId).summernote('insertImage',imageUrl);
					}
				});
			});
		},
		onBlur: function(e) {
			// Create a hidden field so that if the form has been submitted the content will be available in the backend
			var form = summernote.closest("form");
			if($('#'+summernoteconfig.summernoteEditorId+"_content",form).length <= 0){
				$(form).append("<input type='hidden' id='"+summernoteconfig.summernoteEditorId+"_content' name='"+summernoteconfig.summernoteEditorId+"_content' value='' />");
			}
			$('#'+summernoteconfig.summernoteEditorId+"_content",form).val(window.btoa(summernote.code()));
		}
	};
	
	$.extend(summernoteconfigdefault, summernoteconfig);
	summernote.summernote(summernoteconfigdefault);
	summernote.code(summernoteconfig.content);
});