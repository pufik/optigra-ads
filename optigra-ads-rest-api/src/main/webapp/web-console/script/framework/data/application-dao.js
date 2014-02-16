function ApplicationDAO(context) {
	
	var applicationUrl = context.contextPath + "/application";
	
	this.getAll = function(search, responseHandler) {		 
		getDataByAjax({pageUrl : applicationUrl, reqData: search, handler : responseHandler});
	};
	
	this.create = function(application, responseHandler) {		 
		getDataByAjaxJSON({pageUrl : applicationUrl, reqType: "POST", dataObject: application, handler : responseHandler});
	};
	
}