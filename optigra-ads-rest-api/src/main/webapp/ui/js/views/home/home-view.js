define([
  'jquery',
  'underscore',
  'backbone',
  
  'text!templates/home/home.html'
], function($, _, Backbone, homeView){ 
  var HomeView = Backbone.View.extend({
    el: $("body"),

    render: function(){
        var data = {};
        var compiledTemplate = _.template(homeView, data);
        
        this.$el.children().remove();
        this.$el.append(compiledTemplate);
    }
  
  });
  
  return HomeView;
});