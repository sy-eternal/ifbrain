$(function(){
	//留言
	$(".leavemessage").click(function(){
		if($("#usersession").val()!=""){
		var messagecontent =$("#messagecontent").val();
		var materialid =$("#materialid").val();
		$.ajax({
			type: "Post",
			url: "/activity/leavemessage?messagecontent="+messagecontent+"&materialid="+ materialid,
			success: function () {
			
			window.location.href="/activity/materialdetail?id="+materialid;
		
			}
		});
		}else{
			window.location.href = "/user/logins?returnUrl="+"/activity/materialdetail"+"&id="+materialid;
		}
	});
	
	//点赞
	$(".clickzan").click(function(){
		var materialid =$("#materialid").val();
	if($("#usersession").val()!=""){
		$.ajax({
			type: "Post",
			url: "/activity/clickzan?materialid="+materialid,
			success: function () {
					window.location.href="/activity/materialdetail?id="+materialid;
			}
		});
	}else{
		window.location.href = "/user/logins?returnUrl="+"/activity/materialdetail"+"&id="+materialid;
	}
})
	
	
	//取消点赞
	$(".noclickzan").click(function(){
	
		var materialid =$("#materialid").val();
		$.ajax({
			type: "Post",
			url: "/activity/noclickzan?materialid="+materialid,
			success: function () {
				
			
					window.location.href="/activity/materialdetail?id="+materialid;
				
			}
		});
	})
	
});

