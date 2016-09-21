var MyInformationController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/myinformation/searchbabylist";
		$.get(url,function(childlist) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: false,
	            ordering: false,
				processing: true,
				data : JSOG.decode(childlist),
				columns: [
				          	{ data: null },	
						    { data: null },
						    { data: null },
						    { data: null },
						    { data: null }
						],
				columnDefs : [{
                    render : function(data, type, row) {
                		var name = row.name;
                		var id=row.id;
                		var link="<a href='/myinformation/detail/" + id + "' style='display:block;max-width:80px;'>"+name+"</a>";
                		return link;
                	},
                	targets : 0
                },{
                    render : function(data, type, row) {
                		var id = row.id;
                		var link="";
                		link="<input  type='checkbox' name='check'  id='check"+id+"' onclick='javascript: MyInformationController.changecheck("+id+")'' style='max-width:40px;display:none'/>"+
                		    "<input type='hidden' name='childId'  id='childId"+id+"' value='"+row.id+"'/>";
                		return link;
                	},
                	targets : 4
                },{
                    render : function(data, type, row) {
                    	var id = row.id;
                		var link="";
                		link="<input  type='text' name='age' id='age"+id+"' value="+row.age+"  required='required'  style='border:none;background-color:transparent;max-width:80px;' readonly='readonly' />";
                		return link;
                	},
                	targets : 1
                },{
                    render : function(data, type, row) {
                    	var id = row.id;
                		var link="";
                		link="<input  type='text' name='birth' id='birth"+id+"' value="+row.birth+"  required='required'  style='border:none;background-color:transparent;text-align:center;max-width:110px;' readonly='readonly' />";
                		return link;
                	},
                	targets : 2
                },{
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	  var link = "";
	                	    link += "&nbsp;<a href='/myinformation/update/" + id + "'>修改</a>" ;
	                	   link += "&nbsp;<a href='javascript:MyInformationController.deleteList(" + id + ")'>删除</a>";
	                	   
	                	   return link;
	                   },
	                   targets: 3
	               }]
			});
		})
	};
	
	return {
		init : function () {
			showTable();
			
			
		},
		deleteList: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/myinformation/" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		},changecheck: function(id){
			var childs = $("#childs").val();
			var births =   $("#births").val();
			var ages = $("#ages").val();
			if($("#check"+id).prop("checked")){		
				var childId = $("#childId"+id).val();
				var birth = $("#birth"+id).val();
				var age = $("#age"+id).val();
				
				childs+=childId+",";
				births+=birth+",";
				ages+=age+",";
			
				$("#childs").val(childs);
				$("#births").val(births);	
				$("#ages").val(ages);
			}else{
				var childId = $("#childId"+id).val();
				var birth = $("#birth"+id).val();
				var age = $("#age"+id).val();
				
				$("#childs").val(childId);
				$("#births").val(birth);
				$("#ages").val(age);
				
				if (childs.indexOf(id+",") > 0){
					var idIndex = childs. indexOf(","+id+",")+1;
					var n=(childs.substr(0, idIndex).split(",")).length-1;
					var index1;
					var predex1;
					var re1 = /,/g;
					var count1 = 0;
					while ((arr = re1.exec(childs)) != null) {
						count1++;
						if (count1 == n) {
							predex1 = arr.index;
						}
						if (count1 == n+1) {
							index1 = arr.index;
							break;
						}
					}

					var index ;
					var predex;
					var re = /,/g;
					var count = 0;
					while ((arr = re.exec(births)) != null) {
						count++;
						if (count == n) {
							predex = arr.index;
						}
						if (count == n+1) {
							index = arr.index;
							break;
						}
					}
					
					
					
					var index3 ;
					var predex3;
					var re = /,/g;
					var count = 0;
					while ((arr = re.exec(ages)) != null) {
						count++;
						if (count == n) {
							predex3 = arr.index;
						}
						if (count == n+1) {
							index3 = arr.index;
							break;
						}
					}

					childs = childs.substr(0,predex1) + childs.substr(index1);
					births = births.substr(0,predex) + births.substr(index);
					ages = ages.substr(0,predex3) + ages.substr(index3);
					
					
					ages=ages.replace(","+id+",",",");
					births=births.replace(","+id+",",",");
					childs=childs.replace(","+id+",",",");
					

				}
				if (childs.indexOf(id+",") == 0){
					var index =births.indexOf(",") + 1;
					var index3 =ages.indexOf(",") + 1;
					var index1 =childs.indexOf(",") + 1;
				
					births = births.substr(index);
					ages = ages.substr(index3);
					childs = childs.substr(index1);
					
					childs=childs.replace(id+",",""); 
					ages=ages.replace(id+",",""); 
					births=births.replace(id+",",""); 
				
					
				}
				$("#childs").val(childs);
				$("#births").val(births);
				$("#ages").val(ages);
			}
		},
		
	};
}();
$(function () {
	MyInformationController.init();

});
