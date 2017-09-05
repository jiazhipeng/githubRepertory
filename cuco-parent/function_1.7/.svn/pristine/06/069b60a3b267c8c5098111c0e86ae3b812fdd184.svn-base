<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <link href="${ctx}/static/gcar_html/css/bultertask.css" rel="stylesheet" type="text/css" />
    
	<title>关于公社</title>
	</head>
	<body>
	<input type="hidden" id="memberId" value="${memberId }">
	<section class="li" onclick='javascript:toSuggestCreate();'><label class="title">用户反馈</label><span id="city_span"></span><i></i></section>
	<section class="li" onclick='javascript:aboutUs();'><label class="title">关于我们</label><span id="city_span"></span><i></i></section>
	<script>
		//跳到用户反馈页面
		function toSuggestCreate(){
			var memberId = $("#memberId").val();
			window.location.href="${ctx}/wechat/personalCenter/toSuggest?memberId="+memberId;
		}
		//跳到关于我们页面
		function aboutUs(){
			//跳转H5页面
			window.location.href="http://www.gcarclub.com/about_v1/index.html";
		}
	</script>
</body>
</html>