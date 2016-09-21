var SpecialCarRateController=function(){
	var _dataTable = null;
	var showTable = function() {
		var rentalCarId=$("#rentalCarId").val();
		var url = "/specialCarRate/search?rentalCarId="+rentalCarId;
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
						        	   data : "specialcar"
						           }, {
						        	   data : "carratecost"
						           }, {
						        	   data : "carrateprice"
						           },{
						        	   data : null,
						        	   orderable : false
						           } ],
						           columnDefs : [
						                         {
						                        	 render : function(data, type, row) {
						                        		 if (!data) {
						                        			 return "";
						                        		 }
						                        		 return data.cartype;
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
						                        		 var specialCarRateId = row.id;
						                        		 var link = "<a href= /specialCarRate/update/" + specialCarRateId + ">修改</a>";
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

		