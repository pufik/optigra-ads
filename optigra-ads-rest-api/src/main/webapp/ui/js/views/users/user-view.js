define([
  'jquery',
  'underscore',
  'backbone',

  'settings',
  'views/views',
  'models/users/user-model',
  'text!templates/users/user-template.html'
  
], function($, _, Backbone, settings, viewTools, Model, Template){ 
  var ApplicationView = Backbone.View.extend({
	  
		el: 'body',
		model: Model,
	    
	    events: {
	      'click #saveUserBtn': 'saveUser',
	      'click #deleteUserBtn': 'deleteUser',
	      'change input[type=file]': 'uploadFile'
	    },
	    
	    saveUser: function (ev) {
	      this.undelegateEvents();
	      this.unbind();
	      
	      var userDetails = viewTools.serializeObject($('.edit-user-form'));
	      this.model = new Model();
	      
	      this.model.save(userDetails, {
	        success: function (model, response) {
	        	Backbone.history.navigate('users', {trigger:true});
	        }
	      });
	      
	      return false;
	    },
	    
	    deleteUser: function (ev) {
	      this.undelegateEvents();
	      this.unbind();
	      
	      this.model.destroy({
	        success: function () {
	        	Backbone.history.navigate('users', {trigger:true});
	        }
	      });
	      
	      return false;
	    },
	    
	    render: function (options) {
	      var that = this;
	      var data = {entity:null};

	      if(options.id) {
	    	  this.model = new Model({id: options.id});
	    	  this.model.fetch({
		    		async: false,
					success: function (user) {
						data = {entity:user};
					}
	          });
	    	  
	      }
	      
    	  var rendered = _.template(Template, data);
    	  
    	  this.$el.children().remove();
		  this.$el.append(rendered);
	    },
	    
	    uploadFile: function(event) {
	    	viewTools.uploadEvent(event);
	    }
  });
  
  return ApplicationView;
});