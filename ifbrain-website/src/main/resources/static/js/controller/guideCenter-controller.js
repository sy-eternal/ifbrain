var GuideCentersController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/guidecenter/search";
		$.get(url,function(data) {
			console.log(JSOG.decode(data));
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
							{data : "guidetype"},
							{data : "guideordernum"}, 
							{data : "createtime"},
							{data : "startdate"},
							{data : "orderstatus"},
							{ data: null, orderable: false,defaultContent : ""}
						],
						columnDefs : [{
			            	render: function (data, type, row) {
			            		var orderstatus = "";
			            		 if (data == 1) {
			            			 orderstatus = "未结算";
			            		}
			            		else if (data == 2) {
			            			orderstatus = "已结算";
			            		}
			            		
			            		return orderstatus;
			            	},
			            	targets: 4
			            },{
							
			                   render: function (data, type, row) {
			                	   var id=row.id;
			                	  var link = "<a href='/guidecenter/detail/" + id + "'>详情</a>";
			                	  return link;
			                   },
			                   targets: 5
			               }
						]
			});
		})
	
	};
	return {
		init : function () {
			showTable();
		},
		deleteGuideOrder : function(id) {
		if (!confirm("确认要删除吗？")) {
			return;
		}
		$.ajax({
			type : "DELETE",
			url : "/guidecenter/" + id,
			success : function() {
				// 刷新表格
				_dataTable.ajax.reload();
			}
		});
	}
	};
}();
$(function () {
	GuideCentersController.init();
});

