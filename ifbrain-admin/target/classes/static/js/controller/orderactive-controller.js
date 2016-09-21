//var OrderActiveController = function() {
//	var _dataTable = null;
//
//	var showTable = function() {
//		_dataTable = $("#table")
//				.DataTable(
//						{
//							language : {
//								url : Web.contextPath
//										+ "/js/lib/datatables/Chinese.json"
//							},
//							lengthChange : false,
//							searching : true,
//							ordering : true,
//							processing : true,
//							serverSide : true,
//							ajax : Web.contextPath + "/orderActive/search",
//							columns : [  {
//								data : "createTime"
//							}, {
//								data : "orderNumber"
//							}, {
//								data : "traveler.firstName",
//							}, {
//								data : "traveler.email"
//							}, {
//								data : "traveler.mobile"
//							}, {
//								data : "activityCode"
//							}],
//							columnDefs: [ 
//{
//    render: function (data, type, row) {
// 	  var firstName = row. traveler.firstName;
//	      var lastName = row.traveler.lastName; 
//	        return firstName + lastName;
//    },
//    targets: 2
//    }]
//						});
//	};
//
//	return {
//		// main function to initiate the module
//		init : function() {
//			showTable();
//		},
//	};
//}();
//
//$(function() {
//	OrderActiveController.init();
//});


var OrderActiveController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/orderActive/search";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: true,
				processing: true,
				data : JSOG.decode(data),
				columns : [  {
					data : "createTime"
				}, {
					data : "orderNumber"
				}, {
					data : "traveler.firstName",
				}, {
					data : "traveler.email"
				}, {
					data : "traveler.mobile"
				}, {
					data : "activityCode"
				}],
				columnDefs: [ {
					 render: function (data, type, row) {
					 	  var firstName = row. traveler.firstName;
						      var lastName = row.traveler.lastName; 
						        return firstName + lastName;
					    },
					    targets: 2
	               }]
			});
		})
		};

						return {
							// main function to initiate the module
							init : function() {
								showTable();
							},
						
						};
					}();


