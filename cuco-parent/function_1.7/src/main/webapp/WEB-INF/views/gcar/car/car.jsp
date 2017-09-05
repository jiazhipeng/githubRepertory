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
 <title>单车详情</title>
 <style>
 	 header .query .car-icon{
            width:1.8rem;
            height:1.8rem;
            margin-right:6px;
            background: url(${ctx}/static/gcar_html/images/caricon_2.png) no-repeat left center;
            background-size: 100%;
        }
        .a-car-page {
            padding:0 4%;
        }
        .a-car-page .car-name{
            margin-bottom: 4%;
            padding:3% 0;
            background: url(${ctx}/static/gcar_html/images/arrow_right_3.png) no-repeat right center;
            background-size:10px;
            color:#333;
            border-bottom:1px solid #ccc;
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
        <a href="toCarList?loginId=${loginId}" class="query"><i class="icon car-icon"></i>车辆概况</a>
    </header>
    <div class="a-car-page">
        <a href="searchCar?loginId=${loginId}"><p class="car-name" data-val="${carPlateNum}" >${carPlateNum}</p></a>
        <input type="hidden" class="car-id" data-val="${carOperateId}">
         <input type="hidden" class="loginId" data-val="${loginId}">
        <div class="car-calendar">
            
        </div>
    </div>

    <script src="${ctx}/static/gcar_html/js/touch.js"></script>
    <script  src="${ctx}/static/gcar_html/js/calendar.js"></script>
    <script src="${ctx}/static/gcar_html/js/carinfo.js"></script>
</body>
</html>