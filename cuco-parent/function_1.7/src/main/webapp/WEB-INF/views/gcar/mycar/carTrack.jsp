<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <title>车辆追踪</title>
     <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/2v.css" rel="stylesheet" type="text/css" />
</head>
<body>
<ul class="nodeList coupNode">
<c:forEach items="${list }" var="item">
    <li <c:if test="${item.status ==1 }">class="on"</c:if> >
        <div class="node">
            <div class="line"></div>
            <div class="crile"></div>
        </div>
        <div class="nodeCon">
            <div class="title">
                ${item.msg } ${item.butlerInfo }  <a href="tel:${item.mobile }"><span class="tel">${item.mobile }</span></a>
            </div>
            <div class="date">${item.time }</div>
        </div>
    </li>
</c:forEach>  
</ul>

</body>
</html>