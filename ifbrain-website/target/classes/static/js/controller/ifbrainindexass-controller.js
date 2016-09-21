 $(function(){
	 
	 var name=$("#varName").val();
	 var names=name.split(",");
	 
	 var value=$("#varValue").val();
	 var valueobject=eval(value);
	 
	
	 
// 路径配置
        require.config({
            paths:{ 
                'echarts' : 'js/echarts',
                'echarts/chart/bar' : 'js/echarts'
            }
        });
        
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar',
                'echarts/chart/pie'// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('classscore')); 
                
               
                
                option = {

                	    title : {
                	        text: ''
                	    },
                	    tooltip : {
                	        trigger: 'axis'
                	    },
                	    legend: {
                	        data:['应用','知识']
                	    },
                	    toolbox: {
                	        show : true,
                	       
                	    },
                	    calculable : true,
                	    xAxis : [
                	        {
                	            type : 'category',
                	            data : names
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value'
                	        }
                	    ],
                	    series : valueobject

                };
                	var ecConfig = require('echarts/config');
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
        
        
 });