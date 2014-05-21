define([
  'jquery',
  'underscore',
  'backbone',
  'bootstrap',

  'settings',
  'views/views',
  'models/applications/application-model',
  'models/certificate/certificate-model',
  'text!templates/applications/applicationTemplate.html'
  
], function($, _, Backbone, bootstrap, settings, viewTools, ApplicationModel, CertificateModel , Template){ 
  var ApplicationView = Backbone.View.extend({
	  
		el: 'body',
		model: ApplicationModel,
	    
	    events: {
	      'click #saveApplicationBtn': 'saveApplication',
	      'click #deleteApplicationBtn': 'deleteApplication',
	      
	      'click #saveCertificateBtn': 'saveCertificate',
	      'click #deleteCertificateBtn': 'deleteCertificate',
	      
	      'click #crateNotificationBtn' : 'sendNotification',
	      
	      'click #addCertificate': 'addCertificateDialog',
	      'click #showNotificationDialogBtn': 'addNotificationDialog',
	      
	      'change #applicationFile': 'uploadFile',
    	  'change #certificateFile': 'uploadCertificate'
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

	    saveCertificate: function () {

	    	var certificateDetails = viewTools.serializeObject($('.edit-certificate-form'));
	    	
	    	if(!certificateDetails.id) {
	    		this.certificate = new CertificateModel({applicationId: this.applicationId});
	    	} else {
	    		this.certificate.put(certificateDetails.id);
	    	}
	    	
	    	this.certificate.save(certificateDetails, {
	    		success: function (model, response) {
	    			$('#certificateModalDialog').modal('hide');
	    	    	Backbone.history.stop(); 
	    	    	Backbone.history.start();
	    		},
    		    error: function(model, response) {
    		    	console.error(model);
    		    	console.error(response);
    		    }
	    	});
				    	
	    	return false;
	    },
	    
	    sendNotification: function() {
	    	var notificationDetails = JSON.stringify(viewTools.serializeObject($('.send-notification-form')));
	    	var url = '/api/application/' + $("#applicationId").val() + '/notification';
	    	
			$.ajax({
				type: "POST",
				url: url,
				data: notificationDetails,
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function(data){
					console.info(data)
				},
				failure: function(errMsg) {
					console.erro(errMsg);
				}
			});
			
			$('#apnsNotificationDialog').modal('hide');
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

	    deleteCertificate: function (ev) {
	    	this.certificate.destroy({
	    		success: function () {
	    			$('#certificateModalDialog').modal('hide');
	    	    	Backbone.history.stop(); 
	    	    	Backbone.history.start();
	    		}
	    	});
	    	
	    	return false;
	    },
	    
	    render: function (options) {
	      var that = this;
	      var data = {entity:null, certificate:null};

	      if(options.appId) {
	    	  this.applicationId = options.appId;
	    	  
	    	  this.model = new ApplicationModel({applicationId: options.appId});
	    	  this.certificate = new CertificateModel({applicationId: options.appId});
	    	  
	    	  $.when(
    			  that.model.fetch({
    				  success:function(application) {
    					  data.entity = application;
    				  },
    				  error: function(model, response) {
    					  data.entity = null;
    				  }
    			  }),
		    	  that.certificate.fetch({
		    		  success:function(certificate) {
		    			  data.certificate = certificate;
		    		  },
		    		  error: function(model, response) {
		    			  data.certificate = null;
		    		  }
		    	  })
	    	  ).done(function(response1, response2){
	    		  that.process(data);
	    	  }).fail(function() {
	    		  console.log("Failed to execute all requests")
	    		  that.process(data);
	    	  });
	      } else {
	    	  this.process(data);
	      }
	      
	    },
	    addCertificateDialog: function() {
	    	$("#certificateModalDialog").modal('show');
	    },
	    addNotificationDialog: function() {
	    	$("#apnsNotificationDialog").modal('show');
	    },
	    process: function(data) {
	    	  var rendered = _.template(Template, data);
	    	  
	    	  this.$el.children().remove();
			  this.$el.append(rendered);
	    },
	    uploadFile: function(event) {
	    	viewTools.uploadEvent(event);
	    },
	    uploadCertificate: function(event) {
	    	viewTools.uploadCustomEvent(event, function(data) {
				var url = data.path;
				$("#certificatePath").val(url);	  
	    	}, function(evt) {
				var progress = evt.loaded/evt.total*100;
				$("#prgrsCertificate").css("width", progress + "%");	
	    	});
	    }
  });
  
  return ApplicationView;
});