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
     <!--时间插件  -->
    <link href="${ctx}/static/gcar_html/css/gjd/date/demos.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/4v.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
    <style type="text/css">
         .charge-t li {
            float: left;
            width: 30%;
            box-sizing: border-box;
            position: relative;
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
        .textarea{
           	border:1px solid #002a86;
            width: 80%;
            position: absolute;
            left : 60px;
        }
        .input{
        	border:1px solid #002a86;
            height: 25;
            width: 70%;
        }
        
        .charge-page span{
        	color:A8A8A8;
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
       
    </style>
<title>车辆管理</title>
</head>
<body>
       <div class="charge-page" id="main">
            <h3><P>${car.carPlateNum }</P></h3>
            <hr>
            <P>
               	品牌      &nbsp; <span>${car.carBrand }</span>
            </P>
            <P>
             	车型     &nbsp; <span>${car.carType }</span>
            </P>
           <P>
           		车架号     &nbsp; <span>${car.carVin }</span>
           </P>
           <hr>
           
        <c:if test="${type ==1}">
           		<p>预约记录</p><br/>
        <ul class="charge-t" id="charging_list">
       		<li>时间</li>
		    <li class = "right">预约状态</li>
		    <li class = "right">预约人</li><br/>
		     <hr>
            <c:forEach items="${list}" var="model">
            	<c:if test="${model.started == null}">
            		<li><center>-</center></li>
            	</c:if>
            	<c:if test="${model.started != null}">
            		<li><fmt:formatDate value="${model.started}" pattern="yyyy-MM-dd HH:mm"/></li>	
            	</c:if>
			    <li class = "right"> <c:if test="${model.memberId != 0}">会员预约</c:if></li>
			    <li class = "right"> <c:if test="${model.memberId == 0}">平台预约</c:if></li>
			    <c:if test="${model.memberId == 0}"><li class = "right">${model.butlerName }</li><br/><br/></c:if></li>
			     <c:if test="${model.memberId != 0}"><li class = "right">${model.memberName }</li><br/><br/></c:if></li>
			</c:forEach>
			</br></br></br>
        </ul>
        <div class="fixBtnBox">  
       		<input class="left" type="button" onclick="javascript:history.back(-1);" value="返回" >
       		<!--<input class="bottom" type="button" onclick="showButton()" value="更多操作" >-->
       		
       		 <div class="button_more">
             <ul id="buttonShow" class="more_ul show" style="display:none">
                <li><a href="toAppointment?carId=${car.id}&type=${type}&dateStr=${dateStr}&started=${started}&carPlateNum=${car.carPlateNum }&loginId=${loginId}" class="more_check">签约预约</a></li>
                <li><a href="toReplace?carId=${car.id}&carPlateNum=${car.carPlateNum }&loginId=${loginId}" class="more_check">发起维修</a></li>
                <li><a href="getOperate?id=${car.id}&type=${type}&dateStr=${dateStr}&started=${started}&loginId=${loginId}" class="more_check">平台运营</a></li>
            </ul>
         <a onclick="showButton()" class="more"><div class="more_h2">更多操作</div></a>
           
        </div>
       		<!--<input class="bottom" type="button" onclick="window.location.href='getOperate?id=${car.id}&type=${type}&dateStr=${dateStr}&started=${started}&loginId=${loginId}'" value="平台运营" >-->
    	</div>
       </c:if> 
       
        <c:if test="${type ==2}">
           	<p>预约方式 	&nbsp;  <span>会员预约</span></p>
           	<p>会员姓名 	&nbsp; <span> ${carOperatePlan.memberName}</span></p>
           	<p>联系电话 	&nbsp; <span><a href="tel:${carOperatePlan.memberMobile}" class="tel">  ${carOperatePlan.memberMobile}  </a></span></p> 
           	<p>用车时间 	&nbsp;  <span><fmt:formatDate value="${carOperatePlan.started}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
           	<p>用车地点 	&nbsp;  <span>${carOperatePlan.carUsedAddress}</span></p>
           	<p>负责管家 	&nbsp;  <span>${carOperatePlan.butlerName}</span></p>
            <div>  
       			<input class="left" type="button" onclick="toCarList()" value="返回" >
       			<input class="bottom" type="button" onclick="window.location.href='../butler/task/sendCar/toEditSendCarTask?taskId=${butlerTaskId}';" value="查看任务">
    		</div>
        </c:if>
        
        <!--单车故障换车 -->
        <c:if test="${type ==4}">
        	<c:if test = "${carOperatePlan.memberId == 0}">
        		<p>平台使用</p>
        		<p>用车方 	&nbsp;  <span>${carOperatePlan.operateTo}</span></p>
        	</c:if>
           	<c:if test = "${carOperatePlan.memberId != 0}">
        		<p>会员使用</p>
        		<p>会员姓名 	&nbsp;  <span>${carOperatePlan.memberName}</span></p>
        	</c:if>
           	<p>联系电话 	&nbsp;  <span><a href="tel:${carOperatePlan.memberMobile}" class="tel"> ${carOperatePlan.memberMobile} </a></span></p>
           	<p>开始时间 	&nbsp;  <span><fmt:formatDate value="${carOperatePlan.started}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
            <p>结束时间 	&nbsp;  <span><fmt:formatDate value="${carOperatePlan.ended}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
            <p>负责管家 	&nbsp;  <span>${carOperatePlan.butlerName}</span></p>
            <div>  
            	<c:if test="${!fix}">
            		<input class="left" type="button" style="width:100%" onclick="javascript:history.back(-1);" value="返回" >
            	</c:if>
       			<c:if test="${fix}">
       				<input class="left" type="button"  onclick="javascript:history.back(-1);" value="返回" >
       				<input class="bottom" type="button" onclick="window.location.href='toFailureChange?oldCarPlateNum=${car.carPlateNum }&carOperatePlanId=${carOperatePlan.id}&status=${carOperatePlan.status}&carTypeId=${car.carTypeId}&memberId=${carOperatePlan.memberId}&loginId=${loginId}&powerUsedId=${carOperatePlan.powerUsedId}&started=${carOperatePlan.started}'" value="故障换车">
       			</c:if>
    		</div>
        </c:if>
        
        <c:if test="${type ==3}">
        	<p>预约方式 	&nbsp;  <span>平台预约</span></p>
           	<p>开始时间 	&nbsp; <span> <fmt:formatDate value="${carOperatePlan.started}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
           	<p>结束时间 	&nbsp;  <span><fmt:formatDate value="${carOperatePlan.ended}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
           	<p>预计收益	&nbsp;  <span>${carOperatePlan.expectedReturn}元/日</span></p>
           	<p>用车方 	&nbsp;  <span>${carOperatePlan.operateTo}</span></p>
           	<p>联系电话	&nbsp;  <span><a href="tel:${carOperatePlan.memberMobile}" class="tel">  ${carOperatePlan.memberMobile}  </a></span></p> 
           	<p>用车备注	&nbsp;  <span>${carOperatePlan.remark}</span></p>
           	<p>负责管家 	&nbsp;  <span>${carOperatePlan.butlerName}</span></p>
            <div>  
            	<c:if test="${!flag}">
            	   <input class="left" type="button" style="width:100%" onclick="javascript:history.back(-1);" value="返回">
            	</c:if>
       			<c:if test="${flag}">
       			    <input class="left" type="button"  onclick="javascript:history.back(-1);" value="返回" >
       			    <input class="bottom" type="button" onclick="show()" value="取消平台运营" >
       			</c:if>
    		</div>
        </c:if>
        
         <c:if test="${type ==8}">
        	<p>失联任务</p>
        	<p>会员姓名 	&nbsp;  <span>${carOperatePlan.memberName}</span></p>
        	<p>会员电话 	&nbsp;  <span><a href="tel:${carOperatePlan.memberMobile}" class="tel"> ${carOperatePlan.memberMobile} </a></span></p>
           	<p>预计用车时间 	&nbsp; <span> <fmt:formatDate value="${carOperatePlan.started}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
           	<p>预计用车地点 	&nbsp;  <span>${carOperatePlan.carUsedAddress}</span></p>
            <p>预计还车时间  	&nbsp;  <span><fmt:formatDate value="${carOperatePlan.ended}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
           	<p>预计还车地点 	&nbsp;  <span>${carOperatePlan.carUsedAddress}</span></p>
            <div>  
            	<c:if test="${!flag}">
            	   <input class="left" type="button" style="width:100%" onclick="javascript:history.back(-1);" value="返回">
            	</c:if>
       			<c:if test="${flag}">
       			    <input class="left" type="button"  onclick="javascript:history.back(-1);" value="返回" >
       			    <input class="bottom" type="button" onclick="comeHere()" value="失联找回" >
       			</c:if>
    		</div>
        </c:if>
        
        <input type="hidden" id="status" value="${carOperatePlan.status}" >
        
         <c:if test="${type ==6}">
            <c:if test="${carOperatePlan != null}">
	        	<p>故障时间 	&nbsp; <span> <fmt:formatDate value="${carOperatePlan.failureTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
	           	<p>换车备注	&nbsp; <span> ${carOperatePlan.remark}</span></p>
	           	<p>负责管家 	&nbsp;  <span>${carOperatePlan.butlerName}</span></p>
           </c:if>
            <div>  
       				<input class="left" type="button" onclick="javascript:history.back(-1);" value="返回" >
       				<input class="bottom" type="button" onclick="repairFinishShow()" value="修理完成" >
    		</div>
        </c:if>
        
         <c:if test="${type ==7}">
        	<p>车辆整备	&nbsp; </p>
        	<c:if test="${carOperatePlan.ended != null}">
        	     <p>整备开始	&nbsp;  <span><fmt:formatDate value="${carOperatePlan.ended}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
        	</c:if>
        	<c:if test="${carOperatePlan.ended == null}">
        	     <p>整备开始	&nbsp;  <span><fmt:formatDate value="${car.readyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
        	</c:if>
        		<input type="hidden" id="carId" value="${car.id}">
            <div>  
	       			<input class="left" type="button" onclick="javascript:history.back(-1);" value="返回" >
	       			<input class="bottom" type="button" onclick="readySuccess()" value="整备完成" >
    		</div>
        </c:if>
        
         <c:if test="${type ==9}">
        	<p>签约预约	&nbsp; </p>
        	<c:if test="${carOperatePlan.started != null}">
        	     <p>预计送车时间	&nbsp;  <span id="start"><fmt:formatDate value="${carOperatePlan.started}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
        	</c:if>
        	<c:if test="${carOperatePlan.carUsedAddress != ''}">
        	     <p>预计送车地点	&nbsp;  <span>${carOperatePlan.carUsedAddress}</p>
        	</c:if>
        		<input type="hidden" id="carId" value="${car.id}">
            <div>  
            	<c:if test="${!flag}">
            	   <input class="left" type="button" style="width:100%" onclick="javascript:history.back(-1);" value="返回">
            	</c:if>
            	<c:if test="${flag}">
            		<input class="left" type="button" onclick="javascript:history.back(-1);" value="返回" >
	       			<input class="bottom" type="button" onclick="window.location.href='toStartAppointment?carId=${car.id}&type=${type}&dateStr=${dateStr}&carPlateNum=${car.carPlateNum }&started=${started}&address=${carOperatePlan.carUsedAddress}&sendCarTime=${carOperatePlan.begin}&loginId=${loginId}'" value="处理签约" >
            	</c:if>
    		</div>
        </c:if>
        
    </div>
    
    <!--取消原因影藏域 -->
    <div id="cacle" style="display:none">
     	<br/> 取消原因<textarea class="textarea" rows="3" cols="20" id="remark" name="remark" maxlength="250"></textarea>
     	 <div>  
       		<input class="left" type="button" onclick="noShow()" value="取消" >
       		<input class="bottom" type="button" onclick="cancle()" value="确认取消" >
    	</div>
    	<script>
    		function showButton(){
    			var display = $('#buttonShow')[0].getAttribute('style');
    			if(display.indexOf("none") >0){
    				$('#buttonShow').css('display','block');
    			}else{
    				$('#buttonShow').css('display','none');
    			}
    			
    		} 
    		
    	  	function cancle(){
    	  	 console.log('1111');
    	  	 if($('#remark').val() === ''){
    	  	 	alert("请填写停止原因！");
    	  	 	return;
    	  	 }
    	  	 var status = $("#status").val();
	    	  	 if(1 != status){//待执行的运营计划不需要这条提示
	    	  		if(confirm("当日是否有运营收益?单击'确定'有收益,单击'取消'无收益")){
	       	  	 	 var type = 1;
	       	  	 }else{
	       	  	 	 var type = 2;
	       	  	 }
    	  	 }else{
    	  		//待执行的运营计划不需要插入日志
    	  		var type = 2;
    	  	 }
    	  	 
    		 var reason = $('#remark').val();
    		 var operateNum = "${carOperatePlan.operateNum}";
    		 var carOperateId = "${carOperatePlan.operateId}"; 
    		 var carOperatePlanId = "${carOperatePlan.id}";
    		 $.ajax({
		             type: "GET",
		             url: "cancelOperate",
		             data:{
		             		reason:reason,
		            		type:type,
		            		operateNum:operateNum,
		            		carOperateId:carOperateId,
		            		carOperatePlanId:carOperatePlanId
		            	  },
		             dataType: "json",
		             success: function(data){
		                var obj = eval("("+data+")"); 
	                    alert(obj["msg"]);
	                    toCarList();
					}
	 		})
    	}
    	function toCarList(){
    		 window.location.href="${ctx}/wechat/carOperate/toCarList?loginId=${loginId}";
    	}
    	</script>
    </div>
    
   <!--修理完成影藏域 -->
    <div  id="repairFinish" style="display:none">
   			 <br/><br/>
   			维修厂商 &nbsp;
   			 <input type="text" id="repairManufacturer"  placeholder="维修厂商"  style="border:1px solid #003399; padding:5px;"   name="repairManufacturer" maxlength="40"><br/><br/>
            维修费用&nbsp;
            <input type="number" onchange="isMoney(value)"  placeholder="维修费用" style="border:1px solid #003399; padding:5px;"  id="maintenanceCosts" name="maintenanceCosts" maxlength="9"  value=""><br/><br/><br/>
            <div id="handleTypeUse" style="display:none">
            处理方式
            	&nbsp; <input name="handle" id="use" type="radio" value="1">用户继续使用<br/>
          	   &nbsp; <label style="color:white">处理方式</label><label> <input name="handle" id="handle" type="radio" value="2">车辆入库</label>
             </div>
             
             <div id="handleType" style="display:none">
          	   	处理方式
          	   &nbsp; <input name="handle" id="handle" type="radio" value="2">车辆入库</label>
            </div>
           <span style="display:none" id="ended">
                <fmt:formatDate value="${carOperatePlan.ended}" pattern="yyyy-MM-dd HH:mm"/>	
           </span>
             <div>  
	       		<input class="left" type="button" onclick="repairFinishNoShow()" value="返回" >
	       		<input class="bottom" type="button" onclick="repairFinish()" value="确认" >
    		</div>
           <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/js/date/sm.js"></script>
        	<script>
		    	console.log(${carOperatePlan == null});
		    	 if(${carOperatePlan == null}){
		    	 	$('#handleType').css('display','block');
		    		$('#handleTypeUse').css('display','none');
		    	 }else if(${carOperatePlan.status == 2}){
		    	 	$('#handleType').css('display','none');
		    		$('#handleTypeUse').css('display','block');
		    	 }
		    	 
		    	
		      function isMoney(money){
		      	var regu = "^[0-9]+[\.][0-9]{0,2}$";
				var re = new RegExp(regu);
				console.log(money);
				if (re.test(money)) {
					$('#maintenanceCosts').val(money);
				} else {
					$('#maintenanceCosts').val('');
					alert('维修费用必须为小数点2位的正数');
					return;
				}
				console.log(money.length);
				if(money.length > 9){
					alert('维修费用最大为9位数字');
					$('#maintenanceCosts').val('');
				}
				
		      }
		      
		      function repairFinish(){
				    var carOperateId = "${car.id}"; 
		    		var carOperatePlanId = "${carOperatePlan.id}";
		    		var handle = $("input[name='handle']:checked").val();
		    		console.log(handle);
		    		var repairManufacturer = $('#repairManufacturer').val();
		    		var maintenanceCosts = $('#maintenanceCosts').val();
		    		console.log(maintenanceCosts);
		    		
		    		if($('#repairManufacturer').val() == ''){
 				 		alert('请填写维修厂商');
 				 		return;
 				 	}
		    		if(maintenanceCosts == ''){
						alert('请填写维修费用');
		    			return;		    		
		    		}
		    		if(handle == undefined){
		    			alert('请选择处理方式');
		    			return;
		    		}
		      		$.ajax({
		             type: "GET",
		             url: "repairFinish",
		             data:{
		            	 maintenanceCosts:$('#maintenanceCosts').val(),
		            	 handle:handle,
		            	 carOperateId:carOperateId,
		            	 carOperatePlanId:carOperatePlanId,
		            	 repairManufacturer:repairManufacturer,
		            	 memberId:"${loginId}"
		             },
		             dataType: "json",
		             success: function(data){
		                var obj = eval("("+data+")"); 
	                    alert(obj["msg"]);
	                    toCarList();
					 }
	 				})
		      }
		   </script>
    </div>
    
    <script>
    
    	function toCar(){
    		 window.location.href="${ctx}/wechat/carOperate/toCar?carPlateNum=${car.carPlateNum}&loginId=${loginId}";
    	}
    	
    	//车辆找回
    	function comeHere(){
    		//车辆id
    		var carOperateId = "${car.id }"; 
    		 if(confirm("是否确定找回车辆?"))
			 {
			 	$.ajax({
		             type: "GET",
		             url: "comeBack",
		             data:{
		            	 carOperateId:carOperateId,
		            	 loginId:${loginId}
		             },
		             dataType: "json",
		             success: function(data){
		                var obj = eval("("+data+")"); 
		                var code = obj["code"]; 
	                    alert(obj["msg"]);
	                    if(code == 200){
	                    	toCar();
	                    }
					 }
	 				})
			 } 
    	}
    
    	function readySuccess(){
    		var carOperateId = "${carOperatePlan.operateId}"; 
    		console.log(carOperateId);
    		var carId = $('#carId').val();
    		if(carOperateId === ''){
    			carOperateId = carId;
    		}
    		$.ajax({
		             type: "GET",
		             url: "readySuccess",
		             data:{
		            	 carOperateId:carOperateId,
		            	 loginId:${loginId}
		             },
		             dataType: "json",
		             success: function(data){
		                var obj = eval("("+data+")"); 
	                    alert(obj["msg"]);
	                    toCarList();
					 }
	 				})
    	}
    	
    	function show(){
    		$('#cacle').css('display','block');
    		$('#main').css('display','none');
    	}
    	
    	 function noShow(){
	    	 $('#cacle').css('display','none');
	    	 $('#main').css('display','block');
    	}
    	
    	function repairFinishShow(){
    		$('#main').css('display','none');
    		$('#repairFinish').css('display','block');
    	}
    	
    	function repairFinishNoShow(){
    		$('#main').css('display','block');
    		$('#repairFinish').css('display','none');
    	}
    	 
    </script>
</body>
</html>