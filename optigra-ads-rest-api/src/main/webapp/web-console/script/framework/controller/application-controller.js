function ApplicationController(context) {
	var applicationDao = new ApplicationDAO(context);
	var applicationPresenter = new ApplicationPresenter(context);

	this.getAll = function(pageNum) {
		var offset = 0;
		var limit = $("#applicationPageSize").val();
		
		if (pageNum) {
			offset = (pageNum - 1) * limit;
		}

		var search = {};
		search.offset = offset;
		search.limit = (limit) ? limit : 10;

		applicationDao.getAll(search, this.getAllResponseHandler);
	};

	this.getAllResponseHandler = function(search) {
		search.from = search.start;
		search.to = ((search.start + search.offset) < search.count) ? (search.start + search.offset)
				: search.count;
		applicationPresenter.showApplications(search);
	};
	
	this.createApplication = function() {
		var application = {};
		
		application.name = $("#applicationName").val();
		application.url = $("#applicationUrl").val();
		application.status = "PENDING";
		application.applicationId = new Date().getTime();
		
		applicationDao.create(application, this.createApplicationResponseHandler);
		
		//Form restriction
		return false;
	};
	
	this.createApplicationResponseHandler = function(message) {
		if(message.type == "INFO"){
			$('#addApplicationModal').modal('hide');
			context.getApplicationController().getAll();
		}else{
			//TODO: Show error message
		}
		
		
	};
}