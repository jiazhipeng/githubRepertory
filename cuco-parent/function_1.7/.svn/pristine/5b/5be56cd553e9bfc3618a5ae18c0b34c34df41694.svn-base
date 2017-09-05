// Date.prototype.Format = function (fmt) { //author: meizz
//     var o = {
//         "M+": this.getMonth() + 1, //月份
//         "d+": this.getDate(), //日
//         "h+": this.getHours(), //小时
//         "m+": this.getMinutes(), //分
//         "s+": this.getSeconds(), //秒
//         "q+": Math.floor((this.getMonth() + 3) / 3), //季度
//         "S": this.getMilliseconds() //毫秒
//     };
//     if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//     for (var k in o)
//         if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//     return fmt;
// };
//
// $(function ($) {
//
//     var screen_height = $(window).height(),
//         page_offset = $('.page').offset().top;
//
//     // code for fade in element by element
//     $.fn.fadeInWithDelay = function () {
//         var delay = 0;
//         return this.each(function () {
//             $(this).delay(delay).animate({
//                 opacity: 1
//             }, 100);
//             delay += 50;
//         });
//     };
//     $.fn.year_month_html = function (year, month, callback) {
//
//         var privateMethod = {
//             //判断年份是否为润年
//             isLeapYear: function (year) {
//                 return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
//             },
//             //获取某一年份的某一月份的天数
//             getMonthDays: function (year, month) {
//                 month--;
//                 return [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month] || (privateMethod.isLeapYear(year) ? 29 : 28);
//             },
//             getMonthWeek: function (a, b, c) {
//                 var date = new Date(a, parseInt(b) - 1, c),
//                     w = date.getDay(),
//                     d = date.getDate();
//                 return Math.ceil(
//                     (d + 6 - w) / 7
//                 );
//             },
//             year: year,
//             month: month,
//             callback: callback
//         };
//
//         var _loopMonth = function (year, month) {
//             var days = privateMethod.getMonthDays(year, month);
//             var weeks = privateMethod.getMonthWeek(year, month, days);
//             var html_cell = $('<section class="date"></section>');
//             var $date_body = $('<section class="date-body"></section>');
//
//             html_cell.append('<h2 class="date-title">' + year + '年' + month + '月</h2>');
//
//             for (var index = 0; index < weeks; index++) {
//                 var $row = $('<div class="date-r"></div');
//
//                 for (var index_w = 0; index_w < 7; index_w++) {
//                     var day_crrt = index_w + index * 7;
//                     var start = new Date([year, month, 1].join('/')).getDay();
//
//                     if ((index === 0 && day_crrt < start) || (index === (weeks - 1) && (day_crrt - start + 1) > days)) {
//                         $row.append('<span></span>');
//                     } else {
//                         var crrt_date = new Date([year, month, day_crrt - start + 1].join('/'));
//                         var now_date = new Date(new Date().Format('yyyy/MM/dd'));
//                         var $crrt_cell = $('<span class="cell disabled"></span>');
//
//                         // if (new Date().getTime() - crrt_date.getTime() > 0) {
//                         //     $crrt_cell.addClass('disabled');
//                         // }
//
//                         if (crrt_date.Format('yyyy/MM/dd') === (new Date().Format('yyyy/MM/dd'))) {
//                             $crrt_cell.html('今天');
//                         } else {
//                             $crrt_cell.html(day_crrt - start + 1);
//                         }
//
//                         $crrt_cell
//                             .data('val', crrt_date.Format('yyyy/MM/dd'));
//
//                         if (now_date.getTime() - crrt_date.getTime() <= 0) { //今天之后
//                             if (callback.call(this, $crrt_cell, crrt_date.Format('yyyy/MM/dd'))) {
//                                 var prev = $crrt_cell.html();
//                                 $crrt_cell.html(prev + '<i>有</i>');
//                                 $crrt_cell.removeClass('disabled');
//                             }
//                         }
//                         $row.append($crrt_cell);
//
//                         // cells += '<span class="cell" data-val="' + crrt_date.Format('yyyy/MM/dd') + '">' + (day_crrt - start + 1) + '</span>';
//                     }
//
//                 }
//                 //$row.append(cells);
//                 $date_body.append($row);
//             }
//             html_cell.append($date_body);
//             return html_cell;
//         };
//
//         return this.each(function () {
//             $(this).append(_loopMonth(privateMethod.year, privateMethod.month));
//         });
//     };
//
//
//     var  timer=null;
//     $('.page').scrollPagination({
//
//         ADD_MONTH: function (date, val) {
//             date.setMonth(date.getMonth() + val);
//             return date;
//         },
//         _fn_render_html(opts, page_index, callback) {
//             var obj = opts._fn_get_crrt_nxt_month(opts, page_index);
//             var $li = $('<li></li>');
//
//             $li.year_month_html(obj.crrt_year, obj.crrt_month, callback);
//             $li.year_month_html(obj.crrt_nxt_year, obj.crrt_nxt_month, callback);
//             return $li;
//         },
//         _fn_get_crrt_nxt_month(opts, page_index) {
//             var crrt_year,
//                 crrt_month,
//                 crrt_nxt_year,
//                 crrt_nxt_month;
//
//             crrt_year = opts.ADD_MONTH(new Date(), 2 * page_index).getFullYear();
//             crrt_month = opts.ADD_MONTH(new Date(), 2 * page_index).getMonth() + 1;
//             crrt_nxt_year = opts.ADD_MONTH(new Date(), 2 * page_index + 1).getFullYear();
//             crrt_nxt_month = opts.ADD_MONTH(new Date(), 2 * page_index + 1).getMonth() + 1;
//
//             return {
//                 crrt_year: crrt_year,
//                 crrt_month: crrt_month,
//                 crrt_nxt_year: crrt_nxt_year,
//                 crrt_nxt_month: crrt_nxt_month
//             };
//         },
//
//         fn_contentData: function (obj, opts) {
//             var page_index = $('.page').children().length;
//             var data = {};
//             var date_msg = opts._fn_get_crrt_nxt_month(opts, page_index);
//             console.log("---------------------------");
//             $.ajax({
//                 type: 'POST',
//                 url: "${ctx}/wechat/carOperate/searchStore",
//                 async:false,
//                 data:data,
//                 success: function(data){
//                     if(data.isSuccess == "true"){
//                         console.info(data);
//                     }
//                 }
//             });
//
//             // timer=  setTimeout(function () {
//             //   clearTimeout(timer)
//             //     var $li = opts._fn_render_html(opts, page_index, function ($obj, val) {
//             //         //console.log(val)
//             //
//             //         if (['2017/01/01', '2017/02/02'].indexOf(val) >= 0) {
//             //             return true;
//             //         }
//             //         return false;
//             //     });
//             //
//             //     $(obj).append($li);
//             //     $('.page li').height(screen_height - page_offset + 10);
//             //
//             //     var objectsRendered = $(obj).children('[rel!=loaded]');
//             //     if (opts.afterLoad != null) {
//             //         opts.afterLoad(objectsRendered);
//             //     }
//             // }, 2000);
//
//             return true;
//         },
//         'scrollTarget': $(window),
//         'heightOffset': 10,
//         'beforeLoad': function () {
//             $('#loading').fadeIn();
//         },
//         'afterLoad': function (elementsLoaded) {
//             $('#loading').fadeOut();
//             var i = 0;
//             $(elementsLoaded).fadeInWithDelay();
//             if ($('.page').children().length >= 12) {
//                 $('#nomoreresults').fadeIn();
//                 $('.page').stopScrollPagination();
//                 setTimeout(function () {
//                     $('#nomoreresults').fadeOut();
//                 });
//             }
//         }
//     });
//
// });