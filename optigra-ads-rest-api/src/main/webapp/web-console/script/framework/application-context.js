function ApplicationContext(){
	var controller = {};
	var cache = {};
	var templateProvider = new TemplateProvider(this);
	
	this.getTemplateContext = function(){		
		return {prefix: this.webConsoleContextPath + "/script/framework/view/tpl", sufix: ".htm" };
	};
		
	this.getTemplateProvider = function(){	
		return templateProvider;
	};
		
	this.getUserController = function(){		
		if(!controller.user){
			controller.user = new UserController(this);
		}
		
		return controller.user;
	};
	
	this.getCache = function(){
		return cache;
	};
	
	this.addToCache = function(key, value){
		cache[key] = value;
	};
}

function getContext(){		
	return APPLICATION_CONTEXT;
}

