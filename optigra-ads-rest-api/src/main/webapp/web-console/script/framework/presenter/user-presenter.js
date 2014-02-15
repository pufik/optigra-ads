function UserPresenter(context) {
	var templateProvider = context.getTemplateProvider();
	
	
	this.showDashboard = function(profile) {
		templateProvider.getContent("/dashboard/dashboard.tpl", profile, this.showDashboardRender);
	};

	this.showDashboardRender = function(content) {		
		$("#wrapper").children().remove();
		
		$("#wrapper").append(content);
	};
}