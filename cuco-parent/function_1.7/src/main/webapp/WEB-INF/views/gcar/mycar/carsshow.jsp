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
    <title>极车展厅</title>
    <link href="${ctx }/static/gcar_html/css/service.css" rel="stylesheet" type="text/css" />
      <link href="${ctx}/static/gcar_html/css/popup.css" rel="stylesheet">
  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> 
    <script src="${ctx}/static/gcar_html/js/popup.js"></script>
    <style>
        .package{padding-bottom:4%; background:#eee; margin-bottom:4%;border-bottom:1px solid #ccc;}
        .package .box{background:#fff; padding:0 4%; line-height:4.5rem; border-bottom:1px solid #ccc; overflow:hidden;}
        .package .right{color:#999; background:url("../../images/arrow_right_grey.png") no-repeat right center; background-size:7px; padding-right:16px; float:right;}
    	.carShow ul li{position:relative; margin-top:4%;}  /* overflow:hidden; padding-top:54.6% */
    	/* .carShow ul li a{display:block; position:absolute; top:0; left:0; width:100%; height:100%;} */
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
            <li class="flex " id="mycar">
                <span>我的车库</span>
               
            </li>
            <li class="flex  active" id="casshow">
                <span>极车展厅</span>
                 <em class="line"></em>
            </li>
        </ul>
    </header>


    <!--极车展厅 start-->
    <div class="carShow" >
        <!--<img src="../../images/carshowtit.jpg" class="title">-->
        <ul class="section">
            
        </ul>
    </div>
    <!--极车展厅 stop-->


    <!--用户菜单-->
    <div class="userSlider" style="display: none">
        <div class="sliderSec">
            <div class="userInfo">
                <a href="${ctx }/wechat/member/show/memberInfo?id=${memberId}" class="icon">
                 <%--    <img id="imageUrl" src="${ctx }/static/gcar_html/images/usr.png"> --%>
                    <!-- <em class="vip"></em> -->
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
          	<%-- 	<a id="isSell" style="display: none" href="${ctx }/wechat/order/toOrderList?loginId=${memberId}">
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
                <a href="javascript:logout();;">
                    <img src="${ctx }/static/gcar_html/images/logout_icon.png">
                    退出登录
                </a>
            </div>
        </div>
    	<div class="closeUerList"></div>
    </div>
<!--     <script src="../../js/zepto.js"></script> -->

<script>
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
	window.location.href='${ctx}/wechat/memberPower/toCreate';
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

$(document).ready(function(){
	$.ajax({
		type: 'GET',
		url: "${ctx}/wechat/carsshow/carsList" ,
	/* 	async:false, */
		//  返回数据处理
		success: function(data){
			$(".carShow .section").empty();
			 for(var i=0;i<data.length;i++){
				var li='<li><a href="${ctx}/wechat/carsshow/carTypeDetail?id='+data[i].id+'&memberId=${memberId}&price='+data[i].dayPrice+'"><img src="'+data[i].imageUrl+'">'
				+'<p class="car-info-bar">'
				+'<span class="name">'+data[i].brand+''+ data[i].carType+'</span>'
        		+'<span class="price">'+data[i].dayPrice+'元/天</span>'
				+'</p>'
				+"</a></li>"
				$(".carShow .section").append(li);
			} 
			
			
		}
	});
});



    $('.header .userIcon').on('click',function(){
//     	var memberId=$("#memberId").val();
//     	alert(getCookie("openid_224"));
//     	var openid=getCookie("openid_224");
		
	 	$.ajax({
   				url: "${ctx}/wechat/login/checkLogin" ,
   				async:false,
   				success: function(data){
//    					alert(data.isLogin);
   					if(data.isLogin){
		    		  	if(null==data.member.name||''==data.member.name){
							$("#memberName").html('极车会员');
						}else{
							$("#memberName").html(data.member.name);
						}
						if(null!=data.member.imageUrl&&''!=data.member.imageUrl){
							$(".icon").empty();
							$(".icon").append('<img id="imageUrl" src="'+data.member.imageUrl+'">')
//		 				data.member.member.imageUrl").attr("src",data.imageUrl);
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
//						if(1==data.member.isCustomer){
//                            $("#isButler").show();
//                        }
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
    $('#memberName').click(function(){
		window.location.href="${ctx }/wechat/member/show/memberInfo?id=${memberId}";
    });
    $('.closeUerList').click(function(){
        $('.userSlider').hide();
    });
    

    $('#mycar').on('click',function(){
    	//window.location.href="${ctx}/wechat/mycars/mycarsList?memberId="+$('#memberId').val();
	    	$.ajax({
					url: "${ctx}/wechat/login/checkLogin" ,
					async:false,
					success: function(data){
	//					alert(data.isLogin);
						if(data.isLogin){
							window.location.href="${ctx}/wechat/mycars/mycarsList?memberId="+data.member.id;
						}else{
							window.location.href="${ctx}/wechat/login";
						}
					}
	   	}); 
    }); 
    function getCookie(name) {
    	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)"); 
    	if(arr=document.cookie.match(reg))
    		return unescape(arr[2]); 
    	else
    		return null; 
    } 

    
    
</script>
</body>
</html>
