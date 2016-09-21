var UserController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            ordering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/guidecarmanage/search",
			columns: [
                
                { data: "classes"}, 
			    { data:"cost"},
			    { data: "salesPrice"},
			    { data: "excursionCost"}, 
			    { data: "excursionPrice"}, 
			    { data: null, orderable: false }
			],
			columnDefs: [
			               {
			                   render: function (data, type, row) {
			                	   var guideCarmanageId = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/guidecarmanage/update/" + guideCarmanageId + "' style='color:#0000E3 '>修改</a>";
			                	   link += "&nbsp;<a href='javascript: UserController.deleteCarManage(" + guideCarmanageId + ")' style='color:#0000E3 '>删除</a>";
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
		deleteCarManage: function (id) {
		if (!confirm("确认要删除吗？")) {
			return;
		}
		$.ajax({
			type: "DELETE",
			url: Web.contextPath + "/guidecarmanage/" + id,
			success: function () {
				// 刷新表格
				_dataTable.ajax.reload();
			}
		});
	}
	};
}();

$(function () {
	UserController.init();
});
