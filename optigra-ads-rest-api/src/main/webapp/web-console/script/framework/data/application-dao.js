function ApplicationDAO(context) {
	
	var applicationUrl = context.contextPath + "/application";
	
	this.getAll = function(search, responseHandler) {		 
		getDataByAjax({pageUrl : applicationUrl, reqData: search, handler : responseHandler});
	};
	
}