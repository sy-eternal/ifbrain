    //获取内容
/*$('input:radio:checked').val();
$("input[type='radio']:checked").val();
$("input[name='rd']:checked").val();*/
    /*function submitbefore(){
        	 var itemtmanagetitle=$("#itemtmanagetitle").val();
        	 var perscore=$("#perscore").val();
        	 var ordernumber=$("#ordernumber").val();
        	 if(itemtmanagetitle==''){
        	 alert("请填写标题!");
        	 return false;
        	 }
        	 else if(perscore==''){
        	 alert("请请填写每题的分数!");
        	 return false;
        	 }
        	 else if(ordernumber==''){
            	 alert("请填写序号!");
            	 return false;
        	 }
        	 else{
        	 $("#mainFormsubmit").submit();
        	 }
    }*/
 var id;
 var itemmanagementid;
$(function(){
	//删除题型
	$(".deleteitemmanagement").click(function(){
		id=$(this).siblings(".itemmanagementid").val();
		$.ajax({
			type: "DELETE",
			url: "/itemmanagement/deleteitemmanagement?id=" + id,
			success: function (data) {
				// 刷新表格
				alert(data);
				window.location.reload();
			}
		});
	});
	//批量删除试题
	var result1="";
	$(".deletebyid").click(function(){
		$('input:checkbox[name=questioncheckeddel]:checked').each(function(){
			var val=$(this).val().split(",");
			result1+=val[0]+",";
			itemmanagementid=val[1];
		 });
		if(result1!=""){
		$.ajax({
			type: "DELETE",
			url: "/itemmanagement/deletebyid?ids=" + result1+"&itemmanagementid="+itemmanagementid,
			success: function (data) {
				// 刷新表格
				alert(data);
				window.location.reload();
			}
		});
		}else{
			alert("请选择!");
		}
	});
	// 查看窗口的全选事件
	$(".alldel").click(function(){
		$("input[name='questioncheckeddel']").attr("checked",true);
	});
	//查看窗口的取消事件
	$(".notalldel").click(function(){
		$("input[name='questioncheckeddel']").attr("checked",false);
	});
	// 添加窗口的全选事件
	$(".all").click(function(){
		$("input[name='questionchecked']").attr("checked",true);
	});
	//添加窗口的取消事件
	$(".notall").click(function(){
		$("input[name='questionchecked']").attr("checked",false);
	});
	//
	var result="";
	$(".sure").click(function(){
		$('input:checkbox[name=questionchecked]:checked').each(function(){
			var val=$(this).val().split(",");
			result+=val[0]+",";
			itemmanagementid=val[1];
		 });
		if(result!=""){
		$.ajax({
			type: "POST",
			url: "/itemmanagement/addquestions?ids=" + result+"&itemmanagementid="+itemmanagementid,
			success: function (data) {
				// 刷新表格
				alert("选择成功!");
				window.location.reload();
			}
		});
		}else{
			alert("请选择!");
		}
	});
	//修改题型
	$("input[name=shiti3]").click(function(){ 
		 //$('#myModal1').find('tr').first().nextAll().remove();
	    id=$(this).siblings(".itemmanagementid").val();
	   $.post('/itemmanagement/initupdateitemmanagement', {id:id}, function(data){
		    //数据的格式如： [{no:123,old:abc},{no:234,old:def},{no:345,old:ghi}]
		        $("#itemtmanageid").val(data.id);
		        $("#itemtmanagetitleupdate").val(data.title); 
		        $("#ordernumberupdate").val(data.ordernumber);
		        $("#perscoreupdate").val(data.perscore);
		        $("#itemdescriptionupdate").val(data.itemdescription);
		     });
                                        });
	

	
	
	
	//查看试题
	$("input[name=shiti2]").click(function(){ 
		 //$('#myModal1').find('tr').first().nextAll().remove();
	    itemmanagementid=$(this).siblings(".itemmanagementid").val();
	    ItemmanagementController2(itemmanagementid);
	   /*$.post('/itemmanagement/getquestions', {id:id}, function(data){
		    //数据的格式如： [{no:123,old:abc},{no:234,old:def},{no:345,old:ghi}]
		     var obj = eval('(' + data + ')');
		     $.each(obj, function(i, n){
		     var tr = $('#myModal1').find('tr').last();
		     tr.after("<tr><td><input type='checkbox' name='questionchecked' id='questionchecked' /></td><td>"+n['questionTypeName']+"</td><td>"+n['testdifficulty']+"</td><td>"+n['questionNameContent']+"</td><td><input type='button' onclick='xuanze("+n['id']+")' value='选择' /></td></tr>");
		            });
		     });*/
                                        });
	//添加试题
	$("input[name=shiti]").click(function(){ 
		 //$('#myModal1').find('tr').first().nextAll().remove();
	    id=$(this).siblings(".questiontypeid").val();
	    itemmanagementid=$(this).siblings(".itemmanagementid").val();
	    
	    ItemmanagementController(id,itemmanagementid);
	   /*$.post('/itemmanagement/getquestions', {id:id}, function(data){
		    //数据的格式如： [{no:123,old:abc},{no:234,old:def},{no:345,old:ghi}]
		     var obj = eval('(' + data + ')');
		     $.each(obj, function(i, n){
		     var tr = $('#myModal1').find('tr').last();
		     tr.after("<tr><td><input type='checkbox' name='questionchecked' id='questionchecked' /></td><td>"+n['questionTypeName']+"</td><td>"+n['testdifficulty']+"</td><td>"+n['questionNameContent']+"</td><td><input type='button' onclick='xuanze("+n['id']+")' value='选择' /></td></tr>");
		            });
		     });*/
   });
		});
     //单个选择
		function xuanze(id,itemmanagementid){
			$.ajax({
				type: "POST",
				url: "/itemmanagement/addquestion?id=" + id+"&itemmanagementid="+itemmanagementid,
				success: function (data) {
					// 刷新表格
					alert("选择成功!");
					window.location.reload();
				}
			});
                           }
	//单个删除	
		function deletebyidsingle(id,itemmanagementid){
			$.ajax({
				type: "DELETE",
				url: "/itemmanagement/deletebyidsingle?id=" + id+"&itemmanagementid="+itemmanagementid,
				success: function (data) {
					// 刷新表格
					alert("删除成功!");
					window.location.reload();
				}
			});
                           }

	
		
		 function ItemmanagementController(id,itemmanagementid) {
				var _dataTable = null;
					var url ="/itemmanagement/getquestions?id="+id+"&itemmanagementid="+itemmanagementid;
					$.get(url,function(data) {
						_dataTable = $("#mytable").DataTable({
							language : {
								url : "/js/lib/datatables/Chinese.json"
							},
							lengthChange: false,
				            searching: true,
				            ordering: false,
							processing: true,
							bRetrieve: true,
							data : JSOG.decode(data),
							columns: [
			                            { 
				                           data: null
			                            },
									    { 
									    	data: "answer"
									    },
									    { 
									    	data: "testdifficulty"
									    }, { 
									    	data: "questionNameContent"
									    },
									    { 
									    	data: null
									    }
									],
				            columnDefs : [{
				                   render: function (data, type, row) {
				                	   var id = data.id;
				                	   var link = "<input type='checkbox' name='questionchecked' id='questionchecked' value='"+id+","+itemmanagementid+"'/>";
				                	   return link;
				                   },
				                   targets: 0
				               },{
				                   render: function (data, type, row) {
				                	   var id = data.id;
				                	   var link = "<input type='button' onclick='xuanze("+id+","+itemmanagementid+")' value='选择' />";
				                	   return link;
				                   },
				                   targets: 4
				               }
				            ]
						});
					})
			};

		 function ItemmanagementController2(itemmanagementid) {
				var _dataTable = null;
					var url ="/itemmanagement/getquestions1?itemmanagementid="+itemmanagementid;
					$.get(url,function(data) {
						_dataTable = $("#mytable1").DataTable({
							language : {
								url : "/js/lib/datatables/Chinese.json"
							},
							lengthChange: false,
				            searching: true,
				            ordering: false,
							processing: true,
							bRetrieve: true,
							data : JSOG.decode(data),
							columns: [
			                           { 
				                           data: null
			                           },
									    { 
									    	data: "answer"
									    },
									    { 
									    	data: "testdifficulty"
									    }, { 
									    	data: "questionNameContent"
									    },
									    { 
									    	data: null
									    }
									],
				            columnDefs : [{
				                   render: function (data, type, row) {
				                	   var id = data.id;
				                	   var link = "<input type='checkbox' name='questioncheckeddel' id='questioncheckeddel' value='"+id+","+itemmanagementid+"'/>";
				                	   return link;
				                   },
				                   targets: 0
				               },{
				                   render: function (data, type, row) {
				                	   var id = data.id;
				                	   var link = "<input type='button' onclick='deletebyidsingle("+id+","+itemmanagementid+")' value='删除' />";
				                	   return link;
				                   },
				                   targets: 4
				               }
				            ]
						});
					})
			     };
		