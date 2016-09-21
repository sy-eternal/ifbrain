//孩子选择头像
$(function(){
	 //根据flagvalue来判断进入该页面后选中第几个图片
	 var flagvalue=$("#flagaa").val();
	 if(flagvalue!=""){
		 $(".portlet-body.mypanel img").each(function() {
			 var value=$(this).parent().find('input').attr('value');
			 if(flagvalue==value){
				 $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
			 }
		 });
	 }
	//根据孩子id获得相应孩子
	$(".portlet-body.mypanel img").each(function() {
		 $(this).click(function () {
			 var id=$(this).parent().find('input').attr('value');
			 var commdityid=$("#commdityids").val();
			window.location.href="/wish/demendwishlist?shoppingmallCommodityid="+commdityid+"&childid="+id;
			});
	});
	
	$(".touchspin_listshopping").each(function() {
		$(this).TouchSpin({
			buttondown_class: 'btn ',
	        buttonup_class: 'btn ',
	        min: 1,
	        max: 60,
	        stepinterval: 1,
	        maxboostedstep: 10000000,
	        postfix: ''
		})
	})

});

//添加心愿清单
function addxinyuan(){
	
   if ($("#sessionuser").val() == "") {
	window.location.href = "/user/logins?returnUrl="+"/wish/initwish";
   } else{
	   var childid=$("#childid").val();
	   if(childid==""){
		  alert("请创建孩子!"); 
		  return false;
	   }else{
		   var commodityName=$("#commodityName").val();
		   var price=$("#price").val();
		   if(commodityName==""&&price==""){
			   alert("请填写商品名称和价格!");
		   }else if(isNaN(price)){
			  $("#messages").css('display','block');
		   }else{
			   $("#mainForm1").submit();
		   }
		   
	   }
  } 
}
//心愿清单
function wish(){
			window.location.href = "/wish/initwish";
}
function shangcheng(){
	        window.location.href = "/wish/initshangcheng";
}
function mywishlist(){
		var flagvalue=$("#tokenfoot").val();
		window.location.href = "/wish/mywhishlist?childid="+flagvalue;	 
}

function addwishlist(){
	var flagaa=$("#flagaa").val();
	var commdityid=$("#commdityids").val();
	if ($("#sessionuser").val() == "") {
		window.location.href = "/user/logins?returnUrl="+"/wish/addwishlist";
	   } else{
		   var childid=$("#flagvalue").val();
	   if(childid==""){
			  alert("请创建孩子!"); 
			  return false;
		   }else{
		 $.ajax({
				type : "Get",
				url : "/wish/addwishlist?childId="+flagaa+"&commdityid="+commdityid,
				success : function(data) {	
					alert("添加心愿清单成功!");
					window.location.reload();
				}
			});
		   }
	   }
}