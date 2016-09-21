var UserController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/guidecar/search",
			columns: [
			    { data: "carType"},
			    { data: "productionDate"},
			    { data: "excursionStatus"},
			    { data: "guideCarManage.classes"},
			    { data: "guideCarManage.cost"},
			    { data: "guideCarManage.excursionCost"},
			    { data: null, orderable: false }
			],
			columnDefs: [
			             {
							   render: function (data, type, row) {
								 var excursionStatus=row.excursionStatus;
								 var link="";
								   if(excursionStatus==1){
									   link="是";
								   }if(excursionStatus==0){
									   link="否";
								   }
								    return  link;
								   },
										targets: 2
					 },
			        {
					   render: function (data, type, row) {
						   var approvalStatus=row.approvalStatus;
						   var link="";
						        if(approvalStatus==2){
						        	link=row.guideCarManage.classes;
						        }
									return link;
						   },
								targets: 3
					 },
			     	 {
							render: function (data, type, row) {
							       var approvalStatus=row.approvalStatus;
								var link="";
								if(approvalStatus==2){
									link=row.guideCarManage.cost;
								}
								return link;
							},
							targets: 4
						},
						{
									render: function (data, type, row) {
									var excursionStatus=row.excursionStatus;
									       var excursionCost= row.excursionCost;
									       var approvalStatus=row.approvalStatus;
										var link="";
										if(excursionStatus==1 && excursionCost!="" && approvalStatus==2 ){
											link=row.guideCarManage.excursionCost;
										}
										return link;
									},
									targets: 5
								},
                          {
			                   render: function (data, type, row) {
			                	   var approvalStatus=row.approvalStatus;
			                	   var link="审核未通过" ;
			                	   if(approvalStatus==1){
			                		   link = "正在审核";
			                	   } if(approvalStatus==2){
			                		   link = "审核通过";
			                	   }
			                	   return link;
			                   },
			                   targets: 6
			               }
			           ]
		});
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		}
	};
}();

$(function () {
	UserController.init();
});
