// Require.js allows us to configure shortcut alias
require.config({
  paths: {
    jquery: 'libs/jquery/jquery-min',
    jqueryui: 'libs/jquery/jquery-ui-min',
    jqueryform: 'libs/jquery/jquery-form',
    jqueryvalidate: 'libs/jquery/jquery-validate-min',
    
    underscore: 'libs/underscore/underscore-min',
    
    backbone: 'libs/backbone/backbone-min',
    backboneroutefilter: 'libs/backbone/backbone-routefilter',

    spin: 'libs/spin/spin-min',
    
    templates: '../templates',

    settings: 'settings/pre-settings',
    security: 'libs/settings/security',

    ui: 'ui/ui'
  }

});

require([
  // Load our app module and pass it to our definition function
  'app'

], function(App){
  // The "app" dependency is passed in as "App"
  // Again, the other dependencies passed in are not "AMD" therefore don't pass a parameter to this function
  App.initialize();
});