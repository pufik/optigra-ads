function ApplicationController(context) {
	var applicationDao = new ApplicationDAO(context);
	var applicationPresenter = new ApplicationPresenter(context);

	this.getAll = function(pageNum) {
		var start = 0;
		var offset = $("#applicationPageSize").val();
		
		if (pageNum) {
			start = (pageNum - 1) * offset;
		}

		var search = {};
		search.start = start;
		search.offset = (offset) ? offset : 10;

		applicationDao.getAll(search, this.getAllResponseHandler);
	};

	this.getAllResponseHandler = function(search) {
		search.from = search.start;
		search.to = ((search.start + search.offset) < search.count) ? (search.start + search.offset)
				: search.count;
		applicationPresenter.showApplications(search);
	};
}