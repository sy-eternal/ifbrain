
//密码验证
function passwordconfirm(value){
	var confirmphonepassword=$("#confirmphonepassword").val();
	if(value!=''){
	if(confirmphonepassword!=''){
		if(confirmphonepassword!=value){
			//alert("密码和确认密码不一致!");
			/*$("#passwordvalidate").modal({
 	   	        show:true,
 	   	        backdrop:true
 	   	        });*/
			$("#message").text("密码和确认密码不一致!");
			return false;
		}
	}
	}
}


function emailpasswordconfirm(value){
	var confirmpassword=$("#confirm-password").val();
	if(value!=''){
	if(confirmpassword!=''){
		if(confirmpassword!=value){
			//alert("密码和确认密码不一致!");
			/*$("#passwordvalidate").modal({
 	   	        show:true,
 	   	        backdrop:true
 	   	        });*/
			$("#messageInfo").text("密码和确认密码不一致!");
			return false;
		}
	}
	}
}
//验证手机号的格式
function validateNumber(value){
	if(value!=''){
		var telReg = !!value.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
		if(telReg!=true){
			//alert("手机号格式不正确!");
			$("#message").text("请输入正确的手机号!");
			/* $("#phonevalidate").modal({
	 	   	        show:true,
	 	   	        backdrop:true
	 	   	        });*/
			return false;
		}
	}
}

function validateEmail(value){
	//邮箱格式验证
	var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(value!=''){
	if(!reg.test(value)){
		//alert("邮箱格式不正确!");
		/*$("#eamilvalidate").modal({
	   	        show:true,
 	   	        backdrop:true
 	   	        });*/
		$("#messageInfo").text("邮箱格式不正确!");
		$("#emailvalue").val("");
		return false;
	}
	 $.ajax({
		    type:"Post",
		    url: "/user/getuserbyemailregister?email=" + value,
		    success: function(data) {
		    	var obj=eval('(' + data + ')')
		    	if(obj.result=="1"){
		    		 /*$("#eamiltishi").modal({
			 	   	        show:true,
			 	   	        backdrop:true
			 	   	        });*/
		    		$("#messageInfo").text("该邮箱已存在!");
		    		 $("#emailvalue").val("");
		    	}else if(obj.result=="0"){
		    		$("#messageInfo").text("邮箱激活链接会发到该邮箱中,请注意查收!!");
		    		/* $("#eamilregistertishi").modal({
			 	   	        show:true,
			 	   	        backdrop:true
			 	   	        });*/
		    		return false;
		    	}
		    	
		    }
	});
	
	
	
	}
}

//取消
function cancelclick(){
		window.location.href="/home/travelindex";
	}

function changeUserType() {
	var userType = event.srcElement.value;
	if (userType == "2") {
		$("#guideInfo").show();
	} else {
		$("#guideInfo").hide();
	}
}
/*jQuery.validator.addMethod("isPassword", function(value, element) {
	var chrnum = /^.*?[\d]+.*$/;
	var chrnum1=/^.*?[A-Za-z].*$/;
	return this.optional(element) || (chrnum.test(value) && chrnum1.test(value));
}, "必须包含数字和字母(字符A-Z, a-z, 0-9)");*/

