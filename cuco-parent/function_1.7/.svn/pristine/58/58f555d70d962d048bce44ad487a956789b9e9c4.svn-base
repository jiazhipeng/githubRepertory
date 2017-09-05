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
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/dateUtils.js?v=<%=new Date().getTime()%>"></script>
    <!--高德地图插件end  -->
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <link href="${ctx}/static/gcar_html/css/gjd/date/demos.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css?v=yy" rel="stylesheet" type="text/css" />
</head>
<body class="greyBg">
<div class="charge-page" id="main_div">
    <ul class="charge-info-list">

            <c:if test="${butlerTask.status == 0}">
                <li>
                    <a onclick="showChoseButler()">
                        <span class="info-name">
                              负责管家：
                        </span>
                        <span class="info-val">
                            <span id="modifier_span">${butlerTask.operaterName }</span>
                            <input type="hidden" value="${butlerTask.operaterId}" id="operaterIdBack"/>
                        </span>
                    </a>
                </li>
            </c:if>
            <c:if test="${butlerTask.status > 0 && butlerTask.status != 9}">
                <li class="list-row">
                    <span class="info-name">
                          负责管家：
                    </span>
                    <span class="info-val">
                        <span>${butlerTask.operaterName }</span>
                    </span>
                </li>
            </c:if>
            
          <c:if test="${butlerTask.status == 9}"><!-- 9:待处理 -->
                <li>
                    <a onclick="showCustomer()"><!-- 选择客服  -->
                        <span class="info-name">
                              负责客服：
                        </span>
                        <span class="info-val">
                            <span id="customer_span">${butlerTask.customerName }</span>
                        </span>
                    </a>
                </li>
           </c:if>     
        </li>
        <li class="list-row">
            <span class="info-name">
                 用车会员：
            </span>
            <span class="info-val">
                ${butlerTask.memberName!=null?butlerTask.memberName:'极车会员' }<c:if test="${not empty butlerTask.memberMobile}"><a href="tel:${butlerTask.memberMobile}" class="tel"> ( ${butlerTask.memberMobile} ) </a></c:if>
            </span>
        </li>
        <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/js/date/sm.js"></script>
        <c:if test="${butlerTask.status == 0 || butlerTask.status == 9}">
            <%@include file="edit_include_date.jsp" %>
        </c:if>

        <c:if test="${butlerTask.status!=0 && butlerTask.status!=9}">
            <li class="list-row">
                <span class="info-name">
                        预计用车时间：
                </span>
                <span class="info-val page-current">
                        <fmt:formatDate value="${butlerTask.planTime }" pattern="yyyy-MM-dd HH:mm"/>
                </span>
            </li>
        </c:if>
        <c:if test="${butlerTask.status==0 || butlerTask.status==9}">
            <li>
                <a  onclick="showSendCarAddress();">

                    <span class="info-name">
                            预计用车地点：
                    </span>
                    <span class="info-val" id="sendCarAddress_span">
                       ${butlerTask.taskSendCarAddress}
                    </span>
                </a>
            </li>
        </c:if>

        <c:if test="${butlerTask.status!=0 && butlerTask.status!=9}">
            <li class="list-row">
                <span class="info-name">
                        预计用车地点：
                </span>
                <span class="info-val">
                        ${butlerTask.taskSendCarAddress}
                </span>
            </li>
        </c:if>

        <c:if test="${butlerTask.status == 9}">
            <li class="list-row">
                <a>
                    <span class="info-name">
                            预计还车时间：
                    </span>
                    <span class="info-val page-current" id="page-datetime-picker">
                        <input type="text" placeholder="" id="datetime-picker-still" readonly="" value="<fmt:formatDate value="${powerUsed.carReturnTime }" pattern="yyyy-MM-dd HH:mm"/>">
                        <script>
                           $(document).on("pageInit", "#page-datetime-picker", function(e) {
                               <%--// var planTime = '${butlerTask.planTime}';--%>
                               <%--var planTime = "";--%>
                               <%--var oDate = null;--%>
                               <%--if(planTime){--%>
                               <%--oDate =  resolveStringDate(planTime);--%>
                               <%--}else{--%>
                               <%--oDate = DateUtil.dateAdd('h',4,new Date());--%>
                               <%--}--%>
                               <%--var year = oDate.getFullYear();   //获取系统的年；--%>
                               <%--var month = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1--%>
                               <%--month=month<10?('0'+month):month;--%>
                               <%--var date = oDate.getDate(); // 获取系统日，--%>
                               <%--var hour = oDate.getHours(); //获取系统时，--%>
                               <%--var minute = oDate.getMinutes(); //分--%>
                               $("#datetime-picker-still").datetimePicker({
                                   toolbarTemplate: '<header class="bar bar-nav">\
                              <button class="button button-link pull-right close-picker">确定</button>\
                              <h1 class="title">选择时间</h1>\
                              </header>',
                               });
                           });
                           $.init();
                        </script>
                    </span>
                </a>
            </li>
        </c:if>

        <c:if test="${butlerTask.status == 9}">
            <li class="list-row">
                <a  onclick="showGetCarAddress();">
                    <span class="info-name">
                            预计还车地点：
                    </span>
                    <span class="info-val" id="getCarAddress_span">
                            ${powerUsed.carReturnAddress}
                    </span>
                </a>
            </li>
        </c:if>
        <li class="list-row">
            <span class="info-name">
                    意向车型：
            </span>
            <span class="info-val" >${carType.brand} &nbsp;&nbsp;${carType.carType}</span>
        </li>

        <c:if test="${butlerTask.status==0}">
            <li>
                <a onclick="showUsedCar();">
                    <span class="info-name">
                            分配车辆：
                    </span>
                    <span class="info-val" id ="usedCar" >${carOperate.carType} &nbsp;&nbsp;${carOperate.carPlateNum}</span>
                </a>
            </li>
        </c:if>
        <c:if test="${butlerTask.status!=0 && butlerTask.status!=9}">
            <li class="list-row">
                <span class="info-name">
                        分配车辆：
                </span>
                <span class="info-val">${carOperate.carType} &nbsp;&nbsp;${carOperate.carPlateNum}</span>
            </li>
        </c:if>
    </ul>
    <ul class="charge-info-list">
        <li class="list-row">
           <span class="info-name">
                任务处理：
           </span>
            <span class="info-val">
               <span class="task-state radioRoup">
                       <em>
                           <label class="chock">
                               <input type="radio" name="butlerStatus" id="follow" value="100" checked="checked">
                               <i>跟进中</i>
                           </label>
                       </em>
                      <c:if test="${butlerTask.status == 9}">
                            <em>
                               <label class="chock">
                                   <input type="radio" name="butlerStatus" value="0"/>
                                   <i>确认用车</i>
                               </label>
                           </em>
                      </c:if>
                      <c:if test="${butlerTask.status == 0}">
                           <em>
                               <label class="chock">
                                   <input type="radio" name="butlerStatus" value="1"/>
                                   <i>出发送车</i>
                               </label>
                           </em>
                      </c:if>
                       <c:if test="${butlerTask.status == 1}">
                               <em>
                                   <label class="chock">
                                       <input type="radio" name="butlerStatus" value="4">
                                       <i>已到达</i>
                                   </label>
                               </em>
                       </c:if>
                       <c:if test="${butlerTask.status == 4}">
                               <em>
                                   <label class="chock">
                                       <input type="radio" name="butlerStatus" value="7"/>
                                       <i>确认送达</i>
                                   </label>
                               </em>
                        </c:if>
                       <em>
                           <label class="chock">
                              <input type="radio" name="butlerStatus" id="cacle" value="8"/>
                              <i>取消任务</i>
                           </label>
                       </em>
               </span>
            </span>
        </li>
        <li data-val="aaa">
            <a href="javascript:;">
                <span class="info-name">
                       	 任务备注：
                </span>
                <span class="info-val" onclick="showRemarks();" id="remarks">
                        备注信息
                </span>
            </a>
        </li>
    </ul>
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
        </ul>

    </c:if>



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
        <div class="button cencle" onclick='toTaskList()' id="toTaskListButton">取消</div>
        <div class="button" onclick='formSubmit();' id="formSubmitButton">确定</div>
    </div>
    <script>
    	function toTaskList(){
    		if("${from}"){
    			location.href = "${ctx}/wechat/butler/task/sendCar/toTaskSendCarList?operaterId=${butlerTask.operaterId}";
    		}else{
    			location.href = document.referrer;
    		}
    	}
    </script>
