var ExchangeRateController = function () {
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
			ajax: Web.contextPath + "/exchangerate/search",
			columns: [
			    { data: "sellingcurrency"},
			    { data: "buyingcurrency" },
			    { data: "exchangerate" },
			    { data:"createTime"},
			    { data: null }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/exchangerate/update/" + id + "'>修改</a>";return link;
			                   },
			                   targets: 4
			               }
			           ]
		});
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

		updateexchangerate: function (id) {
			
			$.ajax({
				type: "UPDATE",
				url: Web.contextPath + "/exchangerate/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	ExchangeRateController.init();
});
