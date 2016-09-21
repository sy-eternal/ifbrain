 $(function(){
	 
	 var ifbrainindexname=$("#ifbrainindexnamebank").val();
	 var name=ifbrainindexname.split(",");
	 
	 var ifbrainindexvalue=$("#ifbrainindexvaluebank").val();
	 var ifbrainindex=eval(ifbrainindexvalue);
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
                var myChart = ec.init(document.getElementById('ifbrainindexbank')); 
                option = {
                	    tooltip : {
                	        trigger: 'axis'
                	    },
                	    legend: {
                	        data:['财脑指数','当前课程财脑得分=知识+应用+课后训练']
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
                	            type : 'category',
                	            boundaryGap : false,
                	            data : name
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value'
                	        }
                	    ],
                	    series : ifbrainindex
                	};
                	                    
                /*option = {
                	    tooltip: {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b}: {c} ({d}%)"
                	    },
                	    legend: {
                	        orient: 'horizontal',
                	        y: 'bottom',
                	        data:['初期财脑指数','课后训练得分','课堂训练等于=知识+应用']
                	    },
                	    series: [
                	        {
                	            name:'',
                	            type:'pie',
                	            selectedMode: 'single',
                	            radius: [0, '30%'],

                	            label: {
                	                normal: {
                	                	show:false,
                	                    position: 'center'
                	                }
                	            },
                	            labelLine: {
                	                normal: {
                	                    show: false
                	                }
                	            },
                	            data:[
                	                
                	            ]
                	        },
                	        {
                	            name:'',
                	            type:'pie',
                	            radius: ['40%', '55%'],
                	            data:ifbrainindexval
                	        }
                	    ]
                	};*/
               /* option = {
                	    tooltip: {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b}: {c} ({d}%)"
                	    },
                	    legend: {
                	        orient: 'vertical',
                	        y: 'bottom',
                	        data:['初期财脑指数','课后训练得分','课堂训练等于=知识+应用']
                	    },
                	    series: [
                	        {
                	            name:'访问来源',
                	            type:'pie',
                	            radius: ['50%', '70%'],
                	            avoidLabelOverlap: false,
                	            label: {
                	                normal: {
                	                    show: false,
                	                    position: 'center'
                	                },
                	                emphasis: {
                	                    show: true,
                	                    textStyle: {
                	                        fontSize: '30',
                	                        fontWeight: 'bold'
                	                    }
                	                }
                	            },
                	            labelLine: {
                	                normal: {
                	                    show: false
                	                }
                	            },
                	            data:ifbrainindexval
                	        }
                	    ]
                	};*/

                	var ecConfig = require('echarts/config');
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
        
        
 });