define([
  'underscore',
  'backbone'
], function(_, Backbone) {
  
  var ProjectModel = Backbone.Model.extend({
	  urlRoot : '/api/application',
	  idAttribute: 'applicationId',
  });

  return ProjectModel;

});