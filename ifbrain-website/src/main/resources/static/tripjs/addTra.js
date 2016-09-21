var addTra = function() {
	return {
		// main function to initiate the module
		init : function() {
			
		}
	};

}();
$("form").submit(
		function(event) {
        		var xins=$("input[name='xins']");
				var mings=$("input[name='mings']");
				var firstNames=$("input[name='firstNames']");
				var secondNames=$("input[name='secondName']");
				/*var genders=$("input[name='genders']:checked");*/
				var birthdates=$("input[name='birthDates']");
				var passportNums=$("input[name='passportNums']");
				var driveLicenseCodes=$("input[name='driveLicenseCodes']");
				var  moblieNums=$("input[name='moblieNums']");
				var weichats=$("input[name='weichats']");
				var emails=$("input[name='emails']");
				var objRegExp=/^(\d{4})\-(\d{2})\-(\d{2})$/;
				
				var xin="";
				var ming="";
				var firstName="";
				var secondName="";
				var gender="";
				var birthdate="";
				var passportNum="";
				var driveLicenseCode="";
				var moblieNum="";
				var email="";
				var weichat="";
				
				for(var i=0;i<xins.length;i++)
				{
					if(xins[i].value=="")
					{
						alert("中文姓不能为空！");
						return false;
					}
					if(mings[i].value=="")
					{
						alert("中文名不能为空！");
						return false;
					}
					if(firstNames[i].value=="")
					{
						alert("英文姓不能为空！");
						return false;
					}
					if(secondNames[i].value=="")
					{
						alert("英文名不能为空！");
						return false;
					}
					/*if(genders[i].value=="")
					{
						alert("性别不能为空！");
						return false;
					}*/
					if(birthdates[i].value=="")
					{
						alert("出生年月不能为空！");
						return false;
					}
					if(moblieNums[i].value=="")
					{
						alert("手机号不能为空！");
						return false;
					}
					if(weichats[i].value==""){
						alert("微信号不能为空！");
						return false;
					}
					if(emails[i].value=="")
					{
						alert("邮箱不能为空！");
						return false;
					}
					if(!objRegExp.test(birthdates[i].value))
					{
						alert("第"+(i+1)+"人的出生日期格式不正确");
						return false;
					}
					
					var gendersi ;

					if (i ==0) {
						gendersi =$("input[name='genders']:checked");	
						if(gendersi.val()==null){
							alert("性别不能为空！");
							return false;
						}
					} else {
						var j = i + 1;
						gendersi = $("input[name='genders" + j + "']:checked");
						if(gendersi.val()==null){
							alert("性别不能为空！");
							return false;
						}
					}

					
				    xin+=xins[i].value+",";
					ming+=mings[i].value+",";
					firstName+=firstNames[i].value+",";
					secondName+=secondNames[i].value+",";
					gender+=gendersi.val()+",";
					birthdate+=birthdates[i].value+",";
					passportNum+=passportNums[i].value+",";
					driveLicenseCode+=driveLicenseCodes[i].value+",";
				    moblieNum+=moblieNums[i].value+",";
				    weichat += weichats[i].value+",";
					email+=emails[i].value+",";
				}
				$("#xinss").val(xin);
				$("#mingss").val(ming);
				$("#firstNamess").val(firstName);
				$("#secondNamess").val(secondName);
				$("#genderss").val(gender);
				$("#passportNumz").val(passportNum);
				$("#mobileNumz").val(moblieNum);
				$("#weichatsz").val(weichat);
				$("#emailsz").val(email);
				$("#birthDatez").val(birthdate);
				$("#driveLicenseCodesz").val(driveLicenseCode);
			return true;
		});


var itenCountS = 1;
function addperson(){	
	itenCountS ++;
	var personCount=$('#personCount').val();
	if(itenCountS>personCount){
		document.getElementById('more-iten-btnS').disabled=true;
		return false;
	}
 	$('#iten-tableS').append("" +
 			"<tr>" +
 			"<th width='20%'></th>" +
 			"<th >第"+itenCountS+"人</th>" +
 					"</tr><tr>" +
 					"<th>中文姓</th>" +
 					"<th><input type='text' id='xins' name='xins' size='50' /></th>" +
 					"</tr><tr><th>中文名</th>" +
 					"<th><input type='text' id='mings' name='mings' size='50'  /></th>" +
 					"</tr><tr>" +
 					"<th>英文姓</th>" +
 					"<th><input type='text' id='firstNames' name='firstNames' size='50'  />" +
 					"</th></tr>" +
 					"<tr>" +
 					"<th>英文名</th>" +
 					"<th><input type='text' id='secondName' name='secondName' size='50'   /></th>" +
 					"</tr><tr>" +
 					"<th>性别</th>" +
 					"<th>" +
 					"<input type='radio' name='genders"+ itenCountS+"' checked='checked' value='1'/>&nbsp;男&nbsp;&nbsp;<input type='radio' name='genders"+ itenCountS+"' value='2'/>&nbsp;女" +
 					"</th>"+
 					"</tr><tr>" +
 					"<th>出生年月</th>" +
 					"<th><input type='text' id='birthDates' name='birthDates' size='50'  /></th>" +
 					"</tr><tr>" +
 					"<th>护照号</th>" +
 					"<th><input type='text' id='passportNums' name='passportNums' size='50'/></th>" +
 					"</tr><tr>" +
 					"<th>国际驾照号</th>" +
 					"<th><p>如果有自驾行程，请输入您的国际驾照号</p><input type='text' id='driveLicenseCodes' name='driveLicenseCodes' size='50' /></th>" +
 					"</tr><tr>" +
 					"<th>手机号（加国家）</th>" +
 					"<th><input type='text' id='moblieNums' name='moblieNums' size='50'  value='+86'/></th>" +
 					"</tr>" +
 					"<tr>" +
 					"<th>微信号</th>" +
 					"<th><input type='text' id='weichats' name='weichats' size='50'  /></th>" +
 					"</tr>"+
 					"<tr>" +
 					"<th>邮箱</th>" +
 					"<th><input type='text' id='emails' name='emails' size='50'  /></th>" +
 					"</tr>");
 	return true;
}/*
$( "#more-iten-btnS" ).click(function() {});			

*/