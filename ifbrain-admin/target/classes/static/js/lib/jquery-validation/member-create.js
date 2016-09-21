$(function() {
	jQuery.validator.setDefaults({
		debug : true,
		success : "valid"
	});

	jQuery.validator.addMethod("isPassword", function(value, element) {
		var chrnum = /^.*?[\d]+.*$/;
		var chrnum1=/^.*?[A-Za-z].*$/;
		return this.optional(element) || (chrnum.test(value) && chrnum1.test(value));
	}, "必须包含数字和字母(字符A-Z, a-z, 0-9)");

//	jQuery.validator.addMethod("isTelephone", function(value, element) {
//		var length = value.length;
//		var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
//		return this.optional(element) || (length == 11 && mobile.test(value));
//	}, "请正确填写您的联系电话");
	
	jQuery.validator.addMethod("isEmail",function(value,element){
		regEmail=  /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		return this.optional(element) || (regEmail.test(value));		
	},"请输入一个有效的Email地址(格式：example@example.com)");

	$("#mainForm").validate({
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
			"email" : {
				required:true,
				isEmail:true
			}
		},

		messages : {
			"confirm-password" : {
				equalTo : "必须与上面的密码填写一致"
			},
			"password" : {
				required : "必须填写",
				minlength : "最小为6位密码",
				maxlength : "最大为16位密码"
			}
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
	});
});