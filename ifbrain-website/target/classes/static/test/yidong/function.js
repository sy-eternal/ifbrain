console=window.console || {dir:new Function(),log:new Function()};
var active=0,
	as=document.getElementById('pagenavi').getElementsByTagName('a');

for(var i=0;i<as.length;i++){
	(function(){
		var j=i;
		as[i].onclick=function(){
			t4.slide(j);
			return false;
		}
	})();
}
var t1=new TouchSlider('slider',{duration:800, interval:3000, direction:0, autoplay:true, align:'center', mousewheel:false, mouse:true, fullsize:false});
var t11=new TouchSlider('slider1',{duration:800, interval:3000, direction:0, autoplay:true, align:'left', mousewheel:false, mouse:true, fullsize:false});
var t2=new TouchSlider('slider2',{duration:600, interval:3000, direction:1, autoplay:true, align:'middle', mousewheel:true, mouse:true, fullsize:true});
var t3=new TouchSlider('slider3',{duration:1000, direction:0, interval:3000, fullsize:true});
var t4=new TouchSlider('slider4',{speed:1000, direction:0, interval:6000, fullsize:true});
t4.on('before',function(m,n){
    as[m].className='';
	as[n].className='active';
})