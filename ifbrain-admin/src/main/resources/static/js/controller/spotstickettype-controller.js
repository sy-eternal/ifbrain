var SpotsTicketTypeController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		var spotsTicketId = $("#spotsTicketId").val();
		var spotsTicketName = $("#spotsTicketName").val();
		var spotsTicketEname = $("#spotsTicketEname").val();
		_dataTable = $("#table").DataTable({
			language: {
                url:"/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            demoOrdering: true,
			processing: true,
			serverSide: true,
			ajax:"/spotstickettype/search?spotsTicketId="+spotsTicketId,
			columns: [
			    { data: "id" , visible: false },
			    { data: "spotsname" },
			    { data: "spotsename" },
			    { data: "type" },
			    { data: "cost" },
			    { data: "price"},
			    { data: null }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	   var link = "<a href='/spotstickettype/update/" + id + "'>修改</a>" ;
			                	    link += "&nbsp;<a href='javascript: SpotsTicketTypeController.deleteSpotsTicketType(" + id + ")'>删除</a>";
			                	  
			                	    return link;
			                   },
			                   targets: 6
			               },
			               {
			                   render: function (data, type, row) {
			                	   var link =spotsTicketId ;
			                	    return link;
			                   },
			                   targets: 0
			               },
			{
                render: function (data, type, row) {
              	   var link =spotsTicketName ;
              	    return link;
                },
                targets: 1
            },
            {
                render: function (data, type, row) {
              	   var link =spotsTicketEname ;
              	    return link;
                },
                targets: 2
            }
			           ]
		});
	};
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},
		deleteSpotsTicketType: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
//				url: Web.contextPath + "/spotstickettype/" + id,
				url:"/spotstickettype/"+id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

/*$(function () {
	SpotsTicketTypeController.init();
});*/
