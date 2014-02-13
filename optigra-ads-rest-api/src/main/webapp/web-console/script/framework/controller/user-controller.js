function UserController(context) {
	var userDao = new UserDAO(context);
	var userPresenter = new UserPresenter(context);
	var tmpId = "";

	this.getAll = function() {
		userDao.getAll(this.getAllResponseHandler);
	};

	this.getAllResponseHandler = function(responseData) {
		userPresenter.showAllView(responseData);
	};
	
	this.hideLoginError = function(){
		$(".signInError").hide();
	}

	this.login = function() {
		var userName = $("#userEmail").val();
		var userPassword = $("#userPassword").val();

		userDao.login({
			email : userName,
			password : userPassword
		}, this.loginResponseHandler);

		return false;
	};

	this.loginResponseHandler = function(jqXHR, textStatus) {
		hideDefaultLoadingScreen();
		if(jqXHR.status == "401"){
		  $(".signInError").show();
		}else{
		  document.location = "home.html";
		}
	}
	
	this.register = function() {
		alert("We will do it ASAP ;)");
		return false;
	};
}