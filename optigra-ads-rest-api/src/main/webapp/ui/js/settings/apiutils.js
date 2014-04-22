
define([
	'jquery'
], function($) {

	var webServiceUrl = function() {
		var contextPath = 'optigra-ads-rest-api';
		var href = window.location.href;
		var n = href.indexOf(contextPath);
		href = href.substring(0, n != -1 ? n : href.length) + contextPath + '/api';
		return href;
	}
	
	return {
		webServiceUrl:webServiceUrl
	};
});