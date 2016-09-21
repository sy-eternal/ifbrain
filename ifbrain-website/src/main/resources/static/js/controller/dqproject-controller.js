var DBProjectController = function () {
	var _app = angular.module("_app", []);
	
	_app.controller("DBProjectController", function($scope, $http) {
		
		var value="";
		function  getId(id){
			value=id;
			alert(value+"id");
			return false;
		}
		
		
		$scope.change = function(id) {
		      
			$.ajax({
			    type: "Get",
			    url: "/project/li/"+value,
			    success: function (data) {
			   
			    	
			    	
			    }
			    	
			 });
			
			
		    };
	
	});
	
	
	

	
	
	
	return {
		init : function () {
	
		}
	};
}();

$(function () {
	DBProjectController.init();
});
