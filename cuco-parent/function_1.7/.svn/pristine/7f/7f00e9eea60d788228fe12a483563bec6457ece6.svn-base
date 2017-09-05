<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<style>

	/*.mask-cartype{*/
		/*position:fixed;*/
		/*width:100%;*/
		/*height:100%;*/
		/*top:0;*/
		/*left:0;*/
	/*}*/
    /*.carType_div {*/
        /*font-family: "黑体";*/
        /*!*width: 100%;*!*/
        /*display: flex;*/
        /*background: #fff;*/
        /*border-top: #ccc 1px solid;*/
        /*border-bottom: #ccc 1px solid;*/
        /*padding: 0.4rem 0;*/
        /*margin-bottom:.5rem;*/

    /*}*/
    /*.carType_p {*/
        /*font-size: 2rem;*/
    /*}*/
    /*.carType_brand {*/
        /*align-items: center;*/
        /*justify-content:center;*/
        /*display: flex;*/
        /*flex: 1;*/
        /*font-size:1.5rem;*/


    /*}*/

    /*.carType_div_ul {*/
        /*flex: 3;*/
        /*border-left: #ccc 1px solid;*/
    /*}*/

    /*.carType_div_li {*/
        /*width: 95%;*/
        /*margin-left:5%;*/
        /*height: 4rem;*/
        /*border-bottom: #ccc 1px solid;*/
        /*line-height: 4rem;*/
        /*font-size: 0.65rem;*/
        /*position: relative;*/

    /*}*/
    /*.car_name{*/
        /*display: inline-block;*/
        /*width: 58%;*/
        /*color: #666;*/
        /*font-size:1.5rem;*/

    /*}*/
    /*.Schedule {*/
        /*font-size: 1.5rem;*/
        /*color: #5ccfcd;*/
        /*border-left: #ccc 1px solid;*/
        /*padding: 0.1rem 0.4rem;*/
    /*}*/
    /*.carType_div_li:last-child{*/
        /*border:none;*/
    /*}*/
    /*.car_succ {*/
        /*background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACUAAAAYCAYAAAB9ejRwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OERBNjRDQUZBNzIxMTFFNkIwNDNCM0Y3RUVGMEU3MEEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OERBNjRDQjBBNzIxMTFFNkIwNDNCM0Y3RUVGMEU3MEEiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4REE2NENBREE3MjExMUU2QjA0M0IzRjdFRUYwRTcwQSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo4REE2NENBRUE3MjExMUU2QjA0M0IzRjdFRUYwRTcwQSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PjneVtkAAAFfSURBVHjazNYxSwNBEIZhcxELEVKJhbFSsEl3fyBGSSq10SaFRYIgdv4ZtTWgjWIhaYSglmIpgoXaaSGpbAMSv4UZGJYN3pxj7g7eKkt4suF2thDH8UROnml0ieaiHIG6qIG+ohyBVtAD2oxyBqpnvVNT6MoHuQ+iDEEXaNUHZYVi0HoIlAVKgh5DIEaV0MyYQU9oLQRi1D16RktjBNVQf9Rih7pGZXT3TzAViFEH6BjNE2zBEFREpxoQo4ZoX8BuaecsQB20pQHJt0/CFmnHygagphbkHwkMO/kjTILe6C3ra77AP6ccrI3OUsJ8UBV9an9V6PD8RjspYCHQe5r/ftSJroWZgX4bMz6sh2YD6wroyAqUZPZJ2DK68WAOdIh2rUBJB7KEVQSMQXuWIPdMJlzHMDe4Nwjmrh0t9EF3axOQBsWwbTHHKgSq0k6ZPdr71IDGxjl6IdCr9QT/EWAA0dJqQbIsrfEAAAAASUVORK5CYII=") left no-repeat;*/
        /*background-size:40%;*/
        /*width: 2rem;*/
        /*height: 1rem;*/
        /*display: inline-block;*/
        /*vertical-align: middle;*/

    /*}*/
    /*.low_stocks{*/
        /*!*float:right;         *!*/

    /*}*/
    /*.low_stocks:after{*/
        /*clear: both;*/
        /*display: block;*/
    /*}*/
    /*.low_stocksm{*/
        /*width:130px;*/
        /*height: 78px;*/
    /*}*/
    /*.low_stocks_p{*/
        /*font-size:0.4rem;*/

        /*!*line-height: .8rem;*!*/
        /*position: absolute;*/
        /*color:#ccc;*/
        /*right:2.2rem;*/
        /*top:-.4rem;*/
    /*}*/
    /*.low_stocks_a{*/
        /*font-size:0.6rem;*/
        /*height: 1.5rem;*/
        /*width:4.5rem;*/
        /*line-height: 1rem;*/
        /*!*padding: 0.2rem;*!*/
        /*border:1px solid #666;*/
        /*border-radius:0.1rem;*/
        /*margin-right: .2rem;*/
        /*position: absolute;*/
        /*right:1.9rem;*/
        /*bottom:-.1rem;*/
        /*display: inline;*/
    /*}*/
    /*.active{*/
        /*color: #ccc;*/
    /*}*/
    /*.show{*/
        /*display: block;*/
    /*}*/
    /*.hide{*/
        /*display: none;*/
    /*}*/




    /*-------------------*/

    .carType{
        width:100%;
        box-sizing: border-box;
        margin-bottom:0.55rem;
    }
    .carType_p{
        /*width:100%;*/
        height:1.45rem;
        line-height: 1.45rem;
        /*margin-left:0.9rem;*/
        font-size:1.5rem;
        color: #666;
        padding: 0 1rem;
    }
    .carType_div {
        font-family: "黑体";
        /*width: 100%;*/
        display: flex;
        background: #fff;
        border-top: #ccc 1px solid;
        border-bottom: #ccc 1px solid;
        padding: 0.4rem 0;
        margin-bottom:.5rem;
    }
    .carType_brand{
        flex:1;
        justify-content: center;
        align-items: center;
        display: flex;
        font-size: 0.6rem;
    }
    .carType_div_ul{
        border-left:1px solid #ccc;
        flex:3;
    }
    .carType_div_li{
        display: flex;
        align-items: center;
        margin-left: 1rem;
        border-bottom: 1px solid #ccc;
        margin-right: .8rem;
        height: 4rem;
    }
    .carType_div_li:last-child{
        border: none;
    }
    .car_name{
        flex:3;
        color: #666;
        font-size: 1.5rem;
    }
    .Schedule{
        flex:2;
        font-size: 1.5rem;
        color: #5ccfcd;
        border-left: #ccc 1px solid;
        padding: 0.1rem 0.4rem;

    }
    .car_succ{
        flex:1;
    }


    /*       */
    .show_succ .car_succ{
        flex:1;
        background:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACUAAAAYCAYAAAB9ejRwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OERBNjRDQUZBNzIxMTFFNkIwNDNCM0Y3RUVGMEU3MEEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OERBNjRDQjBBNzIxMTFFNkIwNDNCM0Y3RUVGMEU3MEEiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4REE2NENBREE3MjExMUU2QjA0M0IzRjdFRUYwRTcwQSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo4REE2NENBRUE3MjExMUU2QjA0M0IzRjdFRUYwRTcwQSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PjneVtkAAAFfSURBVHjazNYxSwNBEIZhcxELEVKJhbFSsEl3fyBGSSq10SaFRYIgdv4ZtTWgjWIhaYSglmIpgoXaaSGpbAMSv4UZGJYN3pxj7g7eKkt4suF2thDH8UROnml0ieaiHIG6qIG+ohyBVtAD2oxyBqpnvVNT6MoHuQ+iDEEXaNUHZYVi0HoIlAVKgh5DIEaV0MyYQU9oLQRi1D16RktjBNVQf9Rih7pGZXT3TzAViFEH6BjNE2zBEFREpxoQo4ZoX8BuaecsQB20pQHJt0/CFmnHygagphbkHwkMO/kjTILe6C3ra77AP6ccrI3OUsJ8UBV9an9V6PD8RjspYCHQe5r/ftSJroWZgX4bMz6sh2YD6wroyAqUZPZJ2DK68WAOdIh2rUBJB7KEVQSMQXuWIPdMJlzHMDe4Nwjmrh0t9EF3axOQBsWwbTHHKgSq0k6ZPdr71IDGxjl6IdCr9QT/EWAA0dJqQbIsrfEAAAAASUVORK5CYII=") right no-repeat;
        background-size:40%;
        background-size:40%;
        width: 2rem;
        height: 1rem;
        display: none;
        display: inline-block;
        vertical-align: middle;
    }

    .low_stocks{
        flex:2;
        font-size: 1.5rem;
    }


    .low_stocks{
        display: none;
    }
    .show_low_stocks .Schedule{
        flex:0;
        display: none;
    }
    .show_low_stocks .low_stocks{
        flex:3;
        font-size: 0.2rem;
        display: block;
    }
    .show_low_stocks .low_stocks_p{
        color: #ccc;
        text-indent: 0.4rem;
    }
    .show_low_stocks .low_stocks_a{
        padding: .1rem .4rem;
        border: 1px solid #666;
        border-radius: 0.2rem;
        display: inline-block;
        color: #ccc;
    }
    .show_low_stocks .car_name{
        color: #ccc;
    }
    .show_low_stocks .car_succ{
        display: none;
    }

