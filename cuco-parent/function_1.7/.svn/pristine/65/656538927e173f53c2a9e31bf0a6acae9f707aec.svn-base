<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--选择分配车辆-->
<div class="distribution-page" id="selectCarDiv" style="display: none;">
    <ul class="distribution-tab">
        <li>车辆信息</li>
        <%--<li>状态</li>--%>
        <li>入库时间</li>
    </ul>
    <ul class="distribution-list" id="selectCarUl">
    </ul>
    <div class="btns">
        <div class="button cencle" onclick='usedCarCancel();'>取消</div>
        <div class="button" onclick='usedCarComplete();'>确定</div>
    </div>
</div>
<input type="hidden" id="carOperateId_copy"/>
<input type="hidden" id="carOperateName_copy"/>
<input type="hidden" id="datetime-picker" value="<fmt:formatDate value="${powerUsed.carReturnTime }" pattern="yyyy-MM-dd HH:mm"/>">
<script type="text/javascript">
    /**
     * 选择车辆
     */
    function showUsedCar() {
        var planTime = $("#datetime-picker").val();
        if(!planTime){
            alert('请选择用车时间');
            return;
        }
        planTime = planTime.replace(/-/g,"/");
        planTime = new Date(planTime);
        var datas = {planTime:planTime,id:'${butlerTask.id}',powerUsedId:'${butlerTask.powerUsedId}'};
        $.ajax({
            type: 'POST',
            url: "${ctx}/wechat/butler/task/sendCar/getAvailableVehicles",
            async:false,
            data:datas,
            datatype:"json",
            //  返回数据处理
            success: function(data){
                if(data.errcode == "ok"){
                    var result = data.result;
                    var selectCarUl = $("#selectCarUl");
                    var li = "";
                    var carOperate = "";
                    var statusInfo = "";
                    var endTime = "";
                    var isLimitLineInfo = "";
                    for(var i = 0;i<result.length;i++){
                        carOperate = result[i];
                        if(carOperate.operatePlanStatusInfo){
                            statusInfo = carOperate.operatePlanStatusInfo;
                        }else{
                            statusInfo = carOperate.statusInfo;
                        }
                        endTime = "--";
                        if(carOperate.endTime){
                            endTime = resolveCharacterDate(carOperate.endTime,"yyyy-MM-dd hh:mm");
                        }
                        var color = "";
                        if(null != carOperate.carColor){
                        	color = "("+carOperate.carColor+")";
                        }
                        isLimitLineInfo = "";
                        if(carOperate.isLimitLine == true){
                            isLimitLineInfo = "(限行)";
                        }
                        li += "<li onclick=\"clickCarLi("+carOperate.isLimitLine+",'"+carOperate.carBrand+"','"+carOperate.carPlateNum+"','"+carOperate.carType+"','"+carOperate.id+"',this)\">"
                                +"<p>"
                                +"  <span>"+carOperate.carBrand+"</span>"
                                +"  <span>"+carOperate.carType+"</span>"
                                +"  <span>"+carOperate.carPlateNum+color+"</span>"
                                +" <span>"+isLimitLineInfo+"</span>"
                                +"</p>"
//                                +"<p>"
//                                +  statusInfo
//                                +"</p>"
                                +"<p>"
                                +  endTime
                                +"</p>"
                                +"</li>";
                    }
                    selectCarUl.html(li);
                }else{
                    alert(data.errmsg);
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
        $("#main_div").hide();
        $("#selectCarDiv").css("display","block");
    }
    /**
     * 取消选择车辆
     */
    function usedCarCancel(){
        $("#main_div").css("display","block");
        $("#selectCarDiv").hide();
    }

    function clickCarLi(isLimitLine,carBrand,car_plate_num,car_type,car_operate_id,obj) {
        console.log(car_plate_num);
        console.log(car_type);
        console.log(car_operate_id);
        console.log(carBrand);
        if(isLimitLine==true){
            alert("此车辆限行，不能选择");
            return;
        }
        $("#carOperateId_copy").val(car_operate_id);
        $("#carOperateName_copy").val(carBrand + "   " + car_type + "   " + car_plate_num);
        $("#usedCar_copy").html($("#carOperateName").val());

        $(obj).siblings().removeClass("on");
        $(obj).addClass("on");

    }
    /**
     * 用车点击确定按钮
     */
    function usedCarComplete(){
        $("#carOperateId").val($("#carOperateId_copy").val());
        $("#carOperateName").val($("#carOperateName_copy").val());
        $("#usedCar").html($("#carOperateName_copy").val());
        usedCarCancel();

    }
</script>