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
    <link href="${ctx}/static/gcar_html/css/user.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/jquery/jquery-1.10.2.min.js"></script>
    
	<title>意见建议</title>
	</head>
	<body>
<div class="section suggest">
   <textarea id="textarea" placeholder="请留下您的宝贵意见和建议，我们将努力改进" maxlength="500"></textarea>
   <input type="hidden" id="memberId" value="${member.id}"/>
   <input type="hidden" id="memberName" value="${member.name}"/>
   <input type="hidden" id="mobile" value="${member.mobile}"/>
   <div class="errInfo" id="errInfo"></div>
   <span class="button" id="sumbutton">提交</span>
</div>
<script>
$(document).ready(function() {
	$('#sumbutton').click(function(){
		var areaContext = $("#textarea").val();
		if(null==areaContext||""==areaContext){
			//alert("请输入具体内容");
			$("#errInfo").html("反馈不能为空");
			return false;
		}
		var memberId = $("#memberId").val();
		var memberName = $("#memberName").val();
		var mobile = $("#mobile").val();
		$.ajax({
			type: 'POST',
			url: "${ctx}/wechat/suggest/create" ,
			async:false,
			data: {"memberId":memberId,"memberName":memberName,"mobile":mobile,"content":$("#textarea").val()},
			success: function(data){
				//alert("提交成功");
				$("#errInfo").html("提交成功");
				//提交成功之后跳转
				window.location.href="${ctx}/wechat/personalCenter/toAbout?memberId=${member.id}";
			}
		})
	
	});
});
</script>
</body>
</html>