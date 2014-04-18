define([
        'jquery'
      ], function($, Spinner){
	
		var htmlEncode = function(value) {
			return $('<div/>').text(value).html();
		}
	
		var serializeObject = function(object) {
			var o = {};
			var a = object.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			
			return o;
		};
		
		var registerUploadFile = function(file) {
			var data = new FormData();
			data.append("file", file);
			
			$.ajax({
				url: '/api/content',
				type: 'POST',
				data: data,
				cache: false,
				dataType: 'json',
				processData: false, 
				contentType: false, 
				success: function(data, textStatus, jqXHR)
				{
					if(typeof data.error === 'undefined') {
						$("input[name='imageUrl']").val(data.path);
					} else {
						console.log('ERRORS: ' + data.error);
					}
				},
				error: function(jqXHR, textStatus, errorThrown)
				{
					console.log('Errors during upload: ' + textStatus);
				},
				xhr: function(){
					var xhr = $.ajaxSettings.xhr() ;
					xhr.upload.onprogress = function(evt){ 
						var progress = evt.loaded/evt.total*100;
						$("#prgrs").css("width", progress + "%");	
					} ;
					xhr.upload.onload = function(){
						setTimeout(function() {$("#prgrs").css("width", "0%");},1000);
					};
					
					return xhr ;
				}
			});
		}
		
		var uploadEvent = function(event) {
	    	var file = event.target.files[0];
	    	registerUploadFile(file);
	    }
		
      	
      	return {
      		serializeObject:serializeObject,
      		htmlEncode:htmlEncode,
      		registerUploadFile:registerUploadFile,
      		uploadEvent:uploadEvent
      	};
      	
});

