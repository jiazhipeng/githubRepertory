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
    <title>${brand } ${carType }</title>
    <link href="${ctx }/static/gcar_html/css/user.css" rel="stylesheet" type="text/css" />
    <link href="${ctx }/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    
    <link href="${ctx}/static/gcar_html/css/popup.css" rel="stylesheet">
  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> 
    <script src="${ctx}/static/gcar_html/js/popup.js"></script>
    
    <style>

        .car-banner {
            position: relative;
        }
        .car-detail li {
            position: relative;
            background: #fff;
            padding: 3% 4%;
            margin-bottom: 10px;
        }
        .video-icon {
            width: 55px;
            height: 55px;
            display: inline-block;
            vertical-align: middle;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
        .plays {
            background: url("${ctx }/static/gcar_html/images/play.png") no-repeat;
            background-size: 100%;

        }
        .pause {
            background: url("${ctx }/static/gcar_html/images/pause.png") no-repeat;
            background-size: 100%;

        }
        .car-pres {
        }
        .car-titles {
            font-size: 1.6rem;
            font-weight: 400;
            color: #333;
            margin-bottom: 10px;
        }
        .brife {
            color: #666;
            font-size: 1.6rem;
            line-height: 20px;
            margin-bottom:20px;
        }
        .text-flow{
        overflow : hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
        }
        .arrow-icons {
            width: 22px;
            height: 15px;
            background: url("${ctx }/static/gcar_html/images/arrow_down.png") no-repeat;
            background-size: 100%;
            display: inline-block;
            vertical-align: middle;
            position: absolute;
            bottom: 6px;
            right: 20px;
        }
        .arrow-icons-up{
        background-image: url("${ctx }/static/gcar_html/images/arrow_up.png");
        }
        .car-info-icon {
            width: 14.5%;
            display: inline-block;
            text-align: center;
        }
        .car-info-icon img {
            width: 20px;
            height: 28px;
        }
        .car-info-icon span {
            font-size: 1rem;
            display: block;
            width: 48px;
            margin: 8px auto;
            transform: scale(0.8);
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
        .type-font a{
            font-size: 1.2rem;
            vertical-align: middle;
        }
        .car_fix {
            width: 100%;
            position: fixed;
            bottom: 0;
            left: 0;
            border-top: 1px #ccc solid;
            display: flex;
            height: 50px;
            line-height: 50px;
            text-align: center;
        }
        .car_money {
            color: #333;
            font-size: 1.4rem;
            background: #eee;
            flex:1.5

        }
        .car_money span {
            color: #f51305;
            font-size: 1.8rem;
        }
        .car-use {
            background: #161616;
            color: #fff;
            display: block;
            flex:1;
            font-size: 1.8rem;

        }
        .car-intr {
            padding-bottom: 50px;
        }
        .car-info-block{width:50%; float:left; height:30px; line-height:30px; overflow:hidden;}
    </style>
</head>
<body class="greyBg car-detail">
<div class="car-banner">
<%--     <img src="${ctx }/static/gcar_html/images/about_1.jpg"/> --%>
    <c:if test="${vidioUrl==null || vidioUrl==''}">
    	<img src="${imageUrl}">
    </c:if>
    <c:if test="${vidioUrl!=null && vidioUrl!=''}">
    <div class="videoBox" style="width:100%; positon:relative;">
    	
    	<video src="${vidioUrl}" id="video" style="width:100%;"></video>
    	<div style="width:100%; height:100%; position:absolute; left:0; top:0; background:url(${imageUrl}) no-repeat left top; background-size:100% 100%;">
    		<a href="javascript:;" id="toPlay" class="video-icon pause"></a>
    	</div>
	</div>   
    </c:if>
</div>
    <ul class="car-intr">
        <li>
            <div class="car-pres">
                <h3 class="car-titles">车型介绍</h3>
                <p class="brife text-flow">${cartypeIntroduce }</p>
                <i class="arrow-icons"></i>
                
            </div>
        </li>
        <li>
            <div class="car-pres">
                <h3 class="car-titles">车型信息</h3>
                <div class="clearfix">
              	  <c:forEach items="${cartyepParam }" var="item">
                	<p class="car-info-block"><img class="car-info-icon" src="${item.paramImage }"> ${item.paramContent }</p>
                 </c:forEach>
                </div>
            </div>
        </li>
        <c:if test="${not empty serviceNames }">
        <li>
            <div class="car-pres">
                <h3 class="car-titles">用车服务</h3>
                <div class="type-font">
                <c:forEach items="${serviceNames }" var="item">
                		${item }
                	</c:forEach>
                    <!-- <a href="javascript:">上门取还</a>
                    <a href="javascript:">清洁养护</a> -->
                </div>
            </div>
        </li>
        </c:if>
        <li>
            <div class="car-pres">
                <h3 class="car-titles">所属套餐</h3>
                <div class="type-font">
                	<c:forEach items="${itemNames }" var="item">
                		${item }　
                	</c:forEach>
                   <!--  <a href="javascript:">标准套餐</a>
                    <a href="javascript:">尊享套餐</a> -->
                </div>
            </div>
        </li>
        <li>
            <div class="car-pres">
                <h3 class="car-titles">用车事宜</h3>
                <div class="type-font">
                    <a href="javascript:">${carMatters }</a>
                </div>
            </div>
        </li>
    </ul>
    <div class="car_fix">
      <c:if test="${flag == 2}">
	        <div class="car_money">
	            <span>${dayPrice }元</span>/日起
	        </div>
	         <a href="javascript:" onclick="showModel()" class="car-use">用车</a>
        </c:if>
        <c:if test="${flag == 0}">
       		 <a href="JavaScript:toCreateMemberPower();" class="car-use">申请入会</a>
        </c:if>
        <c:if test="${flag == 1}">
       		 <a href="JavaScript:toCreateMemberPower();" class="car-use">升级套餐</a>
        </c:if>
      
    </div>
    <script>
    function showModel(){
    	 var myt=  myconfirm();
			myt.show('提示','用车需在极车公社APP端进行发起',function(){
				window.location.href="/static/downLoad/down.html";
			},"下载",function(){},"取消")
    	//查询该车型是否已发起用车 如有 跳转用车信息页 否则 提示下载app
    	
//     	$.ajax({
// 		type: 'GET',
// 		url: "${ctx}/wechat/mycars/userCar" ,
// 		data:{"carTypeId":'${id}'},
// 		success: function(data){
// // 			alert(data.isUsedCar);
// 			if(data.isUsedCar){
// 				//跳用车信息页面
// 				var memberItemId=data.memberItemId;
// 				window.location.href="${ctx}/wechat/mycars/powerUsed?carTypeId="+'${id}'+"&memberItemId="+memberItemId;
// 			}else{
// 			  var myt=  myconfirm();
// 				myt.show('提示','用车需在极车公社APP端进行发起',function(){
// 	  				window.location.href="/static/downLoad/down.html";
// 	  			},"下载",function(){},"取消")
				
// 			}
			
			
// 		}
// 	});
//          var myt=  tooltip();
//  		myt.show('提示','<a href="tel:400-9029-858">如需用车，请联系我们<span class="tel">400-9029-858</span></a>',function(){})
    }
    var arrow = 'down';
    $('.arrow-icons').on('click',function(){
    	if(arrow == 'down'){
    		arrow = 'up';
    		$(this).addClass('arrow-icons-up');
    		$('.brife').removeClass('text-flow');
    	}else{
    		arrow = 'down';
    		$('.brife').addClass('text-flow');
    		$(this).removeClass('arrow-icons-up');
    	}
    });
    
    $('#toPlay').on('click',function(){
    	document.getElementById('video').play();
    	$(this).parent().css('display','none');
    });
    
    function toCreateMemberPower(){
    	window.location.href="${ctx}/wechat/memberPower/toCreate";
    }
    
</script>
</body>
</html>