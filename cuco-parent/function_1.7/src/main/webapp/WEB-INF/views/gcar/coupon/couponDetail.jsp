<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <title>${couponName}</title>
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/2v.css" rel="stylesheet" type="text/css" />
    <style>
    body {
        background: #eee
    }
    .info-title {
        font-size: 16px;
        font-weight: normal;
        padding: 10px;
        color: #666;

    }
    .info-detail {
        font-size: 15px;
        background:#fff;
        padding: 10px 10px;
        border-top: 1px #ccc solid;
        border-bottom: 1px #ccc solid;
        line-height: 26px;
        color: #666;
    }
    </style>
</head>
<body>
    <div>
        <h3 class="info-title">适用套餐</h3>
        <p class="info-detail"><c:forEach items="${couponDetailList}" var="model">
        	${model.itemName} &nbsp;
        </c:forEach></p>
        <h3 class="info-title">优惠券说明</h3>
        <p class="info-detail">${remark }</p>
    </div>
</body>
</html>