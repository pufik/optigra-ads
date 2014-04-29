define([
  'jquery',
  'underscore',
  'backbone',
  
  'models/users/user-model'
], function($, _, Backbone, Model){
  var Collection = Backbone.Collection.extend({
	url:'/api/user',
    model: Model
  });
 
  return Collection;
});