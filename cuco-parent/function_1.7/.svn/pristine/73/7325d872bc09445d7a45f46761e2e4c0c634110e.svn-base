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
	<title>销售名片</title>
	<link rel="stylesheet" href="${ctx}/static/gcar_html/css/css.css">
	<link href="/static/gcar_html/css/2v.css" rel="stylesheet" type="text/css" />
	<%-- <script src='${ctx}/static/js/share/jweixin-1.0.0.js'></script>
	<script src='${ctx}/static/js/share/shield.js'></script> --%>
	</head>
<body class="greyBg">
<div class="box">
    <header class="clearfix">
        <h2>${card.cardName}</h2>
        <a href="javascript:goReg();" class="car">
            <img src="${card.imgUrl}" />
        </a>
        <a href="javascript:goReg();" class="chaining">
            <span></span>
        </a>
    </header>
    <div class='qrcode clearfix'>
    	<div class="line"></div>
    	<span>我的专属二维码</span>
    	<img  class='small' />
    </div>
    <div class='mask'>
    <img  class='preview' />
    </div>
    <div class="tab-conts">
       	 提交线索
    </div>
    <table  > 
    <tr style="font-weight: normal"> 
        <th>日期</th> 
        <th>真实名称</th> 
        <th>套餐等级</th>
        <th>线索状态</th>  
    </tr>
    <c:forEach items="${orderList}" var="model">
	    <tr> 
	        <td><fmt:formatDate value="${model.created}" pattern="yyyy-MM-dd"/></td> 
	        <td>${model.sureName}</td> 
	        <td>${model.itemName}</td>
	        <td>${model.statusInfo}</td> 
	    </tr>
    </c:forEach> 
     
</table> 
</div>
<script src='${ctx}/static/gcar_html/js/jr-qrcode.js'></script>
<script>
	//跳转
	function goReg(){
		//跳转H5页面
		window.location.href="${url}/h5/card/index.html?id=${card.id}";
	}
	
	var url='${url}/h5/card/index.html?id=${card.id}';
    var imgBase64 = jrQrcode.getQrBase64(url);
    $('.small,.preview').attr('src', imgBase64);
	$('.qrcode').click(function(){
		$('.mask').attr('style','display:flex');
	});
	$('.mask').click(function(){
		$(this).hide();
	});
</script>
</body>
</html>