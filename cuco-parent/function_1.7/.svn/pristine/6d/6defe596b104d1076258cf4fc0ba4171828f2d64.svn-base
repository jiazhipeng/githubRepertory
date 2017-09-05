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
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
	
   </head>
   <body class="greyBg">
    <div class="charge-page">
        <ul class="charge-info-list">
            <li class="list-row">
                <span class="info-name">
                   	 用车会员：
                </span>
                <span class="info-val">
                    ${butlerTask.memberName!=null?butlerTask.memberName:'极车会员' }<c:if test="${not empty butlerTask.memberMobile}"><a href="tel:${butlerTask.memberMobile}" class="tel"> ( ${butlerTask.memberMobile} ) </a></c:if>
                </span>
            </li>
           	<li class="list-row">
                   <span class="info-name">
                       	取车时间：
                   </span>
                   <span class="info-val">
                       <fmt:formatDate value="${butlerTask.planTime }" pattern="yyyy-MM-dd HH:mm"/>
                   </span>
           	</li>
            <li class="list-row">
                   <span class="info-name">
                       	取车地点：
                   </span>
                   <span class="info-val">
                       	${butlerTask.taskGetCarAddress }
                   </span>
            </li>
           
            <li class="list-row">
                 <span class="info-name">
                     	送车地点：
                 </span>
                 <span class="info-val">
                    	${butlerTask.taskSendCarAddress }
                 </span>
            </li>
            <li class="list-row">
                <span class="info-name">
                    	使用车辆：
                </span>
                <span class="info-val">
                    	${carOperateMessage }
                </span>
            </li>
        </ul>
        <ul class="charge-info-list">
           <li class="list-row">
               <span class="info-name">
                 	  负责管家：
               </span>
               <span class="info-val">
                   ${butlerTask.operaterName }
               </span>
           </li>
            <li class="list-row">
                   <span class="info-name">
                       	任务处理：
                   </span>
                   <span class="info-val">
                       <span class="task-state radioRoup">
	                           <em>
	                               <i>${butlerTask.statusInfo }</i>
	                           </em>
                       </span>
                   </span>
            </li>
            <c:if test="${butlerTask.status==7}">
            <li class="list-row">
                 <span class="info-name">
                    	 完成时间：
                 </span>
                 <span class="info-val">
                      <fmt:formatDate value="${butlerTask.completeTime }" pattern="yyyy-MM-dd HH:mm"/>                                                
                 </span>
            </li>
            </c:if>
            <c:if test="${butlerTask.status==8}">
            <li class="list-row">
                 <span class="info-name">
                    	 取消时间：
                 </span>
                 <span class="info-val">
                      <fmt:formatDate value="${butlerTask.completeTime }" pattern="yyyy-MM-dd HH:mm"/>                                                
                 </span>
            </li>
            </c:if>
        </ul>
        <div class="charge-task-t">
           	 任务记录
        </div>
        <ul class="charge-task-list">
        	<c:forEach items="${logList}" var="model">
	        	<li>
		        	<div class="row">
		                <p>${model.statusInfo }</p>
		                <p>${model.modifier }</p>
		                <p><fmt:formatDate value="${model.created }" pattern="yyyy-MM-dd HH:mm"/> </p>
		            </div>
	                <div class="row">
	                	${model.remark }
	                </div>
	            </li>
        	</c:forEach>
        </ul>
            <div class="button fixed-btn" onclick='javascript:formCancel();'>返回</div>
        </div>
    </div>
    
</body>
<script>
//取消
function formCancel(){
	window.location.href="${ctx}/wechat/butlerTask/charging/toButlerTaskList?loginId=${loginId}";
}
</script>
</html>