</div>
<!--填写备注-->
<div class="charge-page charge-dia remark" style="display:none;" id="remark_div">
    <textarea class="textarea" id="textarea" placeholder="请输入备注" maxlength="250"></textarea>
    <div class="btns">
        <div class="button cencle" onclick='remarkCancel();'>取消</div>
        <div class="button" onclick='remarkComplete();'>确定</div>
    </div>
</div>
<input type="hidden" name= "memberId" id="memberId" value= "${butlerTask.memberId}" />
<form id = "myForm" style="display:none" method="post">
    <input type="hidden" name= "status" id = "status" />
    <input type="hidden" name= "planTime" id = "planTime" value="${butlerTask.planTime}"/>
    <input type="hidden" name="completeTime" id="completeTime" value="${powerUsed.carReturnTime}">
    <input type="hidden" name= "remark" id = "remark" />
    <input type="hidden" name = "operaterId" id="operaterId" />
    <input type="hidden" name = "operaterName" id="operaterName" />
    <input type="hidden" name = "customerId" id="customerId" />
    <input type="hidden" name = "customerName" id="customerName" />
    <input type="hidden" name="taskSendCarAddress" id="taskSendCarAddress">
    <input type="hidden" name="taskGetCarAddress" id="taskGetCarAddress" value="${powerUsed.carReturnAddress}">
    <input type="hidden" name="id" value="${butlerTask.id}"/>
    <input type="hidden" name="carOperateId" id="carOperateId" value="${butlerTask.carOperateId}">
    <input type="hidden" name="carOperateName" id="carOperateName" value="">
