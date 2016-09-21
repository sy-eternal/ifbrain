var InsuranceActivityController = function () {
	var table =  $("#table"); 
	  table.on('click', ' tbody td .row-details',      function() {
		                    var nTr = $(this).parents('tr')[0];
		console.log("判断是否打开");
		                    if (_dataTable.fnIsOpen(nTr)) //判断是否已打开
			                   {
			                       /* This row is already open - close it */
			                       $(this).addClass("row-details-close").removeClass("row-details-open");
			                       _dataTable.fnClose(nTr);
			                   } else {
				                       /* Open this row */
				                       $(this).addClass("row-details-open").removeClass("row-details-close");
				_dataTable.fnOpen(nTr, fnFormatDetails(_dataTable, nTr), 'details');
				                   }
		               });
	function fnFormatDetails(_dataTable, nTr) {
		var aData = _dataTable.fnGetData(nTr);
		console.log("进拼接啦");
		var sOut = '<table>';
		sOut += '<tr><td>保险范围</td><td>'+aData.insuranceActivity.insuranceCove+'</td></tr>';
		sOut += '</table>';
		return sOut; 
		   }
	var _dataTable = null;
	    var showTable = function () {
		var insuranceDuration = $('#insuranceDuration').val();
		var holderType = $('#holderType').val();
		var url ="/insurancePlan/search?insuranceDuration=" + insuranceDuration + "&holderType=" + holderType;
		//var table =  $("#table");      
		$.get(url,function(insuranceRates) {
			console.log(JSOG.decode(insuranceRates));
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
				data : JSOG.decode(insuranceRates),
				columns: [
				          {data:null},
				          { data: "insuranceActivity.insuranceType" },
				          { data: "insuranceActivity.insuranceName" },
				          { data: "insuranceActivity.supplier.cnName"},
				          { data: "insuranceRatePrice" },
				          { data: null },
				          { data: null },
				          { data: null }
				          ],
				          columnDefs: [
{
	render : function(data, type, row) {
		var id = row.id;
		var link="";
		link="<input  type='checkbox' name='check'  id='check"+id+"' disabled='disabled' onclick='javascript: InsuranceActivityController.changecheck("+id+")''/>"+
		"<input type='hidden' name='insuranceactivityId'  id='insuranceactivityId"+id+"' value='"+row.id+"'/>";
		return link;
	},
	targets : 7
},
{
	render : function(data, type, row) {
		var id = row.id;
		var link="";
		link="<input  type='text' name='insuranceRatePrice' id='insuranceRatePrice"+id+"' readonly='readonly' required='required' value='"+row.insuranceRatePrice+"'/>";
		return link;
	},
	targets : 4
}
,{
	render : function(data, type, row) {
		var id = row.id;
		var link="";
		link="<input type='number' min='0' name='personCount'  id='personCount"+id+"' value='0'  onchange='javascript: InsuranceActivityController.changepersonCount("+id+")'/>" ;
		return link;
	},
	targets : 5

},{
	render : function(data, type, row) {
		var id = row.id;
		var link="<input type='text'  name='subTotalAmount'  id='subTotalAmount"+id+"' required='required'  readonly='readonly'/>";
		return link;
	},
	targets : 6
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
			//保险销售价
			var insuranceRatePrice = $("#insuranceRatePrice"+id).val();
			//投保人数
			var personCount = $("#personCount"+id).val();
			if(personCount<=0){
				$("#check"+id).attr('disabled',true);
			}else{
				$("#check"+id).attr('disabled',false);
			}
			//小计
			var subTotalAmoun = parseFloat(personCount*insuranceRatePrice);
			$("#subTotalAmount"+id).val(subTotalAmoun);
			
		},
		changecheck: function(id){
			var insuranceactivityIds = $("#insuranceactivityIds").val();
			var personCounts =   $("#personCounts").val();
			var subTotalAmounts = $("#subTotalAmounts").val();

			if($("#check"+id).prop("checked")){
			
				
				var insuranceactivityId = $("#insuranceactivityId"+id).val();
				
				//
				var personCount = $("#personCount"+id).val();
				var subTotalAmoun = parseFloat($("#subTotalAmount"+id).val());
				//var totalprices = parseFloat($("#totalprices").val());
				//合计
				var totalprices = parseFloat($("#totalprices").val());
				var totalPrice = parseFloat(totalprices+subTotalAmoun);	
				$("#totalprices").val(totalPrice);

				//拼接数据

				insuranceactivityIds+=insuranceactivityId+",";
				personCounts+=personCount+",";
				subTotalAmounts+=subTotalAmoun+",";
				$("#insuranceactivityIds").val(insuranceactivityIds);
				$("#personCounts").val(personCounts);
				$("#subTotalAmounts").val(subTotalAmounts);
			}else{
				$("#check"+id).attr('disabled',true);
				$("#personCount"+id).attr('disabled',false);
				$("#personCount"+id).val(0);
				var personCount = $("#personCount"+id).val();
				var subTotalAmoun = parseFloat($("#subTotalAmount"+id).val());
				//var totalprices = parseFloat($("#totalprices").val());
				//合计
				var totalprices = parseFloat($("#totalprices").val());
				var totalPrice = parseFloat(totalprices-subTotalAmoun);	
				$("#totalprices").val(totalPrice);
				if (insuranceactivityIds.indexOf(id+",") > 0){
					var idIndex = insuranceactivityIds. indexOf(","+id+",")+1;
					var n=(insuranceactivityIds.substr(0, idIndex).split(",")).length-1;
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
					insuranceactivityIds=insuranceactivityIds.replace(","+id+",",",");

				}
				if (insuranceactivityIds.indexOf(id+",") == 0){
					var index =personCounts.indexOf(",") + 1;
					var index1 =subTotalAmounts.indexOf(",") + 1;
					personCounts = personCounts.substr(index);
					subTotalAmounts = subTotalAmounts.substr(index1);
					insuranceactivityIds=insuranceactivityIds.replace(id+",","");   
				}
				$("#insuranceactivityIds").val(insuranceactivityIds);
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

