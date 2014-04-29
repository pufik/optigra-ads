define([
  'jquery',
  'underscore',
  'backbone',

  'settings',
  'models/users/user-model',
  'collections/users/users-collection',
  'text!templates/users/users-list-template.html'
], function($, _, Backbone, settings, Model, Collection, Template){ 
  var ApplicationsListView = Backbone.View.extend({
    el: $("body"),

    render: function(options){
    	console.info('Rendering user-list');
    	
    	var that = this;
    	var offset = options.offset || 0;
    	
    	this.collection = new Collection();
    	this.collection.fetch({
			data: { offset: offset},
			success : function(response) {
				var data = response.toJSON();
				
				var model = {
					entities: data[0].entities,
					offset: data[0].offset,
					limit: data[0].limit,
					count: data[0].count
				};
				
				var rendered = _.template(Template, model);
				
				that.$el.children().remove();
				that.$el.append(rendered);
			}
		});
    }
  });
  
  return ApplicationsListView;
});