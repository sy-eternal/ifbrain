var WorkTaskListController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/myinformation/searchworktasklists";
		$.get(url,function(worktasklists) {

			console.log(JSOG.decode(worktasklists));
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: true,
				processing: true,
				data : JSOG.decode(worktasklists),
				columns: [
				          { data: null },
						    { data: null },
						    { data: null },
						    { data: null },
						    { data: null }
						 
						],
						columnDefs : [{
		                    render : function(data, type, row) {
		                		var id = row.id;
		                		var link="";
		                		link="<input  type='checkbox' name='check'  id='check"+id+"' data-toggle='modal' data-target='#choosetimes' style='text-align:center'   onclick='javascript: WorkTaskListController.changecheck("+id+")''/>"+
		                		    "<input type='hidden' name='childId'  id='childId"+id+"' value='"+row.id+"'/>";
		                		return link;
		                	},
		                	targets : 0
		                },{
		                    render : function(data, type, row) {
		                    	var id = row.id;
		                		var link="";
		                		link="<input  type='text' name='name' id='name"+id+"' value="+row.name+"  required='required'  style='border:none;background-color:transparent;text-align:center' readonly='readonly' />";
		                		return link;
		                	},
		                	targets : 1
		                },
		                {
		                    render : function(data, type, row) {
		                    	var id = row.id;
		                		var link="";
		                		link="<input  type='number' class='times' value='0' name='times' min='1'  id='times"+id+"'  onblur='javascript: WorkTaskListController.changeblur("+id+")''      required='required' />";
		                		return link;
		                	},
		                	targets : 2
		                },
		                {
		                    render : function(data, type, row) {
		                		var id = row.id;
		                		var link="";
		                		link="<input  type='text' value='1'  name='commodityid'  id='commodityid"+id+"'  required='required' style='display:none;' />";
		                		link+="<div style='float:left；display:none' id='div2"+id+"'>  <span id='stars1"+id+"'  class='glyphicon glyphicon-star  text-warning' aria-hidden='true'  data-toggle='tooltip' data-placement='top' title='简单' style='margin-left:0px;display:none'  onclick='javascript: WorkTaskListController.getStar("+id+",1)'></span>" +
                				"<span  id='stars2"+id+"'  class='glyphicon glyphicon-star-empty  text-warning' aria-hidden='true'  data-toggle='tooltip' data-placement='top' title='一般'   onclick='javascript: WorkTaskListController.getStar("+id+",2)' style='display:none'></span>" +
                				"<span id='stars3"+id+"'   class='glyphicon glyphicon-star-empty  text-warning' aria-hidden='true' data-toggle='tooltip' data-placement='top' title='困难' onclick='javascript: WorkTaskListController.getStar("+id+",3)' style='display:none'></span></div>"
                				/*link+="<input id='sssss"+id+"' type='text' style='border:none;background-color:transparent;width:40px;' readonly='readonly' value='简单'/>"*/
                				return link;
		                	},
		                	targets : 4
		                },{
		                    render : function(data, type, row) {
		                		var id = row.id;
		                		var link="";
		                		link="<input  type='number' min='1' value='1'  name='everytimesmoney'  id='everytimesmoney"+id+"'  required='required'  style='display:none;' />";
		                		link+="<div id='div1"+id+"'> <span id='money1"+id+"' style='display:block;width:20px;height:20px;float:left;margin-left:10px;'  class='jinbi' aria-hidden='true'  data-toggle='tooltip' data-placement='top' title='一代币'   onclick='javascript: WorkTaskListController.getMoney("+id+",1)'></span>" +
                				"<span  id='money2"+id+"' style='display:block;width:20px;height:20px;float:left;'  class='jinbi1' aria-hidden='true'  data-toggle='tooltip' data-placement='top' title='二代币'   onclick='javascript: WorkTaskListController.getMoney("+id+",2)'></span>" +
                				"<span id='money3"+id+"' style='display:block;width:20px;height:20px;float:left;'  class='jinbi1' aria-hidden='true' data-toggle='tooltip' data-placement='top' title='三代币'  onclick='javascript: WorkTaskListController.getMoney("+id+",3)'></span></div>";
		                		/*link+="<input id='ddddddd"+id+"'  type='text' style='border:none;background-color:transparent;width:40px;' readonly='readonly' value='一代币'/>"*/
		                		return link;
		                	},
		                	targets : 3
		                }]
				
			});
		})
	};
	
	return {
		init : function () {
			showTable();
			
		},getStar:function(id,value){
			$("#commodityid"+id+"").val("");
			$("#commodityid"+id+"").val(value);
			if($("#commodityid"+id).val()=="1"){
			/*	$("#sssss"+id+"").val("简单");*/
				$("#stars1"+id+"").removeClass("glyphicon glyphicon-star-empty");
				$("#stars1"+id+"").addClass("glyphicon glyphicon-star");
				
				
				$("#stars3"+id+"").removeClass("glyphicon glyphicon-star");
				$("#stars2"+id+"").removeClass("glyphicon glyphicon-star");
				$("#stars2"+id+"").addClass("glyphicon glyphicon-star-empty");
				$("#stars3"+id+"").addClass("glyphicon glyphicon-star-empty");
			}else if($("#commodityid"+id).val()=="2"){
				/*$("#sssss"+id+"").val("一般");*/
				$("#stars1"+id+"").removeClass("glyphicon glyphicon-star-empty");
				$("#stars2"+id+"").removeClass("glyphicon glyphicon-star-empty");
				$("#stars1"+id+"").addClass("glyphicon glyphicon-star");
				$("#stars2"+id+"").addClass("glyphicon glyphicon-star");
				
				
				$("#stars3"+id+"").removeClass("glyphicon glyphicon-star");
				$("#stars3"+id+"").addClass("glyphicon glyphicon-star-empty");
				
			}else if($("#commodityid"+id).val()=="3"){
				/*$("#sssss"+id+"").val("困难");*/
				$("#stars1"+id+"").removeClass("glyphicon glyphicon-star-empty");		
				$("#stars2"+id+"").removeClass("glyphicon glyphicon-star-empty");
				$("#stars3"+id+"").removeClass("glyphicon glyphicon-star-empty");
				$("#stars1"+id+"").addClass("glyphicon glyphicon-star");
				$("#stars2"+id+"").addClass("glyphicon glyphicon-star");
				$("#stars3"+id+"").addClass("glyphicon glyphicon-star");
			
			}
			$("#span1").tooltip({
			    trigger:'hover',
			    html:true,
			    title:'简单'});
		},getMoney:function(id,value){
			$("#everytimesmoney"+id+"").val("");
			$("#everytimesmoney"+id+"").val(value);
			if($("#everytimesmoney"+id).val()=="1"){
//			/*	$("#ddddddd"+id+"").val("一代币");*/
				$("#money1"+id+"").removeClass("jinbi");
				$("#money1"+id+"").addClass("jinbi");
				$("#money2"+id+"").removeClass("jinbi");
				$("#money3"+id+"").removeClass("jinbi");
				$("#money2"+id+"").addClass("jinbi1");
			
				
				$("#money3"+id+"").addClass("jinbi1");
				
			}else if($("#everytimesmoney"+id).val()=="2"){
				/*$("#ddddddd"+id+"").val("二代币");*/
				$("#money1"+id+"").removeClass("jinbi1");
				$("#money2"+id+"").removeClass("jinbi1");
				$("#money1"+id+"").addClass("jinbi");
				$("#money2"+id+"").addClass("jinbi");
				$("#money3"+id+"").removeClass("jinbi");
				$("#money3"+id+"").addClass("jinbi1");
				
			}else if($("#everytimesmoney"+id).val()=="3"){
			/*	$("#ddddddd"+id+"").val("三代币");*/
				$("#money1"+id+"").removeClass("jinbi1");		
				$("#money2"+id+"").removeClass("jinbi1");
				$("#money3"+id+"").removeClass("jinbi1");
				$("#money1"+id+"").addClass("jinbi");
				$("#money2"+id+"").addClass("jinbi");
				$("#money3"+id+"").addClass("jinbi");
			}
			$("#span1").tooltip({
			    trigger:'hover',
			    html:true,
			    title:'简单'});
		},
		changeblur:function(id){	
			if($("#times"+id).val()<=0){
				
				$("#check"+id).attr('disabled',true);	
			
			}else{
				
				$("#check"+id).attr('disabled',false);
			
			}
		},changecheck: function(id){
				if($("#times"+id).val()<=0){
				/*	$("#cishudialog").css("display","block");*/
				alert("请填写完成的次数")	
				$("#check"+id).attr('checked',false);
			}else{
				$("#cishudialog").css("display","none");
			$("#times"+id).attr('disabled',true);	
			$("#commodityid"+id).attr('disabled',true);	
			$("#everytimesmoney"+id).attr('disabled',true);	
			
			var childs = $("#childs").val();
			var timess =   $("#timess").val();
			var commodityids = $("#commodityids").val();
			var names = $("#names").val();
			var everytimesmoneys = $("#everytimesmoneys").val();
			if($("#check"+id).prop("checked")){	
			$("#stars1"+id).attr('onclick',false);
			$("#stars2"+id).attr('onclick',false);
			$("#stars3"+id).attr('onclick',false);
			$("#money1"+id).attr('onclick',false);
			$("#money2"+id).attr('onclick',false);
			$("#money3"+id).attr('onclick',false);
				var childId = $("#childId"+id).val();
				var times = $("#times"+id).val();
				var commodityid = $("#commodityid"+id).val();
				var name = $("#name"+id).val();
				var everytimesmoney = $("#everytimesmoney"+id).val();
				
				childs+=childId+",";
				timess+=times+",";
				names+=name+",";
				commodityids+=commodityid+",";
				everytimesmoneys+=everytimesmoney+",";
				$("#childs").val(childs);
				$("#timess").val(timess);
				$("#commodityids").val(commodityids);
				$("#names").val(names);
				$("#everytimesmoneys").val(everytimesmoneys);
				
			}else{		
				$("#times"+id).attr('disabled',false);	
				$("#commodityid"+id).attr('disabled',false);	
				$("#everytimesmoney"+id).attr('disabled',false);
				$("#stars1"+id).attr("onclick","javascript:WorkTaskListController.getStar("+id+",1)");
				$("#stars2"+id).attr("onclick","javascript:WorkTaskListController.getStar("+id+",2)");
				$("#stars3"+id).attr("onclick","javascript:WorkTaskListController.getStar("+id+",3)");
				$("#money1"+id).attr("onclick","javascript:WorkTaskListController.getMoney("+id+",1)");
				$("#money2"+id).attr("onclick","javascript:WorkTaskListController.getMoney("+id+",2)");
				$("#money3"+id).attr("onclick","javascript:WorkTaskListController.getMoney("+id+",3)");
				
				var childId = $("#childId"+id).val();
				var times = $("#times"+id).val();
				var commodityid = $("#commodityid"+id).val();
				var name = $("#name"+id).val();
				$("#childIds").val(childId);
				$("#timess").val(times);
				$("#commodityids").val(commodityid);
				$("#names").val(name);
				$("#everytimesmoneys").val(everytimesmoney);
				if (childs.indexOf(id+",") > 0){
					var idIndex = childs. indexOf(","+id+",")+1;
					var n=(childs.substr(0, idIndex).split(",")).length-1;
					var index1;
					var predex1;
					var re1 = /,/g;
					var count1 = 0;
					while ((arr1 = re1.exec(childs)) != null) {
						count1++;
						if (count1 == n) {
							predex1 = arr1.index;
						}
						if (count1 == n+1) {
							index1 = arr1.index;
							break;
						}
					}

					var index ;
					var predex;
					var re = /,/g;
					var count = 0;
					while ((arr = re.exec(timess)) != null) {
						count++;
						if (count == n) {
							predex = arr.index;
						}
						if (count == n+1) {
							index = arr.index;
							break;
						}
					}
					
					var index2 ;
					var predex2;
					var re2 = /,/g;
					var count2 = 0;
					while ((arr2 = re2.exec(commodityids)) != null) {
						count2++;
						if (count2 == n) {
							predex2 = arr2.index;
						}
						if (count2 == n+1) {
							index2 = arr2.index;
							break;
						}
					}
					
					var index3 ;
					var predex3;
					var re3 = /,/g;
					var count3 = 0;
					while ((arr3 = re3.exec(names)) != null) {
						count3++;
						if (count3 == n) {
							predex3 = arr3.index;
						}
						if (count3 == n+1) {
							index3 = arr3.index;
							break;
						}
					}

					
					var index4 ;
					var predex4;
					var re4 = /,/g;
					var count4 = 0;
					while ((arr4 = re4.exec(everytimesmoneys)) != null) {
						count4++;
						if (count4 == n) {
							predex4 = arr4.index;
						}
						if (count4 == n+1) {
							index4 = arr4.index;
							break;
						}
					}
					childs = childs.substr(0,predex1) + childs.substr(index1);
					timess = timess.substr(0,predex) + timess.substr(index);
					commodityids = commodityids.substr(0,predex2) + commodityids.substr(index2);
					names = names.substr(0,predex3) + names.substr(index3);
					everytimesmoneys = everytimesmoneys.substr(0,predex4) + everytimesmoneys.substr(index4);
					
					commodityids=commodityids.replace(","+id+",",",");
					names=names.replace(","+id+",",",");
					timess=timess.replace(","+id+",",",");
					childs=childs.replace(","+id+",",",");
					everytimesmoneys=everytimesmoneys.replace(","+id+",",",");
					

				}
				if (childs.indexOf(id+",") == 0){
					var index =timess.indexOf(",") + 1;
					var index1 =commodityids.indexOf(",") + 1;
					var index2 =names.indexOf(",") + 1;
					var index3 =childs.indexOf(",") + 1;
					var index4=everytimesmoneys.indexOf(",") + 1;
					commodityids = commodityids.substr(index1);
					timess = timess.substr(index);
					names = names.substr(index2);
					childs = childs.substr(index3);
					everytimesmoneys = everytimesmoneys.substr(index4);
					
					childs=childs.replace(id+",",""); 
					names=names.replace(id+",",""); 
					timess=timess.replace(id+",",""); 
					commodityids=commodityids.replace(id+",",""); 
					everytimesmoneys=everytimesmoneys.replace(id+",",""); 
					
				}
				$("#childs").val(childs);
				$("#commodityids").val(commodityids);
				$("#timess").val(timess);
				$("#names").val(names);
				$("#everytimesmoneys").val(everytimesmoneys);
			}
		}
	},
	};
}();
$(function () {
	WorkTaskListController.init();
});
