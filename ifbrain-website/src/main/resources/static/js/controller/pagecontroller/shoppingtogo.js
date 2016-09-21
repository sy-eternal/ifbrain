$(function(){
	//点击购买按钮
	/*$('.buy').click(function() {
	      var commodityid=$(this).parent().find("input").val();
	      var quantity=$(this).parents(".parent").siblings().find(".touchspin_listshopping").val();
	      var childid=$("#flagaa").val();
	        var sumprice = $("#sumprice").val();
	        alert(sumprice+"sumprice");
			var price = $(this).find('.shopprice').attr('value');
			if(price*quantity-sumprice>0){
				alert("余额不足");
			}
			else{
	     $.ajax({
				type : "Get",
				url : "/demandcityshopping/buy?commodityid="+commodityid+"&quanlity="+quantity+"&childid="+childid,
				success : function(data) {	
					alert(data);
					window.location.reload();
				}
			});  
		}
	});*/

	$('.buy').click(function() {
		var childid=$("#flagaa").val();
		  var commodityid=$(this).find(".shoppingmallCommodityid").val();
		 
		  window.location.href="/wish/demendwishlist?shoppingmallCommodityid="+commodityid+"&childid="+childid;
		
	});
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
			window.location.href="/demandcityshopping/switchbychildid?childId="+id;
			});
	});
	
	//遍历
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

	//根据childid查出收入总价
		 /*var childId=$("#flagaa").val();
		$.ajax({
			type : "Get",
			url : "/demandcityshopping/findSumpriceById?id=" + childId,
			success : function(data) {
				$("#sumtokenprice").val(data);
			}
		});*/
	});
	