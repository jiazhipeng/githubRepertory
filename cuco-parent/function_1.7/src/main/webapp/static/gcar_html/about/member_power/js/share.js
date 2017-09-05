toShare();
function toShare() {
	var _href = window.location.href.split('#')[0];
	$.ajax({
		type : "post",
		url : basePath + "/wxshare",
		// url: "http://1mobility.cn/service/service/customer/wxshare",
		dataType : "json",
		data : {
			"url" : _href
		},
		success : function(data) {
			console.log(data);
			wx.config({
				debug : false,
				appId : data.appId,
				timestamp : data.timestamp,
				nonceStr : data.nonceStr,
				signature : data.sign,
				jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
						'onMenuShareAppMessage', 'hideMenuItems',
						'showMenuItems' ]
			});
		}
	});
}
var param = {"title":"","link":"","imgUrl":"","desc":""};
//var onMenuShareAppMessage =  {"title":"","link":"","imgUrl":"",}

wx.ready(function() {

	wx.onMenuShareTimeline({
		title : param.desc, // 分享标题
		link : param.link, // 分享链接
		imgUrl : param.imgUrl, // 分享图标
		success : function() {
			alert('朋友圈分享成功！');
		},
		cancel : function() {
			// 用户取消分享后执行的回调函数
			alert('朋友圈分享取消！');
		}
	});
	wx.onMenuShareAppMessage({
		title : param.title, // 分享标题
		link : param.link, // 分享链接
		desc : param.desc, // 分享描述
		imgUrl : param.imgUrl, // 分享图标
		type : '', // 分享类型,music、video或link，不填默认为link
		dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
		success : function() {
			// 用户确认分享后执行的回调函数
			alert('分享给朋友成功了！');
		},
		cancel : function() {
			// 用户取消分享后执行的回调函数
			alert('分享给朋友取消了！');
		}
	});
	// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
});