var InsuranceActivityController = function() {

	var _dataTable = null;

	var showTable = function() {

		//因冲突，多加一个"s"
		var url = "/insuranceActivity/searchs";
		$.get(url,function(data){

			console.log(JSOG.decode(data));

			_dataTable = $("#table").DataTable({
						language : {
							url : "/js/lib/datatables/Chinese.json"
						},
						lengthChange : false,
						searching : true,
						ordering : true,
						processing : true,
						data : JSOG.decode(data),
						columns : [
						           {
						        	   data : "supplier"
						           }, {
						        	   data : "insuranceType"
						           }, {
						        	   data : "insuranceName"
						           }, {
						        	   data : null,
						        	   orderable : false
						           } ],
						           columnDefs : [
						                         {
						                        	 render : function(data, type, row) {
						                        		 if (!data) {
						                        			 return "";
						                        		 }
						                        		 return data.cnName;
						                        	 },
						                        	 targets : 0
						                         },
						                         {
						                        	 render : function(data, type, row) {
						                        		 if (!data) {
						                        			 return "";
						                        		 }
						                        		 return data;
						                        	 },
						                        	 targets : 1
						                         },
						                         {
						                        	 render : function(data, type, row) {
						                        		 if (!data) {
						                        			 return "";
						                        		 }
						                        		 return data;
						                        	 },
						                        	 targets : 2
						                         },
						                         {
						                        	 render : function(data, type, row) {
						                        		 var activityId = row.id;
						                        		 var link = "<a href= /insuranceActivity/update/"
						                        			 + activityId + ">修改</a>";
						                        		 link += "&nbsp&nbsp<a href= /insuranceActivity/detail/"
						                        			 + activityId + ">查看</a>";
						                        		 link += "&nbsp&nbsp<a href=/insuranceActivity/insuranceRate/"
						                        			 + activityId + ">保费管理</a>";
						                        		 return link;
						                        	 },
						                        	 targets : 3
						                         } ]
					});
		})
	};
	return {
		init : function() {
			showTable();
		}
	};
}();
