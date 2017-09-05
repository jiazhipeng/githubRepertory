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
	<link href="${ctx}/static/gcar_html/css/member.css" rel="stylesheet" type="text/css" />
	<title>订单详情</title>
	</head>
	<body class="greyBg">
	<!--默认详情页-->
	<div style="display: block;" id="main_div">
    <%-- <p class="state">订单信息（${order.statusInfo}）</p> --%>
    <!-- 当前登录用户 -->
    <section class="li saler" onclick='javascript:showChoseSaler();'><label class="title">负责销售</label><span id="modifier_span">${order.modifier }</span><i></i></section>
    <!--个人会员-->
    <input type="hidden" value="${member.sex}" id="hidden_sex"/>
    <input type="hidden" value="${member.type}" id="hidden_type"/>
    <input type="hidden" value="${memberItem.itemId }" id="hidden_itemId"/>
   	<c:if test="${0==member.type}">
    <section class="self">
        <section class="li"><label class="title">姓名</label><span>${member.name!=null?member.name:'极车会员'}</span></section>
        <section class="li"><label class="title">性别</label>
        <span>
        	<c:if test="${member.sex==1}">男</c:if> 
			<c:if test="${member.sex==0}">女</c:if>
			<c:if test="${member.sex==-1}">-</c:if>
		</span>
		</section>
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
        <section class="li"><label class="title">使用城市</label><span id="city_span">北京市</span></section>
        <section class="li" onclick='javascript:showChoseProduct();'><label class="title">购买产品</label><span id="product_span">${item.levelName!=null?item.levelName:'请选择产品'}</span><i></i></section>
    </section>
    
    <!--状态-->
   	<section class="review clearfix">
        <section class="li state" style="height: auto; position:relative;">
            <label class="title" style="position:absolute; left:0; top:8px">状态</label>
            <div style="width:100%; box-sizing:border-box; padding-left:9rem">
            <p style="display:inline-block; white-space: nowrap;"><input type="radio" name="statusRedio" id="go" value="100" checked><label for="go">跟进中</label></p>
            <p style="display:inline-block; white-space: nowrap;"><input type="radio" name="statusRedio" id="pass" value="2"><label for="pass">审核通过</label></p>
            <p style="display:inline-block; white-space: nowrap;"><input type="radio" name="statusRedio" id="cancel" value="0"><label for="cancel">已取消</label></p>
            </div>
        </section>
    </section>
   	<section class="upload">
        <div class="title">上传身份证照片</div>
        <div class="wrapper">
           <c:if test="${null==memberInfo.idcardFront || ''==memberInfo.idcardFront}">
           		<img onclick="editPicture('idcardFront')" id="idcardFrontImg" class="id-z">
           </c:if>
           <c:if test="${null!=memberInfo.idcardFront && ''!=memberInfo.idcardFront}">
           		<img onclick="editPicture('idcardFront')" id="idcardFrontImg" src="${memberInfo.idcardFront}">
           </c:if>
           <c:if test="${null==memberInfo.idcardBack || ''==memberInfo.idcardBack}">
           		<img class="id-b" onclick="editPicture('idcardBack')" id="idcardBackImg" >
           </c:if>
           <c:if test="${null!=memberInfo.idcardBack && ''!=memberInfo.idcardBack}">
           		<img onclick="editPicture('idcardBack')" id="idcardBackImg" src="${memberInfo.idcardBack}">
           </c:if>
           
        </div>
    </section>
    <section class="upload">
        <div class="title">上传驾驶证照片</div>
        <div class="wrapper">
        	<c:if test="${null==memberInfo.drivercardOriginal || ''==memberInfo.drivercardOriginal}">
           		<img onclick="editPicture('drivercardOriginal')" id="drivercardOriginalImg" class="driver-z">
           </c:if>
           <c:if test="${null!=memberInfo.drivercardOriginal && ''!=memberInfo.drivercardOriginal}">
           		<img onclick="editPicture('drivercardOriginal')" id="drivercardOriginalImg" src="${memberInfo.drivercardOriginal}">
           </c:if>
           <c:if test="${null==memberInfo.drivercardCopy || ''==memberInfo.drivercardCopy}">
           		<img class="driver-b" onclick="editPicture('drivercardCopy')" id="drivercardCopyImg" >
           </c:if>
           <c:if test="${null!=memberInfo.drivercardCopy && ''!=memberInfo.drivercardCopy}">
           		<img onclick="editPicture('drivercardCopy')" id="drivercardCopyImg" src="${memberInfo.drivercardCopy}">
           </c:if>
           <input id="drivercardCopy" type="hidden" value=""/>
           <input id="drivercardOriginal" type="hidden" value=""/>
           <input id="idcardFront" type="hidden" value=""/>
           <input id="idcardBack" type="hidden" value=""/>
           
           <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
           <script src="${ctx}/static/gcar_html/js/weixin/wechatConfig.js"></script>
