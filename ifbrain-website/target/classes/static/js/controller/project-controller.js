var ProjectController = function() {
	var _dataTable = null;
	var showTable = function() {
		var childId = $("#childs").val();

		var url = "/project/lslist/" + childId;

		$.get(url, function(HistoryTask) {
			console.log(JSOG.decode(HistoryTask));
			_dataTable = $("#table1").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},

				lengthChange : false,
				searching : true,
				ordering : true,
				processing : true,
				searching : false,
				destroy:true,
				data : JSOG.decode(HistoryTask),
				columns : [ {
					data : null
				}, {
					data : null
				}, {
					data : null
				}, {
					data : null
				}, {
					data : null
				} ],
				columnDefs : [ {
					render : function(data, type, row) {
						var link="";
						link="<input type='checkbox' name='test'value='"+data.id+"' /> ";
						return link;

					},
					targets : 0
				}, {
					render : function(data, type, row) {
						var link="";
						link="<input type='text' name='task' id='task' value="+data.taskName+"  required='required'  style='border:none;background-color:transparent;font-size: 18px;text-align:center;max-width:200px;' readonly='readonly' />";
						return link;

					},
					targets : 1
				},{
					render : function(data, type, row) {
						var link="";
						link="<input type='text' name='task' id='task' value="+data.child.name+"  required='required'  style='border:none;background-color:transparent;font-size: 18px;text-align:center;max-width:200px;' readonly='readonly' />";
						return link;

					},
					targets : 2
				},{
					render : function(data, type, row) {
						var link="";
						link="<input type='text' name='task' id='task' value="+data.timesAdd+"  required='required'  style='border:none;background-color:transparent;font-size: 18px;text-align:center;max-width:200px;' readonly='readonly' />";
						return link;

					},
					targets : 3
				},{
					render : function(data, type, row) {
						var link="";
						link="<input type='text' name='task' id='task' value="+data.startTime+"/"+data.taskTimes+"  required='required'  style='border:none;background-color:transparent;font-size: 18px;text-align:center;max-width:200px;' readonly='readonly' />";
						return link;
			

					},
					targets : 4
				} ]

			});
		})
	};

	return {
		init : function() {
			showTable();
		},search: function(){
			//_dataTable.destroy();
			showTable();}
	};
}();
$(function() {
	ProjectController.init();
});
