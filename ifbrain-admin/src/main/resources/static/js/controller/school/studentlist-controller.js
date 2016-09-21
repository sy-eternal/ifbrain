var StudentController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/student/searchlist";
		//var url ="";
		$.get(url,function(data) {
			console.log(JSOG.decode(data))
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true,
				data : JSOG.decode(data),
				columns: [
						    
						    { 
						    	data: "schoolClass.school.scName"
						    },
						   { 
						    	data: "schoolClass.name"
						    
						    },
						    { 
						    	data: "studentName"
						    
						    },
						    { 
						    	data: "createTime"
						    
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var 	   link ="&nbsp;<a href='javascript: StudentController.deleteStudent(" + id + ")'>删除</a>";
	                	   link+="&nbsp;<a href='/student/studentupdate/" + id + "'>修改</a>";
	                	   return link;
	                   },
	                   targets: 4
	               }
	            ]
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		},
		deleteStudent: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/student/" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();


//查询试卷
function searchquestion(){
	$("#Exam").each(function(){
        $(this).find("option").each(function(){
                if($(this).val() ==""){
                    alert("请选择试卷!");
                }else{
                	window.location.href="/student/searchquestion?examid="+$(this).val();
                }
        });
    });
}
$(function(){
	$(".namecontent").each(function(){
		$(this).html($(this).siblings(".questionNameContent").val());
		$(this).siblings(".questioncontent").html($(this).siblings(".questionOptionContent").val());
	});
	var examid=$("#examid").val();
	$("#Exam").each(function(){
        $(this).find("option").each(function(){
                if($(this).val() ==examid){
                    $(this).attr("selected","selected");
                }
        });
    });
	
});
function getPaystatus(){
	var answer="";
	//支付状态
$('input[name="status"]:checked').each(function(){ 
       $("#paystatus").val($(this).val()); 
   }); 
var size=$("#size").val();
for(i=1;i<=size;i++){
	answer += $("input[name='checkradio"+i+"']:checked").val()+",";
}
  $("#checkanswer").val(answer)
}

//查询试卷
function searchexam(){
	$("#Exam").each(function(){
        $(this).find("option").each(function(){
                if($(this).val() ==""){
                    alert("请选择试卷!");
                }else{
                	window.location.href="/student/searchexamquestion?examid="+$(this).val()+"&studentid="+$("#id").val();
                }
        });
    });
}

function getStudentupdate(){
	var answer="";
	//支付状态
	$('input[name="status"]:checked').each(function(){ 
	       $("#paystatus").val($(this).val()); 
	   }); 
	var size=$("#size").val();
	for(i=1;i<=size;i++){
		answer += $("input[name='checkradio"+i+"']:checked").val()+",";
	}
	  $("#checkanswer").val(answer)
}