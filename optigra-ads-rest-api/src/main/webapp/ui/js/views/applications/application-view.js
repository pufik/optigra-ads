define([
  'jquery',
  'underscore',
  'backbone',

  'settings',
  'views/views',
  'models/applications/application-model',
  'text!templates/applications/applicationTemplate.html'
  
], function($, _, Backbone, settings, viewTools, ApplicationModel, ApplicationTemplate){ 
  var ApplicationView = Backbone.View.extend({
	  
		el: 'body',
		model: ApplicationModel,
	    
	    events: {
	      'click #saveApplicationBtn': 'saveApplication',
	      'click #deleteApplicationBtn': 'deleteApplication',
	      'change input[type=file]': 'uploadFile'
	    },
	    
	    saveApplication: function (ev) {
	      this.undelegateEvents();
	      this.unbind();
	      
	      var applicationDetails = viewTools.serializeObject($('.edit-application-form'));
	      this.model = new ApplicationModel();
	      
	      this.model.save(applicationDetails, {
	        success: function (model, response) {
	        	Backbone.history.navigate('applications', {trigger:true});
	        }
	      });
	      
	      return false;
	    },
	    
	    deleteApplication: function (ev) {
	      this.undelegateEvents();
	      this.unbind();
	      
	      this.model.destroy({
	        success: function () {
	        	Backbone.history.navigate('applications', {trigger:true});
	        }
	      });
	      
	      return false;
	    },
	    
	    render: function (options) {
	      var that = this;
	      var data = {entity:null};

	      if(options.appId) {
	    	  this.model = new ApplicationModel({applicationId: options.appId});
	    	  this.model.fetch({
		    		async: false,
					success: function (application) {
						data = {entity:application};
					}
	          });
	    	  
	      }
	      
    	  var rendered = _.template(ApplicationTemplate, data);
    	  
    	  this.$el.children().remove();
		  this.$el.append(rendered);
	    },
	    
	    uploadFile: function(event) {
	    	viewTools.uploadEvent(event);
	    }
  });
  
  return ApplicationView;
});