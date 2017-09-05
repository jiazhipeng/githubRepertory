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
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <!--时间插件  -->
    <link href="${ctx}/static/gcar_html/css/gjd/date/demos.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel=s"stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
    <style type="text/css">
         body {
            background: white;
            overflow:scroll;
        }
        .down{
           display: none;
        }
        
        .right{
           position: absolute;
           left: 40;
        }
        
        .bottom{
           width: 55%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
        }
        
        .left{
           width: 30%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
        }
       
        .charge-page select{
        	border:1px solid #002a86;
            height: 25;
            width: 70%;
        }
        
        .charge-page input{
            border:1px solid #002a86;
            height: 25;
            width: 70%;
        }
        .charge-page textarea{
            border:1px solid #002a86;
            width: 70%;
        }
        *{padding: 0; margin: 0}
        .box{
            position: fixed;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.2);
            display: none;
        }
        .box1{
            width: 500px;
            height: 500px;
            position: fixed;left: 50%; top: 25%;
            margin-left: -250px;
            border: 1px solid #000000;
        }
        
      	.charge-page {
            font-size: 1.5rem;
        }
        
        .charge-page-car .charge-t {
            width: 100%;
            height: 45px;
            line-height: 45px;
            background: #1e1e1e;
            color: #fff;
            padding: 0 2%;
            display:flex;
            position: fixed;
		    z-index: 10;
		    left: 0;
		    top: 0;
        }
        
        .charge-page-car .charge-new {
            width: 100%;
            background: white;
            color: black;
            padding: 0 2%;
            padding-bottom: 50px;
    		padding-top: 45px;
        }
        
        .charge-new li {
            float: left;
            width: 26%;
            box-sizing: border-box;
            position: relative;
            line-height:20px;
            flex:1;
            text-align:center;
            
        }
        .charge-new li p {
            margin:0;
        } 
        
        .charge-t li {
            float: left;
            width: 23%;
            box-sizing: border-box;
            position: relative;
            flex:1;
            text-align:center;
        }
        .btns {
        	position: fixed;
		    bottom: 0;
		    /* overflow: hidden; */
		    left: 0;
		    width: 100%;
		    display: flex;
		    border-top: 1px #ccc solid;
		    /* padding-top: 50px; */
        }
        .btns button {
        	display: inline-block;
    		flex: 1;
        }
         .btns left {
        	display: inline-block;
    		flex: 1;
        }
        
        .fixBtnBox{
        	width:100%;
        	position:fixed;
        	left:0;
        	bottom:0; 
        	background:#fff;       	
        }
        .fixBtnBox input{
        	font-size:1.5rem;
        }
        .fixBtnBox .left{float:left}
        .fixBtnBox .bottom{float:right}
        input{
        	padding:0 5px;
        }
       
       
    </style>
<title>发起维修</title>
</head>
<body>

      <script>
         if(${code == 500}){
	    	  alert("${msg}");	
	    	  window.location.href= "javascript:history.back(-1);"; 
   		 }
      		function getByType(){
      			$('#car').css('display','block');
      			$('#main').css('display','none');
      		}
      		
      		function getByTypeHide(){
      			$('#car').css('display','none');
      			$('#main').css('display','block');
      		}
      		
      		function getByTypeUrl(newCarPlateNum,status,oldCarPlateNum,carOperatePlanId,loginId,powerUsedId,started,isLimitLine){
      			if(isLimitLine){
      				alert('此车辆限行,请选择其它车辆');
      				return;
      			}
      			window.location.href = "getByType?newCarPlateNum="+newCarPlateNum+"&status="+status+"&oldCarPlateNum="+oldCarPlateNum+"&carOperatePlanId="+carOperatePlanId+"&loginId="+loginId+"&powerUsedId="+powerUsedId+"&started="+started;
      		}
      		
   			function ajaxCar(){
      			 var t = $('#operateForm').serializeArray();	
      			 $.ajax({
		             type: "GET",
		             url: "replace",
		             data:t,
		             dataType: "json",
		             success: function(data){
		                var obj = eval("("+data+")"); 
	                    alert(obj["msg"]);
	                    if(obj["code"] === 200){
	                    	window.location.href='toCar?carPlateNum=${carPlateNum}&loginId=${loginId}';
	                    }
					 }
	 				});
      		}
      		$(function() {
      			$('#submit').click(function submitForm(){
      				$('#submit').unbind("click");
 					var sel = $('#operateType');
 				 	if($('#remark').val() == ''){
 				 		alert('请填写维修原因');
 				 		$('#submit').bind("click",submitForm);
 				 		return;
 				 	}
 				 	console.log('111');
	 			   	ajaxCar();
      			})
      		})
     
      	
      </script>
		
       <form id="operateForm" action="platformOperation">
       <div class="charge-page" id = "main">
          <br/>
          	 <input type="hidden" name="loginId" value="${loginId}" id="loginId">
		   <input type="hidden" name="carId" value="${carId}" id="carId">
            <p>维修原因 &nbsp;<textarea rows="3" cols="20" id="remark" name="remark" maxlength="250"></textarea></p>
         
    </div>
     <div class="fixBtnBox">  
       		<input class="left" type="button" onclick="javascript:history.back(-1);" value="返回" >&nbsp;&nbsp;
       		<input class="bottom" type="button" id="submit" value="发起维修" >
    	</div>

    	</form>
</body>
</html>