function ApplicationPresenter(context) {
	var templateProvider = context.getTemplateProvider();
	
	
	this.showApplications = function(applications) {
		templateProvider.getContent("/application/application-list.tpl", applications, this.showApplicationsRender);
	};

	this.showApplicationsRender = function(content) {		
		$("#page-wrapper").children().remove();
		
		$("#page-wrapper").append(content);
	};
}