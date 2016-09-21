var EditSpotPlanController = function() {
	// Defining a Angular module
	var _app = angular.module("_app", []);

	// Defining a Angular Controller
	_app.controller("EditSpotPlanController", function($scope, $http) {
		// 根据景点获取门票类型
		$scope.loadSpotTicketTypesBySpot = function() {
			var spotId = $scope.spotId;
			if (!spotId) {
				$('#spotDetail').hide();
				$scope.spotTicketTypes = [];
				return;
			}

			$http.get("/spotstickettype/getBySpotId?spotId=" + spotId).success(function(data) {
				$scope.spotTicketTypes = data;
			});

			$.ajax({
				type : "GET",
				url : "/spot/" + spotId,
				dataType : 'json',
				success : function(data) {
					$('#spotsnamedetail').val(data.spotsname);
					$('#spotsename').val(data.spotsename);
					$('#location').val(data.location);
					$('#spotsdescription').val(data.spotsdescription);
					$('#spotssummary').val(data.spotssummary);
					$('#specialnotes').val(data.specialnotes);
					$('#clothingtips').val(data.clothingtips);
					//$('#excursionPrice').val(data.excursionPrice);
					$('#spendingtips').val(data.spendingtips);
					$('#weathertips').val(data.weathertips);
				}
			});
			$('#spotDetail').show();
		};

		// 页面初始化
		var datePlanId = $("#datePlanId").val();
		$http.get("/datePlan/" + datePlanId).success(function(datePlan) {
			if (datePlan.spotPlan) {
				$scope.spotId = datePlan.spotPlan.spot.id;
				$scope.spotTicketTypeId = datePlan.spotPlan.spotPlanTicket.spotTicketType.id;
			}
			$scope.loadSpotTicketTypesBySpot();
		});
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


//表单提交
function updateSpots() {
	var spotId = $("#spotId option:selected").val();
	$.ajax({
		type: "post",
		url: "/spots/update/" + spotId,
		data: $("#spotsForm").serialize(),
		success: function (data) {
			$('#spotDetail').hide();
		}
	});
}

function restSpots() {
	var spotId = $("#spotId option:selected").val();

	$.ajax({
		type: "GET",
		url: "/spot/" + spotId,
		dataType: 'json',
		success: function (data) {
			$('#spotsnamedetail').val(data.spotsname);
			$('#spotsename').val(data.spotsename);
			$('#location').val(data.location);
			$('#spotsdescription').val(data.spotsdescription);
			$('#spotssummary').val(data.spotssummary);
			$('#specialnotes').val(data.specialnotes);
			$('#clothingtips').val(data.clothingtips);
//                    $('#excursionPrice').val(data.excursionPrice);
			$('#spendingtips').val(data.spendingtips);
			$('#weathertips').val(data.weathertips);
		}
	});
}
