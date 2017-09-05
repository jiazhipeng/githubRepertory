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
	<title>我的优惠券</title>
	<link rel="stylesheet" href="${ctx}/static/gcar_html/css/css.css">
	<link href="${ctx}/static/gcar_html/css/2v.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
	</head>
	<body class="greyBg">
    <div class="p-info-page">
        <div class="p-info-sec">
            可用券 <span id="count_em">${couponInfoCount!=null?couponInfoCount:'0' }</span>张
        </div>
		<input type="hidden" id="memberId" value="${memberId}">
        <div class="title">优惠券明细</div>
        <ul class="coup-list" id="coupList">
            <c:forEach items="${couponInfoList }" var="model">
            	<c:if test="${model.status !=5 && model.status !=4}">
            	  <a href="${ctx}/wechat/couponInfo/couponDetail?couponId=${model.couponId}&remark=${model.remark}&couponName=${model.couponName}">
            		<li>
		                <div class="l-site">
		                    <p>${model.couponName}</p>
		                    <p class="date">有效期<fmt:formatDate value="${model.getTime}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${model.outTime}" pattern="yyyy-MM-dd"/></p>
		                </div>
		                <div class="coup-line"></div>
		                <div class="r-site">
		                    	${model.statusInfo }
		                </div>
            		</li>
            	  </a>
            	</c:if>
            	<c:if test="${model.status ==5 || model.status ==4}">
            	   <a href="${ctx}/wechat/couponInfo/couponDetail?couponId=${model.couponId}&remark=${model.remark}&couponName=${model.couponName}">
            		<li class="err">
		                <div class="l-site">
		                    <p>${model.couponName}</p>
		                    <p class="date">有效期<fmt:formatDate value="${model.getTime}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${model.outTime}" pattern="yyyy-MM-dd"/></p>
		                </div>
		                <div class="coup-line"></div>
		                <div class="r-site">
		                    	${model.statusInfo }
		                </div>
            		</li>
            	   </a>	
            	</c:if>
            </c:forEach>
        </ul>
        
        <!--一键清除不可用券start  -->
        <div class="fixHeight"></div>
    	<div class="fixBottom ">
        <a href="javascript:cleanUpOuttimeCoupons();" class="cleCoup">一键清除不可用券</a>
    	</div>
    </div>
</body>
<script type="text/javascript">
//一键清除功能
function cleanUpOuttimeCoupons(){
	var memberId = $("#memberId").val();
	$.ajax({
		type: 'GET',
		url : "${ctx}/wechat/couponInfo/cleanUpCouponOutTime",
		data:{"memberId":memberId},
		async:false,
		success: function(data){
			if(data.result){
				//兑换成功之后，刷新列表数据
				getCouponListAjaxInit();
			}else{
				alert("清除可用券失败");
			}
		}
	})
}

function getCouponListAjaxInit(){
	 var memberId = $("#memberId").val();
	$.ajax({
		type: 'GET',
		url : "${ctx}/wechat/couponInfo/list",
		data:{"memberId":memberId},
		async:false,
		success: function(data){
			//显示可用券的个数
			$("#count_em").html(data.couponInfoCount);
			//首先清空优惠券列表
			$("#coupList").empty();
			//重新追加
			for(var i=0;i<data.couponInfoList.length;i++){
				var name = data.couponInfoList[i].couponName;
				var time = data.couponInfoList[i].getTime;
				time = resolveCharacterDate(time,'yyyy-MM-dd');
				var outTime = data.couponInfoList[i].outTime;
				outTime = resolveCharacterDate(outTime,'yyyy-MM-dd')
				var statusInfo = data.couponInfoList[i].statusInfo;
				var couponId = data.couponInfoList[i].couponId;
				var remark = data.couponInfoList[i].remark;
				var couponInfoStr="<a href='${ctx}/wechat/couponInfo/couponDetail?couponId="+couponId+"&remark="+remark+"&couponName="+name+"'><li><div class='l-site'><p>"+name+"</p><p class='date'>有效期"+time+"至"+outTime+"</p></div><div class='coup-line'></div><div class='r-site'>"+statusInfo+"</div></li></a>";
				
				//拼接优惠券信息
				$("#coupList").append(couponInfoStr);
			}
		}
	}) 
}

</script>
</html>
