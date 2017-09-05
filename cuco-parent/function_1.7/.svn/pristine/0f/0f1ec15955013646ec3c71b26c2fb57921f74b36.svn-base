<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
    <link href="${ctx}/static/gcar_html/css/bultertask.css" rel="stylesheet" type="text/css" />
	<!-- <script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script> -->
	
	<title>订单详情</title>
	</head>
	<body class="greyBg">
	<!--默认详情页-->
	<div style="display: block;" id="main_div">
    <%-- <p class="state">订单信息（${order.statusInfo}）</p> --%>
    <!-- 当前登录用户 -->
    <section class="li saler" onclick='javascript:showChoseSaler();'><label class="title">负责销售</label><span id="modifier_span">${order.modifier }</span><i></i></section>
    <!--个人会员-->
   	<c:if test="${0==member.type}">
    <section class="self">
        <section class="li"><label class="title">姓名</label><span>${member.name}</span></section>
        <section class="li"><label class="title">性别</label>
        <span>
        	<c:if test="${member.sex!=-1 }">
				<c:if test="${member.sex==1}">男</c:if> 
				<c:if test="${member.sex==0}">女</c:if>
			</c:if>
		</span></section>
        <section class="li"><label class="title">手机号码</label><c:if test="${not empty member.mobile}"><a href="tel:${member.mobile}"><span class="tel">${member.mobile}</span></a></c:if></section>
        <section class="li"><label class="title">会员等级</label><span>${memberItem.itemName!=null?memberItem.itemName:'-'}</span></section>
    </section>
    </c:if>
    <!--个人会员结束-->
    <!--企业会员-->
    <c:if test="${1==member.type}">
	    <section class="self">
	        <section class="li"><label class="title">企业名称</label><span>${member.name!=null?member.name:'极车会员'}</span></section>
	        <section class="li"><label class="title">联系人</label><span>${member.orgName!=null?member.orgName:'-'}</span></section>
	        <section class="li"><label class="title">联系方式</label><c:if test="${not empty member.orgTel}"><a href="tel:${member.orgTel}"><span class="tel">${member.orgTel}</span></a></c:if></section>
	        <section class="li"><label class="title">会员等级</label><span>${memberItem.itemName!=null?memberItem.itemName:'-'}</span></section>
	    </section>
  	</c:if>
   	<!--企业会员结束-->
   	
   	<!--城市以及产品-->
   	<section class="packages">
        <section class="li" ><label class="title">使用城市</label><span>${order.cityName}</span></section>
        <section class="li" ><label class="title">购买产品</label><span>${item.levelName}</span></section>
    </section>
    
    <!--状态-->
   	<section class="review clearfix">
        <section class="li state" style="height: auto; position:relative;">
            <label class="title " style="position:absolute; left:0; top:8px">状态</label>
            <div style="width:100%; box-sizing:border-box; padding-left:9rem">
            <c:choose>
		    	<c:when test="${order.status==2}">
		    		<p style="display:inline-block; white-space: nowrap;"><input type="radio" name="statusRedio"  value="100" checked><label for="go">跟进中</label></p>
		    		<p style="display:inline-block; white-space: nowrap;"><input type="radio" name="statusRedio"  value="3"><label for="pass">已签约</label></p>
		    		<p style="display:inline-block; white-space: nowrap;"><input type="radio" name="statusRedio"  value="0"><label for="cancel">已取消</label></p>
                   </c:when>
                   <c:when test="${order.status==3 || order.status==4}">
                   	<p style="display:inline-block; white-space: nowrap;"><input type="radio" name="statusRedio" id="go" value="100" checked><label for="go">跟进中</label></p>
                   	<p style="display:inline-block; white-space: nowrap;"><input type="radio" name="statusRedio"  value="0"><label for="cancel">已取消</label></p>
                   </c:when>
	        </c:choose>
	        </div>
        </section>
    </section>
   	
   	<!--付款信息-->
   	<c:if test="${order.status==3 || order.status==4}">
   	<section class="packages">
   		<c:if test="${not empty couponinfoList}">
   				<section class="li" ><label class="title">优惠券</label>
	   				<select class='sel_state' onchange="changeCoupon()" id="coupon_select">
	   				<option value="no" couponPrice="0">不选择优惠券</option>
	  				<c:forEach items="${couponinfoList }" var="model">
			        	<option value="${model.id}" couponPrice="${model.price}" >${model.couponName}</option>
			        </c:forEach>
			        </select><span class='money' id="counpon_hidden"></span>
		        </section>
   		</c:if>
   		
        <section class="li" ><label class="title">实收金额</label><input class='ipt_money'  type='number' id="payment"/><span class='money'>应付金额${item.price }元</span></section>
        <section class="li" ><label class="title">实收押金</label><input  class='ipt_money' type='number' id="paymentDeposit"/><span class='money'>应付押金10000元</span></section>
    </section>
    </c:if>
    
   	<!--备注-->
   	<section class="li" onclick='javascript:showRemarks();'><label class="title">备注</label><span id="remarks"></span><i></i></section>
   	
   	<section class="btns">
        <div class="btn cancel" onclick='javascript:createOrderCancel();'>取消</div>
        <div class="btn sure" onclick='javascript:formSubmit();' id="submitButton">确定</div>
    </section>

    <form style="display:none" id="orderForm" method="post" action="${ctx}/wechat/order/edit">
        <!--设置隐藏域-->
		<input type="hidden" name="remark" id="remark0">
        <input type="hidden" name="loginId" id="memberId" value="${loginId}">
        <input type="hidden" name="id" value="${order.id}"/>
        <input type="hidden" name="status" id="status"/>
        <input type="hidden" name="itemId" id="itemId" value="${order.itemId}"/>
        <input type="hidden" name="modifierId" value="${order.modifierId}" id="modifierId"/>
       	<input type="hidden" name="modifier" value="${order.modifier}" id="modifier"/>
       	<input type="hidden" name="cityId" id="cityId" value="${order.cityId}">
       	<input type="hidden" name="cityName" id="cityName" value="${order.cityName}">
       	<input type="hidden" name="payment" id="order_payment">
       	<input type="hidden" name="paymentDeposit" id="order_paymentDeposit">
       	<input type="hidden" name="refundPrice" id="refundPrice">
       	<input type="hidden" name="couponId" id="couponId">
       	<input type="hidden" name="couponName" id="couponName">
       	<input type="hidden" name="couponPrice" id="couponPrice">
 	</form>
 	
    <form style="display:none" id="detailForm" method="post" action="${ctx}/wechat/order/detail">
        <!--设置隐藏域-->
		<input type="hidden" name="loginId" id="loginId">
        <input type="hidden" name="id"  id="orderId">
       
 	</form>
       	<input type="hidden" id="itemPrice" value="${item.price }">
       	<input type="hidden" id="hasPayment" value="${order.payment }">
       	<input type="hidden" id="oldStatus" value="${order.status }"/>
