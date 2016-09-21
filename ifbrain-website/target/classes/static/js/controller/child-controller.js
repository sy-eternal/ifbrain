var CommodityController = function () {

	
	
	var _dataTable = null;
	
	var showTable =function (){
		var childId = $('#childss').val();
		/*
		 * 先执行一遍选中孩子的金钱余额
		 */
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
		//执行结束
		
		var url ="/commodity/searchbuyplan?childId="+childId;
		$.get(url,function(childlist) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: false,
	            ordering: true,
				processing: true,
				destroy:true,
				data : JSOG.decode(childlist),
				columns: [
				          	{ data: null },	
						    { data: null },
						    { data: null },
						    { data: null },
						    { data: null },
						    { data: null }
						],
				columnDefs : [{
                    render : function(data, type, row) {
                		var id = row.id;
                		var link="";
                		link="<input  type='checkbox' name='buyPlan'  id='buyPlan' value='"+id+"' />"
                		return link;
                	},
                	targets : 0
                },{
                    render : function(data, type, row) {
                		var id = row.id;
                		var link="";
                		var imageId = row.image.id
                		link="<img src='/image/show/"+imageId+"' 'alt='Amazing Project' class='img-responsive' />";
                		return link;
                	},
                	targets : 1
                },{
                    render : function(data, type, row) {
                    	var id = row.id;
                		var link="";
                		link=row.name;
                		link="<input  type='text' name='name' id='name' value="+row.name+"  required='required'  style='border:none;background-color:transparent;text-align:center;max-width:110px;font-size: 18px; ' readonly='readonly' />";
                		return link;
                	},
                	targets : 2
                },{
                    render : function(data, type, row) {
                    	var id = row.id;
                		var link="";
                		link=row.commodityType.name;
                		link="<input  type='text' name='commodityTypename' id='commodityTypename' value="+row.commodityType.name+"  required='required'  style='border:none;background-color:transparent;text-align:center;max-width:110px;font-size: 18px; ' readonly='readonly' />";
                		return link;
                	},
                	targets : 3
                },
                {
                	 render : function(data, type, row) {
                		 var link=row.price;
                		/* link="<input  type='text' name='price' id='price' value="+row.price+"  required='required'  style='border:none;background-color:transparent;text-align:center;max-width:110px;font-size: 18px; ' readonly='readonly' />";*/
                		 return link;
                	 },
                targets : 4
                },
                {
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var balances = childmoney  -  row.price;
	                	   var link = balances;
	                	   /*link="<input  type='text' name='balances' id='balances' value="+links+"  required='required'  style='border:none;background-color:transparent;text-align:center;max-width:110px;font-size: 18px;' readonly='readonly'   />";*/
	                	   return link;
	                   },
	                   targets: 5
	               },
                {
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var link = "<button type='button' onclick='javascript: CommodityController.toshopss("+id+")' style='border: none; background-color: transparent;padding: 0' value='"+id+"' >"+
							"<img alt='' src='../../img/购买.png' class='img-responsive' />"+
						    "</button>" ;
	                	   return link;
	                   },
	                   targets: 6
	               }]
			});
		})
	};
	
	return {
		init : function () {
			if($('#childss').val()){
			showTable();
			}
		},
		search: function(){
			//_dataTable.destroy();
			if($('#childss').val()){
			showTable();
			}
		},
		toshopss:function(id) {
			var childId = $("#childss").val();
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
	};
}();

$(function(){
	CommodityController.init();
});