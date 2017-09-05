<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="yes" name="apple-touch-fullscreen" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/css.css">
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css">
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/date.css">
<title>单车查询</title>
 <style>
 	
 </style>
</head>

<body class="greyBg">
        
    <!--单车-->
        <div class="searc-sec">
            <div class="searchBox_black">
                <div class="searc-in-sec">
                    <a href="javascript:;" class="searchBtn"></a>
                    <input type="search" placeholder="请输入车牌号" id="search" onKeyUp="change(this.value)">
                </div>
                <a href="javascript:history.back(-1);" class="cencle-search">取消</a>
            </div>
            <ul class="search-list s-car-list" id="carPlateNum">
              
            </ul>
			<input type="hidden" id="loginId" value="${loginId}">
            <div class="btns">
                <a href="javascript:history.back(-1);" class="button cencle">取消</a>
                <a href="javscript:;" id="tip" class="button">确定</a>
            </div>
        </div>
        
        <script>
         	$('#tip').click(function(){
         		alert('请选择车牌号');
         	})
           
			var oldValue;
			oldValue = "";
            function change(newValue){
            	var loginId = $("#loginId").val();
            	if (oldValue != newValue)
			    {
			        oldValue = newValue;
			    	$("#carPlateNum").empty();
	         		 $.ajax({
			             type: "GET",
			             url: "searchCarPlateNum",
			             data:{
			            		carPlateNum:$('#search').val()
			            	  },
			             dataType: "json",
			             success: function(data){
			                var obj = eval("("+data+")"); 
		                    for(var i=0;i<obj.length;i++){
		                    	if(obj[i]["carPlateNum"] != 'undefined'){
		                    	    var carPlateNum = obj[i]["carPlateNum"];
		                    	    console.log(carPlateNum);
		                    	 	$('#carPlateNum').append("<a href='toCar?loginId="+loginId+"&carPlateNum="+carPlateNum+"'><li>"+carPlateNum+"</li></a>");
		                    	}
		                    }
						}
		 			})
			    }
            }
        
        </script>
</body>
</html>