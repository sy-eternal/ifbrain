var HotelActivityController= function () {
	
	var _dataTable = null;

	var showTable = function() {
		var url = "/hotelActivity/search";
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
				        	   data : "city"
				           }, {
				        	   data : "hotelEngName"
				           }, {
				        	   data : "hotelChName"
				           }, {
				        	   data : "hotelClass"
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
				                        		 return data.cityName;
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
				                        		 var hotelClass=data;
				                        		 if(hotelClass==6){
				                        			 return "无星级";
				                        		 }else if(hotelClass==1){
				                        			 return "一星级";
				                        		 }else if(hotelClass==2){
				                        			 return "二星级";
				                        		 }else if(hotelClass==3){
				                        			 return "三星级";
				                        		 }else if(hotelClass==4){
				                        			 return "四星级";
				                        		 }else if(hotelClass==5){
				                        			 return "五星级";
				                        		 }
				                        		 else if(!hotelClass){
				                        			 return "";
				                        		 }
				                        		 else
				                        		 {
				                        			 return "超级";
				                        		 }
				                        	 },
				                        	 targets : 4
				                         },
				                         {
				                        	 render : function(data, type, row) {
				                        		 var hotelActivityId = row.id;
				                        		 var link = "<a href= /hotelActivity/update/"
				                        			 + hotelActivityId + ">修改</a>";
				                        		 link += "&nbsp&nbsp<a href= /hotelActivity/detail/"
				                        			  + hotelActivityId + ">查看</a>";
				                        		 link += "&nbsp&nbsp<a href=/hotelActivity/hotelRoomType/"
				                        			 + hotelActivityId + ">房间类型管理</a>";
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
