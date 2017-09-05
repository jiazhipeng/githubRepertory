weChatConfig();
function weChatConfig() {
	var _href = window.location.href.split('#')[0];
	$.ajax({
		url:basePath + '/gcar/common/wxshare',
		type: "get",
		data : {"url" : _href},
		async: false,
		dataType: "json",
		success: function(data) {
			console.log(data);
			wx.config({
				debug : false,
				appId : data.appId,
				timestamp : data.timestamp,
				nonceStr : data.nonceStr,
				signature : data.sign,
				jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
					'onMenuShareAppMessage', 'hideMenuItems',
					'showMenuItems','chooseImage','previewImage',
					'uploadImage',
					'downloadImage',"scanQRCode"]
			});
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		}
	});
}