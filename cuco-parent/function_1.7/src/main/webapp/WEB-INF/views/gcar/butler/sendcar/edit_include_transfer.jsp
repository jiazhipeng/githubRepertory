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
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
    <!--高德地图插件end  -->
    <script src="${ctx}/static/gcar_html/js/weixin/jweixin-1.0.0.js"></script>
    <script src="${ctx}/static/gcar_html/js/weixin/wechatConfig.js"></script>
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css" rel="stylesheet" type="text/css" />
</head>
<style>
    .fixed-head,.fixed-select,.update-name {
        width: 100%;
        height: 100%;
        position: fixed;
        top: 0;
        left: 0;
        background: rgba(0,0,0,0.7);
    }
    .fixed-head img {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
    }
    .select-cen {
        width: 100%;
        position: absolute;
        bottom: 0;
        left: 0;
    }
    .select-cen li {
        background: #fff;
        border-bottom: 1px #ccc solid;
        text-align: center;
        font-size: 1.4rem;
        line-height: 40px;
        height: 40px;
    }
    .select-cen li:last-child {
        border-bottom: 0;
        margin-top: 5px;
    }
</style>
<body>
<!--图片上传 temp_1-->
<div class="charge-page charge-dia upload-img" id="upload-img">
    <div class="upload-sec" id ="upload-sec">
        <c:forEach items="${transferLists}" var="transferList">
           <div class="img-box">
               <img src="${transferList.imageUrl}" alt="" onclick="deleteFixedSelectShow(true,this);" id="img-${transferList.id}"/>
               <input type="hidden" id="img-box-${transferList.id}" value="${transferList.imageUrl}"/>
            </div>
        </c:forEach>
        <div class="img-box add_img" onclick="editHeadPortrait(true)">

        </div>
    </div>
    <div class="btns">
    	<a class="button cencle" onclick="location.href = document.referrer;">取消</a>
        <a class="button" id="uploadImg" onclick="uploadImg()">确定</a>

    </div>
</div>

<!-- 点击上传操作-->
<div class="fixed-select" style="display: none;" id="uploadFixedSelect">
    <div class="select-cen">
        <ul>
            <%--<c:if test="${not empty m.imageUrl}">--%>
                <%--<li><a href="javascript:seeBigPicture();">查看大图</a></li>--%>
            <%--</c:if>--%>
            <li><a href="javascript:photograph();">拍照</a></li>
            <li><a href="javascript:selectAlbum();">从手机选择</a></li>
            <li><a href="javascript:editHeadPortrait(false);">取消</a></li>
        </ul>
    </div>
</div>
<input  type="hidden" id="deleteID"/>
<!-- 删除照片和查看大图操作-->
<div class="fixed-select" style="display: none;" id="deleteFixedSelect">
    <div class="select-cen">
        <ul>
            <li onclick="seeBigPicture();"><a>查看大图</a></li>
            <li onclick="deletePicture();"><a>删除</a></li>
            <li onclick="deleteFixedSelectShow(false,this)"><a>取消</a></li>
        </ul>
    </div>

    <script>
        //删除图片
        function deletePicture() {
            var deleteID =  $("#deleteID").val();
            var p = $("#"+ deleteID).parent();
            console.log(p.html())
            p.detach();
            $("#deleteFixedSelect").hide();
        }

        /**
         * 显示查看大图和删除层
         * @param isShow
         * @param obj
         */
        function deleteFixedSelectShow(isShow,obj) {
            if(isShow){
                $("#showBig").attr("src",$(obj).attr("src"));
                $("#deleteID").val($(obj).attr("id"));
                $("#deleteFixedSelect").show();
            }else{
                $("#deleteFixedSelect").hide();
            }
        }
        /**
         * 查看大图
         */
        function seeBigPicture(){
            $("#deleteFixedSelect").hide();
            $("#seeBigPicture").show();
        }
        /**
         * 隐藏大图
         */
        function seeBigPictureHide(){
            $("#seeBigPicture").hide();
        }

    </script>
</div>
<!--查看大图头像-->
<div class="fixed-head" style="display: none;" onclick="seeBigPictureHide();" id="seeBigPicture">
    <img src="" id="showBig"/>
</div>
</body>


<script>

    /**
     * 上传图片
     */
    function uploadImg(){
        $("#uploadImg").attr("onclick","return false");
        var url = null;
        if('${type}' == 1){
            url = "${ctx}/transferList/uploadCarPicture";
        }else if ('${type}' == 0){
            url = "${ctx}/transferList/uploadTransferPicture";
        }

        var imgBox = $("input[id^='img-box-']");
        if(imgBox.length > 10){
            alert('最多上传10张照片');
            return;
        }
        var imageUrl = "";
        for(var i = 0 ; i<imgBox.length;i++){
            imageUrl += "," + $(imgBox[i]).val();
        }
        var data = {imageUrl:imageUrl,taskId:'${butlerTask.id}',type:'${type}'};
        $.ajax({
            type: 'POST',
            url: url,
            async:false,
            data:data,
            //  返回数据处理
            success: function(data){
                if(data.errcode == "ok"){
                    alert("上传成功！");
                    if(3=='${butlerTask.type}'){
                    	location.href = "${ctx}/wechat/butler/task/sendCar/toEditSendCarTask?taskId=${taskId}";
                    }
                    if(2=='${butlerTask.type}'){
                    	location.href = "${ctx}/wechat/butlerTask/getCar/detail?id=${taskId}&loginId=${loginId}";
                    }
                }else{
                    alert("上传失败");
                    $("#uploadImg").attr("onclick","uploadImg('"+obj+"')");
                }
            }
        });
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
                if(localIds[0]){
                    uploadSec(localIds);
                }
            }
        });
    }
    
    function  uploadSec(localIds) {
        var random = Math.floor(Math.random()*10000);
        var html = '<div class="img-box"><img src="'+localIds+'" alt="" onclick="deleteFixedSelectShow(true,this);" id="img-'+random+'"/><input type="hidden" id="img-box-'+random+'"></div>';
        $("#upload-sec").html(html + $("#upload-sec").html());
        uploadWechat(localIds,"img-box-"+random);
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
                    uploadSec(localIds);
                }
            }
        });
    }
    function closePictureDiv(){
        $("#uploadFixedSelect").hide();
    }
    //微信上传图片
    function uploadWechat(localId,imgId){
        closePictureDiv();
        wx.uploadImage({
            localId: localId[0], // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                var serverId = res.serverId; // 返回图片的服务器端ID
                $("#"+imgId).val(serverId);
            },
            error: function (res) {
                alert(JSON.stringify(res));
            }
        });
    }


    //编辑头像
    function editHeadPortrait(isShow){
        if(isShow){
            var imgBox = $("input[id^='img-box-']");
            if(imgBox.length >=10){
                alert('最多上传10张照片');
                return;
            }
            $("#uploadFixedSelect").show();
        }else{
            $("#uploadFixedSelect").hide();
        }
    }

</script>