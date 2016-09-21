 
$(function(){
	var finish=$("#finish").val();
	var obj1 = finish.split(",");
	var unfinish=$("#unfinish").val();
	var obj2 = unfinish.split(",");
	var name=$("#name").val();
	var n=name.split(",");
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
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                option = {
                	    tooltip : {
                	        trigger: 'axis',
                	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                	        }
                	    },
                	    legend: {
                	        data:['完成', '未完成']
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	            mark : {show: true},
                	            dataView : {show: true, readOnly: false},
                	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    xAxis : [
                	        {
                	            type : 'value'
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'category',
                	            data : n //任务名字
                	        }
                	    ],
                	    series : [
                	              {
                	                  name:'完成',
                	                  type:'bar',
                	                  stack: '总量',
                	                  itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                	                  data:obj1
                	              },
                	              {
                	                  name:'未完成',
                	                  type:'bar',
                	                  stack: '总量',
                	                  itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                	                  data:obj2
                	              }
                	    ]
                	};
                	                    
           /* })*/
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
        
        
});