 $(function(){
// 路径配置
	 var commoditytypename=$("#commoditytypename").val();
	 var name=commoditytypename.split(",");
	 
	 var commoditytypevalue=$("#commoditytypevalue").val();
	 var value=eval(commoditytypevalue);
	 
	 
	 
	 var demandlevelnames=$("#demandlevelnames").val();
	    var demandlevelname=demandlevelnames.split(",");
	    
	 var demandlevelnameandprice=$("#demandlevelnameandprice").val();
	 var demandlevelnameobject=eval(demandlevelnameandprice);
	 
	 
	
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
                var myChart = ec.init(document.getElementById('commoditymall')); 
            
                var myChart1 = ec.init(document.getElementById('demandlevel')); 
          
                
                option = {
                  		 tooltip: {
                 	        trigger: 'item',
                 	        formatter: "{a} <br/>{b}: {c} ({d}%)"
                 	    },
                 	    legend: {
                 	        orient: 'horizontal',
                 	        y: 'bottom',
                 	        data:name
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

                 	            data:value
                 	        }
                 	    ]};
                
               
                
                
               
                
                option1 = {
               		 tooltip: {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b}: {c} ({d}%)"
                	    },
                	    legend: {
                	        orient: 'horizontal',
                	        y: 'bottom',
                	        data:demandlevelname
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

                	            data:demandlevelnameobject
                	        }
                	    ]
               		
               };
                	var ecConfig = require('echarts/config');
                // 为echarts对象加载数据 
                myChart.setOption(option); 
              
                myChart1.setOption(option1); 
                
            }
        );
        
        
 });