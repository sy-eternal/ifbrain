/*var VisaInstructionController=function(){
		var _dataTable = null;

		var showTable = function() {
			_dataTable = $("#table")
					.DataTable(
							{
								language : {
									url : Web.contextPath + "/js/lib/datatables/Chinese.json"
								},
								lengthChange : false,
								searching : false,
								ordering : true,
								processing : true,
								serverSide : true,
								bPaginate:false,
								ajax : Web.contextPath + "/visainstruction/search",
								columns : [ {
									data : "id",
									visible : false
								}, {
									data : "processName"
								},
								{
									data : "image.updateTime"
								}, 
//								{
//									data : "updatetime"									
//								}, 
								{
									data : null,
									orderable : false
								} ],
								columnDefs : [
										{
											render : function(data, type, row) {
												var visainstructionId = row.id;
												if(row.id==1){
												var link = "<a href='"
														+ Web.contextPath
														+ "/visainstruction/updateimg/"
														+ visainstructionId + "'>修改图片</a>";
													link +="&nbsp;&nbsp;<a href='"
														+ Web.contextPath
														+ "/visainstruction/updatefile/"
														+ visainstructionId + "'>修改文件</a>";
												return link;
												}else{
													var link = "<a href='"
														+ Web.contextPath
														+ "/visainstruction/updateimg/"
														+ visainstructionId + "'>修改图片</a>";
													return link;
												}
											},
											targets :3
										} 
										
										
										]
							});
		};

		return {
			// main function to initiate the module
			init : function() {
				showTable();
			},
		};
	}();
*/

var VisaInstructionController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		var url ="/visainstruction/search";
		$.get(url,function(data) {
			console.log(JSOG.decode(data));
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: true,
				processing: true,
				data : JSOG.decode(data),
				columns : [ {
					data : "id",
					visible:false
					
				}, {
					data : "visainstruction"
				},
				{
					data : "visainstruction"
				}, 
				{
					data : "document"									
				}, 
				{
					data : null,
					orderable : false
				} ],
				columnDefs : [{
					render : function(data, type, row) {
						if(!data){
							return "";
						}
						return row.id;
					  },
				         targets :0
					},{
						render : function(data, type, row) {
							if(!data){
								return "";
							}
							return data.processName;
						},
						targets :1
						},{
							render : function(data, type, row) {
								if(!data){
									return "";
								}
								return data.image.updateTime;
							},
							targets :2
						},{
							render : function(data, type, row) {
								if(!data){
									return "";
								}
								return data.updateTime;
							},
							targets :3
						},
						{
							render : function(data, type, row) {
								var visainstructionId = row.id;
								var visainstruction=row.visainstruction.id;
								if(visainstruction==1){
								var link = "<a href='"
										+ Web.contextPath
										+ "/visainstruction/updateimg/"
										+ visainstruction + "'>修改图片</a>";
									link +="&nbsp;&nbsp;<a href='"
										+ Web.contextPath
										+ "/visainstruction/updatefile/"
										+ visainstruction + "'>修改文件</a>";
								return link;
								}else{
									var link = "<a href='"
										+ Web.contextPath
										+ "/visainstruction/updateimg/"
										+ visainstruction + "'>修改图片</a>";
									return link;
								}
							},
							targets :4
						}
						]
			});
		})
	
		
		
		
		
		
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

	};
}();

