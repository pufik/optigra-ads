function ApplicationPresenter(context) {
	var templateProvider = context.getTemplateProvider();
	
	
	this.showApplications = function(applications) {
		templateProvider.getContent("/application/application-list.tpl", applications, this.showApplicationsRender);
	};

	this.showApplicationsRender = function(content, search) {		
		$("#page-wrapper").children().remove();
		$("#page-wrapper").append(content);
		
		// Init pagination
		var currentPage = 1;
		if(search.start > 0){
			var currentPage = Math.ceil((search.start + search.offset) / search.start);
		}
		
		var totalPages = Math.ceil(search.count / search.offset);
		totalPages = (totalPages <= 0) ? 1 : totalPages;
		
		var options = {
				page: currentPage,
				total: totalPages,
				maxVisible: 10
		        };
		
		$("#applicationPageSize").val(search.offset);
		
		$("#applicationSearch").bootpag(options).on('page',
				function(event, pageNum){
				context.getApplicationController().getAll(pageNum);
			});
		
	};
}