$(function() {
	//默认选中邮箱
	$("li.active").css({"border-bottom":"4px solid #015289"});
	$("#email").css("color","#00B38A");
	$("#phone").css("color","#333333");
	$("#emaildiv").css("display","block");
	$("#phonediv").css("display","none");
	$("#emaildiv").slideDown(3000);
	//注册成功后的提示
	var flag=$("#emailflag").val();
	if(flag==1){
		$("#showregister1").css("display","none");
		$("#showregister2").css("display","block");
		/*$("#phone").css("color","#00B38A");
		$("#email").css("color","#333333");
		$("#emaildiv").css("display","none");
		$("#phonediv").css("display","block");
		$("#phonediv").slideDown(3000);*/
	}else if(flag==0){
		$("#showregister2").css("display","none");
		$("#showregister1").css("display","block");
	}else{
		$("#showregister2").css("display","none");
		$("#showregister1").css("display","block");
	}
	
	//点击邮箱时
	$("#emailtab").click(function(){
		$("#emailtab").css({"border-bottom":"4px solid #015289"});
		$("#phonetab").css({"border-bottom":"4px solid #FFFFFF"});
		$("#emailimg").attr('src','../../img/mailcheck.png');
		$("#phoneimg").attr('src','../../img/phonemoren.png');
		$("#email").css('display','block'); 
		$("#phone").css('display','none'); 
	});
	//点击电话时
	$("#phonetab").click(function(){
		$("#phonetab").css({"border-bottom":"4px solid #015289"});
		$("#emailtab").css({"border-bottom":"4px solid #FFFFFF"});
		$("#emailimg").attr('src','../../img/mailmoren.png');
		$("#phoneimg").attr('src','../../img/phonecheck.png');
		$("#email").css('display','none'); 
		$("#phone").css('display','block'); 
	});
	//初始化页面
	/*$('#email').mouseover(function () {
		$("#emailimg").attr('src','../../img/icon-mail-active_22ff66f.png');
		$("#phoneimg").attr('src','../../img/icon-phone_4327f93.png');
		$("#email").css("color","#00B38A");
		$("#phone").css("color","#333333");
		$("#emaildiv").css("display","block");
		$("#phonediv").css("display","none");
		$("#emaildiv").slideDown(3000);
	});
	$('#phone').mouseover(function () {
		$("#phoneimg").attr('src','../../img/icon-phone-active_74d4e76.png');
		$("#emailimg").attr('src','../../img/icon-mail_dd7169e.png');
		$("#phone").css("color","#00B38A");
		$("#email").css("color","#333333");
		$("#emaildiv").css("display","none");
		$("#phonediv").css("display","block");
		$("#phonediv").slideDown(3000);
	});*/
	//获取手机短信验证码
	   $("#button").click(function(){
		   var mobile=$("#phonenumber").val();
		  if(mobile==""){
			  //alert("请输入手机号");
			 /* $("#phonenum").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
				$("#message").text("手机号不能为空!");
			  return false;
		  }
		  $.ajax({
			    type:"Post",
			    url:"/code/getsmsvalidation?mobile="+mobile,
			    success: function(data) {
			    	var obj=eval('(' + data + ')')
			    	if(obj.result=="1"){
			    		//弹出模态框提示
			    		/*$("#phonetishi").modal({
			    	        show:true,
			    	        backdrop:true
			    	        });*/
			    		$("#message").text("该手机已注册!请重新注册.");
			    		$("#phonenumber").val("");
			    		return false;
			    	}else{
			    		//alert(obj.result);
			    		$("#message").text("手机验证码已发送,请注意查看手机!");
			    		/*$("#phoneregistertishi").modal({
			    	        show:true,
			    	        backdrop:true
			    	        });*/
			    		var date=new Date();
			    		$("#button").attr({"disabled":"disabled"});
			    		$("#button").val("60秒后重新获取");
				    	var ms = date.getTime();
			    		var ms1 = ms+62000;
				    	function getRTime(){
				    		var date1=new Date();
				    		var ms2 = date1.getTime();
				    		var ms3 = ms1-ms2;
				    	   $("#button").val(parseInt(ms3/1000)+"秒后重新获取");
				    	   if(0>=ms3){
				    		   clearInterval(intervalId);
				    		   $("#button").removeAttr("disabled");
				    		   $("#button").val("点击获得验证码");
				    	   }
				        } 
				    	var intervalId = setInterval(getRTime,1000);
			    	}
			    }
			}); 
	   });
	//邮箱注册
	   
	
		 
	   $("#eamilregisters").click(function(){
		   var firstName=$("#firstName").val();
		   var email=$("#emailvalue").val();
		   var password=$("#password").val();
		   var confirmpassword=$("#confirm-password").val();
		   if(firstName=='' || firstName==null){
			  // alert("用户姓不能为空!");
			   $("#messageInfo").text("用户姓名不能为空!");
			  /* $("#username").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		   if(email==''){
			   //alert("用户邮箱不能为空!");
			   $("#messageInfo").text("邮箱不能为空!");
			  /* $("#emaildialog").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		   if(password=='' || password==null){
			   //alert("用户密码不能为空!");
			   $("#messageInfo").text("密码不能为空!");
			   /*$("#passworddialog").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		   if(confirmpassword=='' || confirmpassword==null){
			   //alert("用户确认密码不能为空!");
			   $("#messageInfo").text("确认密码不能为空!");
			  /* $("#confirmpassword").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		   if(password!=confirmpassword){
			  // alert("用户密码和确认密码不一致!");
			   $("#messageInfo").text("密码和确认密码不一致!");
			  /* $("#passwordvalidate").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
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
			    /*switch(h){
			     case 1:
			    	 alert('低');
			    	 break;
			     case 2:
			    	 alert('中');
			         break;
			     case 3:
			    	 alert('高');
			    	 break;
			    }*/
			   }
		   else{
			   $("#messageInfo").text(" 密码长度必须大于6位!");
			  /* $("#phonepasswordtest").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		   $("#emailForm").submit(); 
		 
	   });
	
	   
	//手机注册提交
	   $("#phoneregisters").click(function(){
		  var phonefirstname=$("#phonefirstname").val();
		   var mobile = $("#phonenumber").val();
			var code = $("#code").val();
			var password=$("#phonepassworda").val();
			var confirmpassword=$("#confirmphonepassword").val();
			if(phonefirstname==""){
				/*$("#username").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
				$("#message").text("姓名不能为空!");
				   return false;
			   }
		   if(mobile==""){
			  // alert("请输入手机号!");
			   $("#message").text("手机号不能为空!");
			   
			  /* $("#phonenum").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		   if(code==""){
			  // alert("请输入手机验证码!");
			   $("#message").text("手机验证码不能为空!");
			   /*$("#phonepassword").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		   if(password==""){
			  // alert("密码不能为空!");
			  /* $("#passworddialog").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   $("#message").text("密码不能为空!");
			   return false;
		   }
		   if(confirmpassword==""){
			  // alert("确认密码不能为空!");
			   $("#message").text("确认密码不能为空!");
			   /*$("#confirmpassword").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		   if(password!=confirmpassword){
			   //alert("用户密码和确认密码不一致!");
			   $("#message").text("密码和确认密码不一致!");
			   /*$("#passwordvalidate").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
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
			    /*switch(h){
			     case 1:
			    	 alert('低');
			    	 break;
			     case 2:
			    	 alert('中');
			         break;
			     case 3:
			    	 alert('高');
			    	 break;
			    }*/
			   }
		   else{
			   $("#message").text(" 密码长度必须大于6位!");
			  /* $("#phonepasswordtest").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   return false;
		   }
		 $.ajax({
			    type:"Post",
			    url: "/code/smssubmit?mobile=" + mobile + "&amp;code=" + code,
			    success: function(data) {
			    	var obj=eval('(' + data + ')')
			    	if(obj.result=="1"){
			    		$("#phoneForm").submit(); 
			    	}else{
			    		//alert(obj.result);
			    		if(obj.result=="0"){
			    			 $("#message").text("手机验证码错误!");
			    			 /*$("#phonepasswordiswr").modal({
				 		   	        show:true,
				 		   	        backdrop:true
				 		   	        });*/
			    		}else if(obj.result=="2"){
			    			$("#message").text("手机验证码过期,请重新注册!");
			    			/* $("#phonepasswordisgq").modal({
			 		   	        show:true,
			 		   	        backdrop:true
			 		   	        });*/
			    		}
			    		return false;
			    	}
			    	
			    }
		});
			
			
	   });
	
	$("input[name='userType']").on("click", changeUserType);
	/*$("#mainForm").validate({
		rules : {
			"confirm-password" : {
				equalTo : "#password"
			},
			"password" : {
			isPassword : true,
			required:true,
			maxlength:16,
			minlength:6			
		},
		messages : {
			"confirm-password" : {
				equalTo : "必须与上面的密码填写一致"
			},
			"password" : {
				required : "密码不能为空",
				minlength : "最小为6位密码",
				maxlength : "最大为16位密码"
			}
		},
		},
		highlight : function(element) {
			$(element).closest(".form-group").addClass("has-error");
		},
		success : function(label) {
			label.closest(".form-group").removeClass("has-error");
			label.closest(".form-group").addClass("has-success");
		},
		errorPlacement : function(error, element) {
			element.parent("div").append(error);
		},
		submitHandler : function(form) {
			form.submit();
		}
	});*/
});
