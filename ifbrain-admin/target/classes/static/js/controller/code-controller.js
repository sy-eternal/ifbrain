var CodeController=function(){
	var _dataTable = null;
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
	              url: "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: true,
            ordering: true,
			processing: true,
			serverSide: true,
			ajax: "/code/search",
			columns: [
					    { data: "id", visible: false },
					    { data: "type" },
					    { data: "classs" },
					    { data:	"value"},
					    { data: "createTime"},
					    { data: null, orderable: false }
					],
			columnDefs:[{
				render: function (data, type, row) {
					var codeId=row.id;
					var link= "<a href= /code/update/"+codeId+">修改</a>";
					  link += "&nbsp;<a href='javascript: CodeController.deleteCode(" + codeId + ")'>删除</a>";
               	   return link;
                  },
                  targets: 5
				}
			]
		});
	};
	return {
		init : function () {
			showTable();
		},
		deleteCode: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/code/"+id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();


$(function () {
	CodeController.init();
});