<script>
var temp = null;
var basePath = "${ctx}";
	//编辑照片
	function editPicture(param){
		temp = param;
		$(".fixed-select").show();
	}
	
	function closePictureDiv(){
		$(".fixed-select").hide();
	}
	//拍照
	function photograph(){
		wx.chooseImage({
			count: 1, // 默认9
			sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			sourceType: ['camera'], // 可以指定来源是相机
			success: function (res) {
    			var localIds = res.localIds; //返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
				console.log(localIds);
		        //$("#"+temp).val(localIds[0]);
				if(localIds[0]){
					$("#"+temp+"Img").show();
					$("#"+temp+"Img").attr("src",localIds[0]);
					uploadWechat(localIds);
				}
			}
		});
	}
	//选择相册
	function selectAlbum(){
		wx.chooseImage({
			count: 1, // 默认9
			sizeType: ['compressed'], // 压缩图
			sourceType: ['album'], // 可以指定来源是相册
			success: function (res) {
    			var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
				console.log(localIds);
    			if(localIds[0]){
    				$("#"+temp+"Img").show();    
    			    $("#"+temp+"Img").attr("src",localIds[0]);
    	    		uploadWechat(localIds);
    			}
		      
			}
		});
	}
	
	function upload(){
		
		var idcardFront = $("#idcardFront").val();
		var idcardBack = $("#idcardBack").val();
		var drivercardOriginal = $("#drivercardOriginal").val();
		var drivercardCopy = $("#drivercardCopy").val();
		
		if((idcardFront && !idcardBack)
			||(!idcardFront && idcardBack)){
			alert('请上传完整的身份证信息');
			return false;
		}
		
		if((drivercardOriginal && !drivercardCopy)
			||(!drivercardOriginal && drivercardCopy)){
			alert('请上传完整的驾驶证信息');
			return false;
		}
		
		var pictures = {};
		if(idcardFront && idcardBack){
			pictures["idcardFront"] =  idcardFront;
			pictures["idcardBack"] = idcardBack;
			
		}
		
		if(drivercardOriginal && drivercardCopy){
			pictures["drivercardOriginal"] =  drivercardOriginal;
			pictures["drivercardCopy"] = drivercardCopy;
		}
		
		//上传到服务器
		uploadServer(pictures);
		
		return true;
	}
	
	//微信上传图片
	function uploadWechat(localId){
		closePictureDiv();
		wx.uploadImage({
			localId: localId[0], // 需要上传的图片的本地ID，由chooseImage接口获得
		    isShowProgressTips: 1, // 默认为1，显示进度提示
		    success: function (res) {
		        var serverId = res.serverId; // 返回图片的服务器端ID
		        //pictures[name] = serverId;
		        $("#"+temp).val(serverId);
		        closePictureDiv();
		        // uploadHead(serverId);
		    },
		    fail: function (res) {  
	            alert(JSON.stringify(res));  
	        }  
		});	
	}
	
	//从微信服务器下载图片到服务器
	function uploadServer(pictures){
		pictures["memberId"] = "${member.id}";
        //alert(JSON.stringify(pictures));  
		$.ajax({
			type: 'POST',
			url: "${ctx}/wechat/memberInfo/uploadMemberInfo",
			async:false,
			data:pictures,
			//  返回数据处理
			success: function(data){
	            //alert(JSON.stringify(data));  
				if(data.errcode == "ok"){
					alert('上传成功');
				}else{
					alert(data.errmsg);
				}
			},error:function(data){
				alert('上传失败');
				//$(".loading").hide();
			}
		});
		
	}

