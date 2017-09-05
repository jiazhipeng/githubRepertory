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
	<!-- <script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script> -->
    <link href="${ctx}/static/gcar_html/css/bultertask.css" rel="stylesheet" type="text/css" />
    
	<title>创建订单</title>
	</head>
    <body class="greyBg">
	<!--默认详情页-->
	<div style="display: block;" id="main_div">
	<!-- 当前登录用户 -->
    <section class="li saler" ><label class="title">负责销售</label><span id="modifier_span">${loginMember.sysuserName }</span></section>
    
    <!--个人会员-->
   	<c:if test="${0==member.type}">
    <section class="self">
        <section class="li"><label class="title">姓名</label><span class="overflow-text">${member.name!=null?member.name:'极车会员'}</span></section>
        <section class="li"><label class="title">性别</label>
        <input type="hidden" value="${member.sex}" id="hidden_sex"/>
        <input type="hidden" value="${member.type}" id="hidden_type"/>
        <span>
				<c:if test="${member.sex==1}">男</c:if> 
				<c:if test="${member.sex==0}">女</c:if>
				<c:if test="${member.sex==-1}">-</c:if>
		</span>
		</section>
        <section class="li"><label class="title">手机号码</label><c:if test="${not empty member.mobile}"><a href="tel:${member.mobile}"><span class="tel">${member.mobile}</span></a></c:if></section>
    </section>
    </c:if>
    <!--个人会员结束-->
    <!--企业会员-->
   <c:if test="${1==member.type}">
   		<section class="self">
       		<section class="li"><label class="title">企业名称</label><span>${member.name!=null?member.name:'极车会员'}</span></section>
       		<section class="li"><label class="title">联系人</label><span>${member.orgName!=null?member.orgName:'-'}</span></section>
       		<section class="li"><label class="title">联系方式</label><c:if test="${not empty member.orgTel}"><a href="tel:${member.orgTel}"><span class="tel">${member.orgTel}</span></a></c:if></section>
   		</section>
  	</c:if>
   	<!--企业会员结束-->
   	<!--城市以及产品-->
   	<section class="packages">
        <section class="li"><label class="title">使用城市</label><span id="city_span">北京市</span></section>
        <section class="li" onclick='javascript:showChoseProduct();'><label class="title">购买产品</label><span id="product_span">请选择产品</span><i></i></section>
    </section>
   	<!--状态-->
  
   	<!--备注-->
   	<section class="li" onclick='javascript:showRemarks();'><label class="title">备注</label><span id="remarks"></span><i></i></section>
   	
   	<section class="btns">
        <div class="btn cancel" onclick='javascript:createOrderCancel();'>取消</div>
        <div class="btn sure" onclick='javascript:formSubmit();' id="submitButton">确定</div>
    </section>
    
    <form method="post" action="${ctx}/wechat/order/createOrder" id="orderForm">
    	<!--设置隐藏域-->
        <input type="hidden" name="memberId" id="memberId" value="${member.id}">
        <input type="hidden" name="memberName" id="memberName" value="${member.name}">
        <input type="hidden" name="memberMobile" id="memberMobile" value="${member.mobile}">
        <input type="hidden" name="memberSex" id="memberSex" value="${member.sex}">
        <input type="hidden" name="status" id="status" />
        <input type="hidden" name="remark" id="remark0">
        <input type="hidden" name="cityId" id="cityId" value="2">
        <input type="hidden" name="cityName" id="cityName" value="北京市">
        <input type="hidden" name="modifierId"  id="modifierId" value="${loginMember.sysuserId }"/>
       	<input type="hidden" name="modifier"  id="modifier" value="${loginMember.sysuserName }"/>
       	<input type="hidden" name= "loginId" id = "loginId"  value="${loginMember.id}"/>
       	
       	<!--产品隐藏域 -->
       	<input type="hidden" name="itemId" id="itemId">
       	<input type="hidden" name="total" id="total" />
       	<input type="hidden" name="itemName" id="itemName">
       	<input type="hidden" name="carType" id="carType">
       	<input type="hidden" name="carBrand" id="carBrand">
       	<input type="hidden" name="trialStatus" id="trialStatus" >
       	<input type="hidden" id="count" />
       	<input type="hidden" name="applyIntention" id="applyIntention"/>
    </form>
    
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
 
