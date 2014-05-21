define([
  'underscore',
  'backbone'
], function(_, Backbone) {
  

	var Model = Backbone.Model.extend({
		url : function() {
			return this.instanceUrl;
		},
		idAttribute: "id",
		initialize : function(options) {
			this.applicationId = options.applicationId;
			this.instanceUrl = '/api/application/' + options.applicationId + '/certificate'
		},
		put: function(id) {
			this.instanceUrl = '/api/application/' + this.applicationId + '/certificate';
			this.instanceUrl = this.instanceUrl + "/" + id;
		} 
	});
  

  return Model;

});