define([
  'jquery',
  'underscore',
  'backbone',

  'settings',
  'models/applications/application-model',
  'collections/applications/application-collection',
  'text!templates/applications/applicationsListTemplate.html'
], function($, _, Backbone, settings, ApplicationModel, ApplicationCollection, ApplicationListTemplate){ 
  var ApplicationsListView = Backbone.View.extend({
    el: $("body"),

    render: function(options){
    	var that = this;
    	var offset = options.offset || 0;
    	this.collection = new ApplicationCollection();
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
				
				var rendered = _.template(ApplicationListTemplate, model);
				
				that.$el.children().remove();
				that.$el.append(rendered);
			}
		});
    }
  });
  
  return ApplicationsListView;
});