</div>

<!--销售选择-->
<div style="display: none" id="choseSaler_div">
    <ul class="choseBulter" id="choseSaler_ul">
        <li id="chooseSell">当前负责销售：暂未选择</li>
        <c:forEach var="member" items="${memberList}">
        	<li  onclick="choseSaler('${member.sysuserId}','${member.sysuserName}',this)">${member.sysuserName}</li>
        </c:forEach>
    </ul>
    <div class="fixBottom noBg">
        <a href="javascript:choseSalerCancel();" class="button douBtn cancleBtn">取消</a>
        <a href="javascript:choseSalerCompelete();" class="button douBtn">确定</a>
    </div>
</div>
<!--城市选择-->
<div class="selPro" style="display: none;" id="choseCity_div">
	<p class="title"><span id="chooseCity">产品使用城市：暂未选择</span></p>
	<ul class="choseBulter selUl" id="choseCity_ul">
        <li  onclick="choseCity(2,'北京市',this)">北京</li>
    </ul>
    <div class="fixBottom noBg">
        <a href="javascript:choseCityCancel();" class="button douBtn cancleBtn">取消</a>
        <a href="javascript:choseCityCompelete();" class="button douBtn">确定</a>
    </div>
</div>
<!--填写备注-->
<div class="section" style="display: none;" id="remark_div">
   <textarea class="textarea" id="textarea" placeholder="请输入备注" ></textarea>
    <li class="errInfo"></li>
    <div class="fixBottom noBg">
        <a href="javascript:remarkCancel();" class="button douBtn cancleBtn">取消</a>
        <a href="javascript:remarkCompelete();" class="button douBtn">确定</a>
    </div>
