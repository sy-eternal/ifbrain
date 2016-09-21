var AngularController = function () {
	// Defining a Angular module
	var _app = angular.module("_app", []);

	// Defining a Angular Controller 
	_app.controller("AngularController", function($scope, $http) {
		$scope.userId = 1;
		$http.get("/demoUsers/" + $scope.userId).success(function(data) {
			$scope.user = data;
		});

		$scope.userIdChanged = function() {
			if ($scope.userId == null) {
				$scope.user = 1;
				return;
			}
			$http.get("/demoUsers/" + $scope.userId).success(
				function(data) {
					$scope.user = data;
				});
		};
	});
	
	return {
	};
}();
