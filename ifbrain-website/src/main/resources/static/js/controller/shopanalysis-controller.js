 $(function(){
	 
	 var result=$("#result").val();
	 
	 var name=$("#namelist").val();
	 var objectname=name.split(",");
   


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
            		    title: {
            		        text: '购物评估',
            		        subtext: 'Shop Analysis',
            		  //      sublink: 'http://e.weibo.com/1341556070/AhQXtjbqh',
            		        x: 'center',
            		        y: 'center',
            		        itemGap: 20,
            		        textStyle : {
            		            color : 'rgba(30,144,255,0.8)',
            		            fontFamily : '微软雅黑',
            		            fontSize : 35,
            		            fontWeight : 'bolder'
            		        }
            		    },
            		    tooltip : {
            		        show: true,
            		        formatter: "{a} <br/>{b} : {c} ({d}%)"
            		    },
            		    legend: {
            		        orient : 'vertical',
            		        x : document.getElementById('main').offsetWidth / 2,
            		        y : 45,
            		        itemGap:12,
            		        data: objectname
            		    },
            		    toolbox: {
            		        show : true,
            		        feature : {
            		            mark : {show: true},
            		            dataView : {show: true, readOnly: false},
            		            restore : {show: true},
            		            saveAsImage : {show: true}
            		        }
            		    },
			            		    series :eval(result)
            		};
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
        
        
 });