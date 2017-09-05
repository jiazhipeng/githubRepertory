var list = null;


function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}

function searchDayStore(crrt_date){
//	 $.ajax({
//         type: "GET",
//         url: "searchDayStore",
//         data:{
//        	 date:crrt_date,
//        	 carTypeId:$('#carTypeId_input_hidden').val()
//         },
//         dataType: "json",
//         success: function(data){
//        	 list = data.result;
//        	  if(data.errcode == "ok"){
//                  list = data.result;
//              }else{
//                  alert(data.errmsg);
//              }
//		 }
//         
//	});
	$.get("searchDayStore",{
		date:crrt_date,
   	    carTypeId:$('#carTypeId_input_hidden').val()
	},function(data) {
		list = data.result;
  	  if(data.errcode == "ok"){
            list = data.result;
        }else{
            alert(data.errmsg);
        }
	 });
	 
	 
	 console.log(list);
	 return list;
}

//searchDayStore('2016/12/23');


Date.prototype.Format || (Date.prototype.Format = function (fmt) { //author: meizz 
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
});

var privateMethod = {
    //判断年份是否为润年
    isLeapYear: function (year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    },
    //获取某一年份的某一月份的天数
    getMonthDays: function (year, month) {
        month--;
        return [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month] || (privateMethod.isLeapYear(year) ? 29 : 28);
    },
    getMonthWeek: function (a, b, c) {
        var date = new Date(a, parseInt(b) - 1, c),
            w = date.getDay(),
            d = date.getDate();
        return Math.ceil(
            (d + 6 - w) / 7
        );
    }
};


