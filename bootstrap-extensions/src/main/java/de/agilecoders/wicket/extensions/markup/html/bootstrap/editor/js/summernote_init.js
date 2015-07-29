$(document).ready(function() {
	var summernoteconfig = ${summernoteconfig};
	var summernoteconfigdefault = {
		onImageUpload : function(files) {
			var file = files[0];
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
//					FileReader fr = new FileReader();
//					fr.onload(function(e)){
//						
//					};
//					fr.readAsDataURL(file);
					console.log($editable);
				}
			});
		}
	};
	var summernote = $('#summernote');
	$.extend(summernoteconfigdefault, summernoteconfig);
	summernote.summernote(summernoteconfigdefault);
});