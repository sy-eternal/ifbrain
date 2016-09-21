var FlightPlanController = function() {

	// Defining a Angular module
	var _app = angular.module("_app", []);

	// Defining a Angular Controller
	_app.controller("FlightPlanController", function($scope, $http) {
		$scope.datePlan = {
				cityPlan : {},
				vehiclePlan : {}
			};
			$scope._temp = {};
		var callbackDone = function(vehicleInfos) {
			
			var str=JSON.stringify(vehicleInfos);
			$('#vehicleInfos').val(str);
			$.unblockUI();
			document.getElementById('mainForm').submit();
		};

		var callbackFail = function(vehicleInfos) {
			alert("获取机票信息失败！");
			$.unblockUI();
		};
		
		
		// 根据交通方式，获取供应商价格规则
	/*	$scope.loadSupplierByVehicleType = function() {
			
			var vehicleTypeCode=1;
			var url = Web.contextPath + "/supplierPriceRule/getByVehicleTypeCode?vehicleTypeCode=" + vehicleTypeCode;
			$http.get(url).success(function(data) {
				alert("data.priceCoefficient"+data.priceCoefficient);
				$('#priceCoefficient').val(data.priceCoefficient);
			});

		
		};*/
		
		
		// 调用供应商接口，获取交通信息
		$scope.retrieveVehicleInfoFromSupplier=function() {
			$.blockUI({
				message:"<h1>Please wait a moment!</h1>"
			});
			
			var vehicleTypeCode=1;
			var url = Web.contextPath + "/supplierPriceRule/getByVehicleTypeCode?vehicleTypeCode=" + vehicleTypeCode;
			$http.get(url).success(function(data) {
				$('#priceCoefficientID').val(data[0].id);
			});
			
			
			var tripeType="";
			 tripeType=$("input[name='xingcheng']:checked").val();
			 
			 
		if(tripeType=='单程'){
	//单程
			if ($scope.datePlan.startDate && $scope.datePlan.cityPlan.fromCity && $scope.datePlan.cityPlan.toCity && $scope.ngmodelCabin) {
				var date = $scope.datePlan.startDate;
				var datetimeStr=$scope.datePlan.startTime;
				if(datetimeStr.length>8){
					 datetimeStr=datetimeStr.replace("y","");
				}
				 //返程日期
				var endate=$scope.datePlan.returnDate;
				if(endate==""|| endate==null || endate==undefined){
					$('#returnDate').val("单程");
				}else{
					 $('#returnDate').val(endate);
				}
				//返程时间  
				var returndatetime=$scope.datePlan.returnTime;
				if(returndatetime==""|| returndatetime==null || returndatetime==undefined){
					 $("#returndatetime").val("单程");
				}
				else{
				      if(returndatetime.length>8){
					returndatetime=returndatetime.replace("y","");
				      }
				   $("#returndatetime").val(returndatetime);
				}
				   
				
				var url = Web.contextPath + "/cities/" + $scope.datePlan.cityPlan.fromCity;
				$http.get(url).success(
					function(fromCity) {
						var originLocationCode = fromCity.airportCode;
						url = Web.contextPath + "/cities/" + $scope.datePlan.cityPlan.toCity;
						$http.get(url).success(
							function(toCity) {
							
								var destinationLocationCode = toCity.airportCode;
								// 获取仓位信息
								var cabin = $("#selCabin").val();
								
								Supplier.bargainFinderMax(date, datetimeStr,originLocationCode, destinationLocationCode, cabin,
									callbackDone, callbackFail);
							});
					});
				
				
				
				
			}
			else{
				$('#vehicleInfos').val("");
			}
			
		}else{
		//返程	
			if ($scope.datePlan.startDate && $scope.datePlan.cityPlan.fromCity && $scope.datePlan.cityPlan.toCity && $scope.datePlan.returnDate && $scope.datePlan.returnTime && $scope.ngmodelCabin) {
				var date = $scope.datePlan.startDate;
				var datetimeStr=$scope.datePlan.startTime;
				
				var returndate = $scope.datePlan.returnDate;
				var returndatetimeStr=$scope.datePlan.returnTime;
				
				if(datetimeStr.length>8){
					 datetimeStr=datetimeStr.replace("y","");
				}
				
				
				if(returndatetimeStr.length>8){
					returndatetimeStr=returndatetimeStr.replace("y","");
				}
				var url = Web.contextPath + "/cities/" + $scope.datePlan.cityPlan.fromCity;
				$http.get(url).success(
					function(fromCity) {
						var originLocationCode = fromCity.airportCode;
						url = Web.contextPath + "/cities/" + $scope.datePlan.cityPlan.toCity;
						$http.get(url).success(
							function(toCity) {
								var destinationLocationCode = toCity.airportCode;
								// 获取仓位信息
								var cabin = $("#selCabin").val();
								SupplierReturn.bargainFinderMax(date, datetimeStr,returndate,returndatetimeStr,originLocationCode, destinationLocationCode, cabin,
									callbackDone, callbackFail);
							});
					});
			}
			else{
				$('#vehicleInfos').val("");
			}
		}
		
		};
	
	});

	return {
		// main function to initiate the module
		init : function() {
			// 表单验证
			
		}
	};
}();


$(function () {
	FlightPlanController.init();
});
