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
    init: function () {
        this.bind_event();
        this.add_date_li(this.props.page_index++);
        this.add_date_li(this.props.page_index++);
    },
    props: {
        loading: false,
        page_index: 0
    },
    _ADD_MONTH: function (date, val) {
        date.setMonth(date.getMonth() + val);
        return date;
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
            var $row = $('<div class="date-r"></div');

            for (var index_w = 0; index_w < 7; index_w++) {
                var day_crrt = index_w + index * 7;
                var start = new Date([year, month, 1].join('/')).getDay();

                if ((index === 0 && day_crrt < start) || (index === (weeks - 1) && (day_crrt - start + 1) > days)) {
                    $row.append('<span></span>');
                } else {
                    var crrt_date = new Date([year, month, day_crrt - start + 1].join('/'));
                    var now_date = new Date(new Date().Format('yyyy/MM/dd'));
                    var $crrt_cell = $('<span class="cell disabled"></span>');

                    // if (new Date().getTime() - crrt_date.getTime() > 0) {
                    //     $crrt_cell.addClass('disabled');
                    // }

                    if (crrt_date.Format('yyyy/MM/dd') === (new Date().Format('yyyy/MM/dd'))) {
                        $crrt_cell.html('今天');
                    } else {
                        $crrt_cell.html(day_crrt - start + 1);
                    }

                    $crrt_cell
                        .data('val', crrt_date.Format('yyyy/MM/dd'));

                    if (now_date.getTime() - crrt_date.getTime() <= 0) { //今天之后
                        if (callback && callback.call(this, $crrt_cell, crrt_date.Format('yyyy/MM/dd'))) {
                            var prev = $crrt_cell.html();
                            $crrt_cell.html(prev + '<i>有</i>');
                            $crrt_cell.removeClass('disabled');
                        }
                    }
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
        console.log(obj, crrt_date)
    },
    bind_event: function () {
        var self = this;

        $('.list-container').on('click', '.list-container .cell', function () {
            self.cell_click.call(this, this, $(this).data('val'));
        });

        $(document).on('infinite', '.infinite-scroll-bottom', function () {
            var loading = self.props.loading,
                page_index = self.props.page_index;

            // 如果正在加载，则退出
            if (loading) return;

            // 设置flag
            loading = true;

            // 模拟1s的加载过程
            setTimeout(function () {
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
            }, 1000);

        });
    }
};



Timer_Scroll.init();