<!--产品选择-->
<div class="selPro" style="display: none;" id="choseProduct_div">
	 
    <div class="packages" id="item_div" style="padding-bottom:90px;">
	    <!-- <div class="package">体验套餐
	    	<i class="choose"></i>
	   		<p class='model'><label>启用车型</label> <select><option value='1'>请选择</option></select> </p>	
	    </div>
	    <div class="package">入门套餐</div>
	    <div class="package">标准套餐</div>
	    <div class="package">尊贵套餐</div> -->
	</div>
	<div class="btns">
	    <div class="btn cancel" onclick="resetProduct()">取消</div>
	    <div class="btn sure" onclick="chooseProductcomplete()">确定</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("input[name='sex']").on("click",function(){
		$("#hidden_sex").val($("input[name='sex']:checked").val());
		$("#memberSex").val($("input[name='sex']:checked").val());
	});	
})
//取消提交操作
function createOrderCancel(){
	window.location.href="${ctx}/wechat/order/toOrderList?loginId=${loginId}";
}
//确定提交表单
function formSubmit(){
	var sex = $("#hidden_sex").val();
	var hidden_type = $("#hidden_type").val();
	/* if(0==hidden_type && (null==sex||""==sex||"-1"==sex)){
		alert("请选择性别");
		return false;
	} */
	var cityId = $("#cityId").val();//城市
	if(null==cityId||""==cityId){
		alert("请选择城市");
		return false;
	}
	var itemId = $("#itemId").val();//产品
	if(null==itemId||""==itemId){
		alert("请选择购买产品");
		return false;
	}
	
	$("#submitButton").addClass("greyBtn");
	$("#submitButton").removeAttr("onclick");
	$.ajax({
		type: 'POST',
		url: "${ctx}/wechat/order/createOrder",
		data:$("#orderForm").serialize(),
		//  返回数据处理
		success: function(data){
			if(data.result==true){
				window.location.href="${ctx}/wechat/order/toOrderList?loginId=${loginId}";
			}else{
				$("#submitButton").removeClass("greyBtn");
				$("#submitButton").attr("onclick","formSubmit()");	
				alert(data.msg);
			}
		}
	});
}
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
//显示选择产品
function showChoseProduct(){
	$("#main_div").hide();
	//直接显示
	getProduct();
	$("#choseProduct_div").css('display','block');
}
//套餐选择页面-查询所有上架的套餐
function getProduct(){
	$.ajax({
		type: 'GET',
		url: "${ctx}/wechat/order/getItems",
		async:false,
		data:{"status":1},
		//  返回数据处理
		success: function(data){
			if(data.result==true){
				//成功之后加载数据
				$("#item_div").empty();
				var itemList="";
				for(var i=0;i<data.items.length;i++){
					itemList += ('<div class="package" onclick="getCarType('+data.items[i].id+','+data.items[i].price+','+data.items[i].enableCount+',this)">');
					itemList += data.items[i].levelName;
					itemList += ('</div>');
				}
				$("#item_div").append(itemList);
			}else{
				alert(data.msg);
			}
		}
	});
}
//根据套餐ID查询所有的车型
function getCarType(itemId,price,count,obj){
	$("#total").val(price);
	$("#count").val(count);
	$.ajax({
		type: 'GET',
		url: "${ctx}/wechat/order/getCarTypes",
		async:false,
		data:{"itemId":itemId},
		//  返回数据处理
		success: function(data){
			if(data.result==true){
				//成功之后加载数据
				$(obj).find('p.model,i').remove();
				var carTypeOption="";
				carTypeOption += '<i class="choose"></i>';
				carTypeOption += '<p class="model">';
				carTypeOption+='<label>启用车型</label>';
				//carTypeOption+=' <select onclick="test(event)" onchange="getcarTypeInfo(this)" id="carTypeSelect")>';
				
				
				//carTypeOption += '<option value="" carBrand="" cartype="">请选择</option>';
				for(var i=0;i<data.cartypes.length;i++){
					carTypeOption += '<span class="chkitem" onclick="test(event)"><input id="carTypeCheckbox" name="carTypeCheckbox" type="checkbox" itemId="'+data.cartypes[i].itemId+'" itemName="'+data.cartypes[i].itemName+'" value="'+data.cartypes[i].cartypeId+'" carBrand="'+data.cartypes[i].carBrand+'" cartype="'+data.cartypes[i].cartype+'">'+data.cartypes[i].carBrand+" "+data.cartypes[i].cartype+'</span>';
				}
				//carTypeOption += '</select> </p>';
				
				
				carTypeOption+='</p>';
				$(obj).append(carTypeOption).siblings().find('p.model,i').remove();
			}else{
				alert(data.msg);
			}
		}
	});
}
//事件捕获
function test(e){
	e.stopPropagation();
}
/* //车型选中事件
function getcarTypeInfo(obj){
	var itemId = $(obj).find("option:selected").val();
	var itemName = $(obj).find("option:selected").attr("itemName");
	var carBrand = $(obj).find("option:selected").attr("carBrand");
	var carType = $(obj).find("option:selected").attr("cartype");
	$("#itemId").val(itemId);
	$("#carBrand").val(carBrand);
	$("#itemName").val(itemName);
	//alert('{"id":"'+itemId+'","carBrand":"'+carBrand+'","carType":"'+carType+'"}');
	$("#carType").val('[{"id":"'+itemId+'","carBrand":"'+carBrand+'","carType":"'+carType+'"}]');
} */

//取消选择产品
function resetProduct(){
	$("#choseProduct_div").hide();
	$("#main_div").show();
}
//选择完产品点击确定
function chooseProductcomplete(){
	var carTypes = $("input[name='carTypeCheckbox']:checked").length;
	var count = $("#count").val();
	if(parseInt(carTypes)!=parseInt(count)){
		alert("该等级启用数量为"+count);
		return false;
	}
	var cartypeStr = "[";
	for (var i=0; i<carTypes; i++){
		var itemId = $('input[name="carTypeCheckbox"]:checked:eq('+i+')').attr("itemId");
		var itemName = $('input[name="carTypeCheckbox"]:checked:eq('+i+')').attr("itemName");
		var carTypeId = $('input[name="carTypeCheckbox"]:checked:eq('+i+')').val();
		var carBrand = $('input[name="carTypeCheckbox"]:checked:eq('+i+')').attr("carBrand");
		var carType = $('input[name="carTypeCheckbox"]:checked:eq('+i+')').attr("carType");
		var price = $('input[name="carTypeCheckbox"]:checked:eq('+i+')').attr("price");
		$("#itemId").val(itemId);
		$("#applyIntention").val(itemName);
		//选择的产品带出
		$("#product_span").empty();
		//选择的产品带出
		$("#product_span").html(itemName);
		
		cartypeStr += '{"id":"'+carTypeId+'","brand":"'+carBrand+'","carType":"'+carType+'"}';
		if(i!=carTypes-1){
			cartypeStr += ",";
		}
	}
	cartypeStr += "]";
	
	if("[]" != cartypeStr){
		$("#carType").val(cartypeStr);
	}
	
	$("#choseProduct_div").hide();
	$("#main_div").show();
}
</script>
</html>
