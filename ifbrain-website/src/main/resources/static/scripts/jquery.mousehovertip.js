$(function(){
		var x = 10;
		var y = 20;
		$("a.tooltips").mouseover(function(e){
			this.myTitle = this.title;       //得到当前链接的titl，即标题
			this.title = "";
			var imgTitle = this.myTitle? "<br/>" + this.myTitle : "";
			var tooltip = "<div id='tooltips'> "+
			              "<img src='"+ this.href +"' alt='放大提示'/>"+
						  imgTitle+"</div>"; //新建一个id为tooltip的div元素
			$("body").append(tooltip);	     //元素内部包含一个img，用来显示图片并且显示标题，追加到body区
			$("#tooltips")                   //更改tooptip的顶部和左侧位置
				.css({
					"top": (e.pageY+y) + "px",
					"left":  (e.pageX+x)  + "px"
				}).show("fast");	       //使用show函数快速显示
		}).mouseout(function(){
			this.title = this.myTitle;
			$("#tooltips").remove();	        //当鼠标移出时，从body区删除该div元素
		}).mousemove(function(e){         //当鼠标移动时，调整div的位置位于鼠标箭头的下方
			$("#tooltips")
				.css({
					"top": (e.pageY+y) + "px",
					"left":  (e.pageX+x)  + "px"
				});
		});
	});