var ChildManageController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/classes/search";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		},
	};
}();

$(function(){
	$('a').click(function(){
		var childid=$(this).find("input[name='childid']").val();
		var courselevel=$(this).find("input[name='courselevel']").val();
		var ordinalnumber=$(this).find("input[name='ordinalnumber']").val();
		window.location.href="/classes/showchildifbrainchart?childid="+childid+"&courselevel="+courselevel+"&ordinalnumber="+ordinalnumber;
	});
});
