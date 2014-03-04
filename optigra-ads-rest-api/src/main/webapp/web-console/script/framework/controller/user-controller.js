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
		var user = { role: 'ADMIN'};
		user.login = $("#userCreateEmail").val();
		user.password = $("#userCreatePassword").val();;
		user.email = $("#userCreateEmail").val();
		user.fullName = $("#userCreateFullName").val();
		
		userDao.register(this.registerResponseHandler, user);
		
		return false;
	};
	
	this.registerResponseHandler = function(message) {
		if(message.type == "INFO"){
		  alert('User created!')
		}
	}
	
	this.getDashboard = function() {
		userDao.getProfile(this.getProfileResponseHandler);
	};

	this.getProfileResponseHandler = function(profile) {
		userPresenter.showDashboard(profile);
	}
}