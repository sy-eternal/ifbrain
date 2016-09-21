var ThemeController = function () {
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
			ajax: Web.contextPath + "/theme/search",
			columns: [
			    { data: "id", visible: false },
			    { data: null },
			    { data: "createTime" },
			 /*   { data: "image.filePath" },*/
			    { data: null }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	 /* var link = "&nbsp;<a href='javascript: ThemeController.deleteTheme(" + id + ")'>删除</a>";
			                	  * */
			                	   var link = "&nbsp;<a href='/theme/update/" + id + "'>修改</a>" ;
			                	   return link;
			                   },
			                   targets: 3
			               }
			,  {
         	   render: function (data, type, row) {
              	   var id = row.id;
         		   var theme=row.theme;
              	   var link = "&nbsp;<a href='/theme/detail/" + id + "'>"+theme+"</a>";
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

		deleteTheme: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: Web.contextPath + "/theme/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	ThemeController.init();
});
