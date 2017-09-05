<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
	<!-- <script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script> -->
	
	<title>订单详情</title>
	</head>
	<body class="greyBg">
	<!--默认详情页-->
	<div style="display: block;">
    <%-- <p class="state">订单信息（${order.statusInfo}）</p> --%>
    <section class="li saler"><label class="title">负责销售</label><span>${order.modifier }</span></section>
    <!--个人会员-->
   	<c:if test="${0==member.type}">
    <section class="self">
        <section class="li"><label class="title">姓名</label><span>${member.name}</span></section>
        <section class="li"><label class="title">性别</label>
        <span>
        	<c:if test="${member.sex!=-1 }">
				<c:if test="${member.sex==1}">男</c:if> 
				<c:if test="${member.sex==0}">女</c:if>
			</c:if>
		</span></section>
        <section class="li"><label class="title">手机号码</label><c:if test="${not empty member.mobile}"><a href="tel:${member.mobile}"><span class="tel">${member.mobile}</span></a></c:if></section>
        <section class="li"><label class="title">会员等级</label><span>${memberItem.itemName!=null?memberItem.itemName:'-'}</span></section>
    </section>
    </c:if>
    <!--个人会员结束-->
    <!--企业会员-->
     <c:if test="${1==member.type}">
	    <section class="self">
	        <section class="li"><label class="title">企业名称</label><span>${member.name!=null?member.name:'极车会员'}</span></section>
	        <section class="li"><label class="title">联系人</label><span>${member.orgName!=null?member.orgName:'-'}</span></section>
	        <section class="li"><label class="title">联系方式</label><c:if test="${not empty member.orgTel}"><a href="tel:${member.orgTel}"><span class="tel">${member.orgTel}</span></a></c:if></section>
	        <section class="li"><label class="title">会员等级</label><span>${memberItem.itemName!=null?memberItem.itemName:'-'}</span></section>
	    </section>
  	</c:if>
   	<!--企业会员结束-->
   	
   	<!--城市以及产品-->
   	<section class="packages">
        <section class="li" ><label class="title">使用城市</label><span >${order.cityName}</span></section>
        <section class="li" ><label class="title">购买产品</label><span>${item.levelName}</span></section>
    </section>

	<!--状态以及付款信息-->
   	<section class="packages">
        <section class="li" ><label class="title">状态</label><span>${order.statusInfo}</span></section>
        <section class="li" ><label class="title">实收金额</label><span>${order.payment!=null?order.payment:'0'}</span></section>
        <section class="li" ><label class="title">实收押金</label><span>${order.paymentDeposit!=null?order.paymentDeposit:'0'}</span></section>
    </section>
    
	<!-- <section class="btns">
        <div class="btn cancel" onclick='javascript:closeDetail();'>取消</div>
        <div class="btn sure" onclick='javascript:closeDetail();' id="submitButton">确定</div>
    </section> -->
    
</body>
<script>
function closeDetail(){
	window.location.href="${ctx}/wechat/order/toOrderList?loginId=${loginId}";
}
</script>
</html>
