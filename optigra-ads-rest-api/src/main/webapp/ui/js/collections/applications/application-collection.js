define([
  'jquery',
  'underscore',
  'backbone',
  
  'models/applications/application-model'
], function($, _, Backbone, Model){
  var ProjectsCollection = Backbone.Collection.extend({
	url:'/api/application',
    model: Model,
    
    initialize: function(){

    }

  });
 
  return ProjectsCollection;
});