</script>
           
           
            
        </div>
    </section>
   	<!--证件结束-->
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
       	<input type="hidden" name="memberId" value="${order.memberId}"/>
		
		<!--产品隐藏域 -->
       	<!-- <input type="hidden" name="itemId" id="itemId"> -->
       	<input type="hidden" name="carType" id="carType" value='${order.carType}'>
       	<input type="hidden" name="total" id="total" value="${order.total }"/>
       	<input type="hidden" name="carBrand" id="carBrand">
       	<input type="hidden" name="trialStatus" id="trialStatus" >
       	<input type="hidden" name="applyIntention" id="applyIntention" value="${order.applyIntention }"/>
       	<input type="hidden" id="count" />
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
	 
    <div class="packages" id="item_div">
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

        <!-- 点击头像操作-->
 <div class="fixed-select" style="display: none;">
     <div class="select-cen">
         <ul>
             <li><a href="javascript:photograph();">拍照</a></li>
             <li><a href="javascript:selectAlbum();">从手机选择</a></li>
             <li><a href="javascript:closePictureDiv()">取消</a></li>
         </ul>
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
	var hidden_itemId = $("#hidden_itemId").val();
	var status = $("input[name='statusRedio']:checked").val();
	var remark = $("#remark0").val();
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
	
	if(2==status){
		if(null==itemId||""==itemId){
			alert("请选择购买产品");
			return false;
		}
		
		if((null!=itemId || ""!= itemId)&& itemId == hidden_itemId){
			alert("会员已购买该套餐 ,不能进行购买");
			return false;
		}
		
		if(!"${memberInfo.idcardFront}" && !$("#idcardFront").val()){
			alert("请上传身份证");
			return false;
		}
		if(!"${memberInfo.idcardBack}" && !$("#idcardBack").val()){
			alert("请上传身份证");
			return false;
		}
		
	}
		
	if(status!=100){
		$("#status").val(status);
	}
	//取消的情况
	if(status==0){
		if(null==remark || ""==remark){
			alert("取消订单，请输入备注信息");
			return false;
		}
	}
	
	if(2==status){
		//只有审核通过状态才需要判断上传
		var idcardFront = $("#idcardFront").val();
		var idcardBack = $("#idcardBack").val();
		var drivercardOriginal = $("#drivercardOriginal").val();
		var drivercardCopy = $("#drivercardCopy").val();
		if(""!=idcardFront||""!=idcardBack||""!=drivercardOriginal||""!=drivercardCopy){
			//有上传的情况
			if(!upload()){
				return false;
			};
		}
		
	}
	
	
	$("#submitButton").addClass("greyBtn");
	$("#submitButton").removeAttr("onclick");
	$("#orderForm").submit();
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
	$("#choseProduct_div").show();
}
//套餐选择页面-查询所有上架的套餐
function getProduct(){
	var itemId = $("#itemId").val();
	
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
					if(itemId == data.items[i].id){
						//已经选择了产品
						itemList += '<i class="choose"></i>';
					}
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
	var orderItemId = $("#itemId").val();
	var orderCarType = $("#carType").val();
	$.ajax({
		type: 'GET',
		url: "${ctx}/wechat/order/getCarTypes",
		async:false,
		data:{"itemId":itemId,"orderItemId":orderItemId,"orderCarType":orderCarType},
		//  返回数据处理
		success: function(data){
			if(data.result==true){
				//成功之后加载数据
				$(obj).find('p.model,i').remove();
				var carTypeOption="";
				carTypeOption += '<i class="choose"></i>';
				carTypeOption += '<p class="model">';
				carTypeOption+='<label >启用车型</label>';
				//carTypeOption+=' <select onclick="test(event)" onchange="getcarTypeInfo(this)" id="carTypeSelect")>';
				
				
				//carTypeOption += '<option value="" carBrand="" cartype="">请选择</option>';
				for(var i=0;i<data.cartypes.length;i++){
// 					alert(data.cartypes[i].flagStr);
					if(true == data.cartypes[i].flagStr){
						carTypeOption += '<span class="chkitem" onclick="test(event)"><input name="carTypeCheckbox"  type="checkbox" itemId="'+data.cartypes[i].itemId+'" itemName="'+data.cartypes[i].itemName+'" value="'+data.cartypes[i].cartypeId+'" carBrand="'+data.cartypes[i].carBrand+'" cartype="'+data.cartypes[i].cartype+'" checked>'+data.cartypes[i].carBrand+" "+data.cartypes[i].cartype+'</span>';
					}else{
						carTypeOption += '<span class="chkitem" onclick="test(event)"><input name="carTypeCheckbox"  type="checkbox" itemId="'+data.cartypes[i].itemId+'" itemName="'+data.cartypes[i].itemName+'" value="'+data.cartypes[i].cartypeId+'" carBrand="'+data.cartypes[i].carBrand+'" cartype="'+data.cartypes[i].cartype+'" >'+data.cartypes[i].carBrand+" "+data.cartypes[i].cartype+'</span>';
					}
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
//车型选中事件
/* function getcarTypeInfo(obj){
	var itemId = $(obj).find("option:selected").val();
	var carBrand = $(obj).find("option:selected").attr("carBrand");
	var itemName = $(obj).find("option:selected").attr("itemName");
	var carType = $(obj).find("option:selected").attr("cartype");
	$("#itemId").val(itemId);
	$("#carBrand").val(carBrand);
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
