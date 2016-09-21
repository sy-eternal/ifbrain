


		//邮箱格式验证
		function validatePhone(value){
			if(value!=''){
				var telReg = !!value.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
				if(telReg!=true){
					//alert("手机号格式不正确!");
					/* $("#phonevalidate").modal({
			 	   	        show:true,
			 	   	        backdrop:true
			 	   	        });*/
					$("#messageInfo").text("手机号格式不正确!");
					$("#phonenumber").val("");
					return false;
				}
			$.ajax({
			    type:"Post",
			    url:"/user/getuserbyphone?phone="+value,
			    success: function(data) {
			    	var obj=eval('(' + data + ')');
			    	if(obj.result=="0"){
			    		//alert("该电话号码不存在,请注册!")$("#phonetishi").modal({
			    		/*$("#phoneresettishi").modal({
			    	        show:true,
			    	        backdrop:true
			    	        });*/
			    		$("#messageInfo").text("该手机不存在!请注册.");
			    		$("#phonenumber").val("");
			    		return false;
			    	}
			    }
			}); 
			}
		}




$(function(){
	//手机重新设置密码的页面
	var setphonepwdsuccess=$("#setphonepwdsuccess").val();
	
		if(setphonepwdsuccess!=""){
			$("#showregister2").css("display","none");
			$("#showregister1").css("display","none");
			$("#phoneFindpwd").css("display","block");
		}
	
	
	
	  var flag=$("#flag").val();
	if(flag=="1"){
		$("#phoneresetsecondForm").css("display","block");
		$("#phoneresetForm").css("display","none");
	}
	$("#buttonA").click(function(){
		   var mobile=$("#phonenumber").val();
		  if(mobile==""){
			  //alert("请输入手机号");
			 /* $("#phonenum").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			  $("#messageInfo").text("手机号不能为空!");
			  return false;
		  }
		  $.ajax({
			    type:"Post",
			    url:"/code/getsmsvalidationAgain?mobile="+mobile,
			    success: function(data) {
			    	var obj=eval('(' + data + ')')
			    	 $("#messageInfo").text("手机验证码已发送,请注意查看手机!");
			    		/*$("#phoneregistertishi").modal({
			    	        show:true,
			    	        backdrop:true
			    	        });*/
			    		var date=new Date();
			    		$("#buttonA").attr({"disabled":"disabled"});
			    		$("#buttonA").val("60秒后重新获取");
				    	var ms = date.getTime();
			    		var ms1 = ms+62000;
				    	function getRTime(){
				    		var date1=new Date();
				    		var ms2 = date1.getTime();
				    		var ms3 = ms1-ms2;
				    	   $("#buttonA").val(parseInt(ms3/1000)+"秒后重新获取");
				    	   if(0>=ms3){
				    		   clearInterval(intervalId);
				    		   $("#buttonA").removeAttr("disabled");
				    		   $("#buttonA").val("点击获得验证码");
				    	   }
				        } 
				    	var intervalId = setInterval(getRTime,1000);
			    	}
			}); 
	   });
	
	
	$("#phoneresetpasswordsecond").click(function(){
		var password=$("#phoneresetpassword").val();
		var confirmpassword=$("#confirmphonepassword").val();
		 if(password==""){
			  // alert("密码不能为空!");
			  /* $("#passworddialog").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			 $("#phonemessage").text("密码不能为空!");
			   return false;
		   }
		   if(confirmpassword==""){
			  // alert("确认密码不能为空!");
			   /*$("#confirmpassword").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   $("#phonemessage").text("确认密码不能为空!");
			   
			   
			   return false;
		   }
		   if(password!=confirmpassword){
			   //alert("用户密码和确认密码不一致!");
			  /* $("#passwordvalidate").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   $("#phonemessage").text(" 密码和确认密码不一致!");
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
			  /* $("#phonepasswordtest").modal({
		   	        show:true,
		   	        backdrop:true
		   	        });*/
			   $("#phonemessage").text("密码长度必须大于6位!");
			   return false;
		   }
		 $("#phoneFindpwdForm").submit(); 
	})
	
	
	   $("#phoneresetpasswordfirst").click(function(){
			  var phonenumber=$("#phonenumber").val();
			  var code = $("#code").val();
			 if(phonenumber==''){
				 //alert("请输入电话号码，不能为空!");
				 $("#messageInfo").text("手机号不能为空!");
				 /*$("#phonenum").modal({
			   	        show:true,
			   	        backdrop:true
			   	        });*/
				 return false;
			 }  
			 
			 if(code==''){
				 //alert("请输入电话号码，不能为空!");
				 $("#messageInfo").text(" 手机验证码不能为空!");
				/* $("#phonepassword").modal({
			   	        show:true,
			   	        backdrop:true
			   	        });*/
				 return false;
			 }
			 
			 $.ajax({
				    type:"Post",
				    url: "/code/smssubmit?mobile=" + phonenumber + "&amp;code=" + code,
				    success: function(data) {
				    	var obj=eval('(' + data + ')');
				    	
				    	if(obj.result=="1"){
							   $("#phoneresetForm").submit(); 
				    	}else{
				    		//alert(obj.result);
				    		if(obj.result=="0"){
				    			 /*$("#phonepasswordiswr").modal({
					 		   	        show:true,
					 		   	        backdrop:true
					 		   	        });*/
				    			 $("#messageInfo").text("手机验证码错误!");	
				    			
				    		}else if(obj.result=="2"){
				    			/* $("#phonepasswordisgq").modal({
				 		   	        show:true,
				 		   	        backdrop:true
				 		   	        });*/
				    			 $("#messageInfo").text("手机验证码过期!");	
				    		}
				    		return false;
				    	}
				    	
				    }
			});
			
		   });
});
