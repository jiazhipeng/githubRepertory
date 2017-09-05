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
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/js/date/sm.js"></script>
    <link href="${ctx}/static/gcar_html/css/gjd/date/demos.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />
    
	<link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/4v.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css?v=yy" rel="stylesheet" type="text/css" />
    </head>
	<body>
	<div id="main_div">	
	<div class="fault-page">
    <ul>
        <li>
                <span class="info-name">
                        任务类型：
                    </span>
            <span class="info-val">
                        ${butlerTask.typeInfo}
             </span>
        </li>
        <li>
                <span class="info-name">
                        取车时间：
                </span>
            <span class="info-val">
                        <fmt:formatDate value="${butlerTask.planTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
             </span>
        </li>
        <li>
           <span class="info-name">
                        取车地点：
            </span>
            <span class="info-val">
                        ${butlerTask.taskGetCarAddress}
            </span>
        </li>
         <input type="hidden" id="butlerTaskId" value="${butlerTask.id}"/>
         <input type="hidden" id = "loginId" value="${loginId}" id="loginId"/>
		 <input type="hidden" id = "queryTime" value="${queryTime}" id="queryTime"/>
    </ul>
</div>

<div class="fault-page fault-type">
    <ul>
        <li>
                <span class="info_type">
                        任务类型
                    </span>
            <span class="info_time">
                        任务时间
                    </span>
        </li>
        <c:forEach items="${butlerTasks}" var="model">
        	<%-- <c:if test="${model.id != butlerTask.id}"> --%>
            <li>
                <span class="info_type">
                        ${model.typeInfo}
                    </span>
                <span class="info_time">
                       <fmt:formatDate value="${model.planTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                 </span>
            </li>
            <%-- </c:if> --%>
        </c:forEach>
    </ul>
</div>
<c:if test="${isOperate == true}">
    <div class="btns" id="btns">
        <a href="javscript:;" class="button cencle" onclick="showChoseButler()">转派</a>
        <a href="javscript:;" class="button" onclick="pendingCompelete()">接单</a>
    </div>
</c:if>
<c:if test="${isOperate != true}">
        <div class="button fixed-btn" onclick='javascript:returnBack();'>返回</div>
</c:if>	
</div>
<!--任务备注-->
<div class="charge-page charge-dia remark" style="display:none;" id="remark_div">
    <textarea class="textarea" id="textarea" placeholder="请输入备注" maxlength="250"></textarea>
    <input name="remark" id="remark" type="hidden"/>
    <div class="btns">
        <div class="button cencle" onclick='remarkCancel();'>取消</div>
        <div class="button" onclick='remarkComplete();'>确定</div>
    </div>
</div>

<!--转派管家列表-->
<div class="charge-page charge-dia" style="display:none;" id="choseBulter_div">
    <div class="charge-tasker">
        <div class="tasker-t">当前负责管家:<span id="chooseButler">${butlerTask.operaterName!=null?butlerTask.operaterName:'暂未选择' }</span></div>
        <ul id="choseButler_ul">
            <c:forEach items="${butlers}" var="model">
                <li onclick="choseButler('${model.sysuserId}','${model.sysuserName}',this)" <c:if test="${model.sysuserId == butlerTask.operaterId}">class='on'</c:if> id="${model.sysuserId}"> ${ model.sysuserName} </li>
            </c:forEach>
        </ul>
    </div>
    <input id="old_operaterId" type="hidden" value="${butlerTask.operaterId}"/>
    <input id="old_operaterName" type="hidden" value="${butlerTask.operaterName}"/>
    <input id="operaterId" type="hidden"/>
    <input id="operaterName" type="hidden"/>
    <div class="btns">
        <div class="button cencle" onclick='choseBulterCancel();'>取消</div>
        <div class="button" onclick='choseBulterComplete();' >确定</div>
    </div>
