 $(function(){
	 var shopobjname=$("#shopname").val();
	    var shopobjname=shopobjname.split(",");
	
	 var shopnameandprice=$("#shopnameandprice").val();
	 var shopobject=eval(shopnameandprice);
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
                var myChart2 = ec.init(document.getElementById('main')); 
                
                /*option = {
                	    tooltip : {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b} : {c} ({d}%)"
                	    },
                	    legend: {
               	    	      y:'bottom',
                	        data:shopobjname
                	    },
                	  
                	    calculable : false,
                	    series : [
                	        {
                	            name:'',
                	            type:'pie',
                	            selectedMode: 'single',
                	            radius : [0, 70],
                	            
                	            // for funnel
                	            x: '20%',
                	            width: '40%',
                	            funnelAlign: 'right',
                	            max: 1548,
                	            
                	            itemStyle : {
                	                normal : {
                	                    label : {
                	                        position : 'inner'
                	                    },
                	                    labelLine : {
                	                        show : false
                	                    }
                	                }
                	            },
                	            data:[
                	               
                	            ]
                	        },
                	        {
                	            name:'',
                	            type:'pie',
                	            radius : [100, 140],
                	            
                	            // for funnel
                	            x: '60%',
                	            width: '35%',
                	            funnelAlign: 'left',
                	            max: 1048,
                	            
                	            data:shopobject
                	        }
                	    ]
                	};*/
                
                option2 = {
                	    tooltip: {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b}: {c} ({d}%)"
                	    },
                	    legend: {
                	        orient: 'horizontal',
                	        y: 'bottom',
                	        data:shopobjname
                	    },
                	    series: [
                	        {
                	            name:'',
                	            type:'pie',
                	            selectedMode: 'single',
                	            radius: [0, '30%'],

                	            label: {
                	                normal: {
                	                    position: 'inner'
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

                	            data:shopobject
                	        }
                	    ]
                	};
                	var ecConfig = require('echarts/config');
                // 为echarts对象加载数据 
                myChart2.setOption(option2); 
            }
        );
        
        
 });