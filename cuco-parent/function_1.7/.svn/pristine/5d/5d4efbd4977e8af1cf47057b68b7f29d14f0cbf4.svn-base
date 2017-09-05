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
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css" rel="stylesheet" type="text/css" />
   </head>
   <body class="greyBg">
    <div class="charge-page" id="main_div">
        <ul class="charge-info-list">
            <li class="list-row">
                <span class="info-name">
                   	 用车会员：
                </span>
                <span class="info-val">
                    ${butlerTask.memberName!=null?butlerTask.memberName:'极车会员' }<c:if test="${not empty butlerTask.memberMobile}"><a href="tel:${butlerTask.memberMobile}" class="tel"> ( ${butlerTask.memberMobile} ) </a></c:if>
                </span>
            </li>
           	<li class="list-row">
                   <span class="info-name">
                       	取车时间：
                   </span>
                   <span class="info-val">
                       <fmt:formatDate value="${butlerTask.planTime }" pattern="yyyy-MM-dd HH:mm"/>
                   </span>
           	</li>
            <li class="list-row">
                   <span class="info-name">
                       	取车地点：
                   </span>
                   <span class="info-val">
                       	${butlerTask.taskGetCarAddress }
                   </span>
            </li>
            <li class="list-row">
                <span class="info-name">
                    	分配车辆：
                </span>
                <span class="info-val">
                    	${carOperateMessage }
                </span>
            </li>
        </ul>
        <ul class="charge-info-list">
            <li class="list-row">
                <span class="info-name">
                  	  负责管家：
                </span>
                <span class="info-val">
                    ${butlerTask.operaterName }
                </span>
            </li>
            <li class="list-row">
                   <span class="info-name">
                       	任务处理：
                   </span>
                   <span class="info-val">
                       <span class="task-state radioRoup">
                           <c:if test="${butlerTask.status == 2}">
	                           <em>
	                               <input type="radio" name="butlerStatus" value="100" id='rd1'>
	                               <label for='rd1'>跟进中</label>
	                           </em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="3" id='rd2'>
	                               <label for='rd2'>出发取车</label>
	                           </em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="6" id='rd3'>
	                              <label for='rd3'>失联</label>
	                           </em>
                           </c:if>
                           <c:if test="${butlerTask.status == 3}">
                           		<em>
	                               <input type="radio" name="butlerStatus" value="100" id='rd1'>
	                               <label for='rd1'>跟进中</label>
                           		</em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="4" id='rd3'>
	                               <label for='rd3'>已到达</label>
	                           </em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="6"  id='rd4'>
	                              <label for='rd4'>失联</label>
	                           </em>
                           </c:if>
                           <c:if test="${butlerTask.status == 4}">                                             
                           		<em>
	                               <input type="radio" name="butlerStatus" value="100" id='rd1' onclick="closeChangePlan()">
	                               <label for='rd1'>跟进中</label>
                           		</em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="7" id='rd3' onclick="closeChangePlan()">
	                               <label for='rd3'>确认取车</label>
	                           </em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="6" id='rd4' onclick="closeChangePlan()">
	                              <label for='rd4'>失联</label>
	                           </em>
                           </c:if>
                       </span>
                   </span>
            </li>
            <li data-val="aaa">
                <a href="javascript:showRemarks();">
                    <span class="info-name">
                       	 任务备注：
                    </span>
                    <span class="info-val" id="remarks">
                                                                         
                    </span>
                </a>
            </li>
            <!-- 已经到达则需要上传交接单-->
		    <c:if test="${butlerTask.status == 4}">
		        <ul class="charge-info-list" id="transferListUl" style="display: none;">
		            <li data-val="aaa">
		                <a href="${ctx}/transferList/toUpload?type=0&taskId=${butlerTask.id}&loginId=${loginId}" >
		                <span class="info-name">
		                       	 交接单：
		                </span>
		                    <span class="info-val"  id="transferPicture-info-val">
		                    已上传${transferCount}张
		                </span>
		                </a>
		            </li>
		
		            <li data-val="aaa">
		                <a href="${ctx}/transferList/toUpload?type=1&taskId=${butlerTask.id}&loginId=${loginId}" >
		                <span class="info-name">
		                       	 车辆图片：
		                </span>
		                    <span class="info-val" id = "carPicture-info-val">
		                    已上传${carCount}张
		                </span>
		                </a>
		            </li>
		            
		            <li class="list-row">
	                <span class="info-name">
	                  	  消耗油量(L)：
	                </span>
	                <span class="info-val">
	                    <input id="gasoline" type="tel" maxlength="2">
	                </span>
	            	</li>
	            
		        </ul>
		    </c:if>
        </ul>
        <div class="charge-task-t">
           	 任务记录
        </div>
        <ul class="charge-task-list">
        	<c:forEach items="${logList}" var="model">
	        	<li>
		        	<div class="row">
		                <p>${model.statusInfo }</p>
		                <p>${model.modifier }</p>
		                <p><fmt:formatDate value="${model.created }" pattern="yyyy-MM-dd HH:mm"/> </p>
		            </div>
	                <div class="row">
	                	${model.remark }
	                </div>
	            </li>
        	</c:forEach>
        </ul>
        <div class="btns">
            <div class="button cencle" onclick='formCancel();'>取消</div>
        	<div class="button" onclick='formSubmit(this);' id="submitButton">确定</div>
        </div>
    </div>
    
     <!--填写备注-->
    <div class="charge-page charge-dia remark" style="display:none;" id="remark_div">
        <textarea class="textarea" id="textarea" placeholder="请输入备注" maxlength="250"></textarea>
        <div class="btns">
            <div class="button cencle" onclick='remarkCancel();'>取消</div>
        	<div class="button" onclick='remarkComplete();' id="submitButton">确定</div>
        </div>
    </div>
    
    <form action="${ctx}/wechat/butlerTask/getCar/edit" id = "myForm" style="display:none" method="post">
		<input type="hidden" name = "loginId" value="${loginId}" id="loginId"/>
		<input type="hidden" name = "queryTime" value="${queryTime}" id="queryTime"/>
		<input type="hidden" name= "status" id = "status" />
		<input type="hidden" name= "remark" id = "remark" />
		<input type="hidden" name="id" value="${butlerTask.id}"/>
		<input type="hidden" name= "gasoline" id = "gasoline_hidden" />
	</form>
	<!--以前的数据作对比-->
	<input type="hidden" id = "oldStatus" value="${butlerTask.status}"/>
	<input type="hidden" id = "carOperateId" value="${butlerTask.carOperateId}"/>
	
