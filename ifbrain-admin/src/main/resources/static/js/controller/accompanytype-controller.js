var AccomPanyActionTypeController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: true,
            demoOrdering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/accompanytype/search",
			columns: [
			    { data: "id", visible: false },
			    { data: null },
			    { data: "createTime" },
			  /*  { data: "image.filePath" },*/
			    { data: null }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	    var id = row.id;
				                	/*var link = "&nbsp;<a href='javascript: AccomPanyActionTypeController.deleteAccomPanyType(" + id + ")'>删除</a>";*/
			                	    var link = "&nbsp;<a href='/accompanytype/update/" + id + "'>修改</a>" ;
			                	    return link;
			                   },
			                   targets: 3
			               },{
								
			                   render: function (data, type, row) {
			                	   var accompanyType = row.accompanyType;
			                	   var id=row.id;
			                	  var link = "<a href='/accompanytype/detail/" + id + "'>"+accompanyType+"</a>";
			                	  return link;
			                   },
			                   targets: 1
			               }
			           ]
		});
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

		deleteAccomPanyType: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: Web.contextPath + "/accompanytype/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	AccomPanyActionTypeController.init();
});
