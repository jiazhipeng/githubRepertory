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
    <title>用车信息</title>
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/2v.css" rel="stylesheet" type="text/css" />
    
     <link href="${ctx}/static/gcar_html/css/popup.css" rel="stylesheet">
  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> 
    <script src="${ctx}/static/gcar_html/js/popup.js"></script>
</head>

<body class="greyBg">
    <div class="use-info-body" style="padding-top:2%;">
       <%--  <div class="use-info-t">${brand } ${carType }<c:if test="${carPlateNum != null}">(${carPlateNum })</c:if> </div> --%>
        <div class="use-sec">
<%--                 <a href="${ctx}/wechat/mycars/carTracking?powerUsedId=${powerUsedId } " class="arrow u-row" style="padding-right:20px;"> --%>
                <a href="#" onclick="showProcess()" class="arrow u-row" style="padding-right:20px;">
            <div class="u-name">
                <c:if test="${status ==0}"><div class="state">已取消</div></c:if>
                <c:if test="${status ==1}"><div class="state">已预约</div></c:if>
                <c:if test="${status ==2}"><div class="state">使用中</div></c:if>
                <c:if test="${status ==3}"><div class="state">已完成</div></c:if>
                    
                    <div class="info">
                   <%--  <c:if test="${status ==1}">距您用车时间还剩 <span>${time }</span>，<br>${msg }</c:if>
                    <c:if test="${status ==2}">距您还车时间还剩 <span>${time }</span>，<br>${msg }</c:if> --%>
                    </div>
            </div>
            <div class="u-val" style="white-space:normal;">
           		<p style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;">${brand } ${carType }<c:if test="${carPlateNum != null}">(${carPlateNum })</c:if> </p>
           </div>
                </a>
        </div>
        <c:if test="${changeCarPlateNum != null }">
        
	        <div class="use-sec">
	            <div class="u-row">
	                <div class="u-name">临时换车</div>
	                <div class="u-val">${changeBrand } ${changeCarType }(${changeCarPlateNum })</div>
	            </div>
	        </div>
        
        </c:if>
      

        <div class="use-sec">
            <div class="u-row">
                <div class="u-name">接车时间</div>
                <div class="u-val">${carUsedTime }</div>
            </div>
            <div class="u-row">
                <div class="u-name">接车地点</div>
                <div class="u-val" style="white-space:normal;">
                	<p style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;">${carUsedAddress }</p>
                </div>
            </div>
        </div>

        <div class="use-sec use-sec-nom">
            <div class="u-row">
                <div class="u-name">预计还车时间</div>
                <div class="u-val">${carReturnTime }</div>
            </div>
            <div class="u-row">
                <div class="u-name">预计还车地点</div>
                <div class="u-val" style="white-space:normal;">
                	<p style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;">${carReturnAddress }</p>
                </div>
            </div>
        </div>
  <c:if test="${status ==2}">
	<c:if test="${charging==1 || roadRescue==1 }">
	        <div class="use-s-t">
	            极车服务
	        </div>
	
	        <div class="use-sec">
	        <c:if test="${charging==1  }">
	                <a href="javascript:;" onclick="showModelCharging()" class="use-ser-charge"  <c:if test="${roadRescue==1 }"> style="border-bottom:1px solid #ccc;"  </c:if>>
	            <div class="u-row arrow">
	                    代充电服务
	            </div>
	                </a>
	        </c:if>
	        <c:if test="${roadRescue==1 }">
	                <a href="javascript:;" onclick="showModel()" class="use-ser-help" >
	            <div class="u-row arrow">
	                    紧急救援
	            </div>
	                </a>
	         </c:if>
	        </div>
	</c:if>
</c:if>
    </div>
    <div class="fixBottom" style="text-align:right">
    	<p style="width:70%; position:absolute; top:0; bottom:0; left:0; text-align:left; line-height:40px; box-sizing:border-box; padding-left:10px; font-size:1.4rem;">
    	 	<c:if test="${status ==1}">距您用车开始还剩 <span><span style="color:#fe4438; padding-left:5px;">${time }</span></c:if>
            <c:if test="${status ==2}">距您用车结束还剩 <span><span style="color:#fe4438; padding-left:5px;">${time }</span></c:if>
    	</p>
       <c:if test="${status ==1}"> <a href="javascript:;" onclick="showPromptUserCar()" class="button" style="width:30%;">  取消用车 </a></c:if>
        <c:if test="${status ==2}"><a href="javascript:;" onclick="showPromptReturnCar()" class="button" style="width:30%;"> 修改行程  </a></c:if>
    </div>
</body>
<script type="text/javascript">
function showModelCharging(){
	
	$.ajax({
		url: "${ctx}/wechat/mycars/checkMemberStatuts" ,
		data:{"memberId":'${memberId}'},
		success: function(data){
//				alert(data.isUsedCar);
			if(data){
			     var myt=  tooltip();
					myt.show('提示','<a href="tel:400-9029-858">您已被冻结，请联系客服<span class="tel">400-9029-858</span></a>',function(){})
			}else{
			    var myt=  myconfirm();
				myt.show('提示','代充电服务需在APP发起',function(){
						window.location.href="/static/downLoad/down.html";
					},"下载",function(){},"取消")
			}
		}
	}); 


}
//查看进程
function showProcess(){
	  			
	  var myt=  myconfirm();
		myt.show('提示','该功能需在APP进行操作',function(){
				window.location.href="/static/downLoad/down.html";
			},"下载",function(){},"取消")
}
function showPromptReturnCar(){
// 	 var myt=  tooltip();
// 		myt.show('提示','发起还车需在APP发起',function(){
// 	  				window.location.href="/static/downLoad/down.html";
// 	  			},"下载")
	  			
	  			
	  var myt=  myconfirm();
		myt.show('提示','该功能需在APP进行操作',function(){
				window.location.href="/static/downLoad/down.html";
			},"下载",function(){},"取消")
}
function showPromptUserCar(){
	 var myt=  myconfirm();
		myt.show('提示','取消用车需在APP发起',function(){
				window.location.href="/static/downLoad/down.html";
			},"下载",function(){},"取消")
// 	 var myt=  tooltip();
// 	myt.show('提示','取消用车需在APP发起',function(){
// 	  				window.location.href="/static/downLoad/down.html";
// 	  			},"下载")
}

function showModel(){
// 	判断是否冻结
	$.ajax({
	url: "${ctx}/wechat/mycars/checkMemberStatuts" ,
	data:{"memberId":'${memberId}'},
	success: function(data){
//			alert(data.isUsedCar);
		if(data){
		     var myt=  tooltip();
				myt.show('提示','<a href="tel:400-9029-858">您已被冻结，请联系客服<span class="tel">400-9029-858</span></a>',function(){})
		}else{
		     var myt=  tooltip();
				myt.show('提示','<a href="tel:400-9029-858">如需道路救援请拨打服务电话<span class="tel">400-9029-858</span></a>',function(){})
			
		}
	}
}); 
}
</script>
</html>