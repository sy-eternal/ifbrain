
var SpecialCarPlanController = function () {
var oTable;

           var showTable = function () {
        	var url ="/specialplancar/search";
    		$.get(url,function(specialcarrate) {
    	  console.log(JSOG.decode(specialcarrate));
               oTable = $("#table").dataTable({
        	language : {
				url : "/js/lib/datatables/Chinese.json"
			},

			lengthChange: false,
			ordering: true,
			processing: true,
			paging : false,
			searching: false,
			serverSide: false,
			data : JSOG.decode(specialcarrate),
			columns: [
			          {data:null},
			          { data: "specialcar.cartype" },
			          { data: "specialcar.supplier.cnName" },

			          { data: "carrateprice" },//3
			          { data: null },//4
			          {data :null},//5
			          { data: null },//6

			          ],
         columnDefs: [
{
				                        	
				                        
				                        	render : function(data, type, row) {
				                        		var id = row.id;
				                        		var link="";
				                        		link="<input  type='text'   name='carrateprice' id='carrateprice"+id+"' readonly='readonly' required='required' value='"+row.carrateprice+"'/>";
				                        		return link;
				                        	},
				                        	targets : 3
				                        }
				                        ,{
				                        	render : function(data, type, row) {
				                        		var id = row.id;
				                        		var link="";
				                        		link="<input type='number' min='0' name='personCount'  id='personCount"+id+"' value='0'  onchange='javascript: SpecialCarPlanController.changepersonCount("+id+")'/>" ;
				                        		return link;
				                        	},
				                        	targets : 4

				                        },{
				                        	render : function(data, type, row) {
				                        		var id = row.id;
				                        		var link="<input type='text'  name='subTotalAmount'  id='subTotalAmount"+id+"' required='required'  readonly='readonly'/>";
				                        		return link;
				                        	},
				                        	targets : 5
				                        },{
				                        render : function(data, type, row) {
			                        		var id = row.id;
			                        		var link="";
			                        		link="<input  type='checkbox' name='check'  id='check"+id+"' disabled='disabled' onclick='javascript: SpecialCarPlanController.changecheck("+id+")''/>"+
			                        		"<input type='hidden' name='specialcarId'  id='specialcarId"+id+"' value='"+row.id+"'/>";
			                        		return link;
			                        	},
			                        	targets : 6
			                        },
] ,		
         
                   //多语言配置
                   "fnCreatedRow": function(nRow, aData, iDataIndex) {
                       $('td:eq(0)', nRow).html("<span class='row-details row-details-close' ></span>&nbsp;");
                   }
               });
         
               $('.table').on('click', ' tbody td .row-details',      function() {
                           var nTr = $(this).parents('tr')[0];
                           if (oTable.fnIsOpen(nTr)) //判断是否已打开
                           {
                               $(this).addClass("row-details-close").removeClass("row-details-open");
                               oTable.fnClose(nTr);
                           } else {
                               $(this).addClass("row-details-open").removeClass("row-details-close");
               oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr), 'details');
                           }
                       });
           });
         
          function fnFormatDetails(oTable, nTr) {
             var aData = oTable.fnGetData(nTr);
             console.log(oTable.fnGetData(nTr));
             var sOut = '<table>';
             /*sOut += '<tr><td>租车类型:</td><td>' + aData.specialcar.cartype + '</td></tr>';
             sOut += '<tr><td>供应商名称:</td><td>' + aData.specialcar.supplier.cnName + '</td></tr>';*/
             sOut += '<tr><td>介绍:</td><td>' + aData.specialcar.count + '</td></tr>';
             sOut += '</table>';

             return sOut;
             
               }
        };



	return {
		init : function () {
			showTable();
		},
		changepersonCount: function (id) {
			//销售价
			var carrateprice = $("#carrateprice"+id).val();
			//数量
			var personCount = $("#personCount"+id).val();
			if(personCount<=0){
				$("#check"+id).attr('disabled',true);
			}else{
				$("#check"+id).attr('disabled',false);
			}
			//小计
			var subTotalAmoun = parseFloat(personCount*carrateprice);
			$("#subTotalAmount"+id).val(subTotalAmoun);
			var totalprices = parseFloat($("#totalprices").val());
			//合计
			var totalPrice = parseFloat(totalprices+subTotalAmoun);
//			$("#totalprices").val(totalPrice);
		},
		changecheck: function(id){
			var specialcarIds = $("#specialcarIds").val();
			var personCounts =   $("#personCounts").val();
			var subTotalAmounts = $("#subTotalAmounts").val();
			if($("#check"+id).prop("checked")){
				var carrateprice = $("#carrateprice"+id).val();
				//数量
				var personCount = $("#personCount"+id).val();
				if(personCount<=0){
					$("#check"+id).attr('disabled',true);
				}else{
					$("#check"+id).attr('disabled',false);
				}
				//小计
				var subTotalAmoun = parseFloat(personCount*carrateprice);
				$("#subTotalAmount"+id).val(subTotalAmoun);
				var totalprices = parseFloat($("#totalprices").val());
				//合计
				var totalPrice = parseFloat(totalprices+subTotalAmoun);
				$("#totalprices").val(totalPrice);
				
				
				$("#personCount"+id).attr('disabled',true);
				var personCount = $("#personCount"+id).val();
				var  subTotalAmoun = $("#subTotalAmount"+id).val();
				var specialcarId = $("#specialcarId"+id).val();
				//拼接数据
				specialcarIds+=specialcarId+",";
				personCounts+=personCount+",";
				subTotalAmounts+=subTotalAmoun+",";
				$("#specialcarIds").val(specialcarIds);
				$("#personCounts").val(personCounts);
				$("#subTotalAmounts").val(subTotalAmounts);
			}else{
				$("#check"+id).attr('disabled',true);
				$("#personCount"+id).attr('disabled',false);
				$("#personCount"+id).val(0);
				$("#totalprices").val($("#totalprices").val()-$("#subTotalAmount"+id).val());
				$("#subTotalAmount"+id).val(0);
				
				if (specialcarIds.indexOf(id+",") > 0){
					 
					var idIndex = specialcarIds. indexOf(","+id+",")+1;
					var n=(specialcarIds.substr(0, idIndex).split(",")).length-1;
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
					specialcarIds=specialcarIds.replace(","+id+",",",");
				}
				if (specialcarIds.indexOf(id+",") == 0){
					var index =personCounts.indexOf(",") + 1;
					var index1 =subTotalAmounts.indexOf(",") + 1;
					personCounts = personCounts.substr(index);
					subTotalAmounts = subTotalAmounts.substr(index1);
					specialcarIds=specialcarIds.replace(id+",","");   
				}
				$("#specialcarIds").val(specialcarIds);
				$("#personCounts").val(personCounts);
				$("#subTotalAmounts").val(subTotalAmounts);
			}
		}
	};
}();
