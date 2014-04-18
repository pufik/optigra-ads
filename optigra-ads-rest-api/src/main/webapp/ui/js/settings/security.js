var app = app || {};

function getAuthCookie() {
   var cn = "Authorization=";
   var idx = document.cookie.indexOf(cn);

   if (idx != -1) {
       var end = document.cookie.indexOf(";", idx + 1);
       if (end == -1) end = document.cookie.length;
       var result = unescape(document.cookie.substring(idx + cn.length, end));
       return result;
   } else {
       return "";
  }
};

sendAuthentication = function (xhr) {
	  xhr.setRequestHeader('Authorization', getAuthCookie());
};

authorize = function(login, password) {
	var token = login + ':' + password;
	var hash = 'Basic ' + btoa(token);
	document.cookie = 'Authorization=' + hash;
};


isAuthorized = function() {
	var result = false;
    $.ajax({
        url: '/api/users/current',
        cache: false,
        dataType: 'json',
        async:   false,
        success: function(data, textStatus, jqXHR)
        {
        	if(typeof data.message !== 'undefined' || typeof data.role !== 'undefined') {
        		result = false;
        	} else {
        		result = true;
        	}
        },
        error: function(jqXHR, textStatus, errorThrown)
        {
        	result = false;
        }
    });
    return result;
};