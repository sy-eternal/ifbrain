 $(function(){
	 
	$("#tijiao").click(function(){
		var userid=$("#userid").val();
		var memberid=$("#memberid").val();
		var content=$("#content").val();
		var ifbrainid=$("#ifbrainid").val();
		var ordinalNumber=$("#ordinalNumber").val();
		var courselevel=$("#courselevel").val();
		var childid=$("#childid").val();
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/classes/comment?userid="+userid+"&memberid="+memberid+"&content="+content+"&ifbrainid="+ifbrainid+"&type="+0+"&ordinalNumber="+ordinalNumber+"&courselevel="+courselevel+"&childid="+childid,
			    success: function (data) {
			    	alert(data.applicationtotalScore);
					window.location.href="/classes/showchildifbrainchart?childid="+data.id+"&courselevel="+data.courseLevel+"&ordinalnumber="+data.ordinalNumber;
			    }
			 });
	});
	$("#tijiao1").click(function(){
		var userid=$("#userid").val();
		var memberid=$("#memberid").val();
		var content=$("#content1").val();
		var ifbrainid=$("#ifbrainid").val();
		var ordinalNumber=$("#ordinalNumber").val();
		var courselevel=$("#courselevel").val();
		var childid=$("#childid").val();
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/classes/comment?userid="+userid+"&memberid="+memberid+"&content="+content+"&ifbrainid="+ifbrainid+"&type="+1+"&ordinalNumber="+ordinalNumber+"&courselevel="+courselevel+"&childid="+childid,
			    success: function (data) {
			    	alert(data.applicationtotalScore);
					window.location.href="/classes/showchildifbrainchart?childid="+data.id+"&courselevel="+data.courseLevel+"&ordinalnumber="+data.ordinalNumber;
			    }
			 });
	});
	
	
	
	
	 //删除
	 $(".deletecommentlist").each(function() {
		 $(this).click(function () {
			 var userid=$("#userid").val();
				var memberid=$("#memberid").val();
				var content=$("#content1").val();
				var ifbrainid=$("#ifbrainid").val();
				var ordinalNumber=$("#ordinalNumber").val();
				var courselevel=$("#courselevel").val();
				var childid=$("#childid").val();
				var deleteid =$(this).attr("id");
					$.ajax({
						async : false,
					    type: "Get",
					    url: "/classes/deletecommentlistindex?userid="+userid+"&memberid="+memberid+"&content="+content+"&ifbrainid="+ifbrainid+"&type="+1+"&ordinalNumber="+ordinalNumber+"&courselevel="+courselevel+"&childid="+childid+"&deleteid="+deleteid,
					    success: function (data) {
					    	alert(data.applicationtotalScore);
							window.location.href="/classes/showchildifbrainchart?childid="+data.id+"&courselevel="+data.courseLevel+"&ordinalnumber="+data.ordinalNumber;
					    }
					 });
		 })
	 })
 
 });