</div>
 </body>
 <script>
 
     function buttonHide(){
        $("#btns").hide();
        $.showPreloader("正在努力处理中...");
    }

    function buttonShow(){
        $("#btns").show();
        $.hidePreloader();
    }
 
 
 /**
  * 显示管家列表
  */
 function showChoseButler(){
     $("#main_div").hide();
     //直接显示
     $("#choseBulter_div").css('display','block');
 }
 //选择管家点击确定
 function choseBulterComplete(){
     if('${isFillInNotes}' == "false"){
         showRemarks();
         return false;
     }
     if(!confirm('你确定要更换管家么？')){
         return;
     }
     var data = {"remark":$("#remark").val(),"operaterId":$("#operaterId").val(),"operaterName":$("#operaterName").val(),"id":$("#butlerTaskId").val(),"loginId":$("#loginId").val(),"queryTime":$("#queryTime").val()};
     buttonHide();
     editGetCarTaskAjax(data);
 }
 //返回操作
 function returnBack(){
	 window.location.href="${ctx}/wechat/butlerTask/getCar/toButlerTaskList?loginId=${loginId}&queryTime=${queryTime}";
 }
 //提交操作
 function editGetCarTaskAjax(data){
     $.ajax({
         type: 'POST',
         url: "${ctx}/wechat/butlerTask/getCar/edit",
         data:data,
         //  返回数据处理
         success: function(data){
        	 if(true==data.result){
             	window.location.href="${ctx}/wechat/butlerTask/getCar/detail?id="+data.butlerTaskId+"&loginId="+data.loginId+"&queryTime="+data.queryTime;
             }else{
             	 alert(data.msg);
             }
         },error: function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
         }
     });
 }
 //选择管家点击取消
 function choseBulterCancel(){
	 $("#choseButler_ul li").removeClass("on");
	 var old_operaterId = $("#old_operaterId").val();
	 $("#"+old_operaterId+"").addClass("on")
	 $("#chooseButler").html($("#old_operaterName").val());
     $("#operaterId").val("");
     $("#operaterName").val("");
     $("#choseBulter_div").hide();
     $("#main_div").css("display","block");
 }
 //选择管家切换样式
 function choseButler(operaterId,operaterName,obj){
     //交换样式
     $("#choseButler_ul li").removeClass("on");
     $(obj).addClass("on");
     $("#chooseButler").html(operaterName);
     $("#operaterId").val(operaterId);
     $("#operaterName").val(operaterName);
 }
//显示备注div
 function showRemarks(){
     $("#textarea").val("");
     $("#choseBulter_div").hide();
     $("#remark_div").css("display","block");
 }

 //备注取消操作
 function remarkCancel(){
     $("#textarea").val("");
     $("#remark_div").hide();
     $("#choseBulter_div").css("display","block");
 }
 //备注完成点击确定操作
 function remarkComplete(){
	 if(null == $("#textarea").val() || ""==$("#textarea").val()){
		 alert("请填写转派原因");
		 return false;
	 }
     $("#remark").val($("#textarea").val());
     if(!confirm('你确定要更换管家么？')){
         return;
     }
     //$("#remark_div").hide();
     //$("#choseBulter_div").css("display","block");
     var data = {"remark":$("#remark").val(),"operaterId":$("#operaterId").val(),"operaterName":$("#operaterName").val(),"id":$("#butlerTaskId").val(),"loginId":$("#loginId").val(),"queryTime":$("#queryTime").val()};
     buttonHide();
     editGetCarTaskAjax(data);
 }
 //接单操作
 function pendingCompelete(){
      buttonHide();
	//将任务的状态从待接单变成待取车,直接进入下一个页面
	
	//提交表单
		$.ajax({
	        type: 'POST',
	        url: "${ctx}/wechat/butlerTask/getCar/edit",
	        //async:false,
	        data:{"id":$("#butlerTaskId").val(),"loginId":$("#loginId").val(),"queryTime":$("#queryTime").val(),"status":"2"},
	        //  返回数据处理
	        success: function(data){
	            if(true==data.result){
	            	alert("接单成功");
	            	window.location.href = "${ctx}/wechat/butlerTask/getCar/detail?id="+data.butlerTaskId+"&loginId="+data.loginId+"&queryTime="+data.queryTime;
	            }else{
	            	 if("该任务已被修改"==data.msg){
	            		 alert(data.msg);
	            		 window.location.href="${ctx}/wechat/butlerTask/getCar/detail?id="+data.butlerTaskId+"&loginId="+data.loginId+"&queryTime="+data.queryTime;
	            	 }else{
	            		 alert(data.msg);
	            	 }
	            	 buttonShow();
	            }
	        }
	    }); 
 }
 </script>
 
 </html>