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
        }
        
        .bottom{
           width: 100%;
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
            height: 76px;
            background: #1e1e1e;
            color: #fff;
            padding: 0 2%;
            position: fixed;
		    z-index: 10;
		    left: 0;
		    top: 0;
        }
        .charge-t h3 {
        	    margin: 10px 0px 10px 37px;
        	    font-size:18px;
        }
        .charge-t ul {
        	 display:flex;
        }
        
        .charge-page-car .charge-new {
            width: 100%;
            background: white;
            color: black;
            padding: 0 2%;
            padding-bottom: 50px;
    		padding-top: 76px;
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
        
        .charge-t .up span,
       
       .time{
       	word-wrap:break-word;
       }
       
    </style>
   
<title>车辆管理</title>
</head>
<body>
       <div class="charge-page-car">
     		<div class="charge-t"> 
     			<h3>${dateStr }</h3>
		        <ul class="">
		        	<li>
		               	 车辆信息
		            </li>
		            <li class = "right">
		             	   车牌号
		            </li>
		            <c:if test="${type == 1 }">
		            	<li class = "right">入库时间</li>
		            </c:if>
		            <c:if test="${type == 2 }">
		            	<li class = "right">会员姓名</li>
		            </c:if>
		            <c:if test="${type == 3 }">
		            	<li class = "right">管家姓名</li>
		            </c:if>
		            <c:if test="${type == 6 }">
		            	<li class = "right">维修时间</li>
		            </c:if>
		        </ul>
        </div>
      
        <ul class="charge-new" id="charging_list">
            <c:forEach items="${list}" var="model">
			    <c:if test="${model.started != null}">
			    	 <a href="${ctx}/wechat/carOperate/getById?id=${model.id}&type=${type}&dateStr=${dateStr}&started=${model.startedStr}&loginId=${loginId}">
			    </c:if>
			     <c:if test="${model.started == null}">
			    	 <a href="${ctx}/wechat/carOperate/getById?id=${model.id}&type=${type}&dateStr=${dateStr}&started=0&loginId=${loginId}">
			    </c:if>
			        <div style="overflow:hidden;padding:10px 0;display:flex; border-bottom:1px #ccc solid;">
		         <li>
		       		<p>${model.carBrand}</p>
	        		<p>${model.carType}</p>
		          </li>  
		           <li> ${model.carPlateNum}</li>
		           <li>
		            <c:if test="${type == 1}">
			            <c:if test="${model.status == 7}">
			               整备中
			            </c:if>
			            <c:if test="${model.status != 7}">
			                 <fmt:formatDate value="${model.created}" pattern="MM-dd"/>
			            </c:if>
		            </c:if>
		            <c:if test="${type == 2 }">
		            	${model.memberName }
		            </c:if>
		            <c:if test="${type == 3 }">
		            	${model.butlerName }
		            </c:if>
		             <c:if test="${type == 6 }">
		            	<fmt:formatDate value="${model.failureTime}" pattern="MM-dd"/>
		            </c:if>
			    </li>
			    </div>
			 </c:forEach>
			 </ul>
       </div> 
        </a>
        <c:if test="${size == 0}">
       		 <c:if test="${type == 1}">
       		 	<center><h3>今日无待分配车辆</h3></center>
       		 </c:if>
       		  <c:if test="${type == 2}">
       		 	<center><h3>今日无会员预约使用车辆</h3></center>
       		 </c:if>
       		  <c:if test="${type == 3}">
       		 	<center><h3>今日无平台预约使用车辆</h3></center>
       		 </c:if>
       		  <c:if test="${type == 6}">
       		 	<center><h3>今日无维修车辆</h3></center>
       		 </c:if>
        </c:if>
       
   	<div class="btns">  
       <input type="button" class="bottom" onclick="javascript:history.back(-1);" value="返回">
    </div>
</body>
</html>