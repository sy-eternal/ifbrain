var guideitemController = function () {
	var _app = angular.module("_app", []);
	
	_app.controller("guideitemController", function($scope, $http) {
		
		
		
		$scope.change = function(id) {
			$.ajax({
			    type: "Get",
			    url: "/guideItemOrder/selectCity/"+id,
			    success: function (data) {
			    	$scope.guideName=data;
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
	guideitemController.init();
});
