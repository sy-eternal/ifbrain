

//重置密码
function clearpwd(){
	$("#password").val("");
	$("#confirmpasswordw").val("");
}
//修改新密码的确认按钮
function resetpassword(){
	 var password=$("#password").val();
	  var confirmpassword=$("#confirmpasswordw").val();
	  if(password==''){
			 //alert("请输入密码，不能为空!");
			/* $("#passworddialog").modal({
	 	   	        show:true,
	 	   	        backdrop:true
	 	   	        });*/
		  $("#emailmessageInfo").text("密码不能为空!")
			 return false;
		 } 
		 if(confirmpassword==''){
			 //alert("请输入确认密码，不能为空!");
			/* $("#confirmpassword").modal({
	 	   	        show:true,
	 	   	        backdrop:true
	 	   	        });*/
			 $("#emailmessageInfo").text("确认密码不能为空!");
			 return false;
		 } 
	
		 if(confirmpassword!=password){
			 //alert("密码和确认密码不一致请重新输入!");
			/* $("#passwordvalidate").modal({
	 	   	        show:true,
	 	   	        backdrop:true
	 	   	        });*/
			 $("#emailmessageInfo").text("密码和确认密码不一致!");
			 return false;
		 } 
		 if(password.length>6){
			    var h =0;
			    var pat1=new RegExp("[a-zA-Z]");
			    var pat2=new RegExp("[0-9]");
			    var pat3=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im; 
			    if(pat1.test(password)){
			     h++
			    }
			    if(pat2.test(password)){
			     h++
			    }
			    if(pat3.test(password)){
			     h++
			    }
			   }
		   else{
			   $("#emailmessageInfo").text("密码长度必须大于6位!!");
			   /*$("#phonepasswordtest").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
	 $("#emaiFindpwdForm").submit(); 
	
}








//邮箱格式验证
function validateEmail(value){
	var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(value!=''){
	if(!reg.test(value)){
		$("#message").text("邮箱格式不正确!");
		//alert("邮箱格式不正确!");
		/*$("#eamilvalidate").modal({
	   	        show:true,
	   	        backdrop:true
	   	        });*/
		$("#emailvalue").val("");
		return false;
	}
	$.ajax({
	    type:"Post",
	    url:"/user/getuserbyemail?email="+value,
	    success: function(data) {
	    	var obj=eval('(' + data + ')');
	    	if(obj.result=="0"){
	    		//alert("该邮箱不存在,请注册!");
	    		/*$("#eamilnotexittishi").modal({
	 	   	        show:true,
	 	   	        backdrop:true
	 	   	        });*/
	    		$("#message").text("该邮箱不存在,请注册!");
	    		$("#emailvalue").val("");
	    		return false;
	    	}else if(obj.result=="1"){
	    		
	    	}
	    }
	}); 
	}
}
//切换按钮时控制显示
function registershow1(){
	$("#emailflag").val(1);
	$("#showregister1").css("display","none");
	$("#showregister2").css("display","block");
}
function registershow2(){
	$("#emailflag").val(0);
	$("#showregister2").css("display","none");
	$("#showregister1").css("display","block");
}

function passEdit(){
	
	$("#showregister1").css("display","none");
	$("#showregister2").css("display","none");
	$("#phoneFind").css("display","block");
} 
function emailEdit(){
	
	$("#showregister1").css("display","none");
	$("#showregister2").css("display","block");
	var emailvalue=$("#emailvalue").val();
	if(emailvalue==''){
		$("#message").text("邮箱不能为空!");
		/* $("#emaildialog").modal({
 	   	        show:true,
 	   	        backdrop:true
 	   	        });*/
		 return false;
	 }  
	 $("#emailsetpwdForm").submit(); 
	
	//$("#emailFind").css("display","block");
}
	
	//提交重置密码
$(function(){
   //页面加载时默认显示
	var flag=$("#emailflag").val();
	if(flag==1){
		$("#showregister1").css("display","none");
		$("#showregister2").css("display","block");
	}else if(flag==0){
		$("#showregister2").css("display","none");
		$("#showregister1").css("display","block");
	}else{
		$("#showregister2").css("display","none");
		$("#showregister1").css("display","block");
	}
	
//显示设置新的密码和确认密码
  var resetpasswordflag=$("#resetpasswordflag").val();
	if(resetpasswordflag!=""){
		$("#showregister2").css("display","none");
		$("#showregister1").css("display","none");
		$("#phoneandemailFindpwd").css("display","block");
	}
//激活链接发送后要显示的
	
	var sendsuccess=$("#sendsuccess").val();
	if(sendsuccess!=""){
		$("#showregister2").css("display","none");
		$("#showregister1").css("display","none");
		$("#emailFind").css("display","block");
	}
	
	$("#emailresetpasswordf").click(function(){
		  var emailvalue=$("#emailvalue").val();
		  var password=$("#password").val();
		  var confirmpassword=$("#confirmpassword").val();
		 if(emailvalue==''){
			// alert("请输入邮箱，不能为空!");
			 $("#message").text("邮箱不能为空!");
			/* $("#emaildialog").modal({
	 	   	        show:true,
	 	   	        backdrop:true
	 	   	        });*/
			 return false;
		 }  
		   $("#emailsetpwdForm").submit(); 
		
	   });
	   $("#emailresetpasswords").click(function(){
		   alert("ddd");
			  var emailvalue=$("#emailvalue").val();
			  var password=$("#password").val();
			  var confirmpassword=$("#confirm-password").val();
			 if(emailvalue==''){
				 $("#message").text("邮箱不能为空!");
				// alert("请输入邮箱，不能为空!");
				/* $("#emaildialog").modal({
		 	   	        show:true,
		 	   	        backdrop:true
		 	   	        });*/
				 
				 
				 return false;
			 }  
			 if(password==''){
				 //alert("请输入密码，不能为空!");
				/* $("#passworddialog").modal({
		 	   	        show:true,
		 	   	        backdrop:true
		 	   	        });*/
				 $("#message").text("密码不能为空!");
				 return false;
			 } 
			 if(confirmpassword==''){
				 //alert("请输入确认密码，不能为空!");
				 /*$("#confirmpassword").modal({
		 	   	        show:true,
		 	   	        backdrop:true
		 	   	        });*/
				 $("#message").text("确认密码不能为空!");
				 return false;
			 } 
			 if(confirmpassword!=password){
				 //alert("密码和确认密码不一致请重新输入!");
				 /*$("#passwordvalidate").modal({
		 	   	        show:true,
		 	   	        backdrop:true
		 	   	        });*/
				 $("#message").text("密码和确认密码不一致!");
				 return false;
			 } 
			   $("#emailsetpwdForm").submit(); 
			
		   });
});
	
	
	

