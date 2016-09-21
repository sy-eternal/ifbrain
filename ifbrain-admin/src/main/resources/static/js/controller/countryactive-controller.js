var SupplierActiveController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
				 url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            demoOrdering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/countryActive/search",
			columns: [
			    { data: "id", visible: false },
			    { data: "name" }
//			    { data: null, orderable: false  }
			],
			columnDefs: [/*{
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	   var link = "<a href='javascript: SupplierActiveController.deleteCountry(" + id + ")'>删除</a>";
//			                	    link += "&nbsp;<a  href='javascript: SupplierActiveController.saveCountry(" + supplierActiveId + ")'>保存</a>";
			                	   return link;
			                   },
			                   targets: 2
			               }*/
			           ]
		});
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

		deleteCountry: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: Web.contextPath + "/countryActive/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		},
	};
}();

$(function () {
	SupplierActiveController.init();
});