</form>
<c:if test="${butlerTask.status == 0 || butlerTask.status == 9}">
    <%@ include file="edit_include.jsp"%>
</c:if>
</body>
<script>
    //取消
    function formCancel(){
        window.location.href = "${ctx}/wechat/butler/task/sendCar/toTaskSendCarList?operaterId=${butlerTask.operaterId}";
    }
    $(function () {
        $(".radioRoup em label").on('tap', function () {
           var status = $(this).find('input[name="butlerStatus"]').val();
            showTransferListUl(status);
        })
    })

    function showTransferListUl(status) {
        if(status == 7){
            $("#transferListUl").show();
        }else{
            $("#transferListUl").hide();
        }
    }
    function buttonHide(){
        $("#toTaskListButton").hide();
        $("#formSubmitButton").hide();
        $("#formSubmitButton").attr("onclick","return false");
        $.showPreloader("正在努力处理中...");

    }


    function buttonShow(){
        $("#toTaskListButton").show();
        $("#formSubmitButton").show();
        $("#formSubmitButton").attr("onclick","formSubmit()");
        $.hidePreloader();
    }

    //提交
    function formSubmit(){

        buttonHide();
        var status = $("input[name='butlerStatus']:checked").val();
        var remark = $("#remark").val();
        var operaterId = $("#operaterId").val();
        var data = null;
        if(!status){
            alert("请选择任务处理方式");
            buttonShow();
            return ;
        }
        if(100 != status){
            //给状态赋值
            $("#status").val(status)
        }
        //取消时，必须填写备注
        if(8==status){
            if(null==remark || ""==remark){
                alert("请输入备注信息");
                buttonShow();
                return ;
            }
            data = {
                status:$("#status").val(),
                id:'${butlerTask.id}',
                remark:$("#remark").val()
            }
            editSendCarTaskAjax(data);
            return;
        }
      //不是取消判断用户是否被冻结
        var freezeFlag = false;
        $.ajax({
   				type: 'POST',
   				url: "${ctx}/wechat/butler/task/sendCar/checkMemberStatuts",
   				async:false,
   				data:{"memberId":$("#memberId").val()},
   				//  返回数据处理
   				success: function(data) {
                    if (data) {
                        //冻结了
                        alert("该用户已被冻结，无法继续用车");
                        buttonShow();
                        $('#cacle').prop("checked", true);
                        $('#follow').prop("checked", false);
                        freezeFlag = true;
                    }
                }
        });
        
        if(freezeFlag){
        	return false;
        }
        if('${butlerTask.status}' == 0 || '${butlerTask.status}'== 9 ){
            if(status == 1){
                if(!$("#carOperateId").val()){
                    alert("请分配车辆");
                    buttonShow();
                    return;
                }
            }
            var planTime = $("#datetime-picker").val();
            if(!planTime){
                alert("请选择预计用车时间");
                buttonShow();
                return;
            }
            planTime = planTime.replace(/-/g,"/");
            planTime = new Date(planTime);
            var  butlerUsecarAdvance  = '${basicThreshold.butlerUsecarAdvance}';
            var minDate = DateUtil.dateAdd('h',parseInt(butlerUsecarAdvance),new Date());
            if(status!=8){
                /*  if(planTime.getTime() < minDate.getTime()){
                 alert("只能选择当前时间 "+ butlerUsecarAdvance +" 小时之后的时间");
                 $(obj).attr("onclick","formSubmit(this)");
                 return;
                 } */

                if(planTime.getTime() < new Date().getTime()){
                    alert("预计用车时间不得小于当前时间");
                    buttonShow();
                    return ;
                }
                var array = DateUtil.toArray(planTime);
                var hours = array[3];
                var minutes = array[4];
                var serviceTimeStart = '${basicThreshold.serviceTimeStart}';
                var serviceTimeEnd =  '${basicThreshold.serviceTimeEnd}';

                if(serviceTimeStart!=null && serviceTimeEnd!=null){
                    var starthour = serviceTimeStart.substring(0,2);
                    var startminute = serviceTimeStart.substring(3,5);
                    var endhour = serviceTimeEnd.substring(0,2);
                    var endminute = serviceTimeEnd.substring(3,5);
                    var start= parseInt(starthour)*60*60+ parseInt(startminute)*60;
                    var end= parseInt(endhour)*60*60+ parseInt(endminute)*60;
                    var plan = parseInt(hours)*60*60+ parseInt(minutes)*60;
                    console.log("start="+start+"-end="+end+"-plan="+plan);
                    if(!(plan<=end && plan>=start)){
                        alert("当前所选时间不在服务时间内，请选择服务时间内的时间，服务时间"+starthour+":"+startminute+"-"+endhour+":"+endminute);
                        buttonShow();
                        return ;
                    }
                }
            }
            //如果是待处理状态需要校验还车地点 和还车时间
            if('${butlerTask.status}'== 9){
                var planTimeStill = $("#datetime-picker-still").val();
                if(!planTimeStill){
                    alert("请选择预计还车时间");
                    buttonShow();
                    return;
                }
                planTimeStill = planTimeStill.replace(/-/g,"/");
                planTimeStill = new Date(planTimeStill);

                array = DateUtil.toArray(planTimeStill);
                hours = array[3];
                minutes = array[4];
                hours = parseInt(hours);
                minutes = parseInt(minutes);
                if(serviceTimeStart!=null && serviceTimeEnd!=null){
                    var starthour = serviceTimeStart.substring(0,2);
                    var startminute = serviceTimeStart.substring(3,5);
                    var endhour = serviceTimeEnd.substring(0,2);
                    var endminute = serviceTimeEnd.substring(3,5);
                    var start= parseInt(starthour)*60*60+ parseInt(startminute)*60;
                    var end= parseInt(endhour)*60*60+ parseInt(endminute)*60;
                    var plan = parseInt(hours)*60*60+ parseInt(minutes)*60;
                    console.log("start="+start+"-end="+end+"-plan="+plan);
                    if(!(plan<=end && plan>=start)){
                        alert("还车时间不在服务时间内，请选择服务时间内的时间，服务时间"+starthour+":"+startminute+"-"+endhour+":"+endminute);
                        buttonShow();
                        return ;
                    }
                }
                if(planTime.getTime() > planTimeStill.getTime()){
                    alert('预计还车时间不能小于用车开始时间');
                    buttonShow();
                    return;
                }
                var taskGetCarAddress = $("#taskGetCarAddress").val();
                if(!taskGetCarAddress){
                    alert('请选择还车地点');
                    buttonShow();
                    return;
                }
                $("#completeTime").val(planTimeStill);

            }
            //如果是待送车状态
            if('${butlerTask.status}'== 0){
                var getCarTime = '${powerUsed.carReturnTime}';
                getCarTime = new Date(getCarTime);
                if(getCarTime.getTime()<= planTime.getTime()){
                    alert("还车时间不能小于用车时间");
                    buttonShow();
                    return;
                }
            }
            if(status!=100){
                var operaterId = $("#operaterId").val();
                var operaterIdBack = $("#operaterIdBack").val();
                if(operaterId){
                    if(operaterId!=operaterIdBack){
                        alert("只有跟进中才能更换管家");
                        buttonShow();
                        return;
                    }
                }
            }

            $("#planTime").val(planTime);
            data = $("#myForm").serialize();
            editSendCarTaskAjax(data);
            return;
        }
        data = {
            status:$("#status").val(),
            id:'${butlerTask.id}',
            remark:$("#remark").val()
        }
        editSendCarTaskAjax(data);
    }


    function editSendCarTaskAjax(data){
        $.ajax({
            type: 'POST',
            url: "${ctx}/wechat/butler/task/sendCar/editSendCarTask",
            //async:false,
            data:data,
            //  返回数据处理
            success: function(resultData){
                if(resultData.errcode == "ok"){
                    alert(resultData.result);
                   var num = Math.random();
                    buttonShow();
                    window.location.href = "${ctx}/wechat/butler/task/sendCar/toEditSendCarTask?taskId=${butlerTask.id}" + "&v=" + num;
                }else{
                    buttonShow();
                    alert(resultData.errmsg);
                }
            },error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                buttonShow();
            }
        });
    }
    //显示备注div
    function showRemarks(){
        $("#textarea").val("");
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
</script>
</html>