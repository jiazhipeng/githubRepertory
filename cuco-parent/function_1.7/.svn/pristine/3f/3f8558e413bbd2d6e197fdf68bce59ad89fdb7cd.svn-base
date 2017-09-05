
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
            CARPLATENUM: null,
        },
        init: function () {
        	var _this = this;
        	this.props.CARPLATENUM = $(".car-name").data('val');
            this.props.YEAR = util.YEAR();
            this.props.MONTH = util.MONTH();
            this.props.DATE = new Date([this.props.YEAR, this.props.MONTH, 1].join('/'));
            var loginId = $(".loginId").data('val');
            //设置显示 年月
            this.date_placeholder();
            //绑定前后tap事件
            this.bind_tap_prev_nxt();
            var carOperateId = $(".car-id").data('val');
            //init  日历
            $('.car-calendar').plugin({
                year: this.props.YEAR,
                month: this.props.MONTH,
                type: 'car_state',
                onCellClick: function (val) {
                	console.log(val);
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
                	window.location.href='getCarById?dateStr='+val+'&type='+_this.props.CARPLATENUM+'&id='+carOperateId+'&loginId='+loginId;
                    console.log(val)
                }
            });
            this._fetch_car_type(this.props.DATE.Format('yyyy/MM/dd'),this.props.CARPLATENUM );
            /*$.fn.plugin.lookup[1].setState([
                { date: '2016/09/19', count: 1 },
                { date: '2016/09/20', count: 2 },
                { date: '2016/09/22', count: 3 },
                { date: '2016/09/24', count: 4 }
            ]);*/
        },
        
        _fetch_car_type:function(date,carPlateNum){
        	$.ajax({   
				type: "get",     
				url: '/wechat/carOperate/get',     
				dataType: "json", 
				data: {
					carPlateNum: carPlateNum,
					dateStr: date
				},
				success: function (data) {
					$.fn.plugin.lookup[1].setState(JSON.parse(data));
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
            $('.prev').on('click', function () {
                _this.props.DATE = util.ADD_MONTH(_this.props.DATE, -1);
                _this.props.YEAR = util.YEAR(_this.props.DATE);
                _this.props.MONTH = util.MONTH(_this.props.DATE);
                _this.date_placeholder();
                for(var item in  $.fn.plugin.lookup){
                	var crrt=$.fn.plugin.lookup[item];
                	if(typeof crrt ==='number')continue;
                	crrt.prev(_this.props.YEAR, _this.props.MONTH);
                }
               // $.fn.plugin.lookup[1].prev(_this.props.YEAR, _this.props.MONTH);
                _this._fetch_car_type(_this.props.DATE.Format('yyyy/MM/dd'),_this.props.CARPLATENUM);
            });
            $('.nxt').on('click', function () {
                _this.props.DATE = util.ADD_MONTH(_this.props.DATE, 1);
                _this.props.YEAR = util.YEAR(_this.props.DATE);
                _this.props.MONTH = util.MONTH(_this.props.DATE);
                _this.date_placeholder();
                for(var item in  $.fn.plugin.lookup){
                	var crrt=$.fn.plugin.lookup[item];
                	if(typeof crrt ==='number')continue;
                	crrt.nxt(_this.props.YEAR, _this.props.MONTH);
                }
               // $.fn.plugin.lookup[1].nxt(_this.props.YEAR, _this.props.MONTH);
                _this._fetch_car_type(_this.props.DATE.Format('yyyy/MM/dd'),_this.props.CARPLATENUM);
            })
        }
    };

    CarMgr.init();

} ($, window));