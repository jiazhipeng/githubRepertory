<%@ page language="java" pageEncoding="UTF-8"%>
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
    <title>用车记录</title>
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/2v.css" rel="stylesheet" type="text/css" />
    <style>
        .no-list{
            height:100%;
            background:url('${ctx}/static/gcar_html/images/nolist.png') no-repeat center 38%;
            background-size:15%;
        }
    </style>
</head>
<body class="greyBg">
     <div class="use-record">
        <ul>
         <c:if test="${powerUserList.size()>0 }">
       		 <c:forEach items="${powerUserList }" var="item">
            	<li class="has-img">
               		<c:if test="${item.usedStatus==0 || item.usedStatus==1 || item.usedStatus==2  }">
		                <div class="top-site">
		                    	<div class="l-site"><fmt:formatDate value="${item.carUsedTime }" pattern="yyyy-MM-dd"/> 至<fmt:formatDate value="${item.carReturnTime }" pattern="yyyy-MM-dd"/></div>
		                   		 <div class="r-site">待送车</div>
		                	
		                </div>
		                <div class="b-site">
		                	<a href="${ctx }/wechat/mycars/toMyUsedLogView?id=${item.id}">
		                    <div class="flex">
<%-- 		                    	<c:if test="${item.carOperate != null}"> --%>
<%-- <%-- 			                         <img src="${item.carOperate.imageUrl}" alt=""> --%>
<%-- 		                    	</c:if> --%>
		                    	<c:if test="${item.carTypeView != null}">
		                         	${item.carTypeView.brand}&nbsp;${item.carTypeView.carType}
		                    	</c:if>
		                    </div>
		                    <div class="flex">
		                    </div>
		                </div>
                		</a>
                	</c:if>
	                
	                	<c:if test="${item.usedStatus==3 || item.usedStatus==4 || item.usedStatus==5}">
	                		 <div class="top-site">
	                    		<div class="l-site"><fmt:formatDate value="${item.carUsedTime }" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${item.carReturnTime }" pattern="yyyy-MM-dd"/></div>
	                   		 	<div class="r-site">使用中</div>
                   			 </div>
                   			  <div class="b-site">
                   			  	<a href="${ctx }/wechat/mycars/toMyUsedLogView?id=${item.id}">
				                    <div class="flex">
				                    	<c:if test="${item.carOperate != null}">
					                         <img src="${item.carOperate.imageUrl}" alt="">
				                    	</c:if>
				                    </div>
				                    <div class="flex">
				                    	<c:if test="${item.carOperate != null}">
				                         	${item.carOperate.carBrand}&nbsp;${item.carOperate.carType}<br>(${item.carOperate.carPlateNum})
				                    	</c:if>
				                    </div>
				                    <div class="flex">
				                    	<c:if test="${item.powerUsedCost != null}">
				                         	￥<fmt:parseNumber value="${item.powerUsedCost.total < 0 ? -item.powerUsedCost.total:item.powerUsedCost.total}"/>元
				                    	</c:if>
				                    </div>
				                  </a>
		               		 </div>
	                	</c:if>
	                	<c:if test="${item.usedStatus==6 }">
	                	 	<div class="top-site">
	                    		<div class="l-site"><fmt:formatDate value="${item.carUsedTime }" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${item.carReturnTime }" pattern="yyyy-MM-dd"/></div>
	                   		 	<div class="r-site">已完成</div>
	                   		 </div>
	                   		  <div class="b-site">
	                   		  	<a href="${ctx }/wechat/mycars/toMyUsedLogView?id=${item.id}">
				                    <div class="flex">
				                    	<c:if test="${item.carOperate != null}">
					                         <img src="${item.carOperate.imageUrl}" alt="">
				                    	</c:if>
				                    </div>
				                    <div class="flex">
				                    	<c:if test="${item.carOperate != null}">
				                         	${item.carOperate.carBrand}&nbsp;${item.carOperate.carType}<br>(${item.carOperate.carPlateNum})
				                    	</c:if>
				                    </div>
				                    <div class="flex">
				                    	<c:if test="${item.powerUsedCost != null}">
				                         	￥<fmt:parseNumber value="${item.powerUsedCost.total < 0 ? -item.powerUsedCost.total:item.powerUsedCost.total}"/>元
				                    	</c:if>
				                    </div>
				                  </a>  
		                	</div>
	                	</c:if>
	                	
	                	<c:if test="${item.usedStatus==7 }">
		                	 <div class="top-site">
		                	 	<div class="l-site"><fmt:formatDate value="${item.lasttimeModify }" pattern="yyyy-MM-dd"/></div>
		                   		 <div class="r-site">已取消</div>
	                   		 </div>
	                   		  <div class="b-site">
	                   		 	 <a href="${ctx }/wechat/mycars/toMyUsedLogView?id=${item.id}">
				                    <div class="flex">
				                    	<c:if test="${item.carTypeView != null}">
				                         	${item.carTypeView.brand}&nbsp;${item.carTypeView.carType}
				                    	</c:if>
				                    </div>
				                    <div class="flex">
				                    	<c:if test="${item.powerUsedCost.contractPrice < 0 || item.powerUsedCost.contractPrice > 0}">
				                         	￥<fmt:parseNumber value="${item.powerUsedCost.contractPrice < 0 ? -item.powerUsedCost.contractPrice:item.powerUsedCost.contractPrice}"/>元
				                    	</c:if>
				                    </div>
				                 </a>
		                	</div>
	                	</c:if>
	            </li>
            </c:forEach>
         </c:if>
           <c:if test="${powerUserList.size()==0 }">
           		暂无记录
           </c:if>
        </ul>
    </div>
</body>
</html>