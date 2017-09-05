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
<title>车型查询</title>
 <style>
 	  .left{
           width: 30%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
           position: absolute;
           bottom: 0px;
           left: 0px;
        }
 </style>
</head>

<body>
        
   		 <!--单车-->
                <div class="searc-in-sec">
                    <p style="padding:10 0 0 20">选择车型</p>
                    <select name="carType" id="selectCarType"  style="border:1px solid  #000; margin:10 0 0 20;width:80%;height:30px;">   
				        <c:forEach items="${carTypeList}" var="model">
		                   <option value="${model.id}"  name="carType">${model.brand}${model.carType}</option>
		               </c:forEach>
				     </select>   
                 </div>
           
			<input type="hidden" id="loginId" value="${loginId}">
            <input class="left" type="button" style="width:100%" onclick="getDate()" value="查询" >
        
        <script>
        	function getDate(){
        	    var carTypeId = $('#selectCarType option:selected').val();
        	    console.log(carTypeId);
        		window.location.href = "getDate?carTypeId="+carTypeId;
        	}
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