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
	<title>取车任务</title>
	<style>
		.fixed-btn{
			position:fixed;
			width:100%;
			left:0;
			bottom:0;
		}
	</style>
</head>

<body>
    <header class="clearfix">
        <span class="date-wrapper">
            <a href="javascript:;" class="prev"></a>
            <span class="date">...</span>
        <a href="javascript:;" class="nxt"></a>
        </span>
        <a href="javascript:searchButler();" class="query"><i class="icon"></i>管家查询</a>
    </header>
    <section class="content">
        <ul class="nav">
            <li class="nav-item active">概况</li>
            <li class="nav-item">管家</li>
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
        <c:if test="${loginId == butlerId }">
			<div class="button fixed-btn" onclick='javascript:managementGetCarButler();'>管理取车任务</div>
		</c:if>
    </section>
	<input type="hidden" value="${loginId }" id="loginId">
	<input type="hidden" value="${changeButlerId }" id="changeButlerId">
		<input type="hidden" value="${butlerName }" id="butlerName">
    <script src="${ctx}/static/gcar_html/js/touch.js"></script>
    <script src="${ctx}/static/gcar_html/js/calendar.js"></script>
    <script src="${ctx}/static/gcar_html/js/getCar/carmgr.js"></script>
    <script>
    	function searchButler(){
    		//查找管家
    		 var loginId = $("#loginId").val();
             window.location.href=basePath+"/wechat/butlerTask/getCar/toSearchMemberList?loginId="+loginId+"&type=1";
    	}
    	function managementGetCarButler(){
    		//进行跳转
    		var loginId = $("#loginId").val();
            window.location.href=basePath+"/wechat/butlerTask/getCar/toSearchMemberList?loginId="+loginId+"&type=2";
    	}
    </script>
</body>
</html>