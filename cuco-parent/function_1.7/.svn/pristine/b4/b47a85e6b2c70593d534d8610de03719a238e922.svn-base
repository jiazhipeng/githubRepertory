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
<title>会员信息</title>
<link rel="stylesheet" href="${ctx}/static/gcar_html/sui_style/sm.css">
<link href="${ctx}/static/gcar_html/css/user.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/gcar_html/css/member.css" rel="stylesheet" type="text/css" />
</head>
<body class="greyBg user-info">
    <!--头像昵称-->
    <div class="userInfo" onclick="editHeadPortrait(true);">
		<div style="position: relative;margin: 0 auto; width: 94%;">
			<!--<div class="icon">-->
				<span class="name-right info-head" style="margin-top: 14px;">
				<c:if test="${m.sexInfo != '女'  && empty m.imageUrl}"><img src="${ctx}/static/gcar_html/images/head-portrait-nan.png" id="imageUrlHead" style="width: 70px;height: 70px"/></c:if>
				<c:if test="${m.sexInfo == '女' && empty m.imageUrl}"><img src="${ctx}/static/gcar_html/images/head-portrait-nv.png" id="imageUrlHead" style="width: 70px;height: 70px"/></c:if>
				<c:if test="${not empty  m.imageUrl}"> <img src="${ m.imageUrl}" id="imageUrlHead" style="width: 70px;height: 70px"/></c:if>
				</span>
			<i class="icons" style="top: 44px"></i>

			<span class="name" style="color: #333">头像</span>
		</div>
    </div>


    <div class="section-info ">
        <div class="section">
        <ul class="aditBox">
            <li onclick="editName(true);">
                <span class="name">姓名</span>
                <input type="text" placeholder="${m.name}" id="memberName" readonly="readonly">
                <i class="icons"></i>
            </li>
            <li>
                <span class="name">手机号</span>
                <input type="tel" maxlength="11" placeholder="${m.mobile}" readonly="readonly">
            </li>
            <li onclick="editSex(true);">
                <span class="name">性别</span>
                <span class="name-right" id="page-sex-picker" style="color: #999">${m.sexInfo}</span>
                <i class="icons"></i>
            </li>
            <li>
                <span class="city name">城市</span>
