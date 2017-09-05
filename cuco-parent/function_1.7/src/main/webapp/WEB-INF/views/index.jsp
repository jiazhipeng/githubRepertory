<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
	<title>登录</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/static/gcar_html/css/login.css">
<%-- 	<script src="${ctx}/static/js/jquery-1.8.3.min.js"></script> --%>
	<script type="text/javascript">
		var wait=60; 
		function validateInput() {
				var mobile = $("#login_mobile").val();
				var code = $("#login_code").val();//login_code
				var hasClass0 = $('#btn_login').hasClass("unableCode");
				var hasClass1 = $('#login_submit').hasClass("greyBtn");
				var flag = 0;
				if($.trim(mobile).length==11){
					flag=1;
					if(hasClass0){
						$("#btn_login").removeClass("unableCode");
					}
				}else if(!hasClass0){
					$("#btn_login").addClass("unableCode");
				}
				
				if(flag == 1 && $.trim(code).length == 6){
					if(hasClass1){
						$("#login_submit").removeClass("greyBtn");
					}
				}else{
					if(!hasClass1){
						$("#login_submit").addClass("greyBtn");
					}
				}
		}
		function countdownTimer() { 
			var o = $("#btn_login");
			if (wait == 0) { 
				o.removeClass("unableCode");
				o.html("验证");
				o.attr('href',"javascript:getCaptcha(this);");

				//o.attr("disabled",true); 
				//o.value="免费获取验证码"; 
				wait = 60; 
			}else{ 
				o.addClass("unableCode");
				o.html(wait + "秒后重发");
				o.removeAttr('href');
				//o.setAttribute("disabled", true); 
				//o.value="重新发送(" + wait + ")"; 
				wait--; 
				setTimeout(function() { 
					countdownTimer(); 
				}, 1000) 
			} 
		} 
		function getCaptcha(){
			var mobile = $("#login_mobile").val();
			if($.trim(mobile) =="" || !(/^1[3|4|5|7|8]\d{9}$/.test(mobile))){
				alert("请输入正确手机号码");
				return;
			}
			countdownTimer();
			$.ajax({
			     type: 'POST',
			     url: '${ctx}/wechat/login/getcaptcha',
			     data: {
			    	 "mobile":mobile		 
			     },
			     dataType: "json",
			     success: function(data){
			    	 if(!data.success){
						 alert(data.message);
					 }
			    }
			});
		}
		
		function login(){
			var mobile = $("#login_mobile").val();
			var code = $("#login_code").val();
			if($.trim(mobile) =="" || !(/^1[3|4|5|7|8]\d{9}$/.test(mobile))){
				alert("请输入正确手机号码");
				return;
			}else if($.trim(code) =="" || $.trim(code).length != 6){
				alert("请输入正确的验证码");
				return;
			}
			if(!$("#checkAgreement").attr("checked")){
				alert("请同意会员协议");
				return;
			}
			$("#login_submit").removeAttr('href');
			$("#login_submit").addClass("greyBtn");
			var name = "${name}";//${sessionScope.adminid}
			var openid="${openid}";
			$.ajax({
			     type: 'POST',
			     url: '${ctx}/wechat/login/login',
			     data: {
			    	 "mobile":mobile,
			    	 "code1":code,
			    	 "openid":openid,
			    	 "name":name
			     },
			     dataType: "json",
			     success: function(data){
			    	 //if(data.auth){
			    	//	 window.location.href=data.auth; 
			    	 //}
			    	 
			    	 if(data.success){
			    		 //alert("登陆成功！");
			    		 window.location.href=${ctx }data.userdata; 
			    	 }else{
			    		 alert(data.message);
			    		 $("#login_submit").removeClass("greyBtn");
			    		 $("#login_submit").attr('href',"javascript:login();");
			    	 }
			    } 
			});
		}
	</script>
</head>
	<body class="wBody">
		<img src="${ctx}/static/gcar_html/images/loglogo.png" class="logo">
		<div class="section">
			<form id="loginForm">
				<ul class="logBox">
					<li>
						<input type="text" name="mobile" placeholder="手机号码" id="login_mobile" oninput="javascript:validateInput();">
						<a href="javascript:getCaptcha();" class="codeBtn unableCode" id="btn_login">验证</a><!--可点击状态去掉class=unableCode-->
					</li>
					<li>
						 <input type="text" placeholder="验证码" name="code" id="login_code" oninput="javascript:validateInput();">
					</li>
				</ul>
				<a href="javascript:login();"class="button greyBtn" id="login_submit">登录</a><!--可点击状态去掉greyBtn-->
			</form>
			<span class="info"><input type="checkbox" id="checkAgreement"></input><span>同意<a href="${ctx}/static/gcar_html/html/service/agreement.html"<em>《极车公社用户服务协议》</em></a></span></span>
		</div>
	</body>
</html>
