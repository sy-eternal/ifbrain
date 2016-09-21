var guidetypeplanController = function () {
	var _app = angular.module("_app", []);
	
	_app.controller("guidetypeplanController", function($scope, $http) {
		
		$scope.change = function(id) {
		      
			$.ajax({
			    type: "Get",
			    url: "/guideTypePlan/selectCity/"+id,
			    success: function (data) {
			   
			    	$scope.guideTypeName=data;
			    	
			    }
			    	
			 });
			
			
		    };
	
	});
	
	
	

	$('#City').change(function(){
		alert("uuuu");
		var id=$(this).val();
		alert(id+"id");
			$.ajax({
			    type: "Get",
			    url: "/guideTypePlan/selectCity/"+id,
			    success: function (data) {
			    	alert(data);
			    	$scope.guideTypeName=data;
			    	
			    }
			    	
			 });
			    
			});
	
	
	return {
		init : function () {
	
		}
	};
}();

$(function () {
	guidetypeplanController.init();
});