<%--                 <span class="name-right">${m.cityName}</span> --%>
<!-- 				<div class="row clearfix page page-current" id="page-city-picker-company" style="position:relative; top:auto; bottom:auto; background:#fff;"> -->
<%-- 				  	<input type="text" placeholder="${m.cityName == null?'':m.cityName}" id="city-picker-company" readonly=""> --%>
<!-- 		        </div> -->
		            <div class="row clearfix page page-current" id="page-city-picker"  style="position:relative; top:auto; bottom:auto; background:#fff;">
		            	<input type="text" placeholder="${m.cityName == null?'':m.cityName}" id="city-picker"  class="cityPicker" readonly=""/>
		    			<input name="cityId" id="cityId" type="hidden" value="${m.cityId==null?'2':m.cityId}"/>
		    			<input name="cityCode" id="cityCode" type="hidden" value="${m.cityCode == null?'110000':m.cityCode}"/>
		    			<input name="cityName" id="cityName" type="hidden" value="${m.cityName == null?'北京市':me.cityName}"/>
					</div> 
		            	<script>
			            	var rawCitiesData = null;
			            	$.ajax({
				   			     type: 'GET',
				   			  	 async:false,
				   			     url: '${ctx}/wechat/order/getNation',
				   			     dataType: "json",
				   			     success: function(data){
				   			    	 console.log(JSON.stringify(data));
				   			    	rawCitiesData = data;
				   			    } 
			   				});
			            	  var province = "北京市";           
			            	  var city = "";
			            	  if('${m.cityName}'!=null && '${m.cityName}'!=''){
			            		  city = '${m.cityName}';
			            	  }
			            	  
		            		  if('${m.cityName}' == null || '${m.cityName}' == ''){
		            			  city = "";
		                		  province = "";
		                		  $("#cityId").val("");
		                		  $("#city").val("");
		                		  $("#cityName").val("");
		            		  }
		            	  
		            	</script>  
            	<i class="icons"></i>
            </li>
            <li>
                <span class="name">完善信息</span>
                <span class="name-right per" onclick="location.href='${ctx}/wechat/memberInfo/editMemberInfo?memberId=${m.id}'">${isPerfect}</span>
                <i class="icons"></i>

            </li>
        </ul>
    </div>
    </div>

	
    <div class="update-name" id = "update-name" style="display: none;">
        <div class="update-con">
            <div class="popup-head"><h3>修改姓名</h3><span class="close" onclick="editName(false);">&times;</span></div>
            <div class="popup-body">
               	姓名: 
               	<input type="text" class="ipn-text" id="updateName" onkeyup="value=value.replace(/[^\w\s\u4E00-\u9FA5]/g, '')"  maxlength="80" />
            </div>
            <div class="popup-body" id="checkAlert">
            </div>
            <div class="popup-foot">
                <a href="javascript:updateName()">确认</a>
            </div>
        </div>
    </div>
    
   <div class="update-name" id = "update-sex"style="display: none;">
        <div class="update-con">
            <div class="popup-head"><h3>修改性别</h3><span class="close" onclick="editSex(false);">&times;</span></div>
            <div class="popup-body">
               	性别:
               	<select id="updateSex">
               	    <option value="0">女</option>
               		<option value="1">男</option>
               	</select>
            </div>
            <div class="popup-body" id="checkAlertSex">
            </div>
            <div class="popup-foot">
                <a href="javascript:updateSex()">确认</a>
            </div>
        </div>
    </div>

    <!-- 点击头像操作-->
    <div class="fixed-select" style="display: none;">
        <div class="select-cen">
            <ul>
            	<c:if test="${not empty m.imageUrl}">
            		<li><a href="javascript:seeBigPicture();">查看大图</a></li>
            	</c:if>
                <li><a href="javascript:photograph();">拍照</a></li>
                <li><a href="javascript:selectAlbum();">从手机选择</a></li>
                <li><a href="javascript:editHeadPortrait(false);">取消</a></li>
            </ul>
        </div>
    </div>
 	
 	
    <!--查看大图头像-->

    <div class="fixed-head" style="display: none;" onclick="headHide();">
        <img src="" id="showBig"/>
    </div>
    
    <!--  选择城市   -->
    
    
    
<%-- <%@ include file="/WEB-INF/common/loading.jsp"%> --%>
</body>
<script src="${ctx}/static/gcar_html/sui_style/sm.js?v=2.0"></script>
<script src="${ctx}/static/gcar_html/sui_style/sm-city-picker.js"></script>
<script src="${ctx}/static/gcar_html/sui_style/demos.js"></script>
<script src="${ctx}/static/gcar_html/js/weixin/jweixin-1.0.0.js"></script>
<script src="${ctx}/static/gcar_html/js/weixin/wechatConfig.js"></script>
<script>
var basePath = "${ctx}";
//编辑性别
function editSex(isShow){
	if(isShow){
		$("#update-sex").show();
	}else{
		$("#update-sex").hide();
	}
	
}

function headHide(){
	$(".fixed-head").hide();
	
}
function cityPicker(){
	$("#city-picker").cityPicker({
		//value: ['四川', '内江', '东兴区']
	});
}


$(document).on("click", ".cancel-picker", function() {
	var cityName = $("#city-picker").attr("placeholder");
	$(".cityPicker").remove();
	console.log($("#page-city-picker").html());
	$("#page-city-picker").append('<input type="text" placeholder=" '+ cityName + '" id="city-picker"  class="cityPicker" readonly="" onclick = "cityPicker();"/>');
	console.log($("#page-city-picker").html());
	var pickerToClose = $('.picker-modal.modal-in');
	$.closeModal(pickerToClose);
	cityPicker();
});

