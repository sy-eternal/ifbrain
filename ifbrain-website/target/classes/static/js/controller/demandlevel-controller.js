 $(function(){
	 var demandlevelnames=$("#demandlevelnames").val();
	    var demandlevelname=demandlevelnames.split(",");
	    
	 var demandlevelnameandprice=$("#demandlevelnameandprice").val();
	 var demandlevelnameobject=eval(demandlevelnameandprice);
	 
	 
	 
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
                var myChart1 = ec.init(document.getElementById('demandlevel')); 
                
               
                
                option1 = { tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    /*orient : 'horizontal',
                    y : 'bottom',*/
                	 orient : 'vertical',
                     x : 'left',
                    data:demandlevelname
                },
                toolbox: {
                    
                },
                calculable : false,
                series : [
                    {
                        name:'需求层次分析',
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
                        name:'需求层次分析',
                        type:'pie',
                        radius : [100, 140],
                        
                        // for funnel
                        x: '60%',
                        width: '35%',
                        funnelAlign: 'left',
                        max: 1048,
                        
                        data:demandlevelnameobject
                    }
                ]};
                	var ecConfig = require('echarts/config');
                // 为echarts对象加载数据 
                myChart1.setOption(option1); 
            }
        );
        
        
 });