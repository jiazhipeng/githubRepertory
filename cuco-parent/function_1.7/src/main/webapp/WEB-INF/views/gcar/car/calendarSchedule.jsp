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

        <!--时间插件  -->
    <link href="${ctx}/static/gcar_html/css/gjd/date/demos.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/datescroll.css">
     <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css" rel="stylesheet" type="text/css" />
    
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
            top: 0rem;
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
            top: 0rem;
            width: 100%;
        }
        .content.infinite-scroll {
		    top: 3rem !important;
		}
		
		
			.zhezhao 
			{ 
			 width:100%; 
			 height:100%; 
			 background: #000; 
			 filter:alpha(opacity=50); 
			 -moz-opacity:0.5; 
			 opacity:0.5; 
			 position:absolute; 
			 left:0px; 
			 top:0px; 
			 z-index: 9999;
			 margin: 0px;
   			 padding: 0px;
			} 
			
			
		
</style> 
</head>
<body>
<a onclick="cacleallClass()"><div id="all">
	<center id="allCenter" style="margin:30% 0 0 1%; color:#FFFFFF;">
		
	</center>
</div></a>
<div class="page-group">

  
    <div class="page page-current">
       
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
            <input type="hidden" value="${carTypeId}" id="carTypeId_input_hidden">
                <div class="tooltip top" role="tooltip">
                    <div class="tooltip-arrow"></div>
                    <div class="tooltip-inner">
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
    </header>
</script>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
 <script>$.init()</script> 

<script src="${ctx}/static/gcar_html/js/schedule/datescroll.js"></script>
    
     
<script>
	function cacleallClass(){
		 $("#all").attr("class", "");
		 $('#allCenter').html('');
	}

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
		Timer_Scroll.init()
</script>



<input type="hidden" id="startDate"/>
<input type="hidden" id="startTime"/>
<input type="hidden" id="endDate"/>
<input type="hidden" id="endTime"/>
<input id="picker-name" type="hidden"/>
</body>
</html>