var Timer_Scroll = {
    props: {
        loading: false,
        page_index: 0,
        url:"/wechat/carOperate/searchStore"
    },
    init: function () {
        var self=this;
      
        this.bind_event();
        // this.add_date_li(this.props.page_index++,function(){
        //
        // });
        // this.add_date_li(this.props.page_index++);
        this._fetch_date(0);

    },
    
    _fetch_date:function(page_index,callback) {
        var  self = this;
        $.get(
            this.props.url,
            {
                page:page_index,
                carTypeId:$("#carTypeId_input_hidden").val()
            },
            function (response) {
                var result=response;
                if(result.errcode!=='ok')return;
                var data=result.result[0];
                var valid_dates=data.filter(function(item) {
                    return item.flag;
                });
                var dates= valid_dates.map(function(item ) {
                    return  {
                    	val:item.flag,
                    	time:(new Date(item.time*1000)).Format('yyyy/MM/dd')
                    }
                });
                self.add_date_li(page_index,dates, function(obj,crrt){
                	 return dates;
//                    return  dates.indexOf(crrt) >= 0;
                });
                callback&&callback.call(self);
                if(page_index===0){
                   self._fetch_date(1);
                }
            });
    },
    _ADD_MONTH: function (date, val) {
        date.setMonth(date.getMonth() + val);
        return date;
    },
    _ADD_DAY:function(date,val){
     return   new Date(date.getTime() + 24*60*60*1000*val);
    },
    _fn_get_crrt_nxt_month: function (page_index) {
        var crrt_year,
            crrt_month;
        // crrt_nxt_year,
        // crrt_nxt_month;

        crrt_year = this._ADD_MONTH(new Date(), page_index).getFullYear();
        crrt_month = this._ADD_MONTH(new Date(), page_index).getMonth() + 1;
        // crrt_nxt_year = opts.ADD_MONTH(new Date(), 2 * page_index + 1).getFullYear();
        // crrt_nxt_month = opts.ADD_MONTH(new Date(), 2 * page_index + 1).getMonth() + 1;
        return {
            crrt_year: crrt_year,
            crrt_month: crrt_month,
            // crrt_nxt_year: crrt_nxt_year,
            // crrt_nxt_month: crrt_nxt_month
        };
    },
    _loopMonth: function (year, month, datas, callback) {
        var days = privateMethod.getMonthDays(year, month);
        var weeks = privateMethod.getMonthWeek(year, month, days);
        var html_cell = $('<li class="date"></li>');
        var $date_body = $('<section class="date-body"></section>');

        html_cell.append('<h2 class="date-title">' + year + '年' + month + '月</h2>');

        for (var index = 0; index < weeks; index++) {
            var $row = $('<div class="date-r"></div>');

            for (var index_w = 0; index_w < 7; index_w++) {
                var day_crrt = index_w + index * 7;
                var start = new Date([year, month, 1].join('/')).getDay();
                
                
                if ((index === 0 && day_crrt < start) || (index === (weeks - 1) && (day_crrt - start + 1) > days)) {
                    $row.append('<p></p>');
                } else {
                    var crrt_date = new Date([year, month, day_crrt - start + 1].join('/'));
                    var val = ''
                    for(var i = 0; i< datas.length; i++) {
                    	if(datas[i].time == crrt_date.Format('yyyy/MM/dd')) {
                    		 val = datas[i].val;
                    	}
                    }
                    
//                    var val = datas.map(function(item) {
//                    	console.log(crrt_date.Format('yyyy/MM/dd') == item.time)
//                    	if( == item.time) {
//                    		return item.val;
//                    	}
//                    //	return 2;
////                    	return item.val
//                    })
            
                    var now_date = new Date(new Date().Format('yyyy/MM/dd'));
                    var $crrt_cell = $('<p class="cell disabled"></p>');
//                    var flagval = 

                    // if (new Date().getTime() - crrt_date.getTime() > 0) {
                    //     $crrt_cell.addClass('disabled');
                    // }
                    var  $crrt_cell_inner = $('<label></label>');
                    if (crrt_date.Format('yyyy/MM/dd') === (new Date().Format('yyyy/MM/dd'))) {
                        $crrt_cell_inner.html('今天');
                    } else {
                        $crrt_cell_inner.html(day_crrt - start + 1);
                    }

                    $crrt_cell.attr('data-val',crrt_date.Format('yyyy/MM/dd'));
                       // .data('val', crrt_date.Format('yyyy/MM/dd'));

                    if (now_date.getTime() - crrt_date.getTime() <= 0) { //今天之后
                        if (callback && callback.call(this, $crrt_cell, crrt_date.Format('yyyy/MM/dd'))) {
                        	$crrt_cell.removeClass('disabled');
                        	if(val == 1){
                        		$crrt_cell.append("<i>有</i>");
                        	}else if(val == 2){
                        		 $crrt_cell.addClass('disabled');
                        	}else{
                        		$crrt_cell.append("<i style='color:red'>限</i>");
                        	}
                        	var prev = $crrt_cell_inner.html();
                          //  $crrt_cell.html(prev + '<i>有</i>');
                            //$crrt_cell.append('<i>有</i>');
                            
                        }
                    }
                    $crrt_cell.append($crrt_cell_inner);
                    $row.append($crrt_cell);

                }

            }
            //$row.append(cells);
            $date_body.append($row);
        }
        html_cell.append($date_body);
        return html_cell;
    },
    add_date_li: function (page_index, dates, callback) {
        var crrt_year_month = this._fn_get_crrt_nxt_month(page_index);

        var content_htmtl = this._loopMonth(crrt_year_month.crrt_year, crrt_year_month.crrt_month,dates, callback);

        $('.infinite-scroll-bottom .list-container').append(content_htmtl);
    },
    cell_click: function (obj, crrt_date) {
 /*   	list = searchDayStore(crrt_date);
    	console.log('fuck you');
    	console.log(crrt_date);
    	console.log(list);
    	if(list != null){
    		console.log('111');
    		
    		for(var i = 0;i<list.length;i++){
    			var temp = "";
    	   		 console.log(list[i]);
    	   		console.log(list.length);
    	   		 var obj = list[i];
    	   		for(var e in obj){//用javascript的for/in循环遍历对象的属性 
    	   			temp += obj[e]; 
    	   		} 
    	   		 console.log(temp);
    	   		 $('#allCenter').append(temp+"<br/>");
            }

    	   	if(!$("#all").hasClass('zhezhao')){
    	   		$("#all").attr("class", "zhezhao"); 
    	   	}
    	}*/
   	 
    },
    bind_event: function () {
        var self = this;
        $('.list-container').on('click', '.list-container .cell', function () {
            self.cell_click.call(this, this, $(this).data('val'));
        });
        $(document).on('infinite', '.infinite-scroll-bottom',this._bind.bind(this) );
    },
    _bind:function () {
        var self=this;
        $(document).off('infinite' );
        self.props.page_index = $('.list-container li').length;
        var
            page_index = self.props.page_index;
        self._fetch_date(page_index,function () {

            if (page_index >= 10) {
                // 加载完毕，则注销无限加载事件，以防不必要的加载
                $.detachInfiniteScroll($('.infinite-scroll'));
                // 删除加载提示符
                $('.infinite-scroll-preloader').remove();
                return;
            }
            // 更新最后加载的序号
            self.props.page_index = $('.list-container li').length;
            //容器发生改变,如果是js滚动，需要刷新滚动

            $.refreshScroller();

            $(document).on('infinite', '.infinite-scroll-bottom',self._bind .bind(self));
        });
    }
};