</div>


<div class="poper" id="refund_div" style="display: none">
	<div class='poper-body'>
		<div class='form-group'>
			<label>退款金额：</label><input  type='text' id="refund_hidden"/>
		</div>
		<button class='button' onclick="refundSub()">确定</button>
	</div>
</div>
 
</body>
<script type="text/javascript">
function changeCoupon(){
	//
	var couponid = $("#coupon_select option:selected").val();
	var couponName = $("#coupon_select option:selected").html();
	var couponprice = $("#coupon_select option:selected").attr("couponPrice");
	if(0==couponprice){
		$("#counpon_hidden").empty();
		$("#couponId").val("");
		$("#couponName").val("");
		$("#couponPrice").val("");
	}else{
		$("#counpon_hidden").html("抵扣"+couponprice+"元");
		$("#couponId").val(couponid);
		$("#couponName").val(couponName);
		$("#couponPrice").val(couponprice);
	}
	
}
//取消提交操作
function createOrderCancel(){
	window.location.href="${ctx}/wechat/order/toOrderList?loginId=${loginId}";
}
//确定提交表单
function formSubmit(){
	
	var status = $("input[name='statusRedio']:checked").val();
	var oldStatus = $("#oldStatus").val();
	var itemPrice = $("#itemPrice").val();
	var payment = $("#payment").val();
	var paymentDeposit = $("#paymentDeposit").val();
	var hasPayment = $("#hasPayment").val();
	var couponPrice = $("#couponPrice").val();
	if(null==couponPrice || ""==couponPrice){
		couponPrice = 0;
	}
	if(null==hasPayment || ""==hasPayment){
		hasPayment = 0;
	}
	if(status!=100){
		$("#status").val(status);
	}
	if((oldStatus ==3 || oldStatus ==4) && 0 != status){
			
		if(null==payment || ""==payment){
			alert("请先填写实收金额");
			return false;
		}
		if(!(/^(0|[1-9]\d*)$/.test(payment))){
			alert("实收金额必须为正整数");
			return false;
		}
		if(null==paymentDeposit || ""==paymentDeposit){
			alert("请先填写实收押金");
			return false;
		}
		if(!(/^(0|[1-9]\d*)$/.test(paymentDeposit))){
			alert("实收押金必须为正整数");
			return false;
		}
		if(status==100){
			if(parseInt(payment)+parseInt(hasPayment)+parseInt(couponPrice)>=parseInt(itemPrice)){
				//支付金额大于等于套餐金额
				$("#status").val("5");
			}else{
				alert("付款金额不足，无法购买该产品套餐。该产品套餐金额为"+itemPrice+"元");
				return false;
				//不存在待结款了
				//$("#status").val("4");
			}
			if(null==paymentDeposit || ""==paymentDeposit){
				alert("请填写押金");
				return false;
			}else if(parseInt(paymentDeposit)<10000){
				alert("实付押金不足   请确认押金金额");
				return false;
			}
		}
		$("#order_payment").val(payment);
		$("#order_paymentDeposit").val(paymentDeposit);
	}
	//取消的情况
	var remark = $("#remark0").val();
	if(status==0){
		if(null==remark || ""==remark){
			alert("取消订单，请输入备注信息");
			return false;
		}
		//待结款取消，则需要填写退款金额
		/* if(oldStatus ==4){
			$("#refund_div").show();
			return false;
		} */
	}
	$("#submitButton").addClass("greyBtn");
	$("#submitButton").removeAttr("onclick");
	//$("#orderForm").submit(); 
	$.ajax({
        type: 'POST',
        url: "${ctx}/wechat/order/editAjax",
        async:false,
        data:$("#orderForm").serialize(),
        //  返回数据处理
        success: function(data){
            if(true==data.result){
            	$("#orderId").val(data.orderId);
            	$("#loginId").val(data.loginId);
            	if(!data.flag){
// 	            	alert(data.flag);
            		$("#detailForm").attr("action","${ctx}/wechat/order/toOrderList");
            	}
            	$("#detailForm").submit();
//             	alert(data.orderId);
//             	alert(data.loginId);
            }else{
            	 alert(data.msg);
            }
        }
    });
}
/* //退款金额确认
function refundSub(){
	var refundPrice = $("#refund_hidden").val();
	if(null == refundPrice || ""==refundPrice){
		alert("请填写退款金额");
		return false;
	}
	$("#refundPrice").val(refundPrice);
	
	$("#submitButton").addClass("greyBtn");
	$("#submitButton").removeAttr("onclick");
	$("#orderForm").submit(); 
} */

