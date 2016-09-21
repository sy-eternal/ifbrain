    //获取内容
    function submitbefore(){
 	    	var value=$("#videofile").val();
 	    	if(value==''){
 	    		alert("请上传视频!");
 	    		return false;
 	    	}
 	    	var course =$("#Course").val();
			var CourseCode=$("#CourseCode").val();
 	    	$.ajax({
				async : false,
			    type: "Get",
			    url: "/moviematerial/findcheckByClassId?course="+course+"&CourseCode="+CourseCode,
			    success: function (data) {
			    	if(data==true){
			    		$("#alltrues").attr("disabled","disabled");  
			 	    	$("#mainForm").submit();
			    	}else{
			    		alert("该课程级别下课程视频已经添加了！")
			    	}
			    	
			    }
			 });
 	    	
    }