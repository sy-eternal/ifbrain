$(function(){
	//点击上传
	$("#check").click(function(){
		var CourseId=$("#Course").val();
		var CourseCodeId=$("#CourseCode").val();
		$.ajax({
			async:false,
			type: "Get",
			url: "/ifbraintask/check?CourseId="+CourseId+"&CourseCodeId="+CourseCodeId,
			success: function (data) {
				if(data==0){
					mainForm.submit();
				}else{
					if (confirm("已经上传过了，确认覆盖之前上传的文件吗？")) {
						$.ajax({
							async:false,
							type: "GET",
							url: "/ifbraintask/deleteifbraintask?id="+data,
							success: function (data) {
								alert(data);
								mainForm.submit();
							}
						});
					}
				}
			}
		});
		
	})
});
	