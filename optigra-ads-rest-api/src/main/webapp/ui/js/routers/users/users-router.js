// Filename: router.js
define([
  'jquery',
  'underscore',
  'backbone',
  
  'ui',
  'js/views/users/users-view.js',
  'js/views/users/user-view.js'
], function($, _, Backbone, ui, ListView, View) {
  
  var UserRouter = Backbone.Router.extend({
	  routes : {
		  'users':'users',
		  'users/pagination/:offset': 'users',
		  'users/edit/:id': 'editUser',
		  'users/new':'editUser',
	  },
	  
	  users: function(offset) {
		console.info('UserRouter: users');
		ui.progressBar('page-wrapper');
		
    	var usersListView = new ListView();
    	usersListView.render({offset:offset});
	  },
	  
	  editUser: function(id) {
    	console.info('UserRouter: editUser');
		ui.progressBar('page-wrapper');
		  
		var userView = new View();
		userView.render({id:id})
	  }
  });
  
  var initialize = function(){
	  var userRouter = new UserRouter;
  };
  
  return { 
    initialize: initialize
  };
});