</body>
<script>
function closeChangePlan(){
	var status = $("input[name='butlerStatus']:checked").val();
	if(7 == status){
		$("#transferListUl").css("display","block");
	}else{
		$("#transferListUl").hide();
	}
}
//显示备注div
function showRemarks(){
	$("#main_div").hide();
	$("#remark_div").css("display","block");
}
//备注取消操作
function remarkCancel(){
	$("#textarea").val("");
	$("#remark_div").hide();
	$("#main_div").css("display","block");
}
//备注完成点击确定操作
function remarkComplete(){
	$("#remarks").html($("#textarea").val());
	$("#remark").val($("#textarea").val());
	$("#remark_div").hide();
	$("#main_div").css("display","block");
}
//取消
function formCancel(){
	window.location.href="${ctx}/wechat/butlerTask/getCar/toButlerTaskList?loginId=${loginId}&queryTime=${queryTime}";
}
//提交
function formSubmit(obj){
	$(obj).attr("onclick","return false");
	var gasoline = $("#gasoline").val();
	var status = $("input[name='butlerStatus']:checked").val();
	var remark = $("#remark").val();
	var oldStatus = $("#oldStatus").val();
	var carOperateId = $("#carOperateId").val();
	
	if(7==status){
		//确认取车
		if(null==gasoline || ""==gasoline){
			alert("请输入消耗油量");
			$(obj).attr("onclick","formSubmit(this)");
			return false;
		}else{
			if(!(/^(0|[1-9]\d*)$/.test(gasoline))){
				alert("消耗油量必须为正整数");
				$(obj).attr("onclick","formSubmit(this)");
				return false;
			}
			$("#gasoline_hidden").val(gasoline);
		}
	}
	if(null==status || ""==status){
		alert("请选择任务处理方式");
		$(obj).attr("onclick","formSubmit(this)");
		return false;
	}
	//取消时，必须填写备注,以及除了失联变成待取车的状态
	if(8==status || (2==status && 6!=oldStatus)){
		if(null==remark || ""==remark){
			alert("请输入备注信息");
			$(obj).attr("onclick","formSubmit(this)");
			return false;
		}
	}
	//提交操作
	if(100 != status){
		if(null==carOperateId ||""==carOperateId){
			alert("车辆不能为空");
			$(obj).attr("onclick","formSubmit(this)");
			return false;
		}
		//给状态赋值
		$("#status").val(status)
	}
	//提交表单
	$.ajax({
        type: 'POST',
        url: "${ctx}/wechat/butlerTask/getCar/edit",
        async:false,
        data:$("#myForm").serialize(),
        //  返回数据处理
        success: function(data){
            if(true==data.result){
            	window.location.href="${ctx}/wechat/butlerTask/getCar/detail?id="+data.butlerTaskId+"&loginId="+data.loginId+"&queryTime="+data.queryTime;
            }else{
            	 if("该任务已被修改"==data.msg){
            		 alert(data.msg);
            		 window.location.href="${ctx}/wechat/butlerTask/getCar/detail?id="+data.butlerTaskId+"&loginId="+data.loginId+"&queryTime="+data.queryTime;
            	 }else{
            		 $(obj).attr("onclick","formSubmit(this)");
            		 alert(data.msg);
            	 }
            }
        }
    });
	
}
</script>
</html>