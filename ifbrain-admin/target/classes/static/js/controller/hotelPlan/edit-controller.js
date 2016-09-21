var EditHotelPlanController = function() {
	// Defining a Angular module
	var _app = angular.module("_app", []);

	// Defining a Angular Controller
	_app.controller("EditHotelPlanController", function($scope, $http) {
		// 页面初始化
		var datePlanId = $("#datePlanId").val();
		$http.get("/datePlan/" + datePlanId).success(function(datePlan) {
			$scope.datePlan = datePlan;
			if (datePlan.hotelPlan) {
				$scope.hotelPlan = datePlan.hotelPlan;

				$scope.retrieveHotelInfoFromSupplier();
				
				var hotelName = datePlan.hotelPlan.hotelName;
				var hotelRank = datePlan.hotelPlan.hotelRank;
				var hotelCostPrice = datePlan.hotelPlan.hotelCostPrice;
				var hotelAddress = datePlan.hotelPlan.hotelAddress;
				var hotelTel = datePlan.hotelPlan.hotelTel;
				var summary = hotelName + "，" + hotelRank + "，" + hotelCostPrice;
				$scope.hotelRoomInfo = {
					summary : summary,
					hotelName : hotelName,
					hotelRank : hotelRank,
					hotelCostPrice : hotelCostPrice,
					hotelAddress : hotelAddress,
					hotelTel : hotelTel
				};
			}
		});

		var callbackDone = function(hotelRoomInfos) {
			$scope.hotelRoomInfos = hotelRoomInfos;
			$.unblockUI();
		};

		var callbackFail = function(hotelRoomInfos) {
			$.unblockUI();
			alert("获取酒店信息失败！");
		};

		// 调用供应商接口，获取酒店信息
		$scope.retrieveHotelInfoFromSupplier = function() {
			if ($scope.hotelPlan.supplierPriceRule.id) {
				$.blockUI();
				var date = $scope.datePlan.startDate;
				var locationCode = $scope.datePlan.cityPlan.toCity.airportCode;
				Supplier.searchHotel(date, locationCode, callbackDone, callbackFail);
			}
			else {
				$scope.hotelRoomInfos = [];
			}
		};

		// 选择酒店房间信息
		$scope.chooseHotelRoomInfo = function() {
			var hotelRoomInfo = $scope.hotelRoomInfo;
			if (!hotelRoomInfo) {
				hotelRoomInfo = {};
			}
			$scope.hotelRoomInfo.hotelCostPrice = hotelRoomInfo.hotelCostPrice;
		};
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

	EditHotelPlanController.init();
});
