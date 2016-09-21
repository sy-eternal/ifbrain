var TokensBankController = function () {
	var _dataTable = null;

	var showTable = function (ifbrainindexlist) {
		//var url ="/tokensbank/search?ifbrainindexlist="+ifbrainindexlist;
		var url="";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: false,
	            ordering: true,
				processing: true,
				/*data : JSOG.decode(data),
				columns: [
						    { data: "id", visible: false },
						    {data:null},
						    { data: null },
						    { data: null },
						
						    { data: null },
						    { data: null },
						],
				columnDefs : [{
	                   render: function (data, type, row) {
	                	   var ordinalNumber=row.ordinalNumber
	                	  var link = "第"+ordinalNumber+" 节";
	                	  return link;
	                   },
	                   targets: 1
	               },{
	                   render: function (data, type, row) {
	                	   var id=row.id;
	                	   var childname=row.child.name
	                	  var link = childname;"<a href='/tokensbank/detail/" + id + "'>"+childname+"</a>"
	                	  return link;
	                   },
	                   targets: 2
	               },{
	                   render: function (data, type, row) {
	                	return data.income;
	                   },
	                   targets: 3
	               },{
	                   render: function (data, type, row) {
	                	   if(data.expense==null){
	                		   return 0;
	                	   }else{
	                			return data.expense;
	                	   }
		                	
		                   },
		                   targets: 4
		               },{
	                   render: function (data, type, row) {
	                	 
	                	   var knowledgetotalScore=data.knowledgetotalScore
		                	var applicationtotalScore=data.applicationtotalScore
		                	var bankAfterclassIncome=data.bankAfterclassIncome
		                	var supplementaryTokens =data.supplementaryTokens
		                	var dataexpen=Number(knowledgetotalScore)+Number(applicationtotalScore)+Number(bankAfterclassIncome)+Number(supplementaryTokens);
	                	    var bankPrimarysIfbrainindex =data.bankPrimarysIfbrainindex;
	                	   if(row.expense==null){
	                		   var yue =Number(dataexpen)+Number(bankPrimarysIfbrainindex)
	                	   }else{
	                		   var yue =Number(dataexpen)+Number(bankPrimarysIfbrainindex)-Number(data.expense)
	                	   }
	                	   
	                	   
	                	   return data.balance;
		                 
	                   },
	                   targets: 5
	               }]*/
			});
		})
	
	};
	
	return {
		init : function () {
			var ifbrainindexlist = $("#ifbrainindexlist").val();
			showTable(ifbrainindexlist);
		
		}
	};
}();


