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
<title>信息完善</title>
<link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/gcar_html/css/memberIfno.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/gcar_html/css/member.css" rel="stylesheet" type="text/css" />
</head>

<body class="greyBg">
    <div class="user-info">
        <div class="section">
            <div class="user-block">
                <p class="title">身份证照正面</p>
 				<div class="img-section">
                    <div class="loaded">
                        <img src="${memberInfo.idcardFront}" class="card">
                    </div>
                </div>

                <p class="title">身份证照片反面</p>
 				<div class="img-section">
                    <div class="loaded">
                        <img src="${memberInfo.idcardBack}" class="card">
                    </div>
                </div>

                <p class="title">驾驶证正本</p>
                 <div class="img-section">
                    <div class="loaded">
                        <img src="${memberInfo.drivercardOriginal}" class="card">
                    </div>
                </div>

                <p class="title">驾驶证副本</p>
                <div class="img-section">
                    <div class="loaded">
                        <img src="${memberInfo.drivercardCopy}" class="card">
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>