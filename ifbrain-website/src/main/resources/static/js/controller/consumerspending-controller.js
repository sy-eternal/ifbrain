 $(function(){
	 var name=$("#consumerpendingBuffer").val();
	 var names=name.split(",");
	 
	 var value=$("#consumerpendingvalue").val();
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
                var myChart = ec.init(document.getElementById('consumerspending')); 
                option = {
                	    tooltip : {
                	        trigger: 'axis',
                	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                	        }
                	    },
                	    legend: {
                	        data: ['收入', '消费']
                	    },
                	    grid: {
                	        left: '3%',
                	        right: '4%',
                	        bottom: '3%',
                	        containLabel: true
                	    },
                	    xAxis:  {
                	        type: 'value'
                	    },
                	    yAxis: {
                	        type: 'category',
                	        data: /*['周一','周二','周三','周四','周五','周六','周日']*/names
                	    },
                	    series: valueobject/*[
                	        {
                	            name: '收入',
                	            type: 'bar',
                	            stack: '总量', 
                	            data: [320, 302, 301, 334, 390, 330, 320]
                	        },
                	        {
                	            name: '消费',
                	            type: 'bar',
                	            stack: '总量',
                	            data: [120, 132, 101, 134, 90, 230, 210]
                	        },
                	    ]*/
};
                	                    
                

                	var ecConfig = require('echarts/config');
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
        
        
 });