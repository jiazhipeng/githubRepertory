<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="carTypeIdHidden" value="${carOperate.carTypeId}"/>
<div class="distribution" id="selectCarDiv" style="display: none;">
    <div class="moudel-list">
        <ul id="carTypeUL">
            <li class="active">
                特斯拉ModelS
            </li>

        </ul>
    </div>
    <ul class="car-list" id="selectCarUl">
        <li>
            <div class="flex">
                京A·R5678<br> （限行）
            </div>
            <div class="flex">
                <p class="state">待分配</p>
            </div>
        </li>
        <li>
            <div class="flex">
                京A·R5678<br> （限行）
            </div>
            <div class="flex">
                <p class="state">会员使用中</p>
                <p class="small">预计还车时间</p>
                <span class="time">12/8 9：00</span>
            </div>
        </li>
    </ul>
    <div class="btns">
        <div class="button cencle" onclick='usedCarCancel();'>取消</div>
        <div class="button" onclick='usedCarComplete();'>确定</div>
    </div>
</div>
<input type="hidden" id="carOperateId_copy"/>
<input type="hidden" id="carOperateName_copy"/>
<input type="hidden" id="datetime-picker" value="<fmt:formatDate value="${powerUsed.carUsedTime }" pattern="yyyy-MM-dd HH:mm"/>">
<script type="text/javascript">

    function getcarTypeList(){
        var list;
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
                    list =   data.result;
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
        return list;
    }

    var carTypeLists = null;
    /**
     * 选择车辆
     */
    function showUsedCar() {

        carTypeLists = getcarTypeList();
        var carTypeUL = $("#carTypeUL");
        var li =  "";
        var active = "";
        var carOperates = null;
        var carTypeId = $("#carTypeIdHidden").val();
        var isActive = false;
        for(var i = 0;i<carTypeLists.length;i++){
            if(carTypeId == carTypeLists[i].carTypeId){
                isActive = true;
                break;
            }
        }


        for(var i = 0;i<carTypeLists.length;i++){
            carOperates = carTypeLists[i].carOperates;
            active = "";
            if(i == 0 && isActive == false){
                active = "active";
            }
            if(carTypeId == carTypeLists[i].carTypeId){
                active = "active";
            }
            if(carOperates.length == 0){
                li +='<li class="'+active+'" onclick=\"getCarOperates('+carTypeLists[i].carTypeId+',this)\">'+carTypeLists[i].carTypeName+' (无)</li>';
            }else{
                li +='<li class="'+active+'" onclick=\"getCarOperates('+carTypeLists[i].carTypeId+',this)\">'+carTypeLists[i].carTypeName+' (有)</li>';
            }
            if(i == 0  || carTypeLists[i].carTypeId == carTypeId){
                setCarOperatesLiHtml(carOperates);
            }
        }
        carTypeUL.html(li);

        $("#main_div").hide();
        $("#selectCarDiv").css("display","block");
    }
    /**
     * 取消选择车辆
     */
    function usedCarCancel(){
        $("#main_div").css("display","block");
        $("#selectCarDiv").hide();
        $("#carTypeIdHidden").val("${carOperate.carTypeId}");
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
        $("#main_div").css("display","block");
        $("#selectCarDiv").hide();
    }

    function getCarOperates(carTypeId,obj){
        if(carTypeLists == null){
                return;
            }
            var carOperates = null;
            for(var i = 0 ;i<carTypeLists.length;i++){
                if(carTypeLists[i].carTypeId == carTypeId){
                    carOperates = carTypeLists[i].carOperates;
                    break;
                }
            }

        setCarOperatesLiHtml(carOperates);
        $(obj).siblings().removeClass("active");
        $(obj).addClass("active");
        $("#carTypeIdHidden").val(carTypeId);

    }


    function setCarOperatesLiHtml(carOperates){

        var selectCarUl = $("#selectCarUl");
        var carOperatesLi = "";
        var carOperate = null;
        var statusInfo = "";
        var endTime = "";
        var isLimitLineInfo = "";
        if(carOperates == null || carOperates.length == 0){
            selectCarUl.html("该车型无可用车辆，请选择其他车型");
            return;
        }

        var carOperateId = $("#carOperateId").val();
        var on = "";
        for(var j = 0;j<carOperates.length;j++){
            carOperate = carOperates[j];
            if(carOperate.operatePlanStatusInfo){
                statusInfo = carOperate.operatePlanStatusInfo;
            }else{
                statusInfo = carOperate.statusInfo;
            }
            endTime = "--";
            if(carOperate.endTime){
                endTime = resolveCharacterDate(carOperate.endTime,"MM/dd hh:mm");
            }
            var color = "";
            if(null != carOperate.carColor){
                color = "("+carOperate.carColor+")";
            }
            isLimitLineInfo = "";
            if(carOperate.isLimitLine == true){
                isLimitLineInfo = "(限行)";
            }
            on = "";
            if(carOperateId == carOperate.id){
                on = "on"
            }
            if(carOperate.endTime){
                carOperatesLi+=
                        "<li class='"+on+"' onclick=\"clickCarLi("+carOperate.isLimitLine+",'"+carOperate.carBrand+"','"+carOperate.carPlateNum+"','"+carOperate.carType+"','"+carOperate.id+"',this)\">"+
                        '<div class="flex">'+
                        carOperate.carPlateNum + "  "+isLimitLineInfo+""+
                        '</div>'+
                        '<div class="flex">'+
                        '<p class="state">'+statusInfo+'</p>'+
                        '<p class="small">预计还车时间</p>'+
                        '<span class="time">'+endTime+'</span>'+
                        '</div>'+
                        '</li>';
            }else{
                carOperatesLi+=
                        "<li class='"+on+"' onclick=\"clickCarLi("+carOperate.isLimitLine+",'"+carOperate.carBrand+"','"+carOperate.carPlateNum+"','"+carOperate.carType+"','"+carOperate.id+"',this)\">"+
                        '<div class="flex">'+
                        carOperate.carPlateNum + "  "+isLimitLineInfo+""+
                        '</div>'+
                        '<div class="flex">'+
                        '<p class="state">'+statusInfo+'</p>'+
                        '</div>'+
                        '</li>';
            }
        }
        selectCarUl.html(carOperatesLi);


    }
</script>