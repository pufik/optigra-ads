function UserPresenter(context) {
	var templateProvider = context.getTemplateProvider();
	
	
	this.showAllView = function(projects) {
		templateProvider.getContent("/project/project-list.tpl", {items: projects}, this.showAllViewRender);
	};

	this.showAllViewRender = function(content) {		
		$("#main").children().remove();
		$("#main").append(content);
		
		var oTable = $('#projectList').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
	        "bLengthChange": true,
	        "bFilter": true,
	        "bSort": true,
	        "bInfo": true,		        
	        "bAutoWidth": false,
	        "aaSorting": [[ 0, "asc" ]]
		});
		
		$(".buttonCreate").button({ icons: {primary:'ui-icon-plusthick'}});
	};
}