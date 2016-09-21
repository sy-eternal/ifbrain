/*var SupplierController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: true,
            ordering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/supplier/search",
			columns: [
			    {data: "id", visible: false },
			    { data: "cnName" },
			    { data: "code" },
			    { data: "status" },
			    { data: "supplierStatus" },
			    { data: null, orderable: false }
			],
				columnDefs: [ 
				              {
					render : function(data, type, row) {
						var classs = "";
						for (var i = 0; i < data.length; ++i) {
							var code = data[i];
							classs += "," + code.classs;
						}
						if (classs != "") {
							classs = classs.substring(1);
						}
						return classs;
					},
					targets : 2

				},{
								render: function (data, type, row) {
								 var supplierId = row.id;
				                 var link = "<a href='" + Web.contextPath + "/supplier/detail/" + supplierId + "'>" + data + "</a>";
								 return link;
								},
								 targets: 1
								},
				              { 
							   render: function (data, type, row) {
			             	   var status = "";
			             	   if (data == 1) {
			             		   status = "是";
			             	   }
			             	   else if (data == 2) {
			             		   status = "否";
			             	   }
			             	   return status;
			                },
			                targets: 3
			               },
			             {
			                   render: function (data, type, row) {
			                	   var status = "";
			                	   if (data == 1) {
			                		   status = "是";
			                	   }
			                	   else if (data == 2) {
			                		   status = "否";
			                	   }
			                	   return status;
			                   },
			                   targets: 2
			               },
			               {
			                   render: function (data, type, row) {
			                	   var supplierStatus = "";
			                	   if (data == 1) {
			                		   supplierStatus = "激活";
			                	   }
			                	   else if (data == 2) {
			                		   supplierStatus = "暂停";
			                	   }
			                	   return supplierStatus;
			                   },
			                   targets: 4
			               },
			               {
			                   render: function (data, type, row) {
			                	   var supplierId = row.id;
			                	   var status=row.status;
			                	   var link = "<a href='" + Web.contextPath + "/supplier/update/" + supplierId + "'>修改</a>";
			                	   var supplierStatus = row.supplierStatus;
			                	   if (supplierStatus == 1) {
				                	   link += "&nbsp;<a href='javascript: SupplierController.freeze(" + supplierId + ")'>冻结</a>";
			                	   }
			                	   else {
				                	   link += "&nbsp;<a href='javascript: SupplierController.active(" + supplierId + ")'>激活</a>";
			                	   }
			                		   link += "&nbsp;<a href='" + Web.contextPath + "/supplier/supplierpriceruleactive/"+supplierId+"'>类型价格管理</a>"
			                	   return link;
			                   },
			                   targets: 5
			               }
			              
			           ]
		});
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},
		// 冻结
		freeze: function (id) {
			if (!confirm("确认要冻结吗？")) {
				return;
			}
			$.ajax({
				type: "PUT",
				url: Web.contextPath + "/supplier/" + id + "/freeze",
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		},
		// 激活
		active: function (id) {
			if (!confirm("确认要激活吗？")) {
				return;
			}
			$.ajax({
				type: "PUT",
				url: Web.contextPath + "/supplier/" + id + "/active",
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	SupplierController.init();
});
*/



var SupplierController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/supplier/search";
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
				columns: [
						    {data: "id", visible: false },
						    { data: "cnName" },
						    { data: "code" },
						    { data: "status" },
						    { data: "supplierStatus" },
						    { data: null, orderable: false }
						],
						columnDefs: [ 
						              {
							render : function(data, type, row) {
								var classs = "";
								for (var i = 0; i < data.length; ++i) {
									var code = data[i];
									classs += "," + code.classs;
								}
								if (classs != "") {
									classs = classs.substring(1);
								}
								return classs;
							},
							targets : 2

						},{
										render: function (data, type, row) {
										 var supplierId = row.id;
						                 var link = "<a href='" + Web.contextPath + "/supplier/detail/" + supplierId + "'>" + data + "</a>";
										 return link;
										},
										 targets: 1
										},
						              { 
									   render: function (data, type, row) {
					             	   var status = "";
					             	   if (data == 1) {
					             		   status = "是";
					             	   }
					             	   else if (data == 2) {
					             		   status = "否";
					             	   }
					             	   return status;
					                },
					                targets: 3
					               },
					             {
					                   render: function (data, type, row) {
					                	   var status = "";
					                	   if (data == 1) {
					                		   status = "是";
					                	   }
					                	   else if (data == 2) {
					                		   status = "否";
					                	   }
					                	   return status;
					                   },
					                   targets: 2
					               },
					               {
					                   render: function (data, type, row) {
					                	   var supplierStatus = "";
					                	   if (data == 1) {
					                		   supplierStatus = "激活";
					                	   }
					                	   else if (data == 2) {
					                		   supplierStatus = "暂停";
					                	   }
					                	   return supplierStatus;
					                   },
					                   targets: 4
					               },
					               {
					                   render: function (data, type, row) {
					                	   var supplierId = row.id;
					                	   var status=row.status;
					                	   var link = "<a href='" + Web.contextPath + "/supplier/update/" + supplierId + "'>修改</a>";
					                	   var supplierStatus = row.supplierStatus;
					                	   if (supplierStatus == 1) {
						                	   link += "&nbsp;<a href='javascript: SupplierController.freeze(" + supplierId + ")'>冻结</a>";
					                	   }
					                	   else if(supplierStatus == 2){
						                	   link += "&nbsp;<a href='javascript: SupplierController.active(" + supplierId + ")'>激活</a>";
					                	   }
					                		   link += "&nbsp;<a href='" + Web.contextPath + "/supplier/supplierpriceruleactive/"+supplierId+"'>类型价格管理</a>"
					                	   return link;
					                   },
					                   targets: 5
					               }]
			});
		})
	};
			return {
				// main function to initiate the module
				init : function () {
					showTable();
				},
				// 冻结
				freeze: function (id) {
					if (!confirm("确认要冻结吗？")) {
						return;
					}
					$.ajax({
						type: "PUT",
						url: Web.contextPath + "/supplier/" + id + "/freeze",
						success: function () {
							// 刷新表格
							alert("success");
							window.location.reload();
						}
					});
				},
				// 激活
				active: function (id) {
					if (!confirm("确认要激活吗？")) {
						return;
					}
					$.ajax({
						type: "PUT",
						url: Web.contextPath + "/supplier/" + id + "/active",
						success: function () {
							// 刷新表格
							window.location.reload();
						}
					});
				}
			};
		}();
