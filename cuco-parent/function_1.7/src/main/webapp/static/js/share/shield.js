	toShare();
	function toShare(){
		var _href = window.location.href.split('#')[0];
		$.ajax({
			async: false,
			type: "post",
			url: basePath + "/gcar/common/wxshare", 
			//url: "http://1mobility.cn/service/service/customer/wxshare",
			dataType: "json", 
			data:{
				"url": _href
			},
			success: function (data) {
					console.log(data);
					wx.config({
	                    debug: false,
	                    appId: data.appId,
	                    timestamp: data.timestamp,
	                    nonceStr: data.nonceStr,
	                    signature: data.sign,
	                    jsApiList: [
	                        'checkJsApi',
	                        'onMenuShareTimeline',
	                        'onMenuShareAppMessage',
	                        'hideMenuItems',
	                        'showMenuItems'
	                    ]
	                });
			}   
		});
	}
	
	
	wx.ready(function(){
		wx.hideOptionMenu();

	});