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
<title>信息完善</title>
<link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/gcar_html/css/memberIfno.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/gcar_html/css/member.css" rel="stylesheet" type="text/css" />
</head>

<body class="greyBg">
    <div class="user-info">
        <div class="section">
            <div class="user-block">
                <p class="title">上传身份证照片</p>
                <div class="img-section">
                    <!--拍照-->
                    <div class="photo">
                        <div class="photograph idcard_front" onclick="editPicture('idcardFront')" id="idcardFrontDiv"></div>
                        <img src="${memberInfo.idcardFront}" class="card" id="idcardFrontImg" onclick="editPicture('idcardFront')"
                        <c:if test="${empty memberInfo.idcardFront}"> 
                        	style="display: none;"
                        </c:if>
                        >
                        <p>身份证正面照</p>
                    </div>
					<input id="idcardFront" type="hidden" value="" />
                </div>

                <p class="title">上传身份证照片</p>
                <div class="img-section">
                    <!--拍照-->
                    <div class="photo">
                        <div class="photograph idcard_back" onclick="editPicture('idcardBack')" id="idcardBackDiv"></div>
                         <img src="${memberInfo.idcardBack}" class="card" id="idcardBackImg" onclick="editPicture('idcardBack')"
                          <c:if test="${empty memberInfo.idcardBack}"> 
                        	style="display: none;"
                        	</c:if>
                         >
                        
                        <p>身份证反面照</p>
                    </div>
					<input id="idcardBack" type="hidden" value=""/>
                </div>

                <p class="title">驾驶证正本</p>
                <div class="img-section">
                    <!--拍照-->
                    <div class="photo">
                        <div class="photograph drive_front" onclick="editPicture('drivercardOriginal')" id="drivercardOriginalDiv"></div>
                        <img src="${memberInfo.drivercardOriginal}" class="card" id="drivercardOriginalImg" onclick="editPicture('drivercardOriginal')"
                        <c:if test="${memberInfo.drivercardOriginal == null || memberInfo.drivercardOriginal == ''}"> 
                        style="display: none;"
                        </c:if>
                        > 
                        <p>驾驶证正本</p>
                    </div>
                    <input id="drivercardOriginal" type="hidden" value=""/>
                    
                </div>

                <p class="title">驾驶证副本</p>
                <div class="img-section">
                    <!--拍照-->
                    <div class="photo">
                        <div class="photograph drive_back" onclick="editPicture('drivercardCopy')" id="drivercardCopyDiv"></div>
                        <img src="${memberInfo.drivercardCopy}" class="card" id="drivercardCopyImg" onclick="editPicture('drivercardCopy')"
                        <c:if test="${empty memberInfo.drivercardCopy}"> 
                        style="display: none;"
                        </c:if>
                       >
                        <p>驾驶证副本</p>
                    </div>
                    <input id="drivercardCopy" type="hidden" value=""/>
                </div>
            </div>
        </div>
        <a href="javascript:upload();" class="button">确定</a>
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
<script src="${ctx}/static/gcar_html/js/weixin/jweixin-1.0.0.js"></script>
<script src="${ctx}/static/gcar_html/js/weixin/wechatConfig.js"></script>
<script>
var temp = null;
var basePath = "${ctx}";

	if("${memberInfo.idcardFront}"){
		$("#idcardFrontDiv").hide();
       // $("#idcardFrontImg").attr("onclick","return false");
	}

	if("${memberInfo.idcardBack}"){
		$("#idcardBackDiv").hide();
        //$("#idcardBackImg").attr("onclick","return false");

    }

	if("${memberInfo.drivercardOriginal}"){
		$("#drivercardOriginalDiv").hide();
       // $("#drivercardOriginalImg").attr("onclick","return false");
    }

	if("${memberInfo.drivercardCopy}"){
		$("#drivercardCopyDiv").hide();
       // $("#drivercardCopyImg").attr("onclick","return false");
    }


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
					$("#"+temp+"Div").hide();
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
    			    $("#"+temp+"Div").hide();
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
			if(!"${memberInfo.idcardFront}" || !"${memberInfo.idcardBack}"){
				alert('请上传完整的身份证信息');
				return;
			}
		}
		
		if((drivercardOriginal && !drivercardCopy)
			||(!drivercardOriginal && drivercardCopy)){
			if(!"${memberInfo.drivercardOriginal}" || !"${memberInfo.drivercardCopy}"){
				alert('请上传完整的驾驶证信息');
				return;
			}
		}
		
		if(!idcardFront && !idcardBack && !drivercardOriginal && !drivercardCopy){
			alert('请选择要上传的图片');
			return;
		}
		
		var pictures = {};
        pictures["idcardFront"] =  idcardFront;
        pictures["idcardBack"] = idcardBack;
		
        pictures["drivercardOriginal"] =  drivercardOriginal;
        pictures["drivercardCopy"] = drivercardCopy;

		
		//上传到服务器
		uploadServer(pictures);
		
		
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
		pictures["memberId"] = "${memberId}";
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
					location.href = "${ctx}/wechat/member/show/memberInfo?id=${memberId}"
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