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
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
    <style type="text/css">
         body {
            background: white;
        }
        .down{
           display: none;
        }
        
        .right{
           position: absolute;
           left: 40;
        }
        
        .bottom{
           width: 60%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
           position: absolute;
           bottom: 0px;
           right: 0px;
        }
        
        
        .left{
           width: 30%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
           position: absolute;
           bottom: 0px;
           left: 0px;
        }
        
        .charge-page input:not([type=radio]){
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
        .fixBtnBox{
        	width:100%;
        	position:fixed;
        	left:0;
        	bottom:0;
        	text-align:center;
        	background:#fff; 
        }
        .fixBtnBox input{
        	font-size:1.5rem;
        }
        input{
        	padding:0 5px;
        }
        
        input[type="radio"] {
        	 -webkit-appearance: normal;
        }
       
    </style>
<title>平台运营</title>
</head>
<body>
       <form id="operateForm" action="platformOperation">
       <div class="charge-page" style="padding-bottom:70px;">
          <br/>
           运营方式   &nbsp; &nbsp;
            <select name="select" id="operateType">
            	<option value="请选择运营方式" name="operateType">请选择运营方式</option>
               <c:forEach items="${list}" var="model">
                   <option value="${model.id}" name="operateType">${model.name}</option>
               </c:forEach>
			</select>
			<br/><br/>
            <p>预计收益 &nbsp;<input type="tel" id="moneyId" onkeyup="value=value.replace(/[^\-?\d.]/g,'')" value="" name="expectedReturn" maxlength="9">/日</p><br/>
            <span class="info-name">
                	运营时间
            </span>
            <span class="info-val page-current" id="page-datetime-picker">
				<input type="text" placeholder="" id="datetime-picker"  placeholder="开始时间" readonly=""  value="" name="beginTime">
            </span>
            <br/><br/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
             <span class="info-val page-current" id="page-datetime-picker-end">
				<input type="text" placeholder="" disabled="true" id="datetime-picker-end" placeholder="结束时间" readonly="" value="" name="endTime">
            </span>
           <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/js/date/sm.js"></script>
        	<script>
		       $(document).on("pageInit", "#page-datetime-picker", function(e) {
		    	var oDate = new Date();
		    	var year = oDate.getFullYear();   //获取系统的年；
		    	var month = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1
		    	month=month<10?('0'+month):month;
		    	var date = oDate.getDate(); // 获取系统日，
		    	var hour = oDate.getHours()+${time}; //获取系统时，
		    	var minute = oDate.getMinutes(); //分
		    	if(minute < 10){
		    	  	minute = '0'+minute;
		    	}
		        $("#datetime-picker").datetimePicker({
		          toolbarTemplate: '<header class="bar bar-nav">\
		          <button class="button button-link pull-right close-picker">确定</button>\
		          <h1 class="title"></h1>\
		          </header>',
		           value: [year, month, date, hour, minute]
		        });
		       });
		        
		     $(document).on("pageInit", "#page-datetime-picker-end", function(e) {
				var oDate = new Date();
		    	var year = oDate.getFullYear();   //获取系统的年；
		    	var month = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1
		    	month=month<10?('0'+month):month;
		    	var date = oDate.getDate()+1; // 获取系统日，
		    	var hour = oDate.getHours()+${time}; //获取系统时，
		    	var minute = oDate.getMinutes(); //分
		    	if(minute < 10){
		    	  	minute = '0'+minute;
		    	}
		        $("#datetime-picker-end").datetimePicker({
		          toolbarTemplate: '<header class="bar bar-nav">\
		          <button class="button button-link pull-right close-picker">确定</button>\
		          <h1 class="title"></h1>\
		          </header>',
		          value: [year, month, date, hour, minute]
		        });
		      });
		
		      $.init();
		      
		    $('#datetime-picker').change(function(){
		    	$('#datetime-picker-end').removeAttr("disabled");
		    })
		    
			$("#operateType").change(function(){
             	$("#operateTypeSelect").val($('#operateType').val());
 			});
 			$(function() {
 				$('#submit').click(function submitForm() {
 					$('#submit').unbind("click");
 					var sel = $('#operateType');
 					var beginTime = $('#datetime-picker').val();
 					var endTime = $('#datetime-picker-end').val();
 					
 					var beginTimeDate = new Date(beginTime).getTime();
					var endTimeDate = new Date(endTime).getTime();
					var nowDate = new Date().getTime();
					if(nowDate > beginTimeDate){
						alert('运营开始时间必须大于当前时间');
 				 		$('#submit').bind("click",submitForm);
 				 		return;
					}
					if(nowDate > endTimeDate){
						alert('运营结束时间必须大于当前时间');
 				 		$('#submit').bind("click",submitForm);
 				 		return;
					}
					if(endTimeDate < beginTimeDate){
					    alert('运营结束时间必须大于开始时间');
 				 		$('#submit').bind("click",submitForm);
 				 		return;
					}
 					if(beginTime === endTime){
 						alert('运营开始时间和结束时间不能相等');
 				 		$('#submit').bind("click",submitForm);
 				 		return;
 					}
 				 	if(sel.find("option:selected").text() == '请选择运营方式'){
 				 		alert('请选择运营方式');
 				 		$('#submit').bind("click",submitForm);
 				 		return;
 				 	}
	 				if($('#moneyId').val() == ''){
	 					alert('请填写预计收益');
	 					$('#submit').bind("click",submitForm);
	 					return;
	 				}
	 				if($('#operateTo').val() == ''){
	 					alert('请填写用车方');
	 					$('#submit').bind("click",submitForm);
	 					return;
	 				}
	 				if($('#mobile').val() == ''){
	 					alert('请填写联系电话');
	 					$('#submit').bind("click",submitForm);
	 					return;
	 				}
	 				console.log($("input[name='stock']:checked").val());
	 				if($("input[name='stock']:checked").val() == undefined){
	 					alert('请选择是否占用库存');
	 					$('#submit').bind("click",submitForm);
	 					return;
	 				}
 				  	var stock = $("input[name='stock']:checked").val();
 				  	$("#isStock").val(stock);
	 			 	var t = $('#operateForm').serializeArray();	
	 			    $.ajax({
		             type: "GET",
		             url: "platformOperation",
		             data:t,
		             dataType: "json",
		             success: function(data){
		                var obj = eval("("+data+")"); 
	                    alert(obj["msg"]);
	                    if(obj["code"] === 200){
	                    	window.location.href = "getById?id=${carOperate.id}&type=${type}&dateStr=${dateStr}&started=${started}&loginId=${loginId}";
	                    }else{
	                    	$('#submit').bind("click",submitForm);
	                    }
					 }
	 				})
 				});	
 			});
		 </script>
		   <br/> <br/>
		   <input type="hidden" name="operateId" value="${carOperate.id}" id="operateId">
		     <input type="hidden" name="loginId" value="${loginId}" id="loginId">
		    <input type="hidden" name="operateType" value="" id="operateTypeSelect">
		   <input type="hidden" name="operateNum" value="${carOperate.operateNum}">
		   <input type="hidden" name="carPlateNum" value="${carOperate.carPlateNum}">
            <p>用车方 &nbsp;&nbsp; &nbsp;<input type="text" id="operateTo" value="" name="operateTo" maxlength="40"></p><br/>
            <p>联系电话 &nbsp;<input type="tel" id="mobile" value="" name="phone" size="11" maxlength="11"></p><br/>
            <p>用车备注 &nbsp;<textarea rows="3" cols="20" id="remark" name="remark"></textarea></p>
            <p>占用库存 &nbsp;&nbsp; <input name="stock" id="isStock" type="radio" value="1">是
          	<span style="visibility:hidden;">占用库存 &nbsp;&nbsp; </span><input name="stock" id="noStock" type="radio" value="2" >否</p>
            
            <c:if test="${code == 500}">
		   		<h1 style="color:red">${msg}</h1>
		    </c:if>
		    <input type="hidden" id="code" value="${code}">
		    <input type="hidden" id="dateStr" value="${dateStr}">
		    <input type="hidden" id="started" value="${started}">
		    
    </div>
    	<div class="fixBtnBox">  
       		<input class="left" type="button" onclick="javascript:history.back(-1);" value="返回" >
       		<input class="bottom" type="button" id="submit" value="确定运营" >
    	</div>
    	</form>
</body>
</html>