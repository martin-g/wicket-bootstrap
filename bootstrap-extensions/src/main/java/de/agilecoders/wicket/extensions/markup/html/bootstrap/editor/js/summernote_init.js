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
					success : function() {
						var fileReader = new FileReader();
						fileReader.onload = function(e){
							$('#'+summernoteconfig.summernoteEditorId).summernote('insertImage', fileReader.result);
						};
						fileReader.readAsDataURL(file);
					}
				});
			});
		}
	};
	var summernote = $('#'+summernoteconfig.summernoteEditorId);
	$.extend(summernoteconfigdefault, summernoteconfig);
	summernote.summernote(summernoteconfigdefault);
});