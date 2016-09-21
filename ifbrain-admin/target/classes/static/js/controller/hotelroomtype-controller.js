var HotelRoomTypeController=function(){
	var _dataTable=null;
	var showTable=function(){
		var hotelActivityId=$("#hotelActivityId").val();
//		alert(hotelActivityId);
		var url = "/hotelRoomType/search?hotelActivityId="+hotelActivityId;
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
						        	   data : "hotelActivity"
						           }, {
						        	   data : "hotelActivity"
						           }, {
						        	   data : "roomType"
						           },{
						        	   data : "perNightCost"
						           },{
						        	   data :"perNightPrice"
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
						                        		 return data.hotelChName;
						                        	 },
						                        	 targets : 0
						                         },
						                         {
						                        	 render : function(data, type, row) {
						                        		 if (!data) {
						                        			 return "";
						                        		 }
						                        		 return data.hotelEngName;
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
						                        		 var hotelRoomTypeId = row.id;
						                        		 var link = "<a href= /hotelRoomType/update/"
						                        			 + hotelRoomTypeId + ">修改</a>";
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

		