</style>
<!--选择分配车辆-->
<!--选择分配车辆-->
<div class="distribution-page" id="selectCarTypeDiv" style="display: none;">
    <!-- <ul class="distribution-tab">
    </ul> -->
        <div class="carType">
            <p class="carType_p"></p>
            <div id="selectCarTypeUl">
                <%--<div class="carType_div">--%>
                    <%--<div class="carType_brand">商务轿车</div>--%>
                    <%--<ul class="carType_div_ul">--%>
                        <%--<li class="carType_div_li">--%>
                            <%--<span class="car_name">宝马X5</span>--%>
                            <%--<a class="Schedule">查看排期</a>--%>
                            <%--<span class="car_succ"></span>--%>
                            <%--<div class="low_stocks hide">--%>
                                <%--<p class="low_stocks_p">库存不足</p>--%>
                                <%--<a class="low_stocks_a">查看排期</a>--%>
                            <%--</div>--%>
                            <%--<!--<img  class="car_correct"/>                    -->--%>
                        <%--</li>--%>
                        <%--<li class="carType_div_li">--%>
                            <%--<span class="car_name">宝马X5</span>--%>
                            <%--<a class="Schedule">查看排期</a>--%>
                            <%--<span class="car_succ"></span>--%>
                            <%--<div class="low_stocks hide">--%>
                                <%--<p class="low_stocks_p">库存不足</p>--%>
                                <%--<a class="low_stocks_a">查看排期</a>--%>
                            <%--</div>--%>
                            <%--<!--<img  class="car_correct"/>                    -->--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</div>--%>


                <%--<div class="carType_div">--%>
                    <%--<div class="carType_brand">商务轿车</div>--%>
                    <%--<ul class="carType_div_ul">--%>
                        <%--<li class="carType_div_li">--%>
                            <%--<span class="car_name active">宝马X5</span>--%>
                            <%--<a class="Schedule hide">查看排期</a>--%>
                            <%--<span class="car_succ hide"></span>--%>
                            <%--<div class="low_stocks">--%>
                                <%--<p class="low_stocks_p">库存不足</p>--%>
                                <%--<a class="low_stocks_a">查看排期</a>--%>
                            <%--</div>--%>

                        <%--</li>--%>

                    <%--</ul>--%>
                <%--</div>--%>
            </div>
        </div>
    <div class="btns">
        <div class="button cencle" onclick='usedCarTypeCancel();'>取消</div>
        <div class="button" onclick='usedCarTypeComplete();'>确定</div>
    </div>
