function ApplicationController(context) {
	var applicationDao = new ApplicationDAO(context);
	var applicationPresenter = new ApplicationPresenter(context);

	this.getAll = function() {
		var search= {};
		search.start = 0;
		search.offset = 10;
		
		applicationDao.getAll(search, this.getAllResponseHandler);
	};

	this.getAllResponseHandler = function(applications) {
		applicationPresenter.showApplications(applications);
	};
}