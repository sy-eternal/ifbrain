var InsuranceRateController=function(){
	var _dataTable = null;
	var showTable = function() {
		var insuranceActivityId=$("#insuranceActivityId").val();
//		alert(insuranceActivityId);
		var url = "/insuranceRate/search?insuranceActivityId="+insuranceActivityId;
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
						        	   data : "insuranceActivity"
						           }, {
						        	   data : "insuranceDuration"
						           }, {
						        	   data : "holderType"
						           },{
						        	   data : "insuranceRateCost"
						           },{
						        	   data :"insuranceRatePrice"
						           } ,{
						        	   data : null,
						        	   orderable : false
						           } ],
						           columnDefs : [
						                         {
						                        	 render : function(data, type, row) {
						                        		 if (!data) {
						                        			 return "";
						                        		 }
						                        		 return data.insuranceName;
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
						                        		 if (!data) {
						                        			 return "";
						                        		 }
						                        		 return data;
						                        	 },
						                        	 targets : 3
						                         },
						                         {
						                        	 render : function(data, type, row) {
						                        		 if (!data) {
						                        			 return "";
						                        		 }
						                        		 return data;
						                        	 },
						                        	 targets : 4
						                         },
						                         {
						                        	 render : function(data, type, row) {
						                        		 var activityId = row.id;
						                        		 var link = "<a href= /insuranceRate/update/"
						                        			 + activityId + ">修改</a>";
						                        		 return link;
						                        	 },
						                        	 targets : 5
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

		