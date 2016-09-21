 $(function(){
	 
	 
    var objname=$("#name").val();
    var objname=objname.split(",");
    
 var nameandprice=$("#nameandprice").val();
 var object=eval(nameandprice); 

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
                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                var dataStyle = {
                	    normal: {
                	        label: {show:false},
                	        labelLine: {show:false}
                	    }
                	};
                	var placeHolderStyle = {
                	    normal : {
                	        color: 'rgba(0,0,0,0)',
                	        label: {show:false},
                	        labelLine: {show:false}
                	    },
                	    emphasis : {
                	        color: 'rgba(0,0,0,0)'
                	    }
                	};
                	option = {
                			/*title:{
                				text:'购物分析',
                				x:'center',
                				y:'center'
                			},*/
                		    tooltip : {
                		        trigger: 'item',
                		        formatter: "{a} <br/>{b} : {c} ({d}%)"
                		        	
                		    },
                		    legend: {
                    	        orient : 'vertical',
                    	        x : 'left',
                    	        data:objname
                    	        
                    	    },
                		    toolbox: {
                		        show : true,
                		        feature : {
                		            mark : {show: true},
                		            dataView : {show: true, readOnly: false},
                		            magicType : {
                		                show: true, 
                		                type: ['pie', 'funnel'],
                		                option: {
                		                    funnel: {
                		                        x: '25%',
                		                        width: '50%',
                		                        funnelAlign: 'center',
                		                        max: 1548,
                		                        
                		                    }
                		                }
                		            },
                		            restore : {show: true},
                		            saveAsImage : {show: true}
                		        }
                		    },
                		    calculable : true,
                		    series : [
                		        {
                		            name:'访问来源',
                		            type:'pie',
                		            radius : ['50%', '70%'],
                		            itemStyle : {
                		                normal : {
                		                    label : {
                		                        show : false
                		                    },
                		                    labelLine : {
                		                        show : false
                		                    }
                		                },
                		                emphasis : {
                		                    label : {
                		                        show : true,
                		                        position : 'center',
                		                        textStyle : {
                		                            fontSize : '30',
                		                            fontWeight : 'bold'
                		                        }
                		                    }
                		                }
                		            },
                		            data:object 
                		        }
                		    ]
                		};
                		                    
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
 });