$(document).on("click", ".close-picker", function() {
	var cityId = $("#cityId").val();
	var cityCode = $("#cityCode").val();
	var cityName = $("#cityName").val();
	
	$.ajax({
		type: 'POST',
		url: "${ctx}/wechat/member/updatePersonalMember",
		async:false,
		data:{
			"name":name,
			"id":"${m.id}",
			"cityId":cityId,
			"cityCode":cityCode,
			"cityName":cityName
			},
		//  返回数据处理
		success: function(data){
			if(data.errcode == "ok"){
				//$("#memberName").attr("placeholder",name);
				$("#city-picker").attr("placeholder",data.result.cityName);
			}

		},error:function(data){
		}
	});
	
	
    var pickerToClose = $('.picker-modal.modal-in');
    $.closeModal(pickerToClose);
});

function trim(str){ //删除左右两端的空格
	return str.replace(/(^s*)|(s*$)/g, "");
}

function updateSex(){
	var sex = $("#updateSex").val();
	var sexInflo = $("#updateSex").find("option:selected").text()
	$.ajax({
		type: 'POST',
		url: "${ctx}/wechat/member/updatePersonalMember",
		async:false,
		data:{"sex":sex,"id":"${m.id}"},
		//  返回数据处理
		success: function(data){
			if(data.errcode == "ok"){
				$("#page-sex-picker").html(sexInflo);
			}else{
				$("#checkAlertSex").html("修改失败");
			}
			editSex(false);
		},error:function(data){
			editSex(false);
		}
	});
	
}

var memberName = "${m.name}";
function updateName(){
	//编辑用户姓名
	var name = $.trim($("#updateName").val());
	$("#checkAlert").html("");
	if(!name){
		$("#checkAlert").html("请输入姓名");
		return;
	}
	var reg = /^[\u4e00-\u9fa5a-zA-Z ]+$/;
	if(!reg.test(name)){
		$("#checkAlert").html("姓名仅支持中文、英文大小写、空格");
		return;
	}


	$.ajax({
		type: 'POST',
		url: "${ctx}/wechat/member/updatePersonalMember",
		async:false,
		data:{"name":name,"id":"${m.id}"},
		//  返回数据处理
		success: function(data){
			if(data.errcode == "ok"){
				memberName = name;
				if(name.length > 5){
					name = name.substring(0,4) + "...";
				}
				$("#memberName").attr("placeholder",name);
			}else{
				$("#checkAlert").html("修改失败");
			}
			editName(false);
		},error:function(data){
			editName(false);
		}
	});
	
}

	//编辑
	function editName(isShow){
		
		$("#updateName").val(memberName);
		if(isShow){
			$("#update-name").show();
		}else{
			$("#update-name").hide();
		}
	}	
	//编辑头像
	function editHeadPortrait(isShow){
		if(isShow){
			$(".fixed-select").show();
		}else{
			$(".fixed-select").hide();
		}
	}
	
	//查看大图
	function seeBigPicture(){
		$(".fixed-select").hide();
		var imageUrlHead = $("#imageUrlHead").attr("src");
		$("#showBig").attr("src",imageUrlHead);
		$(".fixed-head").show();
		
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
    			//alert(localIds);
    			uploadWechat(localIds);
    			
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
    			uploadWechat(localIds);
			}
		});
	}
	//微信上传图片
	function uploadWechat(localId){
		//alert("微信上传" + localId);
		$(".fixed-select").hide();
		//$(".loading").show();
		wx.uploadImage({
			localId: localId[0], // 需要上传的图片的本地ID，由chooseImage接口获得
		    //isShowProgressTips: 1, // 默认为1，显示进度提示
		    success: function (res) {
		        var serverId = res.serverId; // 返回图片的服务器端ID
		        uploadHead(serverId);
		    },
		    fail: function (res) {  
	            alert(JSON.stringify(res));  
	        }  
		});	
	}
	
	//从微信服务器下载图片到服务器
	function uploadHead(serverId){
		$.ajax({
			type: 'POST',
			url: "${ctx}/wechat/member/uploadHead",
			async:false,
			data:{"mediaId":serverId,"id":"${m.id}"},
			//  返回数据处理
			success: function(data){
	            //alert(JSON.stringify(data));  
				if(data.errcode == "ok"){
					alert('上传成功');
					//$(".loading").hide();
					$("#imageUrlHead").attr("src",data.result);
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
</html>