var CarouselController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/material/searchcarouse";
		//var url ="";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true,
				data : JSOG.decode(data),
				columns: [
						    { 
			    	         data: "id",visible: false 
			               },
						    { 
						    	data: "fileName"
						    },
						   { 
						    	data: "createTime"
						    
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: CarouselController.up(" + id + ")'>上移</a>"
	                	   link +="&nbsp;<a href='javascript: CarouselController.down(" + id + ")'>下移</a>";
	                	   link +="&nbsp;<a href='javascript: CarouselController.materialdelete(" + id + ")'>删除</a>";
	                	   return link;
	                   },
	                   targets: 3
	               }
	            ]
			});
		})
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},
		up: function (id) {
			$.ajax({
				type: "POST",
				url: "/material/up?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		},
		down: function (id) {
			$.ajax({
				type: "POST",
				url: "/material/down?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		},
		materialdelete : function (id){
			$.ajax({
				type: "POST",
				url: "/material/materialdelete?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

/*$(function () {
	ChildController.init();
});*/