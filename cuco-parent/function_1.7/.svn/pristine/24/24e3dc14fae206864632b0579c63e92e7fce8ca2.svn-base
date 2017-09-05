
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

(function ($, window) {	

    var util = {
        YEAR: function (date) {
            return (date || new Date()).getFullYear();
        },
        MONTH: function (date) {
            return (date || new Date()).getMonth() + 1;
        },
        DAY: function (date) {
            return (date || new Date()).getDate();
        },
        format_yyyyMM: function (date) {
            return (date || new Date()).Format('yyyy年MM月')
        },
        ADD_MONTH: function (date, val) {
            date.setMonth(date.getMonth() + val);
            return date;
        }
    };

    var CarMgr = {
        props: {
            YEAR: null,
            MONTH: null,
            DATE: null,
            type:1
        },
        init: function () {
            this.props.YEAR = util.YEAR();
            this.props.MONTH = util.MONTH();
            this.props.DATE = new Date([this.props.YEAR, this.props.MONTH, 1].join('/'));

            //设置显示 年月
            this.date_placeholder();
            //绑定前后tap事件
            this.bind_tap_prev_nxt();
            //nav
            this.nav_init();
            //如果查询了管家的情况
            var changeButlerId = $("#changeButlerId").val();
            var butlerId = $("#loginId").val();
            if(null!=changeButlerId && ""!=changeButlerId){
            	this.nav_active(2);
            	this.props.type =2;
            	butlerId = changeButlerId;
            }
            //init  日历
	            $('.page').eq(0).plugin({
                year: this.props.YEAR,
                month: this.props.MONTH,
                type:'fetch_car',
                onCellClick: function (val) {
                    console.log('1');
                  //判断日期 小于当前时间
              	   var date = new Date(val.replace(/-/g, "/"));
              	   var now = new Date().Format("yyyy/MM/dd");
              	   var nowDate = new Date(now.replace(/-/g, "/"));
              	   console.log(date);
              	   console.log(nowDate);
 	             	if(nowDate - date > 0){
 	             		console.log('之前日期不可点击');
 	             		return false;
 	             	}
                    var loginId = $("#loginId").val();
                    window.location.href=basePath+"/wechat/butlerTask/getCar/toButlerTaskList?loginId="+loginId+"&queryTime="+val+"&butlerId=";
                }
            });
	            
	            $('.page').eq(1).plugin({
	                year: this.props.YEAR,
	                month: this.props.MONTH,
	                type:'fetch_car',
	                onCellClick: function (val) {
	                    console.log('2');
	                  //判断日期 小于当前时间
	               	   var date = new Date(val.replace(/-/g, "/"));
	               	   var now = new Date().Format("yyyy/MM/dd");
	               	   var nowDate = new Date(now.replace(/-/g, "/"));
	               	   console.log(date);
	               	   console.log(nowDate);
	  	             	if(nowDate - date > 0){
	  	             		console.log('之前日期不可点击');
	  	             		return false;
	  	             	}
	                    var loginId = $("#loginId").val();
	                    window.location.href=basePath+"/wechat/butlerTask/getCar/toButlerTaskList?loginId="+loginId+"&queryTime="+val+"&butlerId="+butlerId;
	                }
	            });
	            $('.pages .page').eq(1).find('.butler-name').remove();
            	$('.pages .page').eq(1).prepend('<p class="butler-name">'+$("#butlerName").val()+'</p>');
	           /* $.fn.plugin.lookup[1].setCars([
	                { date: '2016/9/8', val: 8 },
	                { date: '2016/9/15', val: 17 },
	                { date: '2016/9/20', val: 8 },
	                { date: '2016/9/22', val: 5 }
	            ]);*/
            
            this._fetch_car_count(this.props.DATE.Format('yyyy/MM/dd'),this.props.type);
          
          
        },
        _fetch_car_count:function(date,type){
        	var datafile;
        	var loginId = $("#loginId").val();
        	var changeButlerId = $("#changeButlerId").val();
        	//alert(type);
        	if(2===type){
        		//当前管家的情况
        		datafile = {
					queryTime: date,
					loginId:loginId
				}
        		if(null!=changeButlerId && ""!=changeButlerId){
        			datafile = {
        					queryTime: date,
        					loginId:changeButlerId
        				}
        		}
        	}else{
        		//概况
        		datafile = {
    					queryTime: date
    				}
        	}
        	$.ajax({   
				type: "get",     
				url: '/wechat/butlerTask/getCar/getButlerCalendar',     
				dataType: "json", 
				data: datafile,
				success: function (data) {
					$.fn.plugin.lookup[type].fetchCars(JSON.parse(data));
				}   
			})
        },
        _format_date: function (year, month) {
            var date = this.props.DATE;
            return util.format_yyyyMM(date);
        },
        date_placeholder: function () {
            $('.date').html(this._format_date());
        },
        bind_tap_prev_nxt: function () {
        	 var _this = this;
             $('.date-wrapper .prev').on('click', function () {
                 _this.props.DATE = util.ADD_MONTH(_this.props.DATE, -1);
                 _this.props.YEAR = util.YEAR(_this.props.DATE);
                 _this.props.MONTH = util.MONTH(_this.props.DATE);
                 _this.date_placeholder();
                 //$.fn.plugin.lookup[_this.props.type].prev(_this.props.YEAR, _this.props.MONTH);
                 for(var item in  $.fn.plugin.lookup){
                 	var crrt=$.fn.plugin.lookup[item];
                 	if(typeof crrt ==='number')continue;
                 	crrt.prev(_this.props.YEAR, _this.props.MONTH);
                 }
                 _this._fetch_car_count(_this.props.DATE.Format('yyyy/MM/dd'),_this.props.type);
             });
             $('.date-wrapper .nxt').on('click', function () {
                 _this.props.DATE = util.ADD_MONTH(_this.props.DATE, 1);
                 _this.props.YEAR = util.YEAR(_this.props.DATE);
                 _this.props.MONTH = util.MONTH(_this.props.DATE);
                 _this.date_placeholder();
                 //$.fn.plugin.lookup[_this.props.type].nxt(_this.props.YEAR, _this.props.MONTH);
                 for(var item in  $.fn.plugin.lookup){
                 	var crrt=$.fn.plugin.lookup[item];
                 	if(typeof crrt ==='number')continue;
                 	crrt.nxt(_this.props.YEAR, _this.props.MONTH);
                 }
                 _this._fetch_car_count(_this.props.DATE.Format('yyyy/MM/dd'),_this.props.type);
             })

        },
        nav_init: function () {
        	var _this=this;
            $('.nav .nav-item')
                .map(function (i) {
                    $(this).data('index', i);
                    return this;
                })
                .on('click',function () {
                	//点击将之前的搜索记录清空
                	$("#changeButlerId").val("");
                    $(this).addClass('active').siblings().removeClass('active');
                    $('.pages .page').eq($(this).data('index')).show().siblings().hide();
                    _this.props.type=$(this).data('index')+1;
                    console.log(_this.props.type);
                    _this._fetch_car_count(_this.props.DATE.Format('yyyy/MM/dd'),_this.props.type);
                });
        },
        nav_active:function(index){
        	 index--;
        	 $('.nav .nav-item').eq(index).addClass('active').siblings().removeClass('active');
        	 $('.pages .page').eq(index).show().siblings().hide();
        }
    };

    CarMgr.init();

} (Zepto, window));