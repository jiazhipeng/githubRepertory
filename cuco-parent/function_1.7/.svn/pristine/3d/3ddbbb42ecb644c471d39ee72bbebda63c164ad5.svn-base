<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<li>
    <a>
        <span class="info-name">
                预计用车时间：
        </span>
        <span class="info-val page-current" id="page-datetime-picker">
            <input type="text" placeholder="" id="datetime-picker" readonly="" value="<fmt:formatDate value="${butlerTask.planTime }" pattern="yyyy-MM-dd HH:mm"/>">
            <script>
               $(document).on("pageInit", "#page-datetime-picker", function(e) {
                  <%--// var planTime = '${butlerTask.planTime}';--%>
                   <%--var planTime = "";--%>
                   <%--var oDate = null;--%>
                   <%--if(planTime){--%>
                       <%--oDate =  resolveStringDate(planTime);--%>
                   <%--}else{--%>
                       <%--oDate = DateUtil.dateAdd('h',4,new Date());--%>
                   <%--}--%>
                   <%--var year = oDate.getFullYear();   //获取系统的年；--%>
                   <%--var month = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1--%>
                   <%--month=month<10?('0'+month):month;--%>
                   <%--var date = oDate.getDate(); // 获取系统日，--%>
                   <%--var hour = oDate.getHours(); //获取系统时，--%>
                   <%--var minute = oDate.getMinutes(); //分--%>
                   $("#datetime-picker").datetimePicker({
                       toolbarTemplate: '<header class="bar bar-nav">\
                  <button class="button button-link pull-right close-picker">确定</button>\
                  <h1 class="title">选择时间</h1>\
                  </header>',
                   });
               });
               $.init();

               /** function clearCar(){
                    $("#carOperateId").val($("#carOperateId_copy").val());
                    $("#carOperateName").val($("#carOperateName_copy").val());
                    $("#usedCar").html($("#carOperateName_copy").val());
                }**/
            </script>
    </span>
    </a>
</li>
