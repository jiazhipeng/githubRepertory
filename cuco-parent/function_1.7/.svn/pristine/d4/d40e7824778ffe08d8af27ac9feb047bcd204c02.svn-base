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
        url:basePath + "/wechat/carOperate/searchStore"
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

    _fetch_date:function(page_index,callback){
        var  self=this;
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
                console.log("data>>>"+JSON.stringify(data));
                var  valid_dates=data.filter(function(item){
                    return item.flag;
                });
                var  dates=  valid_dates.map(function(item ){

                    return  (new Date(item.time*1000)).Format('yyyy/MM/dd');
                });
                self.add_date_li(page_index,function(obj,crrt){
                    return   dates.indexOf(crrt)>=0;
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
    _loopMonth: function (year, month, callback) {
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
                    var now_date = new Date(new Date().Format('yyyy/MM/dd'));
                    var $crrt_cell = $('<p class="cell disabled"></p>');

                    // if (new Date().getTime() - crrt_date.getTime() > 0) {
                    //     $crrt_cell.addClass('disabled');
                    // }
                    var  $crrt_cell_inner=$('<label></label>');
                    if (crrt_date.Format('yyyy/MM/dd') === (new Date().Format('yyyy/MM/dd'))) {
                        $crrt_cell_inner.html('今天');
                    } else {
                        $crrt_cell_inner.html(day_crrt - start + 1);
                    }

                    $crrt_cell.attr('data-val',crrt_date.Format('yyyy/MM/dd'));
                       // .data('val', crrt_date.Format('yyyy/MM/dd'));

                    if (now_date.getTime() - crrt_date.getTime() <= 0) { //今天之后
                        if (callback && callback.call(this, $crrt_cell, crrt_date.Format('yyyy/MM/dd'))) {
                            var prev = $crrt_cell_inner.html();
                          //  $crrt_cell.html(prev + '<i>有</i>');
                            $crrt_cell.append('<i>有</i>');
                            $crrt_cell.removeClass('disabled');
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
    add_date_li: function (page_index, callback) {
        var crrt_year_month = this._fn_get_crrt_nxt_month(page_index);

        var content_htmtl = this._loopMonth(crrt_year_month.crrt_year, crrt_year_month.crrt_month, callback);

        $('.infinite-scroll-bottom .list-container').append(content_htmtl);
    },
    cell_click: function (obj, crrt_date) {
        var isDisabled=$(obj).hasClass('disabled');
        if(isDisabled){
           // $.toast('操作失敗...');
        }else{
            //$.toast('操作成功...');
            if(!$.trim($("#startDate").val())){
                $("#startDate").val(crrt_date);
            }else if(!$.trim($("#endDate").val())){
                if($.trim($("#startDate").val())){
                    var startDate = new Date($("#startDate").val());
                    if(startDate.getTime() >= new Date(crrt_date).getTime()){
                        $("#startDate").val(crrt_date);
                        $("#picker-name").picker("open");
                        return;
                    }
                }

                for(var i=0 ,item;item=Timer_Scroll._ADD_DAY(new Date($("#startDate").val()),i++);){

                    if($('p[data-val="'+item.Format('yyyy/MM/dd')+'"]').hasClass('disabled')){
                        $.toast(('请选择有库存的时间区域'));
                        return;
                    }
                    //console.warn(item.Format('yyyy/MM/dd'),$(endDate).val())
                    if(item.Format('yyyy/MM/dd')===crrt_date){
                        //console.error(item.Format('yyyy/MM/dd'))
                        break;
                    }
                }


                $("#endDate").val(crrt_date);
            }else if($.trim($("#startDate").val()) && $.trim($("#endDate").val())){
                $("#startDate").val(crrt_date);
                $("#endDate").val("");
            }
            //弹出时间选择框
            $("#picker-name").picker({
                toolbarTemplate: $('#toolbar').html(),
                cols: [
                    {
                        textAlign: 'center',
                        values: timeArray
                        //如果你希望显示文案和实际值不同，可以在这里加一个displayValues: [.....]
                    },
                    {
                        textAlign: 'center',
                        values: ["00", "15", "30", "45"]
                    }
                ]
            });
            $("#picker-name").picker("open");
        }
    },
    bind_event: function () {
        var self = this;
        $('.list-container').on('click', '.list-container .cell', function () {
            self.cell_click.call(this, this, $(this).data('val'));
        });
        $(document).on('infinite', '.infinite-scroll-bottom',this._bind.bind(this) );
    },
    _bind:function () {
        console.warn(1)
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

        /*setTimeout(function () {
         // 重置加载flag
         loading = false;
         if (page_index >= 10) {
         // 加载完毕，则注销无限加载事件，以防不必要的加载
         $.detachInfiniteScroll($('.infinite-scroll'));
         // 删除加载提示符
         $('.infinite-scroll-preloader').remove();
         return;
         }

         self.add_date_li(page_index, function () {
         return true;
         });

         // 更新最后加载的序号
         self.props.page_index = $('.list-container li').length;

         //容器发生改变,如果是js滚动，需要刷新滚动

         $.refreshScroller();
         }, 1000);*/
    }
};




$(document).on("click", ".close-picker", function() {
    var time  = $("#picker-name").val();
    if(!$.trim(time)){
        return;
    }
    time = time.replace(" ",":");
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();

    if(!$.trim(startTime)){
        var now = new Date();
        if(now.getTime() > new Date(startDate + " "+ time).getTime()){
            $.toast('用车发起时间不得小于当前时间');
            return;
        }
        $("#startTime").val(startDate + " "+ time);
        startTime = $("#startTime").val();
        dateSelected(startDate,true);
        $("#completeButton").hide();
        //$('p.cell').removeClass('active');
    }else if(!$.trim(endTime)){
        if(!$.trim(endDate) && $.trim(startDate)){
            var now = new Date();
            if(now.getTime() > new Date(startDate + " "+ time).getTime()){
                $.toast('用车发起时间不得小于当前时间');
                return;
            }
            $("#startTime").val(startDate + " "+ time);
            $('p.cell').removeClass('active');
            dateSelected(startDate,true);
            return;
        }
        //
       // endDate
      //  startDate
        $("#endTime").val(endDate + " "+ time);

        /*endTime = $("#endTime").val();
        startTime = $("#startTime").val();
        endTime = new Date(endTime);
        startTime = new Date(startTime);
        var carTypeId = $("#carTypeId_input_hidden").val();
        var data = {"carTypeId":carTypeId,"planTime":startTime,"completeTime":endTime};
        $.ajax({
            type: 'POST',
            url: basePath + "/wechat/butler/task/sendCar/isStore",
            async:false,
            data:data,
            datatype:"json",
            //  返回数据处理
            success: function(data){
                if(data.errcode == "error"){
                    $.toast(data.errmsg);
                    return ;
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });*/
        $("#completeButton").show();
        //$('p.cell').removeClass('active');
        var offset = dateSelected(endDate,false);
        for(var i=0 ,item;item=Timer_Scroll._ADD_DAY(new Date($("#startDate").val()),i++);){

            var  obj=  $('p[data-val="'+item.Format('yyyy/MM/dd')+'"]');
            obj.addClass('active');
            if(item.Format('yyyy/MM/dd')===endDate){
                //console.error(item.Format('yyyy/MM/dd'))
                break;
            }
        }
        //completeButton();
    }else if($.trim(startTime) && $.trim(endTime)){
        startDate = $("#startDate").val();
        $("#startTime").val(startDate + " "+ time);
        startTime = $("#startTime").val();
        $("#endTime").val("");
        $("#completeButton").hide();

        $('p.cell').removeClass('active');
        $('p.cell').find('i.state').remove();
        dateSelected(startDate,true);
    }

    var pickerToClose = $('.picker-modal.modal-in');
    $.closeModal(pickerToClose);
});

function completeButton(){
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();

    $("#main_div").css("display","block");
    $("#selectCarTypeDiv").hide();
    $("#seeScheduleId").hide();

    $("#datetime-picker").val(startTime.replace(/\//g,"-"));
    $("#datetime-picker-2").val(endTime.replace(/\//g,"-"));
    $("#carTypeId_input").val($("#carTypeId_input_hidden").val());
    $("#carTypeId_div").html($("#carTypeId_div_hidden").val());
}
function dateSelected(time,isTooltip) {
    var  $cell=$('p[data-val="'+time+'"]');
    var offset = $cell.offset(),
        block_offset = $('.list-block').offset(),
        container_offset=$('.list-container')
            .offset(),
        tooltip=$('.tooltip')
            .offset();
    $cell.append('<i class="state">'+(isTooltip?'用':'送')+'</i>').addClass('active');
    if(isTooltip){
        $('.tooltip').css({
            top: offset.top-block_offset.top-$cell.height()+tooltip.height/2,
            left: offset.left-container_offset.left-$cell.width()/2,
            // top:$cell.offset().top-block_offset.top-$cell.height(),
            // left:$cell.offset().left-$cell.width()/(2/3)
        }).show();
       // resolveCharacterDate(,)
        $("#carDateDiv").html(new Date($("#startTime").val()).format("MM月dd日 hh:mm")+" 用车");
    }else{
        $('.tooltip').css({
            top: offset.top-block_offset.top-30,
            left: offset.left-block_offset.left+5,
        }).hide();
    }
}