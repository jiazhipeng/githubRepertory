package org.apache.jsp.WEB_002dINF.views.wechatMenu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class edit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=320, user-scalable=0, initial-scale=1,maximum-scale=1\">\r\n");
      out.write("<meta content=\"yes\" name=\"apple-mobile-web-app-capable\" />\r\n");
      out.write("<meta content=\"yes\" name=\"apple-touch-fullscreen\" />\r\n");
      out.write("<meta content=\"telephone=no\" name=\"format-detection\" />\r\n");
      out.write("<meta content=\"black\" name=\"apple-mobile-web-app-status-bar-style\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<title>微信菜单</title>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\t\t* {\r\n");
      out.write("\t\t\tpadding: 0;\r\n");
      out.write("\t\t\tmargin: 0;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tbody {\r\n");
      out.write("\t\t\tbackground: #eee;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t.clearFix {\r\n");
      out.write("\t\t\toverflow: hidden;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t.fl {\r\n");
      out.write("\t\t\tfloat: left;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t.section {\r\n");
      out.write("\t\t\tmargin-bottom: 30px;\r\n");
      out.write("\t\t\tbackground: #fff;\r\n");
      out.write("\t\t\tpadding: 30px 50px;\r\n");
      out.write("\t\t\twidth: 1000px;\r\n");
      out.write("\t\t\tmargin: auto;\r\n");
      out.write("\t\t\tborder-bottom: 1px solid #ccc;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t.secTitle {\r\n");
      out.write("\t\t\tfont-weight: bold;\r\n");
      out.write("\t\t\tmargin-bottom: 20px;\r\n");
      out.write("\t\t\ttext-align: center;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\ttextarea {\r\n");
      out.write("\t\t\twidth: 300px;\r\n");
      out.write("\t\t\theight: 100px;\r\n");
      out.write("\t\t\tresize: none;\r\n");
      out.write("\t\t\tdisplay: none;\r\n");
      out.write("\t\t\tpadding: 8px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t.subMenu {\r\n");
      out.write("\t\t\tmargin-top: 20px;\r\n");
      out.write("\t\t\tpadding: 20px;\r\n");
      out.write("\t\t\tbackground: #eee;\r\n");
      out.write("\t\t\tborder-radius: 4px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t.subMenu .sub,.subMenu .sub {\r\n");
      out.write("\t\t\tpadding-top: 10px;\r\n");
      out.write("\t\t\tmargin-bottom: 10px;\r\n");
      out.write("\t\t\tborder-bottom: 1px dotted #aaa;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t.reply {\r\n");
      out.write("\t\t\tpadding-top: 10px;\r\n");
      out.write("\t\t\tmargin-bottom: 15px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t.name {\r\n");
      out.write("\t\t\twidth: 100px;\r\n");
      out.write("\t\t\ttext-align: right;\r\n");
      out.write("\t\t\tdisplay: inline-block;\r\n");
      out.write("\t\t\tpadding: 0 6px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t.button{width: 100px; height: 40px; line-height: 40px; font-size:14px; font-weight:bold; background: #34b4ba; border:0; outline:none;cursor:pointer; color:#fff; display: block; margin: 30px auto; text-align: center; border-radius: 4px;}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/static/js/jquery-1.8.3.min.js\">   </script>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"input[type='radio']\").change(function(){\r\n");
      out.write("\t\t\tvar curId = $(this).attr('id');\r\n");
      out.write("\t\t\tvar curVal = $(this).val();\r\n");
      out.write("\t\t\tvar prefix = curId.substring(0,curId.indexOf('_'));\r\n");
      out.write("\t\t\tif(curId.indexOf('showSon') != -1){\r\n");
      out.write("\t\t\t\t//是否显示子菜单 radio change 事件\r\n");
      out.write("\t\t\t\tif(\"son\"==curVal){\r\n");
      out.write("\t\t\t\t\t//显示子菜单\r\n");
      out.write("\t\t\t\t\t$(\"#\"+prefix+\"MainDiv\").hide();\r\n");
      out.write("\t\t\t\t\t$(\"#\"+prefix+\"SonDiv\").show();\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//显示主菜单\r\n");
      out.write("\t\t\t\t\t$(\"#\"+prefix+\"MainDiv\").show();\r\n");
      out.write("\t\t\t\t\t$(\"#\"+prefix+\"SonDiv\").hide();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t//子菜单radio change 事件\r\n");
      out.write("\t\t\t\tif(\"click\"==curVal){\r\n");
      out.write("\t\t\t\t\t//自动回复\r\n");
      out.write("\t\t\t\t\t$(\"#\"+prefix+\"_message\").show();\r\n");
      out.write("\t\t\t\t\t$(\"#\"+prefix+\"_url\").hide();\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//自动回复\r\n");
      out.write("\t\t\t\t\t$(\"#\"+prefix+\"_message\").hide();\r\n");
      out.write("\t\t\t\t\t$(\"#\"+prefix+\"_url\").show();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t})\r\n");
      out.write("\t\r\n");
      out.write("\tfunction update(){\r\n");
      out.write("\t\tvar mainArray = [];\r\n");
      out.write("\t\tfor(var i = 1; i <= 3; i++){//主菜单\r\n");
      out.write("\t\t\t//1.判断是否有主菜单\r\n");
      out.write("\t\t\tvar curMainId = \"mainMenu0\"+i;\r\n");
      out.write("\t\t\tvar curMainName = $.trim($(\"#\"+curMainId).val());\r\n");
      out.write("\t\t\t//alert($(\"#\"+curMainId).val()+\"\\n\"+$.trim($(\"#\"+curMainId).val()));\r\n");
      out.write("\t\t\tif(null == curMainName || \"\"==curMainName){\r\n");
      out.write("\t\t\t\t//alert(\"主菜单\"+i+\"没有填写\");\r\n");
      out.write("\t\t\t\tcontinue;\r\n");
      out.write("\t\t\t}else if(curMainName.replace(/[^\\x00-\\xff]/g, \"**\").length > 8){//.replace(/[^\\x00-\\xff]/g, \"**\").length;\r\n");
      out.write("\t\t\t\talert(\"主菜单\"+i+\"名称过长(最多4个汉字[8个字母])\");\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar curMainMenu = {name:curMainName};\r\n");
      out.write("\t\t\t//2.判断是否有子菜单\r\n");
      out.write("\t\t\tvar checkSonName = curMainId+\"_showSon\";\r\n");
      out.write("\t\t\tvar menuType = $(\"input:radio[name=\"+checkSonName+\"]:checked\").val();\r\n");
      out.write("\t\t\tif(\"main\"==menuType){//没有子菜单\r\n");
      out.write("\t\t\t\tcurMainMenu.hasSubmenu = \"0\";\r\n");
      out.write("\t\t\t\tvar menuTypeId = curMainId+\"_type\";\r\n");
      out.write("\t\t\t\tvar menuType = $(\"input:radio[name=\"+menuTypeId+\"]:checked\").val();\r\n");
      out.write("\t\t\t\tcurMainMenu.type = menuType;\r\n");
      out.write("\t\t\t\t//alert(\"curType:\"+curType);\r\n");
      out.write("\t\t\t\tif(\"view\"==menuType){\r\n");
      out.write("\t\t\t\t\t//1.view\r\n");
      out.write("\t\t\t\t\tvar menuUrl = $.trim($(\"#\"+curMainId+\"_url\").val());\r\n");
      out.write("\t\t\t\t\t//判断url是否为空\r\n");
      out.write("\t\t\t\t\tif(null == menuUrl || \"\"==menuUrl || \"http://\"==menuUrl){\r\n");
      out.write("\t\t\t\t\t\t//alert(\"请填写主菜单\"+i+\"对应的跳转路径\");\r\n");
      out.write("\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tcurMainMenu.url = menuUrl;\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//2.click\r\n");
      out.write("\t\t\t\t\tvar menuMsg = $.trim($(\"#\"+curMainId+\"_message\").val());\r\n");
      out.write("\t\t\t\t\t//判断message是否为空\r\n");
      out.write("\t\t\t\t\tif(null == menuMsg || \"\"==menuMsg){\r\n");
      out.write("\t\t\t\t\t\talert(\"请填写主菜单\"+i+\"对应的回复内容\");\r\n");
      out.write("\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tcurMainMenu.message = menuMsg;\r\n");
      out.write("\t\t\t\t\tcurMainMenu.msgKey = \"key\" + i;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}else{//存在子菜单\r\n");
      out.write("\t\t\t\tcurMainMenu.hasSubmenu = \"1\";\r\n");
      out.write("\t\t\t\t//处理子菜单\r\n");
      out.write("\t\t\t\tvar sonArray = [];\r\n");
      out.write("\t\t\t\tvar chooseNum = 0;\r\n");
      out.write("\t\t\t\tfor(var j = 1; j <= 5; j++){//子菜单\r\n");
      out.write("\t\t\t\t\tvar curSonPrefix = \"subMenu0\"+i+\"0\"+j;\r\n");
      out.write("\t\t\t\t\tvar curSonName = $(\"#\"+curSonPrefix+\"_name\").val();\r\n");
      out.write("\t\t\t\t\tif(null == curSonName || \"\"==curSonName){\r\n");
      out.write("\t\t\t\t\t\t//alert(\"主菜单\"+i+\"的子菜单\"+j+\"没有填写\");\r\n");
      out.write("\t\t\t\t\t\tcontinue;\r\n");
      out.write("\t\t\t\t\t}else if(curSonName.replace(/[^\\x00-\\xff]/g, \"**\").length > 14){\r\n");
      out.write("\t\t\t\t\t\talert(\"主菜单\"+i+\"的子菜单\"+j+\"名称过长(最多7个汉字[14个字母])\");\r\n");
      out.write("\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tvar curSonMenu = {name:curSonName};\r\n");
      out.write("\t\t\t\t\tvar curTypeId = curSonPrefix+\"_type\";\r\n");
      out.write("\t\t\t\t\tvar curType = $(\"input:radio[name=\"+curTypeId+\"]:checked\").val();\r\n");
      out.write("\t\t\t\t\tcurSonMenu.type = curType;\r\n");
      out.write("\t\t\t\t\t//alert(\"curType:\"+curType);\r\n");
      out.write("\t\t\t\t\tif(\"view\"==curType){\r\n");
      out.write("\t\t\t\t\t\t//1.view\r\n");
      out.write("\t\t\t\t\t\tvar curUrl = $.trim($(\"#\"+curSonPrefix+\"_url\").val());\r\n");
      out.write("\t\t\t\t\t\t//判断url是否为空\r\n");
      out.write("\t\t\t\t\t\tif(null == curUrl || \"\"==curUrl || \"http://\"==curUrl){\r\n");
      out.write("\t\t\t\t\t\t\talert(\"请填写主菜单\"+i+\"的子菜单\"+j+\"对应的跳转路径\");\r\n");
      out.write("\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\tcurSonMenu.url = curUrl;\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t//2.click\r\n");
      out.write("\t\t\t\t\t\tvar curMsg = $.trim($(\"#\"+curSonPrefix+\"_message\").val());\r\n");
      out.write("\t\t\t\t\t\t//判断message是否为空\r\n");
      out.write("\t\t\t\t\t\tif(null == curMsg || \"\"==curMsg){\r\n");
      out.write("\t\t\t\t\t\t\talert(\"请填写主菜单\"+i+\"的子菜单\"+j+\"对应的回复内容\");\r\n");
      out.write("\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\tcurSonMenu.message = curMsg;\r\n");
      out.write("\t\t\t\t\t\tcurSonMenu.msgKey = \"key\" + i + j;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tchooseNum++;\r\n");
      out.write("\t\t\t\t\tsonArray.push(curSonMenu);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(chooseNum == 0){\r\n");
      out.write("\t\t\t\t\talert(\"请填写主菜单\"+i+\"对应的子菜单\");\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tcurMainMenu.subMenu = sonArray;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tmainArray.push(curMainMenu);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar pwd = prompt(\"请输入密码\",\"\");\r\n");
      out.write("\t\tif(mainArray.length==0){\r\n");
      out.write("\t\t\talert(\"您尚未填写任何菜单！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar data = JSON.stringify(mainArray);\r\n");
      out.write("\t\t//alert(data);\r\n");
      out.write("\t\tif(confirm(\"确定更新微信菜单？\")){\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//alert(\"更新开始.....\");\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t     type: 'POST',\r\n");
      out.write("\t\t\t     url: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/wechat/menu/save',\r\n");
      out.write("\t\t\t     dataType: \"json\",\r\n");
      out.write("\t\t\t     data: {\r\n");
      out.write("\t\t\t    \t \"data\":data,\r\n");
      out.write("\t\t\t    \t \"pwd\":pwd\r\n");
      out.write("\t\t\t     },\r\n");
      out.write("\t\t\t     success: function(data){\r\n");
      out.write("\t\t\t    \t console.log(data.data);\r\n");
      out.write("\t\t\t    \t var result = eval(data.data);\r\n");
      out.write("\t\t\t    \t alert(result.msg);\r\n");
      out.write("\t\t\t    } \r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"section\">\r\n");
      out.write("\t\t<p class=\"secTitle\">主菜单一</p>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<div class=\"menu\">\r\n");
      out.write("\t\t\t\t主菜单名称:<input type=\"text\" id=\"mainMenu01\"> 是否需要子菜单：\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"mainMenu01_showSon\" id=\"mainMenu01_showSon\" value=\"son\" checked=\"checked\">是\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"mainMenu01_showSon\" id=\"mainMenu01_showSon\" value=\"main\">否\r\n");
      out.write("\t\t\t\t<div id=\"mainMenu01MainDiv\" style=\"display:none;\">\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t回复方式:\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"mainMenu01_type\" id=\"mainMenu01_type\" value=\"view\" checked=\"checked\">跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"mainMenu01_type\" id=\"mainMenu01_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" id=\"mainMenu01_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t\t\t<textarea id=\"mainMenu01_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t<!-- <input type=\"text\" id=\"mainMenu01_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"subMenu\" id=\"mainMenu01SonDiv\">\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0101_name\"> <br> <br><span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0101_type\" id=\"subMenu0101_type\" value=\"view\" checked=\"checked\">跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0101_type\" id=\"subMenu0101_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0101_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0101_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0101_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0102_name\"> <br> <br><span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0102_type\" id=\"subMenu0102_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0102_type\" id=\"subMenu0102_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0102_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0102_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0102_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0103_name\"> <br> <br><span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0103_type\" id=\"subMenu0103_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0103_type\" id=\"subMenu0103_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0103_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0103_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0103_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0104_name\"><br> <br> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0104_type\" id=\"subMenu0104_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0104_type\" id=\"subMenu0104_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0104_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0104_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0104_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0105_name\"> <br> <br><span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0105_type\" id=\"subMenu0105_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0105_type\" id=\"subMenu0105_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0105_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0105_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0105_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"section\">\r\n");
      out.write("\t\t<p class=\"secTitle\">主菜单二</p>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<div class=\"menu\">\r\n");
      out.write("\t\t\t\t主菜单名称:<input type=\"text\" id=\"mainMenu02\"> 是否需要子菜单：\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"mainMenu02_showSon\" id=\"mainMenu02_showSon\" value=\"son\" checked=\"checked\">是\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"mainMenu02_showSon\" id=\"mainMenu02_showSon\" value=\"main\">否\r\n");
      out.write("\t\t\t\t<div id=\"mainMenu02MainDiv\" style=\"display:none\">\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t回复方式：<input type=\"radio\" name=\"mainMenu02_type\" id=\"mainMenu02_type\" value=\"view\" checked=\"checked\">跳转url\r\n");
      out.write("\t\t\t\t\t   \t   <input type=\"radio\" name=\"mainMenu02_type\" id=\"mainMenu02_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" id=\"mainMenu02_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t\t\t<textarea id=\"mainMenu02_message\" value=\"\" placeholder=\"请输入自动回复内容\" ></textarea>\r\n");
      out.write("\t\t\t\t\t\t  <!-- <input type=\"text\" id=\"mainMenu02_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"subMenu\" id=\"mainMenu02SonDiv\">\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0201_name\"><br> <br> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0201_type\" id=\"subMenu0201_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0201_type\" id=\"subMenu0201_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0201_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0201_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0201_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0202_name\"><br> <br> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0202_type\" id=\"subMenu0202_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0202_type\" id=\"subMenu0202_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0202_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0202_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0202_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0203_name\"><br> <br> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0203_type\" id=\"subMenu0203_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0203_type\" id=\"subMenu0203_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0203_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0203_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0203_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0204_name\"> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0204_type\" id=\"subMenu0204_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0204_type\" id=\"subMenu0204_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0204_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0204_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0204_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0205_name\"><br> <br> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0205_type\" id=\"subMenu0205_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0205_type\" id=\"subMenu0205_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0205_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0205_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0205_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"section\">\r\n");
      out.write("\t\t<p class=\"secTitle\">主菜单三</p>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<div class=\"menu\">\r\n");
      out.write("\t\t\t\t主菜单名称:<input type=\"text\" id=\"mainMenu03\"> 是否需要子菜单：\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"mainMenu03_showSon\" id=\"mainMenu03_showSon\" value=\"son\" checked=\"checked\">是\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"mainMenu03_showSon\" id=\"mainMenu03_showSon\" value=\"main\">否\r\n");
      out.write("\t\t\t\t<div id=\"mainMenu03MainDiv\" style=\"display:none\">\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t回复方式：<input type=\"radio\" name=\"mainMenu03_type\" id=\"mainMenu03_type\" value=\"view\" checked=\"checked\">跳转url\r\n");
      out.write("\t\t\t\t\t   \t   <input type=\"radio\" name=\"mainMenu03_type\" id=\"mainMenu03_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" id=\"mainMenu03_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t\t  \t<!-- <input type=\"text\" id=\"mainMenu03_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t\t  \t<textarea id=\"mainMenu03_message\" value=\"\" placeholder=\"请输入自动回复内容\" ></textarea>\r\n");
      out.write("\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"subMenu\" id=\"mainMenu03SonDiv\">\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0301_name\"><br> <br> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0301_type\" id=\"subMenu0301_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0301_type\" id=\"subMenu0301_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0301_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0301_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0301_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0302_name\"> <br> <br><span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0302_type\" id=\"subMenu0302_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0302_type\" id=\"subMenu0302_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0302_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0302_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0302_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0303_name\"> <br> <br><span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0303_type\" id=\"subMenu0303_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0303_type\" id=\"subMenu0303_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0303_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0303_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0303_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0304_name\"><br> <br> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0304_type\" id=\"subMenu0304_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0304_type\" id=\"subMenu0304_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0304_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0304_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0304_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub\">\r\n");
      out.write("\t\t\t\t\t<span class=\"name\">子菜单名称:</span><input type=\"text\" id=\"subMenu0305_name\"><br> <br> <span class=\"name\">回复方式:</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0305_type\" id=\"subMenu0305_type\" value=\"view\" checked>跳转url\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"subMenu0305_type\" id=\"subMenu0305_type\" value=\"click\">自动回复\r\n");
      out.write("\t\t\t\t\t<div class=\"reply clearFix\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl name\">回复内容:</span>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"fl\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"subMenu0305_url\" value=\"http://\">\r\n");
      out.write("\t\t\t\t\t<!-- <input type=\"text\" id=\"subMenu0305_message\" value=\"\" placeholder=\"请输入自动回复内容\" style=\"display:none\"> -->\r\n");
      out.write("\t\t\t\t\t<textarea id=\"subMenu0305_message\" value=\"\" placeholder=\"请输入自动回复内容\"></textarea>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<br/>\r\n");
      out.write("\t\t<button onclick=\"update();\" class=\"button\">更新菜单</button>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
