// Filename: app.js
define([
  'jquery', 
  'underscore', 
  'backbone',
  
  'routers/home/home-router',
  'routers/applications/app-router',
  'routers/users/users-router',
  'routers/login/login-router'
], function($, _, backbone, homeRouter, appRouter, usersRouter ,loginRouter){
		
  var initialize = function() {
		presetup();

		homeRouter.initialize();
		appRouter.initialize();
		usersRouter.initialize();
		loginRouter.initialize();

		Backbone.history.start();
	};

	function presetup() {
		$.ajaxSetup({
			statusCode : {
				401 : function() {
					console.info('Handling 401 error');
					Backbone.history.navigate('login', {
						trigger : true
					});
				},
				403 : function() {
					console.info('Handling 403 error');
					Backbone.history.navigate('login', {
						trigger : true
					});
				}
			}
		});

		$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
			if (!options.beforeSend) {
				options.beforeSend = function(xhr) {
					xhr.setRequestHeader('Authorization', getAuthCookie());
				};
			}
		});

		function getAuthCookie() {
			var cn = "Authorization=";
			var idx = document.cookie.indexOf(cn);

			if (idx != -1) {
				var end = document.cookie.indexOf(";", idx + 1);
				if (end == -1)
					end = document.cookie.length;
				var result = unescape(document.cookie.substring(
						idx + cn.length, end));
				return result;
			} else {
				return "";
			}
		}
		;
	}

	return {
		initialize : initialize
	};
});