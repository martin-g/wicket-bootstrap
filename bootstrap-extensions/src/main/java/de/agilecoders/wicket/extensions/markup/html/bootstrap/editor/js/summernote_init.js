$(function() {
	var summernoteconfig = ${summernoteconfig};
	var summernoteconfigdefault = {
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
		}
	};
	var summernote = $('#'+summernoteconfig.summernoteEditorId);
	$.extend(summernoteconfigdefault, summernoteconfig);
	summernote.summernote(summernoteconfigdefault);
});