<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>demo</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/datescroll.css">
    <style type="text/css">
        /*.infinite-scroll-preloader {*/
        /*margin-top: -20px;*/
        /*}*/
        .title_tip{
            width: 100%;
            height: 1rem;
            color: #86bfbe;
            font-size: 1rem;
            position: absolute;
            top: 3.5rem;
            text-align: center;
        }
        .date-label {
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            background: #191919;
            color: #fff;
            height: 3.5rem;
            position: absolute;
            top: 5rem;
            width: 100%;
        }
    </style>
</head>
<body>


<div class="page-group">

    <a href="" class="button"   id="completeButton" onclick="completeButton();">确定</a>
    <div class="page page-current">
        <header class="bar bar-nav">
            <h1 class="title" id="titleCarType">标题</h1>
        </header>
        <p class="title_tip">您选择的用车时间段内该车型无库存，您可选择有库存的时间</p>
        <div class="date-label">
            <i>日</i>
            <i>一</i>
            <i>二</i>
            <i>三</i>
            <i>四</i>
            <i>五</i>
            <i>六</i>
        </div>

        <div class="content infinite-scroll infinite-scroll-bottom" data-distance="50">
            <div class="list-block">
                <div class="tooltip top" role="tooltip">
                    <div class="tooltip-arrow"></div>
                    <div class="tooltip-inner">
                        请选择还车日期<br/>
                        <span id="carDateDiv"></span>
                    </div>
                </div>
                <ul class="list-container">

                </ul>
            </div>
            <!-- 加载提示符 -->
            <div class="infinite-scroll-preloader">
                <div class="preloader"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/template" id="toolbar">
    <header class="bar bar-nav">
        <button class="button button-link pull-right close-picker btn-picker-sure">确定</button>
        <h1 class="title">请选择时间</h1>
    </header>
</script>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script>$.init()</script>
<script>
    var timeArray = new Array();
    $.ajax({
        type: 'POST',
        url: "${ctx}/wechat/butler/task/sendCar/getServiceTime",
        async:false,
        //data:null,
        datatype:"json",
        //  返回数据处理
        success: function(data){
            if(data.errcode == "ok"){
                var result = data.result;
                if(result){
                    var serviceTimeEnd = parseInt(result.serviceTimeEnd);
                    var serviceTimeStart = parseInt(result.serviceTimeStart);
                }
                 var j = 0;
                for(var i = serviceTimeStart;i<serviceTimeEnd;i++){
                    if(i<10){
                        timeArray[j] = "0"+i + "";
                    }else{
                        timeArray[j] = i+"";
                    }
                    j++;
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
</script>

<script src="${ctx}/static/gcar_html/js/sendCar/datescroll.js"></script>

<input type="hidden" id="startDate"/>
<input type="hidden" id="startTime"/>
<input type="hidden" id="endDate"/>
<input type="hidden" id="endTime"/>
<input id="picker-name" type="hidden"/>
</body>
</html>