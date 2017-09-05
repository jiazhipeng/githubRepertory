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
    <title>用车详情</title>
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
	<div class="use-info-body">
		<c:if test="${item.usedStatus==0 || item.usedStatus==1 || item.usedStatus==2 }">
			<div class="use-info-t"><fmt:formatDate value="${item.carUsedTime }" pattern="yyyy-MM-dd"/> 至<fmt:formatDate value="${item.carReturnTime }" pattern="yyyy-MM-dd"/></div>
	        <div class="use-sec">
	            <div class="use-state display-box">
	                	<div class="state">待送车</div>
            		    <div class="info">
	                		<c:if test="${item.carTypeView != null}">
		                         	${item.carTypeView.brand}&nbsp;${item.carTypeView.carType}<br>
	                    	</c:if>
							<c:if test="${item.powerUsedCost != null}">
		                    <span class="red">￥<fmt:parseNumber value="${item.powerUsedCost.total < 0 ? -item.powerUsedCost.total:item.powerUsedCost.total}"/>元</span>
	                    	</c:if>
	                    	<c:if test="${item.carUsedDay != null}">
		                    	(已使用0天)
	                    	</c:if>
	                	</div>
	            </div>
	        </div>
	         <div class="use-sec">
	            <div class="u-row">
	                <div class="u-name">权益</div>
	                <div class="u-val">${item.memberItemName}</div>
	            </div>
	        </div>
			
	        <div class="use-sec">
	            <div class="u-row">
		                <div class="u-name">使用人</div>
		                <div class="u-val">${item.member.name}</div>
            	</div>
	        </div>
	         <div class="use-sec">
	            <c:if test="${item.powerUsedCost.replacecarPrice < 0 || item.powerUsedCost.replacecarPrice > 0}">
		            <div class="u-row u-row-line">
		                <div class="u-name">换车费用</div>
		                <div class="u-val red"><fmt:parseNumber value="${item.powerUsedCost.replacecarPrice < 0 ? -item.powerUsedCost.replacecarPrice:item.powerUsedCost.replacecarPrice}"/>元</div>
		            </div>
	            </c:if>
   	            <c:if test="${item.powerUsedCost.hurryPrice < 0 || item.powerUsedCost.hurryPrice > 0}">
		            <div class="u-row u-row-line">
		                <div class="u-name">加急费</div>
		                <div class="u-val red"><fmt:parseNumber value="${item.powerUsedCost.hurryPrice < 0 ? -item.powerUsedCost.hurryPrice:item.powerUsedCost.hurryPrice}"/>元</div>
		            </div>
	            </c:if>
	        </div>
		</c:if>
		<c:if test="${item.usedStatus==3 || item.usedStatus==4 || item.usedStatus==5 }">
	        <div class="use-info-t"><fmt:formatDate value="${item.carUsedTime }" pattern="yyyy-MM-dd"/> 至<fmt:formatDate value="${item.carReturnTime }" pattern="yyyy-MM-dd"/></div>
	        <div class="use-sec">
	            <div class="use-state display-box">

	                	<div class="state">使用中</div>
	                	<div class="info">
	                		<c:if test="${item.carOperate != null}">
		                         	${item.carOperate.carBrand}&nbsp;${item.carOperate.carType}&nbsp;(${item.carOperate.carPlateNum})<br>
	                    	</c:if>
							<c:if test="${item.powerUsedCost != null}">
		                    <span class="red">￥<fmt:parseNumber value="${item.powerUsedCost.total < 0 ? -item.powerUsedCost.total:item.powerUsedCost.total}"/>元</span>
	                    	</c:if>
	                    	<c:if test="${item.carUsedDay != null}">
		                    	(使用${item.carUsedDay}天)
	                    	</c:if>
	                	</div>
	            </div>
	        </div>
	        <div class="use-sec">
	            <div class="u-row">
	                <div class="u-name">权益</div>
	                <div class="u-val">${item.memberItemName}</div>
	            </div>
	        </div>
	        <div class="use-sec">
	            <div class="u-row">
		                <div class="u-name">使用人</div>
		                <div class="u-val">${item.member.name}</div>
            	</div>
	        </div>
	         <c:if test="${item.brand !=null && item.carType !=null}">
		         <div class="use-sec">
		            <div class="u-row">
			                <div class="u-name">故障换车</div>
			                <div class="u-val">${item.brand}&nbsp;${item.carType}&nbsp;(${item.carPlateNum})</div>
	            	</div>
		        </div>
	        </c:if>
               <div class="use-sec">
	            <div class="u-row u-row-line">
	                <div class="u-name">车辆使用费</div>
	                <div class="u-val">
	                    <div class="val-row">
		                    <c:if test="${item.carUsedDay != null}">
		                        <p class="l-site">${item.carUsedDay}天</p>
		                    </c:if>
		                    <c:if test="${item.powerUsedCost.usedcarPrice != null }">
	                       		 <p class="r-site red"><fmt:parseNumber value="${(item.powerUsedCost.usedcarPrice-item.powerUsedCost.couponPrice) < 0 ? -(item.powerUsedCost.usedcarPrice-item.powerUsedCost.couponPrice):(item.powerUsedCost.usedcarPrice-item.powerUsedCost.couponPrice)}"/>元</p>
		                    </c:if>
	                    </div> 
	                    <div class="val-row">
		                    <c:if test="${item.powerUsedCost.couponPrice > 0}">
		                        <p>优惠券低值<fmt:parseNumber value="${item.powerUsedCost.couponPrice}"/>元</p>
		                    </c:if>
	                    	<c:if test="${item.powerUsedCost.usedcarPrice < 0 || item.powerUsedCost.usedcarPrice > 0}">
	                        	<p>余额低值<fmt:parseNumber value="${item.powerUsedCost.usedcarPrice < 0 ? -item.powerUsedCost.usedcarPrice:item.powerUsedCost.usedcarPrice}"/>元</p>
	                    	</c:if>
	                    </div>    
	                </div>
	            </div>
	            <c:if test="${item.powerUsedCost.replacecarPrice < 0 || item.powerUsedCost.replacecarPrice > 0}">
		            <div class="u-row u-row-line">
		                <div class="u-name">换车费用</div>
		                <div class="u-val red"><fmt:parseNumber value="${item.powerUsedCost.replacecarPrice < 0 ? -item.powerUsedCost.replacecarPrice:item.powerUsedCost.replacecarPrice}"/>元</div>
		            </div>
	            </c:if>
	            <c:if test="${item.powerUsedCost.hurryPrice < 0 || item.powerUsedCost.hurryPrice > 0}">
		            <div class="u-row u-row-line">
		                <div class="u-name">加急费</div>
		                <div class="u-val red"><fmt:parseNumber value="${item.powerUsedCost.hurryPrice < 0 ? -item.powerUsedCost.hurryPrice:item.powerUsedCost.hurryPrice}"/>元</div>
		            </div>
	            </c:if>
	        </div>
		</c:if>
		<c:if test="${item.usedStatus==6 }">
	        <div class="use-info-t"><fmt:formatDate value="${item.carUsedTime }" pattern="yyyy-MM-dd"/> 至<fmt:formatDate value="${item.carReturnTime }" pattern="yyyy-MM-dd"/></div>
	        <div class="use-sec">
	       			 <div class="use-state display-box">
	                	<div class="state">已完成</div>
               		     <div class="info">
	                		<c:if test="${item.carOperate != null}">
		                         	${item.carOperate.carBrand}&nbsp;${item.carOperate.carType}&nbsp;(${item.carOperate.carPlateNum})<br>
	                    	</c:if>
							<c:if test="${item.powerUsedCost != null}">
		                   		 <span class="red">￥<fmt:parseNumber value="${item.powerUsedCost.total< 0 ? -item.powerUsedCost.total:item.powerUsedCost.total}"/>元</span>
	                    	</c:if>
	                    	<c:if test="${item.carUsedDay != null}">
		                    	(使用${item.carUsedDay}天)
	                    	</c:if>
	               		</div>
	               	</div>

	            </div>
			<div class="use-sec">
	            <div class="u-row">
	                <div class="u-name">权益</div>
	                <div class="u-val">${item.memberItemName}</div>
	            </div>
	        </div>
	        <div class="use-sec">
	            <div class="u-row">
		                <div class="u-name">使用人</div>
		                <div class="u-val">${item.member.name}</div>
            	</div>
	        </div>
	         <c:if test="${item.brand !=null && item.carType !=null}">
		         <div class="use-sec">
		            <div class="u-row">
			                <div class="u-name">故障换车</div>
			                <div class="u-val">${item.brand}&nbsp;${item.carType}&nbsp;(${item.carPlateNum})</div>
	            	</div>
		        </div>
	        </c:if>
             <div class="use-sec">
	            <div class="u-row u-row-line">
	                <div class="u-name">车辆使用费</div>
	                <div class="u-val">
	                    <div class="val-row">
		                    <c:if test="${item.carUsedDay != null}">
		                        <p class="l-site">${item.carUsedDay}天</p>
		                    </c:if>
		                    <c:if test="${item.powerUsedCost.usedcarPrice != null }">
	                       		<p class="r-site red"><fmt:parseNumber value="${(item.powerUsedCost.usedcarPrice-item.powerUsedCost.couponPrice) < 0 ? -(item.powerUsedCost.usedcarPrice-item.powerUsedCost.couponPrice):(item.powerUsedCost.usedcarPrice-item.powerUsedCost.couponPrice)}"/>元</p>
		                    </c:if>
	                    </div> 
	                    <div class="val-row">
		                    <c:if test="${item.powerUsedCost.couponPrice > 0}">
		                        <p>优惠券低值<fmt:parseNumber value="${item.powerUsedCost.couponPrice}"/>元</p>
		                    </c:if>
	                    	<c:if test="${item.powerUsedCost.usedcarPrice < 0 ||item.powerUsedCost.usedcarPrice > 0}">
	                        	<p>余额低值<fmt:parseNumber value="${item.powerUsedCost.usedcarPrice < 0 ? -item.powerUsedCost.usedcarPrice:item.powerUsedCost.usedcarPrice}"/>元</p>
	                    	</c:if>
	                    </div>    
	                </div>
	            </div>
	            <c:if test="${item.powerUsedCost.replacecarPrice < 0 || item.powerUsedCost.replacecarPrice > 0}">
		            <div class="u-row u-row-line">
		                <div class="u-name">换车费用</div>
		                <div class="u-val red"><fmt:parseNumber value="${item.powerUsedCost.replacecarPrice< 0 ? -item.powerUsedCost.replacecarPrice:item.powerUsedCost.replacecarPrice}"/>元</div>
		            </div>
	            </c:if>
	            <c:if test="${item.powerUsedCost.hurryPrice < 0 || item.powerUsedCost.hurryPrice > 0}">
		            <div class="u-row u-row-line">
		                <div class="u-name">加急费</div>
		                <div class="u-val red"><fmt:parseNumber value="${item.powerUsedCost.hurryPrice < 0 ? -item.powerUsedCost.hurryPrice:item.powerUsedCost.hurryPrice}"/>元</div>
		            </div>
	            </c:if>
	              <c:if test="${item.powerUsedCost.gasolinePrice < 0 || item.powerUsedCost.gasolinePrice > 0}">
		            <div class="u-row u-row-line">
		                <div class="u-name">油耗费</div>
		                <div class="u-val red"><fmt:parseNumber value="${item.powerUsedCost.gasolinePrice < 0 ? -item.powerUsedCost.gasolinePrice:item.powerUsedCost.gasolinePrice}"/>元</div>
		            </div>
	            </c:if>
	        </div>
		</c:if>
		<c:if test="${item.usedStatus==7 }">
	        <div class="use-info-t"><fmt:formatDate value="${item.lasttimeModify }" pattern="yyyy-MM-dd"/></div>
	        <div class="use-sec">
	            <div class="use-state display-box">
	                	<div class="state">已取消</div>
	                	 <div class="info">
	                		<c:if test="${item.carTypeView != null}">
		                         	 ${item.carTypeView.brand}&nbsp;${item.carTypeView.carType}<br>
	                    	</c:if>
							<c:if test="${item.powerUsedCost.total != null}">
		                    <span class="red">￥<fmt:parseNumber value="${item.powerUsedCost.total < 0 ? -item.powerUsedCost.total:item.powerUsedCost.total}"/>元</span>
	                    	</c:if>
	                    	<c:if test="${item.carUsedDay != null}">
		                    	(使用0天)
	                    	</c:if>
	               		 </div>
	            </div>
	        </div>
	         <div class="use-sec">
	            <div class="u-row">
	                <div class="u-name">权益</div>
	                <div class="u-val">${item.memberItemName}</div>
	            </div>
	        </div>
	        <div class="use-sec">
	            <div class="u-row">
		                <div class="u-name">使用人</div>
		                <div class="u-val">${item.member.name}</div>
            	</div>
	        </div>
	        <c:if test="${item.powerUsedCost.contractPrice <0 || item.powerUsedCost.contractPrice > 0}">
	          <div class="use-sec">
	            <div class="u-row">
		                <div class="u-name">违约费</div>
		                <div class="u-val red">
		                <fmt:parseNumber value="${item.powerUsedCost.contractPrice< 0 ? -item.powerUsedCost.contractPrice:item.powerUsedCost.contractPrice}"/>元
		                </div>
            	</div>
	        </div>
	        </c:if>
		</c:if>
 
		        
         <div class="img-list-sec">
            <c:if test="${item.useList.size()>0}">
	            <div class="img-list-row">
	                <div class="u-name">用车交接单</div>
	                <div class="u-val">
	                <c:forEach items="${item.useList }" var="item">
	                    <img src="${item.imageUrl }">
	                </c:forEach>
	                    
	                </div>
	            </div>
            </c:if>
             <c:if test="${item.returnList.size()>0}">
		            <div class="img-list-row">
		                <div class="u-name">还车交接单</div>
		                <div class="u-val">
							 <c:forEach items="${item.returnList }" var="item">
			                    <img src="${item.imageUrl }">
							 </c:forEach>
		                  
		                </div>
		            </div>
             </c:if>
        </div>
	
    </div>
    <div class="show-big-img">
        <div class="big-img-box">
            <div class="img-sec-row">
                <div class="img-sec">
                    <div class="close"></div>
                    <img src="">
                </div>
            </div>
        </div>
    </div>
<script>
    $('.img-list-sec img').on('click',function(){
        $('.show-big-img').css('display','block');
        $('.show-big-img img').attr('src',$(this).attr('src'));
        $('.show-big-img .close').on('click',function(){
             $('.show-big-img').hide();
        })
    })
</script>
</body>
</html>