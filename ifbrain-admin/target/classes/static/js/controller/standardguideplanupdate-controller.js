var StandardGuidePlanController = function() {

	var _dataTable = null;

	var showTable = function() {

		//页面取得城市Id 
		var cityId=$('#cityId').val();
		//页面取得导游类型
		var guideActivityType=$('#guideActivityType').val();
		$("#guideactivityIds").val("");
		$("#guideCounts").val("");
		$("#subTotalAmounts").val("");
		$("#totalprices").val(0);

		//跳转到的Controller地址
		var url="/standardGuidePlan/searchs?cityId="+cityId+"&guideActivityType="+guideActivityType;

		$.get(url,function(guideRates){

			console.log(JSOG.decode(guideRates));

			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				processing : true,
				bPaginate: true,
				bLengthChange: true,
				data : JSOG.decode(guideRates),
				columns : [
				           
				           {
				        	   data : "guideActivity"
				           },
				           {
				        	   data : "guideType"
				           }, {
				        	   data : "guideRatePrice"
				           }, {
				        	   data :null
				           }, {
				        	   data : null,
				        	   orderable : false
				           },
				           { data: null }
				           ],
				           columnDefs : [
{
	render : function(data, type, row) {
		var id = row.id;
		var link="";
		link="<input  type='radio' name='radio'  id='radio"+id+"' onclick='javascript: StandardGuidePlanController.changecheck("+id+")'/>"+
		"<input type='hidden' name='guideactivityId'  id='guideactivityId"+id+"' value='"+row.id+"'/>";
		return link;
	},
	targets : 5
},
{
	render : function(data, type, row) {
		if (!data) {
			return "";
		}
		
			return data.city.cityName;
	},
	targets : 0
},
{
	render : function(data, type, row) {
		if (!data) {
			return "";
		}
		
			return data;
	},
	targets : 1
},
{
	render : function(data, type, row) {
		var id = row.id;
		var link="";
		link="<input  type='text' name='guideRatePrice' id='guideRatePrice"+id+"' disabled='disabled' readonly='readonly' required='required' value='"+row.guideRatePrice+"'/>";
		return link;
	},
	targets : 2
},
{
	render : function(data, type, row) {

		var id = row.id;
		var link="";
		link="<input type='number' min='0' name='guideCount'  id='guideCount"+id+"' value='0'  onchange='javascript: StandardGuidePlanController.changepersonCount("+id+")'/>" ;
		return link;
	},
	targets : 3
},
{
	render : function(data, type, row) {
		var id = row.id;
		var link="<input type='text'  name='subTotalAmount'  id='subTotalAmount"+id+"' required='required'  readonly='readonly'/>";
		return link;
	},
	targets : 4
} ]
			});
		})
	};
	return {
		init : function() {
			showTable();
		},
		searchs:function(){
			_dataTable.destroy();
			showTable();
		},

		changepersonCount: function (id) {
			//保险销售价
			var guideRatePrice = $("#guideRatePrice"+id).val();
			//投保人数
			var guideCount = $("#guideCount"+id).val();
			//小计
			var subTotalAmoun = parseFloat(guideCount*guideRatePrice);
			$("#subTotalAmount"+id).val(subTotalAmoun);
			$("#radio"+id).attr('checked',true);
			$("#totalprices").val($("#subTotalAmount"+id).val());
			$("#guideactivityIds").val($("#guideactivityId"+id).val());
			$("#guideCounts").val($("#guideCount"+id).val());
			$("#subTotalAmounts").val($("#subTotalAmount"+id).val());
		},
		
		changecheck: function(id){
			var guideactivityIds = $("#guideactivityIds").val();
			var guideCounts =$("#guideCounts").val();
			var subTotalAmounts = $("#subTotalAmounts").val();

			if($("#radio"+id).prop("checked")){
				var guideCount = $("#guideCount"+id).val();
				var  subTotalAmoun = $("#subTotalAmount"+id).val();
				var guideactivityId = $("#guideactivityId"+id).val();
				//

				//拼接数据

				guideactivityIds=guideactivityId+",";
				guideCounts=guideCount+",";
				subTotalAmounts=subTotalAmoun+",";
				$("#guideactivityIds").val(guideactivityIds);
				$("#guideCounts").val(guideCounts);
				$("#subTotalAmounts").val(subTotalAmounts);
				
				
				var totalprices = parseFloat($("#totalprices").val());
				//合计
				var totalPrice = parseFloat(subTotalAmoun);
				$("#totalprices").val(totalPrice);
				
				
//			}else{
//				$("#guideCount"+id).attr('disabled',false);
//				$("#guideCount"+id).val(0);
//				
//				
//				
////				var  subTotalAmoun = $("#subTotalAmount"+id).val();
////				$("#subTotalAmount"+id).val(0);
//				var totalprices = parseFloat($("#totalprices").val());
//				//合计
//				var totalPrice = parseFloat(subTotalAmoun);
//				$("#totalprices").val(totalPrice);
//				
//				
//				
//				var  subTotalAmoun = $("#subTotalAmount"+id).val();
//				var totalPrice = $("#subTotalAmounts").val();
//				if (guideactivityIds.indexOf(id+",") > 0){
//					var idIndex = guideactivityIds. indexOf(","+id+",")+1;
//					var n=(guideactivityIds.substr(0, idIndex).split(",")).length-1;
//					var index1;
//					var predex1;
//					var re1 = /,/g;
//					var count1 = 0;
//					while ((arr = re1.exec(subTotalAmounts)) != null) {
//						count1++;
//						if (count1 == n) {
//							predex1 = arr.index;
//						}
//						if (count1 == n+1) {
//							index1 = arr.index;
//							break;
//						}
//					}
//
//					var index ;
//					var predex;
//					var re = /,/g;
//					var count = 0;
//					while ((arr = re.exec(guideCounts)) != null) {
//						count++;
//						if (count == n) {
//							predex = arr.index;
//						}
//						if (count == n+1) {
//							index = arr.index;
//							break;
//						}
//					}
//
//					subTotalAmounts = subTotalAmounts.substr(0,predex1) + subTotalAmounts.substr(index1);
//					guideCounts = guideCounts.substr(0,predex) + guideCounts.substr(index);
//					guideactivityIds=guideactivityIds.replace(","+id+",",",");
//
//				}
//				if (guideactivityIds.indexOf(id+",") == 0){
//					var index =guideCounts.indexOf(",") + 1;
//					var index1 =subTotalAmounts.indexOf(",") + 1;
//					guideCounts = guideCounts.substr(index);
//					subTotalAmounts = subTotalAmounts.substr(index1);
//					guideactivityIds=guideactivityIds.replace(id+",","");   
//				}
//				$("#guideactivityIds").val(guideactivityIds);
//				$("#guideCounts").val(guideCounts);
//				$("#subTotalAmounts").val(subTotalAmounts);
			}
		},
		ForDight: function(Dight,How){			 
			var Dight = Math.round (Dight*Math.pow(10,How))/Math.pow(10,How); 			 
			return Dight; 			 
		} ,
		
		
		
		
	};
}();

