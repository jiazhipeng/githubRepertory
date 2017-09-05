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
    <title>我的车库</title>
    <link href="${ctx }/static/gcar_html/css/service.css" rel="stylesheet" type="text/css" />
    
      <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet">
    <link href="${ctx}/static/gcar_html/css/popup.css" rel="stylesheet">
  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> 
    <script src="${ctx}/static/gcar_html/js/popup.js"></script>
    
    <style>
        .package{padding-bottom:4%; background:#eee; margin-bottom:4%;border-bottom:1px solid #ccc;}
        .package .box{background:#fff; padding:0 4%; line-height:4.5rem; border-bottom:1px solid #ccc; overflow:hidden;}
        .package .right{color:#999; background:url("${ctx }/static/gcar_html/images/arrow_right_grey.png") no-repeat right center; background-size:7px; padding-right:16px; float:right;}
    	.carShow ul li{position:relative; margin-top:4%;}
    	.car-info-bar{position:absolute; bottom:0; left:0; width:100%; height:40px; line-height:40px; background:rgba(0,0,0,0.6); font-size:1.3rem; color:#fff;}
    	.car-info-bar .name{float:left; padding-left:8px;}
    	.car-info-bar .price{float:right; padding-right:8px;}
    </style>
</head>
<body>
<input type="hidden" id="memberId" value="${memberId }">
    <!--头部-->
    <header class="header">
        <span class="userIcon"></span>
        <ul class="tab">
            <li class="flex active" id="mycar">
                <span>我的车库</span>
                <em class="line"></em>
            </li>
            <li class="flex " id="casshow">
                <span>极车展厅</span>
            </li>
        </ul>
    </header>

    <!--我的极车 start-->
    <div class="mycar serviSec" >

        <!--车辆信息 start-->
        <div class="carInfo">
            

            <!--多辆车 start-->
            <div style="display: block">
                <div class="package">
                <c:if test="${flag!=0 }">
                <a href="${ctx }/wechat/mycars/carTypeDetail?id=${memberItemId }&memberId=${memberId}">
                    <div class="box">
                        ${itemName }
                        <span class="right">
                        	    权益信息
                        </span>
                    </div>
                    </a>
                    </c:if>
                </div>
                <ul class="myCarList">
                <c:if test="${flag==0 }">
                	<c:forEach items="${cartypes }" var="item" varStatus="model">
	                       <a  href="#" onclick="showModelBeOpend()">
	                 		<img src="${item.carTypeImageUrl}">
	                        <div class="carName">
	                            <span class="name">${item.brand } ${item.carType }</span>
	                            <span class="state">
	                            	<c:if test="${item.status==0 }">
	                            	待启用
	                            	</c:if>
	                            </span>
	                        </div>
                        </a>
                 	</c:forEach>
                </c:if>
                 <c:if test="${flag!=0 }">
                 	<c:forEach items="${cartypes }" var="item" varStatus="model">
                 		<%-- <a  href="#" onclick="showModel('${item.carTypeId }')"> --%>
                 		<c:if test="${item.status==0 }">
	                       <a  href="#" onclick="showModelBeOpend2()">
	                    </c:if>
                 		<c:if test="${item.status==1 || item.status==2 ||item.status==3}">
                 			<a  href="#" onclick="showModel('${model.index }')">
	                    </c:if>
	                 		<img src="${item.carTypeImageUrl}">
	                        <div class="carName">
	                       			 <c:if test="${item.status==2 || item.status==3}">
		                            <span class="name">${item.brand } ${item.carType }</span>
	                            	</c:if>
	                            <span class="state">
	                            	<c:if test="${item.status==1 }">
	                            	待使用
	                            	</c:if>
	                            	<c:if test="${item.status==2 }">
	                            	使用中
	                            	</c:if>
	                            	<c:if test="${item.status==0 }">
	                            	待启用
	                            	</c:if>
	                            	<c:if test="${item.status==3 }">
	                            	已预约
	                            	</c:if>
	                            </span>
	                        </div>
                        </a>
                 	</c:forEach>
                 	
                 
                 
                 </c:if>
                 
                </ul>
            </div>
            <!--多辆车 stop-->
        </div>
        <!--车辆信息 stop-->

     
    </div>
    <!--我的极车 stop-->


  
      <!--用户菜单-->
    <div class="userSlider" style="display: none">
        <div class="sliderSec">
            <div class="userInfo">
                <a href="${ctx }/wechat/member/show/memberInfo?id=${memberId}" class="icon">
                   <%--  <img id="imageUrl" src="${ctx }/static/gcar_html/images/usr.png"> --%>
                   <!--  <em class="vip"></em> -->
                </a>
                <span class="name" id="memberName"></span>
            </div>

            <div class="usrList">
                <a href="javascript:toMycoupon();" class="active">
                    <img src="${ctx }/static/gcar_html/images/coup_icon.png">
                    我的优惠券
                </a>
                <a href="javascript:toMyAssets();">
                    <img src="${ctx }/static/gcar_html/images/integral_icon.png">
                    我的积分
                </a>
                <a href="javascript:toMyPower();">
                    <img src="${ctx }/static/gcar_html/images/membericon.png">
                    会员权益
                </a>
                <a href="javascript:toMyAbout();">
                    <img src="${ctx }/static/gcar_html/images/abouticon.png">
                    关于公社
                </a>
          <%-- 		<a id="isSell" style="display: none"  href="${ctx }/wechat/order/toOrderList?loginId=${memberId}">
                    <img src="${ctx }/static/gcar_html/images/sellicon.png">
       销售入口
                </a> --%>
                <a id="isButler" style="display: none" href="${ctx }/wechat/butlerTask/toButlerManager?memberId=${memberId}">
                    <img src="${ctx }/static/gcar_html/images/butlertaskicon.png">
                      管家入口
                </a>
                    <a id="isStore" style="display: none" href="${ctx }/wechat/carOperate/toSelectCarType">
                    <img src="${ctx }/static/gcar_html/images/butlertaskicon.png">
                      查看排期
                </a>
                 <a id="isCard" style="display: none" href="${ctx }/wechat/card/toCardDetail?memberId=${memberId}">
                    <img src="${ctx }/static/gcar_html/images/butlertaskicon.png">
                      销售名片
                </a>
                <a href="javascript:logout();">
                    <img src="${ctx }/static/gcar_html/images/logout_icon.png">
                    退出登录
                </a>
            </div>
        </div>
        <div class="closeUerList"></div>
    </div>

<script>
$('#memberName').click(function(){
	window.location.href="${ctx }/wechat/member/show/memberInfo?id=${memberId}";
});

//跳转我的优惠券
function toMycoupon(){
	window.location.href="${ctx}/wechat/personalCenter/toCouponList?memberId=${memberId}";
}
//跳转我的积分
function toMyAssets(){
	window.location.href="${ctx}/wechat/personalCenter/toAssets";
}
//跳转到会员权益
function toMyPower(){
	window.location.href="${ctx}/wechat/memberPower/toCreate";
}
//跳转到关于公社
function toMyAbout(){
	window.location.href="${ctx}/wechat/personalCenter/toAbout?memberId=${memberId}";
}
//
function logout(){
	var myc = myconfirm();
	 myc.show('提示', '您确定退出登录么？', function() {
		 window.location.href="${ctx}/wechat/login/logout";
   },'确定'); 
}




$('.header .userIcon').on('click',function(){
	$.ajax({
			url: "${ctx}/wechat/login/checkLogin" ,
			async:false,
			success: function(data){
//				alert(data.isLogin);
				if(data.isLogin){
					if(null==data.member.name||''==data.member.name){
						$("#memberName").html('极车会员');
					}else{
						$("#memberName").html(data.member.name);
					}
					if(null!=data.member.imageUrl&&''!=data.member.imageUrl){
						$(".icon").empty();
						$(".icon").append('<img id="imageUrl" src="'+data.member.imageUrl+'">')
//	 				data.member.member.imageUrl").attr("src",data.imageUrl);
					}else{
						$(".icon").empty();
						if("1"==data.member.sex||"-1"==data.member.sex){
							$(".icon").append('<img id="imageUrl" src="${ctx}/static/gcar_html/images/head-portrait-nan.png">')
						}else if("0"==data.member.sex){
							$(".icon").append('<img id="imageUrl" src="${ctx}/static/gcar_html/images/head-portrait-nv.png">')
						}
					}
					if(1==data.member.isSelles){
						$("#isSell").show();
					}
					if(1==data.member.isButler){
						$("#isButler").show();
					}
					if(1==data.member.isButler){
						$("#isStore").show();
					}
					if(1==data.member.isCustomer){
                        $("#isStore").show();
                    }
//                    if(1==data.member.isCustomer){
//                        $("#isButler").show();
//                    }
					if(data.isCard){
						$("#isCard").show();
					}
		    		$('.userSlider').show();
				}else{
					window.location.href="${ctx}/wechat/login";
				}
			}
	}); 
	
	
	
  
});
$('.closeUerList').click(function(){
    $('.userSlider').hide();
});
    $('#casshow').on('click',function(){
   	 	//window.location.href="${ctx}/wechat/carsshow/toCarsList?memberId="+$('#memberId').val();
    	$.ajax({
			url: "${ctx}/wechat/login/checkLogin" ,
			async:false,
			success: function(data){
//					alert(data.isLogin);
				if(data.isLogin){
					window.location.href="${ctx}/wechat/carsshow/toCarsList?memberId="+data.member.id;
				}else{
					window.location.href="${ctx}/wechat/carsshow/toCarsList";
				}
			}
	}); 
    });
    
    function showModel(index){
//     	alert(index);
    	$.ajax({
    		type: 'GET',
    		url: "${ctx}/wechat/mycars/userCar" ,
    		data:{"index":index},
    		success: function(data){
//     			alert(data.isUsedCar);
    			if(data.isUsedCar){
    				//跳用车信息页面
    				var memberItemId=data.memberItemId;
//     				var carTypeId =data.carTypeId;
    				window.location.href="${ctx}/wechat/mycars/powerUsed?index="+data.index+"&memberItemId="+memberItemId+"&memberId="+data.memberId;
    			}else{
    				
//     			  var myt=  tooltip();
//     	  			myt.show('提示','用车需在极车公社APP端进行发起',function(){
//     	  				window.location.href="/static/downLoad/down.html";
//     	  			},"下载")
    	  			
    	  			var myt=  myconfirm();
					myt.show('提示','用车需在极车公社APP端进行发起',function(){
		  				window.location.href="/static/downLoad/down.html";
		  			},"下载",function(){},"取消")
    				
    			}
    			
    			
    		}
    	});
//          var myt=  tooltip();
//  		myt.show('提示','<a href="tel:400-9029-858">如需用车，请联系我们<span class="tel">400-9029-858</span></a>',function(){})
    }
    function showModelBeOpend(){
    	//alert(1);
    	 var myc = myconfirm();
    	 myc.show('提示', '开通当前车位需加入极车会员', function() {
    		 window.location.href="${ctx}/wechat/memberPower/toCreate";
         },'申请入会'); 
        
    }
    function showModelBeOpend2(){
    	//alert(1);
    	 var myc = myconfirm();
    	 myc.show('提示', '开通当前车位需升级套餐', function() {
    		 window.location.href="${ctx}/wechat/memberPower/toCreate";
         },'升级套餐'); 
        
    }
    
    
    
    
</script>
</body>
</html>
