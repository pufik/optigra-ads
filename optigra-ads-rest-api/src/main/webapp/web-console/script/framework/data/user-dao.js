function UserDAO(context) {
	
	var userUrl = context.contextPath + "/user";
	var loginUrl = context.contextPath;
	
	var makeBaseAuth = function(user, password) {
		  var tok = user + ':' + password;
		  var hash = btoa(tok);
		  return "Basic " + hash;
	}
	
	this.getAll = function(responseHandler) {		 
		getDataByAjax({pageUrl : userUrl, handler : responseHandler});
	};
	
	this.login = function(user, responseHandler) {
	var context = {
		pageUrl : loginUrl,
		reqType: "GET",
		dataType: "json text",
		onComplete : responseHandler,
		onError: responseHandler,
		beforeSend: function (xhr){
			var tok = user.email + ':' + user.password;
			var securedToken = "Basic "  + btoa(tok);
	        
			xhr.setRequestHeader('Authorization', securedToken);
	        showDefaultLoadingScreen();
	    }
	};
		
	getDataByAjax(context);
	
	};
	
	this.register = function(responseHandler, user) {	
		getDataByAjaxJSON({pageUrl : userUrl, handler : responseHandler, reqType: "POST", dataObject : user});
	};
}