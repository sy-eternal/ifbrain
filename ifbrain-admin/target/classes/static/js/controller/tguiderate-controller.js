var InsuranceActivityController = function() {
 
	var _dataTable = null;

	var showTable = function() {
		var guideactivity=$("#guideactivity").val();
		//alert(guideactivity);
		var url = "/guideactivity/searchs?guideactivityid="+guideactivity;
		$.get(url,function(data){
			
			console.log(JSOG.decode(data));

			_dataTable = $("#table").DataTable({
						language : {
							url : "/js/lib/datatables/Chinese.json"
						},
						lengthChange : false,
						searching : false,
						ordering : true,
						processing : true,
						data : JSOG.decode(data),
						columns : [
						           {
						        	   data : "guideType"
						           }, {
						        	   data : "guideRateCost"
						           }, {
						        	   data : "guideRatePrice"
						           },{
						        	   data : "updateTime" 
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
						                        		 return data;
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
						                        		 var activityId = row.id;
						                        		 var link = "<a href= /guideactivity/updatetguiderate/"
						                        			 + activityId + ">修改</a>";
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
		}
	};
}();
