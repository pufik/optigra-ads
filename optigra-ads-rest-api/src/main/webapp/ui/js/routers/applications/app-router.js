// Filename: router.js
define([
  'jquery',
  'underscore',
  'backbone',
  
  'ui',
  'js/views/applications/applications-view.js',
  'js/views/applications/application-view.js'
], function($, _, Backbone, ui, ApplicationListView, ApplicationView) {
  
  var AppRouter = Backbone.Router.extend({
	  routes : {
		  'applications':'applications',
		  'applications/pagination/:offset': 'applications',
		  'applications/edit/:id': 'editApp',
		  'applications/new':'editApp',
	  },
	  
	  applications: function(offset) {
		ui.progressBar('page-wrapper');
		
    	var applicationsView = new ApplicationListView();
    	applicationsView.render({offset:offset});
	  },
	  
	  editApp: function(appId) {
		ui.progressBar('page-wrapper');
		  
		var applicationView = new ApplicationView();
		applicationView.render({appId:appId})
	  }
  });
  
  var initialize = function(){

	  var app_router = new AppRouter;
	   
  };
  
  return { 
    initialize: initialize
  };
});