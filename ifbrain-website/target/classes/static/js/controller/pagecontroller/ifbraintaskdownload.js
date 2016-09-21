$(function(){
	
		//初始化child头像
	var flagvalue=$("#flagaa").val();
	if(flagvalue!=""){
		 $(".portlet-body.mypanel img").each(function() {
			 var value=$(this).parent().find('input').attr('value');
			 if(flagvalue==value){
				 $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
			 }
		 });
	}

	
	$(".portlet-body.mypanel img").each(function() {
		 $(this).click(function () {
			 var ifbraintaskid=$('#ifbraintaskid').val();
			 var id=$(this).parent().find('input').attr('value');
			 window.location.href="/ifbraintask/uploadordownloadtask?todownloadtaskid="+ifbraintaskid+"&childidtask="+id;
			});
	});
	
	//点击上传

	
	$(".checkmainFormUpload").click(function(){
		if($("#projectlistsession").val()==""){
			window.location.href = "/user/logins?returnUrl="+"/ifbraintask/uploadordownloadtask";
		}else{
			if($("#ifbrainchildtask").val()==""){
				$("#checkdenger").css("display","block")
			}else{
				if($("#ifbraintaskcheck").val()!=""){
					if (!confirm("已经上传过了，确认覆盖之前上传的文件吗？")) {
						return;
					}else{
						mainFormUpload.submit();
					}
				}else{
				mainFormUpload.submit();
				}
			}
		}
		
	})
	//点击下载
	$(".downloadifbraintask").click(function(){
		 var ifbraintaskid=$('#ifbraintaskid').val();
		 window.location.href="/ifbraintask/downloads/"+ifbraintaskid;
	})
});
	