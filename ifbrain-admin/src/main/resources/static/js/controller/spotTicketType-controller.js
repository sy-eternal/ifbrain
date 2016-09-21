var SpotTicketTypeController = function () {
	var table =  $("#table"); 
	  table.on('click', ' tbody td .row-details',      function() {
		                    var nTr = $(this).parents('tr')[0];
		console.log("判断是否打开");
		                    if (_dataTable.fnIsOpen(nTr)) //判断是否已打开
			                   {
			                        //This row is already open - close it 
			                       $(this).addClass("row-details-close").removeClass("row-details-open");
			                       _dataTable.fnClose(nTr);
			                   } else {
				                       // Open this row 
				                       $(this).addClass("row-details-open").removeClass("row-details-close");
				_dataTable.fnOpen(nTr, fnFormatDetails(_dataTable, nTr), 'details');
				                   }
		               });
	function fnFormatDetails(_dataTable, nTr) {
		var aData = _dataTable.fnGetData(nTr);
		console.log("进拼接啦");
		var sOut = '<table>';
		sOut += '<tr><td>包含项目</td><td>'+aData.includingItem+'</td></tr>';
		sOut += '</table>';
		return sOut; 
		   }
	var _dataTable = null;
	    var showTable = function () {
		var spotId = $('#spotId').val();
		var url ="/spotplan/searchTicketType?spotId="+spotId;
		//var table =  $("#table");      
		$.get(url,function(spotIterable) {
			console.log(JSOG.decode(spotIterable));
			       _dataTable = $("#table").dataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},

				lengthChange: false,
				ordering: true,
				processing: true,
				paging : false,
				searching: false,
				serverSide: false,
				destroy: true,
				data : JSOG.decode(spotIterable),
				columns: [
				         
				          { data: null },
				          { data: "type" },
				          { data: "price" },
				          { data: ""},
				          { data: ""},
				          {data:null},
				          ],
				          columnDefs: [
{
	render : function(data, type, row) {
		var id = row.id;
		var link="";
		link="<input  type='checkbox' name='check'  id='check"+id+"' disabled='disabled' onclick='javascript: SpotTicketTypeController.changecheck("+id+")''/>"+
		"<input type='hidden' name='spotTicketTypeId'  id='spotTicketTypeId"+id+"' value='"+row.id+"'/>";
		return link;
	},
	targets : 5
},
{
	render : function(data, type, row) {
		var id = row.id;
		var link="";
		link="<input  type='text' name='price' id='price"+id+"' readonly='readonly' required='required' value='"+row.price+"'/>";
		return link;
	},
	targets : 2
}
,{
	render : function(data, type, row) {
		var id = row.id;
		var link="";
		link="<input type='number' min='0' name='personCount'  id='personCount"+id+"' value='0'  onchange='javascript: SpotTicketTypeController.changepersonCount("+id+")'/>" ;
		return link;
	},
	targets : 3

},{
	render : function(data, type, row) {
		var id = row.id;
		var link="<input type='text'  name='subtotalAmount'  id='subtotalAmount"+id+"' required='required'  readonly='readonly'/>";
		return link;
	},
	targets : 4
}
] ,     
           //多语言配置
           // set the initial value
           "fnCreatedRow": function(nRow, aData, iDataIndex) {
	               $('td:eq(0)', nRow).html("<span class='row-details row-details-close' ></span>&nbsp;");
	           }
				       });
			   });
	};


	return {
		init : function () {
			showTable();
		},
		search: function(){
			//_dataTable.destroy();
			showTable();
		},
		changepersonCount: function (id) {
			//景点销售价
			var price = $("#price"+id).val();
			//投保人数
			var personCount = $("#personCount"+id).val();
			if(personCount<=0){
				$("#check"+id).attr('disabled',true);
			}else{
				$("#check"+id).attr('disabled',false);
			}
			//小计
			var subTotalAmoun = parseFloat(personCount*price);
			$("#subtotalAmount"+id).val(subTotalAmoun);
			/*var totalprices = parseFloat($("#totalprices").val());
			//合计
			var totalPrice = parseFloat(totalprices+subTotalAmoun);
			$("#totalprices").val(totalPrice);*/
		},
		changecheck: function(id){
			var spotTicketTypeIds = $("#spotTicketTypeIds").val();
			var personCounts =   $("#personCounts").val();
			var subTotalAmounts = $("#subTotalAmounts").val();

			if($("#check"+id).prop("checked")){
				$("#personCount"+id).attr('disabled',true);
				var personCount = $("#personCount"+id).val();
				var  subTotalAmoun = $("#subtotalAmount"+id).val();
				var spotTicketTypeId = $("#spotTicketTypeId"+id).val();
				//

				//拼接数据

				spotTicketTypeIds+=spotTicketTypeId+",";
				personCounts+=personCount+",";
				subTotalAmounts+=subTotalAmoun+",";
				$("#spotTicketTypeIds").val(spotTicketTypeIds);
				$("#personCounts").val(personCounts);
				$("#subTotalAmounts").val(subTotalAmounts);
				
				//合计
				var price = $("#price"+id).val();
				var subTotalAmoun = parseFloat(personCount*price);
				var totalprices = parseFloat($("#totalprices").val());
				var totalPrice = parseFloat(totalprices+subTotalAmoun);
				$("#totalprices").val(totalPrice);
			}else{
				$("#check"+id).attr('disabled',true);
				$("#personCount"+id).attr('disabled',false);
				$("#personCount"+id).val(0);
				var  subtotalAmount = $("#subtotalAmount"+id).val();
				var totalprices = parseFloat($("#totalprices").val());
				var totalPrice = parseFloat(totalprices-subtotalAmount);
				$("#totalprices").val(totalPrice);
				$("#subtotalAmount"+id).val("")
				if (spotTicketTypeIds.indexOf(id+",") > 0){
					var idIndex = spotTicketTypeIds. indexOf(","+id+",")+1;
					var n=(spotTicketTypeIds.substr(0, idIndex).split(",")).length-1;
					var index1;
					var predex1;
					var re1 = /,/g;
					var count1 = 0;
					while ((arr = re1.exec(subTotalAmounts)) != null) {
						count1++;
						if (count1 == n) {
							predex1 = arr.index;
						}
						if (count1 == n+1) {
							index1 = arr.index;
							break;
						}
					}

					var index ;
					var predex;
					var re = /,/g;
					var count = 0;
					while ((arr = re.exec(personCounts)) != null) {
						count++;
						if (count == n) {
							predex = arr.index;
						}
						if (count == n+1) {
							index = arr.index;
							break;
						}
					}

					subTotalAmounts = subTotalAmounts.substr(0,predex1) + subTotalAmounts.substr(index1);
					personCounts = personCounts.substr(0,predex) + personCounts.substr(index);
					spotTicketTypeIds=spotTicketTypeIds.replace(","+id+",",",");

				}
				if (spotTicketTypeIds.indexOf(id+",") == 0){
					var index =personCounts.indexOf(",") + 1;
					var index1 =subTotalAmounts.indexOf(",") + 1;
					personCounts = personCounts.substr(index);
					subTotalAmounts = subTotalAmounts.substr(index1);
					spotTicketTypeIds=spotTicketTypeIds.replace(id+",","");   
				}
				$("#spotTicketTypeIds").val(spotTicketTypeIds);
				$("#personCounts").val(personCounts);
				$("#subTotalAmounts").val(subTotalAmounts);
			}
		},
		ForDight: function(Dight,How){			 
			var Dight = Math.round (Dight*Math.pow(10,How))/Math.pow(10,How); 			 
			return Dight; 			 
		} ,

	};
}();

