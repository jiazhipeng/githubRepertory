<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
<title>微信支付订单详情确认页面</title>
 <link href="${ctx }/static/gcar_html/css/service.css" rel="stylesheet" type="text/css" />
    
      <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet">
    <link href="${ctx}/static/gcar_html/css/popup.css" rel="stylesheet">
  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> 
    <script src="${ctx}/static/gcar_html/js/popup.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    
</head>
<body>
<div class="body">
<div class="head">
 <%-- <div class="logo"><img src="<%=basePath%>jsp/weixin/img/logo1.png"/></div> --%>
 <div><span>极车公社科技有限公司</span></div>
 
</div>

<c:if test="${msg eq 'ok' }">
<script type="text/JavaScript">
console.log('${payParams.appId}');
console.log('${configParam.timestamp}');
console.log('${configParam.signStr}');
console.log('${configParam.sign}');
console.log('${payParams.timeStamp}');
console.log('${payParams.nonceStr}');
console.log('${payParams.signType}');
console.log('${payParams.paySign}');
console.log('${payId}');
function payWeixin(){
	window.wx.config({
		debug:false,//关闭了JS调试，开发阶段可为true
		appId: '${payParams.appId}',
		timestamp: '${configParam.timestamp}',
		nonceStr: '${configParam.signStr}',
		signature: '${configParam.sign}',
		jsApiList: ['checkJsApi', 'chooseWXPay']
	});
	wx.ready(function() {
	wx.checkJsApi({
	   jsApiList: ['chooseWXPay'],
	   success: function(res) 
	   {
	    	console.log(JSON.stringify(res));
	   }
	   });
	wx.chooseWXPay({
	   timestamp: '${payParams.timeStamp}', 
	   nonceStr: '${payParams.nonceStr}', 
	   package: "prepay_id=${payId}",
	   signType: '${payParams.signType}',
	   paySign: '${payParams.paySign}',
	   success: function (res) 
	   {
	   // 支付成功后的回调函数
	     alert("支付成功");//后续动作自己定
	     console.log('支付成功');
	   }
});
})
}
</script>
<div class="oderDetail">
 <!-- 支付订单详情显示，自己弄-->
   <h1>我要支付</h1>
</div>
<div class="ok">
<button onClick="payWeixin()" class="button button-raised button-caution" type="button">确定支付</button>
</div>
</c:if>
<c:if test="${msg ne 'ok' }">
  <div style="margin-top: 25% auto;">
 <h3>
   ${msg }
 </h3>
  </div>
</c:if>
</div>
</body>
</html>