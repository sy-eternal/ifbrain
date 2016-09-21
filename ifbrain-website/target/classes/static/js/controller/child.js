/*
购物计划购物
*/
function toshops(id) {
	var childId = $("#childss").val();
	if (childId == "-1" || childId == null) {
		alert("请选择孩子!");
		return false;
	}
	
	//选中孩子的钱
	var childmoney = "";
	$.ajax({
		async : false,
		type : "Get",
		url : "/child/findById?childId=" + childId,
		success : function(data) {
			childmoney = data.balance;
		}
	});
	//点击购买的物品的钱
	var commitymoney = "";
	$.ajax({
		async : false,
		type : "Get",
		url : "/commodity/findById?id=" + id,
		success : function(data) {
			commitymoney = data.price;
		}
	});
	//比较孩子和所选物品的钱,看能否购买物品
	if (parseInt(commitymoney) > parseInt(childmoney)) {
		var spread = parseInt(commitymoney) - parseInt(childmoney);
		alert(spread);
		
		alert("余额不足,还要赚取"+spread+"金币哦!");
		return false;
	} else {
		 var r= confirm("确认要购买吗？")
		 if(r==true){
			 window.location.href = '/commodity/tocommodity?commodityId=' + id
				+ '&amp;childId=' + childId; 
		 }else{
			 return false;
		 }
		
	}

}


function toshopss(id) {
	
	var childId = $("#childss").val();
	if (childId == "-1" || childId == null) {
		alert("请选择孩子!");
		return false;
	}
	
	//选中孩子的钱
	var childmoney = "";
	$.ajax({
		async : false,
		type : "Get",
		url : "/child/findById?childId=" + childId,
		success : function(data) {
			childmoney = data.balance;
		}
	});
	//点击购买的物品的钱
	var commitymoney = "";
	$.ajax({
		async : false,
		type : "Get",
		url : "/commodity/findByIds?id=" + id,
		success : function(data) {
			commitymoney = data.price;
		}
	});
	//比较孩子和所选物品的钱,看能否购买物品
	if (parseInt(commitymoney) > parseInt(childmoney)) {
		var spread = parseInt(commitymoney) - parseInt(childmoney);
		alert(spread);
		
		alert("余额不足,还要赚取"+spread+"金币哦!");
		return false;
	} else {
		 var r= confirm("确认要购买吗？")
		 if(r==true){
			 window.location.href = '/commodity/tocommoditys?commodityId=' + id
				+ '&amp;childId=' + childId; 
		 }else{
			 return false;
		 }
		
	}

}



//动态获取所选孩子的余额
function getBalance(childId) {
	var childId=$("#childss").val();
	$.ajax({
		type : "Get",
		url : "/child/findById?childId=" + childId,
		success : function(data) {
			childmoney = data.balance;
			$("#balance").val("X"+childmoney);
		}
	});
}

function getBalances() {
	var childId=$("#childss").val();
	if(childId!=''){
	$.ajax({
		type : "Get",
		url : "/child/findById?childId=" + childId,
		success : function(data) {
			childmoney = data.balance;
			$("#balance").val(childmoney+"金币");
		}
	});
	}
}

//推荐商品到计划商品的切换
function jihua() {
	var childId = $("#childss").val();
	if (childId == "-1" || childId == null) {
		alert("请选择孩子!");
		return false;
	}
	$("#jihua").css("display", "block");
	$("#tuijian").css("display", "none");
	$("#tui").css("display", "none");
	$("#ji").css("display", "block");
	$("#jihua").load("../../commodity/findBuyPlanByChildId?childId=" + childId);
}
//从计划商品切换到推荐商品
function tuijian() {
	$("#tuijian").css("display", "block");
	$("#jihua").css("display", "none");
	$("#ji").css("display", "none");
	$("#tui").css("display", "block");
}

//删除计划商品选中的商品
function jqchk() { //jquery获取复选框值
	var buyplanIds = '';
	$('input[name="buyPlan"]:checked').each(function() {
		buyplanIds += $(this).val() + ',';
	});
	if(buyplanIds == ''){
		$('#myModals').modal('show');
	}else{
		$('#myModal').modal('show');
	}
	/*if (buyplanIds != '') {
		window.location.href = '/commodity/deletebuyplan?buyplanIds='
				+ buyplanIds;
	}*/
}

function deletes(){
	var buyplanIds = '';
	$('input[name="buyPlan"]:checked').each(function() {
		buyplanIds += $(this).val() + ',';
	});
	
	if (buyplanIds != '') {
		window.location.href = '/commodity/deletebuyplan?buyplanIds='
				+ buyplanIds;
	}
}