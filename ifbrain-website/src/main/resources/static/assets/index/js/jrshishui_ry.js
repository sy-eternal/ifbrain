function moveLeft(){
	var oUl = document.getElementById('page1_lists');
	if(oUl.offsetLeft<0 && !$('#page1_lists').is(':animated')) {
		$('.page1_ry_con>ul, .page1_line1, .page1_btn').fadeOut(500);
		var index = $('.page1_list_hover').index()-1;
		$('.page1_list_hover').removeClass('page1_list_hover');
		$('#page1_lists').stop(true).animate({left:'+=180px'}, 500, function(){
			$('#page1_lists>li').eq(index).addClass('page1_list_hover');
			$('.page1_ry_con>ul').css({left:'+=560px'});
			$('.page1_ry_con>ul, .page1_line1, .page1_btn').fadeIn(500);
		})
	}
}

function moveRight(){
	var oUl = document.getElementById('page1_lists');
	var iMax = -($('#page1_lists>li').length-1)*180;
	if(oUl.offsetLeft>iMax && !$('#page1_lists').is(':animated')) {
		$('.page1_ry_con>ul, .page1_line1, .page1_btn').fadeOut(500);
		var index = $('.page1_list_hover').index()+1;
		$('.page1_list_hover').removeClass('page1_list_hover');
		$('#page1_lists').stop(true).animate({left:'-=180px'}, 500, function(){
			$('#page1_lists>li').eq(index).addClass('page1_list_hover');
			$('.page1_ry_con>ul').css({left:'-=560px'});
			$('.page1_ry_con>ul, .page1_line1, .page1_btn').fadeIn(500);
		})
	}
}

function liClick() {
	$('#page1_lists>li').click(function(){
		var index = $(this).index();
		var iLeft_s = -index*180;
		var iLeft_b = -index*560;
		$('.page1_ry_con>ul, .page1_line1, .page1_btn').fadeOut(500, function(){
			$('.page1_list_hover').removeClass('page1_list_hover');
			$('.page1_ry_con>ul').css({left:iLeft_b+'px'});
			$('#page1_lists').css({left:iLeft_s+'px'});
			$('#page1_lists>li').eq(index).addClass('page1_list_hover');
			$('.page1_ry_con>ul, .page1_line1, .page1_btn').fadeIn(500);
		});
	})
}

function baiduMap() {
	var map = new BMap.Map("baidumap");
	var point = new BMap.Point(121.509979,31.240047);
	map.centerAndZoom(point, 18);
	map.enableScrollWheelZoom();
	var p = new BMap.Point(121.509979,31.240047);
	var marker = new BMap.Marker(p);
	map.addOverlay(marker); 
}

function openOnline()
{
	var width = document.documentElement.clientWidth;
	var height = document.documentElement.clientHeight;
	var left = (width-680) / 2;
	var top = (height-600) / 2;
	window.open ('/red.html','Vipabc鍦ㄧ嚎瀹㈡湇','height=600,width=680,top='+top+',left='+left+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no,status=no');
}

function shareNews(s)
{
	var title = $('.page4_title>h1').text();
	var url = window.location.href;
	var pic = '';
	var share;
	var con = '';
	var e = encodeURIComponent;
	var windowName = '鍒嗕韩鍒�';
	var param = getParamsOfShareWindow(700, 450);

	if(s=='sina') share = 'http://service.weibo.com/share/share.php?title=' + e(title+con) + '&url=' + e(url);
	if(s=='renren') share = 'http://widget.renren.com/dialog/share?resourceUrl='+e(url)+'&srcUrl='+e(url)+'&title='+e(title)+'&description='+e(con);
	if(s=='kaixin') share = 'http://www.kaixin001.com/rest/records.php?url=' + e(url) + '&content='+e(title+con)+'&aid=100018706&style=11';
	if(s=='douban') share = 'http://shuo.douban.com/!service/share?href=' + e(url) + '&text=' + e(title+con);
	if (s == 'qq') share = 'http://share.v.t.qq.com/index.php?c=share&a=index&title=' + e(title + con) + '&url' + e(url);
	if (s == 'qzone') share = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=' + e(url)+"&title=" + e(title + con);
	window.open(share, windowName, param);
}

function getParamsOfShareWindow(width, height) {
	return ['toolbar=0,status=0,resizable=1,width=' + width + ',height=' + height + ',left=',(screen.width-width)/2,',top=',(screen.height-height)/2].join('');
}