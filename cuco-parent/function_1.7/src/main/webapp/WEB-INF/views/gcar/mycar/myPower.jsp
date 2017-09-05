<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
        <meta content="yes" name="apple-mobile-web-app-capable" />
        <meta content="yes" name="apple-touch-fullscreen" />
        <meta content="telephone=no" name="format-detection" />
        <meta content="black" name="apple-mobile-web-app-status-bar-style">
        <title>权益信息</title>
        <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
   		<link href="${ctx}/static/gcar_html/css/2v.css" rel="stylesheet" type="text/css" />
        
        
        
        <script type="text/javascript">
//         	function toAddShareMember(){
//         		alert(1);
// //         		
//         	}
        
        
        </script>
</head>
<body>
<div class="interests">
    <%--  <div class="topSec">
          <div class="title"><p>启用车型</p></div>
          <ul class="models">
          <c:forEach items="${cartypes }" var="item">
           <li><p>${item.brand } ${item.carType }</p></li>
          </c:forEach>
                 <!--  <li><p>特斯拉 MODEL S 90 D</p></li>
                  <li><p>特斯拉 MODEL S 90 D</p></li> -->
          </ul>
     </div> --%>
     <ul class="interests-list">
	        <li class="cartype">
                <a href="${ctx }/wechat/mycars/memberItemList?memberItemId=${memberItemId}">
                    <p class="name">权益车型</p>
                </a>
            </li>
	     <c:if test="${isMain=='1' }">
	   				<li class="gongyong">
	                   <a href="${ctx }/wechat/mycars/toMyShareDetail?memberItemId=${memberItemId}&memberId=${memberId}" >
	                           <p class="name">共用人</p>
	                           <p class="last">${count }人</p>
	                   </a>
	              </li>
	      </c:if>
             <li class="quanyi">
                     <a href="${ctx }/wechat/mycars/toMyBalance?memberItemId=${memberItemId}">
                             <p class="name">权益余额</p>
                             <c:if test="${balance<=99999999}"><p class="last">${balance }元</p></c:if>
       						 <c:if test="${balance>99999999}"><p class="last">99999999+元</p></c:if>
                     </a>
             </li>
             <li class="yajin">
                     <a href="${ctx }/wechat/mycars/toMyDeposit?memberItemId=${memberItemId}">
                             <p class="name">押金</p>
                             <c:if test="${deposit<=99999999}"><p class="last">${deposit }元</p></c:if>
       						 <c:if test="${deposit>99999999}"><p class="last">99999999+元</p></c:if>
                     </a>
             </li>
             <li class="jilu">
                     <a href="${ctx }/wechat/mycars/toMyUsedLog?memberItemId=${memberItemId}">
                             <p class="name">用车记录</p>
                     </a>
             </li>
     </ul>
</div>
</body>

</html>