</div>
<input type="hidden" id="carTypeId_input_hidden"/>
<input type="hidden" id="carTypeId_div_hidden"/>
<script type="text/javascript">
    /**
     * 选择车型
     */
    function showUsedCar() {
        var datas = {memberId:'${member.id}'}
        datas["useCarStartTime"] = $("#datetime-picker").val();
        datas["useCarEndTime"] = $("#datetime-picker-2").val();

        var url = "${ctx}/wechat/butler/task/sendCar/getStoreOfCartype";
        $.ajax({
            type: 'POST',
            url: url,
            async:false,
            data:datas,
            datatype:"json",
            //  返回数据处理
            success: function(data){
                if(data.errcode == "ok"){
                    var result = data.result;
                    var selectCarUl = $("#selectCarTypeUl");
                    var div = "";
                    console.log("data.result>>>" + data.result);
                    var ownList = data.result.ownList;
                    var data = null;
                    var type = null;
                    for(var i = 0;i<ownList.length;i++){
                        data = ownList[i].data;
                        type = ownList[i].type;
                        div += "<div class=\"carType_div\">"
                                  +"<div class=\"carType_brand\">"+type+"</div>"
                                  + "<ul class=\"carType_div_ul\">";
                                        for(var j = 0;j<data.length;j++){
                                                     if(data[j].store == true){
                                                         div += "<li class=\"carType_div_li\" onclick=\"clickCarTypeLi('"+data[j].brand+"','"+data[j].carType+"','"+data[j].cartypeId+"',this)\">"
                                                         div+="<span class=\"car_name\">"+data[j].brand+data[j].carType+"</span>";
                                                         div+="<a class=\"Schedule\" onclick=\"seeSchedule('"+data[j].brand+"','"+data[j].carType+"','"+data[j].cartypeId+"',this,true)\">查看排期</a>";
                                                         div+="<span class=\"car_succ\"></span>";
                                                     }else{
                                                         div += "<li class=\"carType_div_li show_low_stocks\" >"
                                                         div+="<span class=\"car_name active\">"+data[j].brand+data[j].carType+ "</span>";
                                                         div+= "<div class=\"low_stocks\">"
                                                                + "<p class=\"low_stocks_p\">库存不足</p>"
                                                                + "<a class=\"low_stocks_a\" onclick=\"seeSchedule('"+data[j].brand+"','"+data[j].carType+"','"+data[j].cartypeId+"',this,false)\">查看排期</a>"
                                                           +  "</div>"
                                                     }
                                                    +  "</li>"
                                        }
                         div+="</ul></div>";
                    }
                    if(div){
                        selectCarUl.html(div);
                    }else{
                        selectCarUl.html("暂无可用车型");
                    }

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
    }

    function selectCarType() {
        var useCarStartTime =  $("#datetime-picker").val();
        var useCarEndTime  = $("#datetime-picker-2").val();
        if(!useCarStartTime){
            alert("请选择用车开始时间");
            return;
        }
        if(!useCarEndTime){
            alert("请选择用车结束时间")
            return;
        }
        useCarStartTime = useCarStartTime.replace(/-/g,"/");
        useCarStartTime = new Date(useCarStartTime);

        useCarEndTime = useCarEndTime.replace(/-/g,"/");
        useCarEndTime = new Date(useCarEndTime);

        if(useCarEndTime <= useCarStartTime){
            alert("用车结束时间不得小于用车开始时间")
            return;
        }
        showUsedCar();
        $("#main_div").hide();
        $("#selectCarTypeDiv").css("display","block");
    }

    /**
     * 取消选择车辆
     */
    function usedCarTypeCancel(){
        $("#main_div").css("display","block");
        $("#selectCarTypeDiv").hide();
    }
    /**
     * 选择车辆点击确定
     */
    function usedCarTypeComplete(){
        usedCarTypeCancel();
        $("#carTypeId_input").val($("#carTypeId_input_hidden").val());
        $("#carTypeId_div").html($("#carTypeId_div_hidden").val());
    }
    function clickCarTypeLi(brand,carType,carTypeId,obj) {
        console.log(brand);
        console.log(carType);
        console.log(carTypeId);

        $("#carTypeId_input_hidden").val(carTypeId);
        $("#carTypeId_div_hidden").val(brand + "   " + carType);
        $(".carType_div_li").removeClass('show_succ')
        $(obj).addClass('show_succ');
//        $(this).next().append("<span class=\"car_succ\"></span>");
        //$(obj).siblings().removeClass("on");
       // $(obj).addClass("on");
    }
    /**
     * 查看排期
     */
    function seeSchedule(brand,carType,carTypeId,obj,isStock) {
        clickCarTypeLi(brand,carType,carTypeId,obj);
        $("#main_div").hide();
        $("#selectCarTypeDiv").hide();
        $("#seeScheduleId").css("display","block");
        $("#completeButton").hide();
        $('span.cell').removeClass('active');
        var  scroll_height=$('.list-block ').height()+$('.list-block').offset().top,
                screen_height=$(window).height(),
             preloader=   $('.infinite-scroll-preloader').height();
        console.warn("scroll-height",scroll_height)
        console.warn("screen_height-height",screen_height)
        console.warn(".infinite-scroll-preloader",preloader)
        if(scroll_height<screen_height){
           // $('.infinite-scroll-preloader').css('margin-top',(screen_height-scroll_height));
        }
        Timer_Scroll.init();
        $("#titleCarType").html($("#carTypeId_div_hidden").val());
        //有库存
        if(isStock){
            $(".title_tip").html("您可以选择有库存的时间");
        }else{
            $(".title_tip").html("您选择的时间段内该车型无库存，您可选择有库存的时间");

        }
    }
</script>
<div style="display: none" id="seeScheduleId">
    <%@ include file="calendarSchedule.jsp"%>
</div>
