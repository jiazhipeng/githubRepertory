<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="yes" name="apple-touch-fullscreen" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/normalize.css?v=1">
	<title>管家任务</title>
	</head>

<body>

    <div class="tack">
        <div class="tack_btn">
            <a href="javascript:toCarOperaterManager()" title="车辆管理"></a>
            <a href="javascript:toSendCarButler()"></a>
            <a href="javascript:toGetGetButler()"></a>
            <a href="javascript:toCharingButler()"></a>
        </div>
    </div>

</body>
<script>
function toCarOperaterManager(){
	window.location.href="/wechat/carOperate/toCarList?loginId=${loginId}";
}
function toSendCarButler(){
	window.location.href="${ctx}/wechat/butler/task/sendCar/tobutlerCalendar?loginId=${loginId}";
}
function toGetGetButler(){
	window.location.href="${ctx}/wechat/butlerTask/getCar/tobutlerCalendar?loginId=${loginId}";
}
function toCharingButler(){
	window.location.href="${ctx}/wechat/butlerTask/charging/toButlerTaskList?loginId=${loginId}";
}
</script>
</html>