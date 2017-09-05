<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>  
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>

<style>


#header .navbar-inner .nav .dropdown-toggle:hover, .navbar-inner .nav .dropdown.open .dropdown-toggle {
   background-color: #183d3a !important;
}

</style>
	<!-- BEGIN HEADER -->
	<div id="header" class="navbar navbar-inverse navbar-fixed-top" style="z-index: 999;">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner"  >
			<div class="container-fluid">
				<!-- BEGIN LOGO -->
				<a class="brand" >
				    <img src="${ctx }/static/images/logo.png"  height="50"/>
				</a>
				<!-- END LOGO -->
				<!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a class="btn btn-navbar collapsed" id="main_menu_trigger" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="arrow"></span>
				</a>
				<!-- END RESPONSIVE MENU TOGGLER -->
		
                   
                <div class="top-nav ">
                 <ul class="nav pull-left top-menu" >
	                 <li class="dropdown" style="margin-top:6px;">
	                 	<a>
	                 		<font style="color:#ffffff;font-size:25px;">海易出行多系统资源管理系统</font>
	                 	</a>
	                 </li> 
                 </ul> 
                    <ul class="nav pull-right top-menu" >
						<!-- BEGIN USER LOGIN DROPDOWN -->
						<li class="dropdown" style="margin-top:6px;" id="dropdpwnid" >
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <font style="color:#ffffff;font-size:15px;">您好，<span class="username"><shiro:principal property="name"/></span>，欢迎光临！</font>
							<b class="caret" style="margin-top:8px;"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a  href="#" id="aaa"><i class="icon-key"></i> 修改密码</a></li>								
								<li class="divider"></li>
								<li><a href="${ctx }/logout"><i class="icon-off"></i> 退出</a></li>
							</ul>
						</li>
						<!-- END USER LOGIN DROPDOWN -->
					</ul>
					<!-- END TOP NAVIGATION MENU -->
				</div>
			</div>
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<div id="ajax-modal-password"  data-backdrop="static"  class="modal container hide fade" tabindex="-1"></div>
	<script>
	$(document).ready(function() {
		$('#aaa').click(function(){
				var $modelreport = $('#ajax-modal-password');
				  $('body').modalmanager('loading');				  
				     $modelreport.load('${ctx }/security/user/userpwdform', function(){
				     $modelreport.modal({height:600,width:500});
				  }); 
		}); 
	});
	</script>