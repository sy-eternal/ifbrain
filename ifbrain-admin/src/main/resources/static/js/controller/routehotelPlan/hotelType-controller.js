var hotelTypeController = function () {
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
		sOut += '<tr><td>房间类型介绍信息</td><td>'+aData.description+'</td></tr>';
		sOut += '</table>';
		return sOut; 
		   }
	var _dataTable = null;
	    var showTable = function () {
		
		var hotelId = $('#hotelId').val();
		/*var toCity=$('#toCity').val();
		var roomNum=$('#roomNum').val();
		var startDate=$('#startDate').val();
		var endDate=$('#endDate').val();
		var datePlanId=$('#datePlanId').val();*/
		
		var url ="/hotelPlan/searchType?id="+hotelId;
		$.get(url,function(hotelRoomType) {
			console.log(JSOG.decode(hotelRoomType));
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
				data : JSOG.decode(hotelRoomType),
				columns: [
				          {data:null},
				          { data: "roomType" },
				          { data: "promotionContent" },
				          { data: "perNightPrice" },
				          { data: null }
				          ],
				          columnDefs: [
{
	render : function(data, type, row) {
		     var id = row.id;
		       var link="";
		     link="<input type='number' min='0' name='perNightPrice' readonly='readonly'style='border:none' id='perNightPrice"+id+"' value='"+row.perNightPrice+"'  />" ;
		      return link;
				                   	},
				                   	targets : 3
				                       },
				                       
{
	render : function(data, type, row) {
		     var id = row.id;
		       var link="";
		     link="<input type='number' min='0' name='roomCount'  id='roomCount"+id+"' value='0'  onchange='javascript: hotelTypeController.changepersonCount("+id+")'/>" ;
		      return link;
				                   	},
				                   	targets : 4
				                       },
				                       {
				                    		render : function(data, type, row) {
				                    			     var id = row.id;
				                    			       var link="";
				                    			     link="<input type='number' min='0'readonly='readonly'style='border:none' name='subtotalAmount'  id='subtotalAmount"+id+"'  />" ;
				                    			      return link;
				                    					                   	},
				                    					                   	targets : 5
				                    					                       },
{
	render : function(data, type, row) {
		
		var id = row.id;
		var link="";
		link="<input  type='checkbox' name='check'  id='check"+id+"' disabled='disabled' onclick='javascript: hotelTypeController.changecheck("+id+")'/>"+
		"<input type='hidden' name='roomTypeId'  id='roomTypeId"+id+"' value='"+row.id+"'/>";;
		return link;
	},
	targets : 6
}
],     
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
			//价格
			var perNightPrice = $("#perNightPrice"+id).val();
			//房间数
			var roomCount = $("#roomCount"+id).val();
			if(roomCount<=0){
				$("#check"+id).attr('disabled',true);
			}else{
				$("#check"+id).attr('disabled',false);
			}
			//小计
			var subTotalAmoun = parseFloat(roomCount*perNightPrice);
			$("#subtotalAmount"+id).val(subTotalAmoun);
			
			
		},
		changecheck: function(id){
			var roomTypeIds = $("#roomTypeIds").val();
			var roomCounts =   $("#roomCounts").val();
			var subTotalAmounts = $("#subTotalAmounts").val();
    
			if($("#check"+id).prop("checked")){
				$("#roomCount"+id).attr('disabled',true);
				var roomCount = $("#roomCount"+id).val();
				var  subTotalAmount = $("#subtotalAmount"+id).val();
				var roomTypeId = $("#roomTypeId"+id).val();
				//拼接数据
				roomTypeIds+=roomTypeId+",";
				roomCounts+=roomCount+",";
				subTotalAmounts+=subTotalAmount+",";
				$("#roomTypeIds").val(roomTypeIds);
				$("#roomCounts").val(roomCounts);
				$("#subTotalAmounts").val(subTotalAmounts);
				//计算总金额
				
				var perNightPrice=$("#perNightPrice"+id).val();
				var subTotalAmoun = parseFloat(roomCount*perNightPrice);
				var totalprices = parseFloat($("#totalprices").val());
				var totalPrice = parseFloat(totalprices+subTotalAmoun);
				$("#totalprices").val(totalPrice);
				
			}else{
				$("#check"+id).attr('disabled',true);
				$("#roomCount"+id).attr('disabled',false);
				$("#roomCount"+id).val(0);
				
				var  subTotalAmoun = $("#subtotalAmount"+id).val();
				$("#subtotalAmount"+id).val(0);
				
				var totalprices = parseFloat($("#totalprices").val());
				var totalPrice = parseFloat(totalprices-subTotalAmoun);
				$("#totalprices").val(totalPrice);
				
				if (roomTypeIds.indexOf(id+",") > 0){
					var idIndex = roomTypeIds.indexOf(","+id+",")+1;
					var n=(roomTypeIds.substr(0, idIndex).split(",")).length-1;
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
					while ((arr = re.exec(roomCounts)) != null) {
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
					roomCounts = roomCounts.substr(0,predex) + roomCounts.substr(index);
					roomTypeIds=roomTypeIds.replace(","+id+",",",");

				}
				if (roomTypeIds.indexOf(id+",") == 0){
					var index =roomCounts.indexOf(",") + 1;
					var index1 =subTotalAmounts.indexOf(",") + 1;
					roomCounts = roomCounts.substr(index);
					subTotalAmounts = subTotalAmounts.substr(index1);
					roomTypeIds=roomTypeIds.replace(id+",","");   
				}
				$("#roomTypeIds").val(roomTypeIds);
				$("#roomCounts").val(roomCounts);
				$("#subTotalAmounts").val(subTotalAmounts);
			}
		},
		ForDight: function(Dight,How){			 
			var Dight = Math.round (Dight*Math.pow(10,How))/Math.pow(10,How); 			 
			return Dight; 			 
		} ,

	};
}();

hotelTypeController.init();

