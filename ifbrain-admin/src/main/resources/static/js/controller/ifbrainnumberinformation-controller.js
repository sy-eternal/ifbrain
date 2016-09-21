var IfbrainNumberController = function () {
	var _app = angular.module("_app", []);
	_app.controller("IfbrainNumberController", function($scope, $http) {
		//查询家长对应的孩子
		$scope.change1=function(id){
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/ifbrainnumber/findByUserId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode(data))
			    	$scope.ChildName=JSOG.decode(data);
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
	IfbrainNumberController.init();	
});
