define([
  'jquery',
  'underscore',
  'backbone',
  
  'models/applications/application-model'
], function($, _, Backbone, ProjectModel){
  var ProjectsCollection = Backbone.Collection.extend({
	url:'/api/application',
    model: ProjectModel,
    
    initialize: function(){

    }

  });
 
  return ProjectsCollection;
});