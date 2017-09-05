<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--高德地图插件  -->
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=b861063582ce96fb14c1e8d61a800608&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
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




<!--管家列表-->
<div class="charge-page charge-dia" style="display:none;" id="choseBulter_div">
    <div class="charge-tasker">
        <div class="tasker-t">当前负责管家:<span id="chooseButler">${butlerTask.operaterName!=null?butlerTask.operaterName:'暂未选择' }</span></div>
        <ul id="choseButler_ul">
            <c:forEach items="${butlers}" var="model">
                <li onclick="choseButler('${model.sysuserId}','${model.sysuserName}',this)" <c:if test="${model.sysuserId == butlerTask.operaterId}">class='on'</c:if>> ${ model.sysuserName} </li>
            </c:forEach>
        </ul>
    </div>

    <div class="btns">
        <div class="button cencle" onclick='choseBulterCancel();'>取消</div>
        <div class="button" onclick='choseBulterComplete();' >确定</div>
    </div>
</div>
<!-- 客服列表 -->
<div class="charge-page charge-dia" style="display:none;" id="choseCustomer_div">
    <div class="charge-tasker">
        <div class="tasker-t">当前负责管家:<span id="chooseCustomer">${butlerTask.customerName!=null?butlerTask.customerName:'暂未选择' }</span></div>
        <ul id="choseCustomer_ul">
            <c:forEach items="${customers}" var="customer">
                <li onclick="choseCustomer('${customer.sysuserId}','${customer.sysuserName}',this)" <c:if test="${customer.sysuserId == butlerTask.customerId}">class='on'</c:if>> ${ customer.sysuserName} </li>
            </c:forEach>
        </ul>
    </div>

    <div class="btns">
        <div class="button cencle" onclick='choseCustomerCancel();'>取消</div>
        <div class="button" onclick='choseCustomerComplete();' >确定</div>
    </div>
</div>



<!--用车地址选择-->
<div class="charge-page charge-dia" style="display:none;" id="sendCar_address_div">
    <div class="searc-sec searc-sec-nop">
        <div class="searchBox">
            <a href="javascript:;" class="searchBtn"></a>
            <input type="search" placeholder="请输入地点" id="sendCar_input">
            <a href="javascript:sendCar_cancel();" class="cencle-search">取消</a>
        </div>
        <!-- <div id="container"></div> -->
        <div class="btns">
            <div class="button cencle" onclick='sendCarAddressCancel();'>取消</div>
            <div class="button" onclick='sendCarAddressComplete();' id="submitButton">确定</div>
        </div>
    </div>
    <script>
        //地图加载
        var map = new AMap.Map("container", {
            resizeEnable: true
        });
        //输入提示
        var autoOptions = {
            input: "sendCar_input"
        };
        var auto = new AMap.Autocomplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            map: map
        });  //构造地点查询类
        AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
        function select(e) {
            placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name);  //关键字查询查询
        }
    </script>
</div>




<!--还车地址选择-->
<div class="charge-page charge-dia" style="display:none;" id="getCar_address_div">
    <div class="searc-sec searc-sec-nop">
        <div class="searchBox">
            <a href="javascript:;" class="searchBtn"></a>
            <input type="search" placeholder="请输入地点" id="getCar_input">
            <a href="javascript:getCar_cancel();" class="cencle-search">取消</a>
        </div>
        <!-- <div id="container"></div> -->
        <div class="btns">
            <div class="button cencle" onclick='getCarAddressCancel();'>取消</div>
            <div class="button" onclick='getCarAddressComplete();' >确定</div>
        </div>
    </div>
    <script>
        //地图加载
        var map = new AMap.Map("container", {
            resizeEnable: true
        });
        //输入提示
        var autoOptions = {
            input: "getCar_input"
        };
        var auto = new AMap.Autocomplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            map: map
        });  //构造地点查询类
        AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
        function select(e) {
            placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name);  //关键字查询查询
        }
    </script>
</div>


<input type="hidden" id="carOperateId_copy"/>
<input type="hidden" id="carOperateName_copy"/>

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



    //选择管家切换样式
    function choseButler(operaterId,operaterName,obj){
        //交换样式
        $("#choseButler_ul li").removeClass("on");
        $(obj).addClass("on");
        $("#chooseButler").html(operaterName);
        $("#operaterId").val(operaterId);
        $("#operaterName").val(operaterName);
    }
    function showChoseButler(){
        $("#main_div").hide();
        //直接显示
        $("#choseBulter_div").css('display','block');
    }
    //选择管家点击确定
    function choseBulterComplete(){
        $("#modifier_span").empty();
        $("#modifier_span").html($("#operaterName").val());
        //负责管家重新赋值
        $("#choseBulter_div").hide();
        $("#main_div").css("display","block");
    }
    //选择管家点击取消
    function choseBulterCancel(){
        $("#choseBulter_div").hide();
        $("#main_div").css("display","block");
    }



/*--------------------------用车开始-------------------------------*/
    //选择用户车地点
    function showSendCarAddress(){
        $("#main_div").hide();
        $("#sendCar_address_div").css("display","block");
    }
    //用车取消
    function sendCarAddressCancel(){
        $("#sendCar_address_div").hide();
        $("#main_div").css("display","block");
    }
    //用车完成
    function sendCarAddressComplete(){
        $("#sendCarAddress_span").empty();
        $("#sendCarAddress_span").html($("#sendCar_input").val());
        $("#taskSendCarAddress").val($("#sendCar_input").val())
        $("#sendCar_address_div").hide();
        $("#main_div").css("display","block");
    }
/*------------------------------用车完成----------------------------------------*/



/*-------------------------------还车开始--------------------------------------*/
    //选择还车地点
    function showGetCarAddress(){
        $("#main_div").hide();
        $("#getCar_address_div").css("display","block");
    }
    //还车取消
    function getCarAddressCancel(){
        $("#getCar_address_div").hide();
        $("#main_div").css("display","block");
    }
    //还车完成
    function getCarAddressComplete(){
        $("#getCarAddress_span").empty();
        $("#getCarAddress_span").html($("#getCar_input").val());
        $("#taskGetCarAddress").val($("#getCar_input").val())
        $("#getCar_address_div").hide();
        $("#main_div").css("display","block");
    }







/*-------------------------------还车结束--------------------------------------*/




    var customerIdParam = null;
    var customerNameParam = null;
    //选择管家切换样式
    function choseCustomer(customerId,customerName,obj){
        //交换样式
        $("#choseCustomer_ul li").removeClass("on");
        $(obj).addClass("on");
        customerIdParam = customerId;
        customerNameParam = customerName;
    }
    function showCustomer(){
        var status = $("input[name='butlerStatus']:checked").val();
        if(status!=100){
            $.toast("只能在跟进中选择管家");
            return;
        }
        $("#main_div").hide();
        //直接显示
        $("#choseCustomer_div").css('display','block');
    }
    //选择管家点击确定
    function choseCustomerComplete(){
        $("#customer_span").empty();
        $("#customer_span").html(customerNameParam);
        //负责管家重新赋值
        $("#choseCustomer_div").hide();
        $("#main_div").css("display","block");
        $("#chooseCustomer").html(customerNameParam);
        $("#customerId").val(customerIdParam);
        $("#customerName").val(customerNameParam);
    }
    //选择管家点击取消
    function choseCustomerCancel(){
        $("#choseCustomer_div").hide();
        $("#main_div").css("display","block");
    }


</script>