var PurposeActive = function () {
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
			ajax: Web.contextPath + "/purposeactive/search",
			columns: [
			    { data: "id", visible: false },
			    { data: null },
			    { data: "createTime" },
			  /*  { data: "image.filePath"},*/
			    { data: null }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	  /* var link = "<a href='javascript: PurposeActive.deleteCountry(" + id + ")'>删除</a>";
			                	   * */
			                	   var link = "&nbsp;<a href='/purposeactive/update/" + id + "'>修改</a>" ;
//			                	    link += "&nbsp;<a  href='javascript: SupplierActiveController.saveCountry(" + supplierActiveId + ")'>保存</a>";
			                	   return link;
			                   },
			                   targets: 3
			               },{
								
			                   render: function (data, type, row) {
			                	   var purposeactive = row.purposeactive;
			                	   var id=row.id;
			                	  var link = "<a href='/purposeactive/detail/" + id + "'>"+purposeactive+"</a>";
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

		deleteCountry: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: Web.contextPath + "/purposeactive/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		},
		
	};
}();

$(function () {
	PurposeActive.init();
});
