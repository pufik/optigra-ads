'use strict';

angular.module('optigraAdsWebClientApp')
  .controller('ApplicationCtrl', function ($scope) {

    $scope.applications = {

	    "start": 0,
	    "offset": 20,
	    "count": 12,
	    "entities": [
	        {
	            "id": 40,
	            "applicationId": "1392644056502",
	            "status": "PENDING",
	            "url": "vk.com/mario_brothers",
	            "name": "Mario Brothers",
	            "uri": "/application/1392644056502"
	        },
	        {
	            "id": 41,
	            "applicationId": "1392644120942",
	            "status": "PENDING",
	            "url": "www.vk.com/barchuk_corporation",
	            "name": "Barchuk fan group",
	            "uri": "/application/1392644120942"
	        },
	        {
	            "id": 42,
	            "applicationId": "1392644156508",
	            "status": "PENDING",
	            "url": "www.vk.com/optigra",
	            "name": "Optigra vk group",
	            "uri": "/application/1392644156508"
	        },
	        {
	            "id": 43,
	            "applicationId": "1392644178810",
	            "status": "PENDING",
	            "url": "www.vk.com/dell",
	            "name": "Dell",
	            "uri": "/application/1392644178810"
	        },
	        {
	            "id": 44,
	            "applicationId": "1392644185745",
	            "status": "PENDING",
	            "url": "www.vk.com/apple",
	            "name": "Apple",
	            "uri": "/application/1392644185745"
	        },
	        {
	            "id": 45,
	            "applicationId": "1392644193079",
	            "status": "PENDING",
	            "url": "www.vk.com/google",
	            "name": "Google",
	            "uri": "/application/1392644193079"
	        },
	        {
	            "id": 46,
	            "applicationId": "1392644225756",
	            "status": "PENDING",
	            "url": "www.vk.com/nexus",
	            "name": "Nexus",
	            "uri": "/application/1392644225756"
	        },
	        {
	            "id": 47,
	            "applicationId": "1392644250363",
	            "status": "PENDING",
	            "url": "www.vk.com/hp",
	            "name": "Hp",
	            "uri": "/application/1392644250363"
	        },
	        {
	            "id": 48,
	            "applicationId": "1392644258522",
	            "status": "PENDING",
	            "url": "www.vk.com/elements",
	            "name": "elements",
	            "uri": "/application/1392644258522"
	        },
	        {
	            "id": 49,
	            "applicationId": "1392644511946",
	            "status": "PENDING",
	            "url": "www.vk.com/gorenje",
	            "name": "Gorenje",
	            "uri": "/application/1392644511946"
	        },
	        {
	            "id": 50,
	            "applicationId": "1392644523696",
	            "status": "PENDING",
	            "url": "www.vk.com/samsung",
	            "name": "Samsung",
	            "uri": "/application/1392644523696"
	        },
	        {
	            "id": 51,
	            "applicationId": "1392646174266",
	            "status": "PENDING",
	            "url": "www.vk.com/nescafe",
	            "name": "nescafe",
	            "uri": "/application/1392646174266"
	        }
	    ],
	    "uri": "/application"

	}
  });
