<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1, user-scalable=no">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="email=no" name="format-detection">
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <title>权益车型</title>
    <link href="${ctx}/static/gcar_html/css/normalize.css" rel="stylesheet" type="text/css" />
    <style>
    .carType_div {
            
            /*width: 100%;*/
            display: flex;
            background: #fff;
            border-top: #ccc 1px solid;
            border-bottom: #ccc 1px solid;
            padding: 0.4rem 0;
            margin-bottom:.5rem;
           
        }
        .carType_p{
		  /*width:100%;*/ 
		  font-size:0.8rem;
		  color: #666; 
		  text-indent:0.7rem;
		  margin-bottom:0.5rem;
		}
        .carType_brand {
		    align-items: center;
		    /* justify-content: center; */
		    text-indent: 0.7rem;
		    display: flex;
		    flex: 1;
		    font-size: 0.8rem;
		}
        
        .carType_div_ul {
            flex: 3;   
            border-left: #ccc 1px solid;        

        }
        
        .carType_div_li {
            width: 95%;
            margin-left:5%;
            height: 2rem;
            border-bottom: #ccc 1px solid;
            line-height: 2rem;
            font-size: 0.8rem;
            position: relative;
           
        }
        .car_name{
            display: inline-block;
            width: 58%;
            color: #666;
            font-size: 0.8rem;    
            float:left; 
           	white-space: nowrap;
    		overflow: hidden;
   			text-overflow: ellipsis;
}       
        }
        .Schedule {
            font-size: 0.8rem;
            color: #5ccfcd;
            border-left: #ccc 1px solid;
            padding: 0.1rem 0.4rem;
        }
        .carType_div_li:last-child{
            border:none;
        }
        .car_succ {
            background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACUAAAAYCAYAAAB9ejRwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OERBNjRDQUZBNzIxMTFFNkIwNDNCM0Y3RUVGMEU3MEEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OERBNjRDQjBBNzIxMTFFNkIwNDNCM0Y3RUVGMEU3MEEiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4REE2NENBREE3MjExMUU2QjA0M0IzRjdFRUYwRTcwQSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo4REE2NENBRUE3MjExMUU2QjA0M0IzRjdFRUYwRTcwQSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PjneVtkAAAFfSURBVHjazNYxSwNBEIZhcxELEVKJhbFSsEl3fyBGSSq10SaFRYIgdv4ZtTWgjWIhaYSglmIpgoXaaSGpbAMSv4UZGJYN3pxj7g7eKkt4suF2thDH8UROnml0ieaiHIG6qIG+ohyBVtAD2oxyBqpnvVNT6MoHuQ+iDEEXaNUHZYVi0HoIlAVKgh5DIEaV0MyYQU9oLQRi1D16RktjBNVQf9Rih7pGZXT3TzAViFEH6BjNE2zBEFREpxoQo4ZoX8BuaecsQB20pQHJt0/CFmnHygagphbkHwkMO/kjTILe6C3ra77AP6ccrI3OUsJ8UBV9an9V6PD8RjspYCHQe5r/ftSJroWZgX4bMz6sh2YD6wroyAqUZPZJ2DK68WAOdIh2rUBJB7KEVQSMQXuWIPdMJlzHMDe4Nwjmrh0t9EF3axOQBsWwbTHHKgSq0k6ZPdr71IDGxjl6IdCr9QT/EWAA0dJqQbIsrfEAAAAASUVORK5CYII=") left no-repeat;
            background-size:40%;
            width: 2rem;
            height: 1rem;
            display: inline-block;
            vertical-align: middle;
           
        }
        .low_stocks{
            float:left;   
           margin-left:3.2rem;
        }
        .low_stocks:after{
            clear: both;
            display: block;
        }
        .low_stocksm{
            width:130px;
            height: 78px;
        }
        .low_stocks_p{
            font-size:0.6rem;
            /*line-height: .8rem;*/
            position: absolute;
            color:#ccc;
            right:2.2rem;
            top:-.4rem;
        }
        .low_stocks_a{
            font-size:0.6rem;
            height: 1rem;
            width:2.5rem;
            line-height: 2.1rem;
            padding: 0.2rem;
            border:1px solid #666;
            border-radius:0.1rem;
            margin-right: .2rem;
           /* position: absolute;
            right:1.9rem;
            bottom:-.1rem;
            display: inline;*/
        }
        .active{
            color: #ccc;
        }
        .show{
            display: block;
        }
        .hide{
            display: none;
        }
    </style>
</head>

<body style="background: #eeeeee">
    <div class="carType">
    	<c:forEach items="${ownList }" var="item">
	        <div class="carType_div">
	            <div class="carType_brand">${item.type }</div>
	            <ul class="carType_div_ul">
	            	<c:forEach items="${item.data }" var="carT">
		                <li class="carType_div_li">
		                    <span class="car_name">${carT.brand } ${carT.carType }</span>
		                </li>
	                </c:forEach>    
	            </ul>
	        </div>
        </c:forEach>
   	<c:if test="${not empty notOwnList }">     
    <p class="carType_p">未解锁车型</p>
    <c:forEach items="${notOwnList }" var="item">
	        <div class="carType_div">
	            <div class="carType_brand">${item.type }</div>
	            <ul class="carType_div_ul">
	            	<c:forEach items="${item.data }" var="carT">
		                <li class="carType_div_li">
		                    <span class="car_name">${carT.brand } ${carT.carType }</span>
		                    <div class="low_stocks" onclick="upgrade()">
                        	<a class="low_stocks_a">升级</a>
                   			</div> 
		                </li>
	                </c:forEach>    
	            </ul>
	        </div>
        </c:forEach> 
      </c:if>
    </div>
</body>

<script type="text/javascript">
function upgrade(){
	window.location.href="${power_app}";
}

</script>
</html>