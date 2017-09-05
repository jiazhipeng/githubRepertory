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
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/css.css">
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/date.css">
<title>车辆管理</title>
</head>

<body>
    <header class="clearfix">
        <span class="date-wrapper">
            <a href="javascript:;" class="prev"></a>
            <span class="date">...</span>
        <a href="javascript:;" class="nxt"></a>
        </span>
        <a href="searchCar?loginId=${loginId}" class="query"><i class="icon"></i>单车查询</a>
         <input type="hidden" class="loginId" data-val="${loginId}">
    </header>
    <section class="content">
        <ul class="nav">
            <li class="nav-item active">待分配</li>
            <li class="nav-item">会员预约</li>
            <li class="nav-item">平台预约</li>
            <li class="nav-item">维修</li>
        </ul>
        <div class="pages">
            <div class="page">
            </div>
            <div class="page">
            </div>
            <div class="page">
            </div>
            <div class="page">
            </div>
        </div>

    </section>

    <script src="${ctx}/static/gcar_html/js/touch.js"></script>
    <script src="${ctx}/static/gcar_html/js/calendar.js"></script>
    <script src="${ctx}/static/gcar_html/js/carmgr.js"></script>
</body>
</html>