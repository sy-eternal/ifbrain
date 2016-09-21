var EditMarginPlanController = function() {
	// Defining a Angular module
	var _app = angular.module("_app", []);

	// Defining a Angular Controller
	_app.controller("EditMarginPlanController", function($scope, $http) {
		// 页面初始化
		var datePlanId = $("#datePlanId").val();
		$http.get("/datePlan/" + datePlanId).success(function(datePlan) {
			$scope.datePlan = datePlan;
			if (datePlan.marginplan) {
//				$scope.marginplan = datePlan.maiginplan;

				
				var marginprice = datePlan.marginplan.marginprice;
				var count = datePlan.marginplan.count;
				var margin = datePlan.marginplan.margin;
				
			}
		});

		// 调用供应商接口，获取酒店信息
//		$scope.retrieveHotelInfoFromSupplier = function() {
//			if ($scope.hotelPlan.supplierPriceRule.id) {
//				$.blockUI();
//				var date = $scope.datePlan.startDate;
//				var locationCode = $scope.datePlan.cityPlan.toCity.airportCode;
//				Supplier.searchHotel(date, locationCode, callbackDone, callbackFail);
//			}
//			else {
//				$scope.hotelRoomInfos = [];
//			}
//		};

		// 选择酒店房间信息
//		$scope.chooseHotelRoomInfo = function() {
//			var hotelRoomInfo = $scope.hotelRoomInfo;
//			if (!hotelRoomInfo) {
//				hotelRoomInfo = {};
//			}
//			$scope.hotelRoomInfo.hotelCostPrice = hotelRoomInfo.hotelCostPrice;
//		};
	});

	return {
		// main function to initiate the module
		init : function() {
			// 表单验证
			$("#mainForm").validate({
				highlight : function(element) {
					$(element).closest(".form-field").addClass("has-error");
				},
				success : function(label) {
					label.closest(".form-field").removeClass("has-error");
					label.closest(".form-field").addClass("has-success");
				},
				errorPlacement : function(error, element) {
					element.parent("div").append(error);
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
		}
	};
}();

$(function() {
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	QuickSidebar.init(); // init quick sidebar
	Demo.init(); // init demo features

	EditMarginPlanController.init();
});
