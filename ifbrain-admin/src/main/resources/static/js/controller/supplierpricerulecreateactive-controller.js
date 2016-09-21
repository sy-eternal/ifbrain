var supplierController = function () {
	var _app = angular.module("_app", []);

	// Defining a Angular Controller 
	_app.controller("supplierController", function($scope, $http) {
		$scope.userId = 1;
		$http.get(Web.contextPath + "/supplierpriceruleactive/" + $scope.supplierid).success(function(data) {
			$scope.supplier = data;
		});

		$scope.supplierChanged = function() {
			$http.get(Web.contextPath + "/supplierpriceruleactive/" + $scope.supplierid).success(
				function(data) {
					$scope.supplier = data;
				});
		};
	});
	return {
	};
}();