//调用销售选择div
function showChoseSaler(){
	$("#main_div").hide();
	//直接显示
	$("#choseSaler_div").css('display','block');
}
//选择销售切换样式
function choseSaler(modifierId,modifier,obj){
	//交换样式
	$("#choseSaler_ul li").removeClass("on");
	$(obj).addClass("on");
	$("#chooseSell").html("当前负责销售："+modifier);
	$("#modifierId").val(modifierId);
	$("#modifier").val(modifier);
}
//选择销售点击确定
function choseSalerCompelete(){
	$("#modifier_span").empty();
	$("#modifier_span").html($("#modifier").val());
	//负责管家重新赋值
	$("#choseSaler_div").hide();
	$("#main_div").show();
}
//选择管家点击取消
function choseSalerCancel(){
	$("#choseSaler_div").hide();
	$("#main_div").show();
}

//显示选择城市
function showChoseCity(){
	$("#main_div").hide();
	//直接显示
	$("#choseCity_div").show();
}
//选择城市切换样式
function choseCity(cityId,cityName,obj){
	//交换样式
	$("#choseCity_ul li").removeClass("on");
	$(obj).addClass("on");
	$("#chooseCity").html("产品使用城市："+cityName+"");
	$("#cityId").val(cityId);
	$("#cityName").val(cityName);
}
//选择城市点击取消
function choseCityCancel(){
	$("#choseCity_div").hide();
	$("#main_div").show();
}
//选择城市点击确定
function choseCityCompelete(){
	$("#city_span").empty();
	$("#city_span").html($("#cityName").val());
	$("#choseCity_div").hide();
	$("#main_div").show();
}
//显示备注div
function showRemarks(){
	//$("#textarea").val($("#remark0").val());
	$("#main_div").hide();
	$("#remark_div").show();
}
//备注取消操作
function remarkCancel(){
	$("#textarea").val("");
	$("#remark_div").hide();
	$("#main_div").show();
}
//备注完成点击确定操作
function remarkCompelete(){
	/* if(null==$("#textarea").val()||""==$("#textarea").val()){
		$(".errInfo").html("请填写备注，再确定")
		return;
	} */
	$("#remarks").html($("#textarea").val());
	$("#remark0").val($("#textarea").val());
	$("#remark_div").hide();
	$("#main_div").show();
}
</script>
</html>
