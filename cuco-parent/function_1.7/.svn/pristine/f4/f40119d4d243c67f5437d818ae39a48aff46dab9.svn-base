<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>

<%@ include file="/WEB-INF/common/jquery.js.jsp"%>
<script src="${ctx}/static/js/sidebar.js" type="text/javascript"></script>

		<!-- BEGIN SIDEBAR -->
		<div id="sidebar" class="nav-collapse collapse" style="margin-top:10px;">
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            <div class="hidden-phone"></div>
		
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="sidebar-menu" id="menuId">
                <li id="menu1">
					<a href="${ctx}" class="">
					    <span class="icon-box"> <i class="icon-home"></i></span>首页  
                        <span class="arrow"></span>
                    </a>                   
				</li>
                
                   <!-- // 动态构造菜单树 add by lrm  -->
              <c:set var="menuList" value="${fns:getMenuList()}"/>
                <c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
                	<c:if test="${empty menu.parent.id}">
	                	<c:choose>
	                		<c:when test="${empty menu.url}">
	                			 <c:set var="hasSub" value="has-sub"/>
	                			 <c:set var="levelHref" value="#"/>
	                		</c:when>
	                		<c:otherwise>
	                			<c:set var="hasSub" value=""/>
	                			<c:set var="levelHref" value="${ctx}/${menu.url}"/>
	                		</c:otherwise>
	                	</c:choose>
	                	<li class="${hasSub}" id="menus${menu.id}">
	                		<a href="${levelHref}?menutype=${menu.id}&sontype=0">
		                        <span class="icon-box"><i class="${menu.iconname}"></i></span>${menu.displayName}
		                        <c:if test="${not empty hasSub}">
		                        	<span class="arrow"></span>
		                        </c:if>
		                    </a>
		             
		                    <ul class="sub">
			                    <c:forEach items="${menuList}" var="menuChild">
			                    	<c:if test="${menuChild.parent.id eq menu.id}">
			                			<li>
			                				<a id="sontype${menuChild.id}" class="" href="${ctx}/${menuChild.url}?menutype=${menu.id}&sontype=${menuChild.id}">${menuChild.displayName}</a>
			                			</li>
			                		</c:if>
			                	</c:forEach>
		                	</ul>
		                </li>
	                </c:if>
                </c:forEach> 
	</ul>
	<!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->
<script type="text/javascript">
var menutype='${menutype}';
var sontype='${sontype}';

jQuery().ready(
		function() {
		

	$('#menuId').children().each(function(i, v) {
		
	$(v).click(function (){
		var ch=$(v).children();
		//去掉样式
		$('#menuId').children().each(function(i, v) {
				var cls=$(v).attr("class");
				
				if(cls!=null && cls!=undefined && cl!="undefined"){
					
					var l=cls.replace("active"," "); 
					
					$(v).attr("class",l);
					
				}
				
			});
		
		//有子标签
		if(ch.length>1){
			var cl=$(v).attr("class");
			$(v).attr("class",'active  '+cl);
		}
		
		
	});
	
}); 



if(sontype!='0'){
 
 	var c=$("#menus"+menutype).attr('class');
 	
	$("#menus"+$.trim(menutype)).addClass('active  open ');
	
	$("#menus"+menutype+" a ").children().each(function(i, spanv) {
		if(i==1){
			$(spanv).attr("class",' arrow  open  ');
			
		}
		
	});

	$("#sontype"+$.trim(sontype)).addClass('images');
	
	
}else {

	$("#menus"+menutype).addClass('active');
}

});

</script>