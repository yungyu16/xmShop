/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-11-22 08:52:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class productDetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"/>\r\n");
      out.write("    <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"https://www.xmlvhy.com/images/xmshop/favicon.ico\"\r\n");
      out.write("          media=\"screen\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/bootstrap.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/style.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/iconfont/iconfont.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/xmstyle.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/animate.min.css\">\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/bootstrap.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/zshop.js\"></script>\r\n");
      out.write("    <title>商品详情页</title>\r\n");
      out.write("\r\n");
      out.write("    <script>\r\n");
      out.write("        $(function () {\r\n");
      out.write("            var t = $('#text_box');\r\n");
      out.write("            $('#min').attr('disabled', true);\r\n");
      out.write("\r\n");
      out.write("            $('#add').click(function () {\r\n");
      out.write("                t.val(parseInt(t.val()) + 1);\r\n");
      out.write("                if (parseInt(t.val()) != 1) {\r\n");
      out.write("                    $('#min').attr('disabled', false);\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            $('#min').click(function () {\r\n");
      out.write("                t.val(parseInt(t.val()) - 1);\r\n");
      out.write("                if (parseInt(t.val()) == 1) {\r\n");
      out.write("                    $('#min').attr('disabled', true);\r\n");
      out.write("                }\r\n");
      out.write("\r\n");
      out.write("            });\r\n");
      out.write("            //调整footer 的位置\r\n");
      out.write("            autoFooterHeight();\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        //TODO: 设置固定在底部的footer 可以自适应高度\r\n");
      out.write("        function autoFooterHeight() {\r\n");
      out.write("            // 获取内容的高度\r\n");
      out.write("            var bodyHeight = $(\"body\").height();\r\n");
      out.write("            // 获取底部导航的高度\r\n");
      out.write("            var navHeight = $(\".footer\").height();\r\n");
      out.write("            // 获取显示屏的高度\r\n");
      out.write("            var iHeight = document.documentElement.clientHeight || document.body.clientHeight;\r\n");
      out.write("            // 如果内容的高度大于（窗口的高度 - 导航的高度）,移除属性样式\r\n");
      out.write("            if (bodyHeight > (iHeight - navHeight)) {\r\n");
      out.write("                $(\"#footer\").removeClass(\"navbar-fixed-bottom\");\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        //添加商品到购物车\r\n");
      out.write("        function addToCart(id) {\r\n");
      out.write("            $.post(\r\n");
      out.write("                '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/front/cart/addToCart',\r\n");
      out.write("                {'id': id, 'textBox': $('#text_box').val()},\r\n");
      out.write("                function (result) {\r\n");
      out.write("                    if (result.status == 1) {\r\n");
      out.write("                        //商品成功添加购物车\r\n");
      out.write("                        layer.msg(result.message, {\r\n");
      out.write("                            time: 1200,\r\n");
      out.write("                            skin: 'successMsg'\r\n");
      out.write("                        });\r\n");
      out.write("                    } else if (result.status == 3) {\r\n");
      out.write("                        //表示用户未登录\r\n");
      out.write("                        layer.msg(result.message, {\r\n");
      out.write("                            time: 2000,\r\n");
      out.write("                            skin: 'warningMsg'\r\n");
      out.write("                        });\r\n");
      out.write("                    } else {\r\n");
      out.write("                        //商品添加失败\r\n");
      out.write("                        layer.msg(result.message, {\r\n");
      out.write("                            time: 1500,\r\n");
      out.write("                            skin: 'errorMsg'\r\n");
      out.write("                        });\r\n");
      out.write("                    }\r\n");
      out.write("                }\r\n");
      out.write("            );\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        //TODO:直接购买功能未实现\r\n");
      out.write("        function buy() {\r\n");
      out.write("            var count = $('#text_box').val();\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("            // var totalPrice = count * price;\r\n");
      out.write("            var productId = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\r\n");
      out.write("            // console.log(count);\r\n");
      out.write("            // console.log(price);\r\n");
      out.write("            // console.log(totalPrice);\r\n");
      out.write("            // console.log(productId);\r\n");
      out.write("\r\n");
      out.write("            //发送一个ajax 请求，到后端直接放入购物车中\r\n");
      out.write("            $.post(\r\n");
      out.write("                '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/front/order/redirectBuyToCart',\r\n");
      out.write("                {'count':count,'productId': productId},\r\n");
      out.write("                function (result) {\r\n");
      out.write("                    if (result.status == 1) {\r\n");
      out.write("                        //表示商品成功放入购物车中\r\n");
      out.write("                        console.log(result);\r\n");
      out.write("                        location.replace('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/front/order/redirectConfirmOrder?cartId='+result.data);\r\n");
      out.write("                    }else {\r\n");
      out.write("                        layer.msg(result.message,{\r\n");
      out.write("                           time: 1500,\r\n");
      out.write("                           skin: 'errorMsg'\r\n");
      out.write("                        });\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"animated fadeIn\">\r\n");
      out.write("<!-- 导航栏 start -->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "top.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("num", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("5", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("<!-- 导航栏 end -->\r\n");
      out.write("\r\n");
      out.write("<!--banner start-->\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/banner.jpg\" class=\"banner\" alt=\"\">\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<!--banner end-->\r\n");
      out.write("\r\n");
      out.write("<div class=\"row\" style=\"margin-top: 50px\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"col-sm-6\" style=\"padding: 0\">\r\n");
      out.write("            <div class=\"imgShow\">\r\n");
      out.write("                <img style=\"width: 400px;height: 400px\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.image}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" alt=\"\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-sm-6\">\r\n");
      out.write("            <span class=\"title\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("            <div class=\"pic\">\r\n");
      out.write("                <span style=\"margin-left: 30px;\">价格:</span>\r\n");
      out.write("                <span class=\"priceIcon\">￥</span>\r\n");
      out.write("                <span class=\"priceIcon\" class=\"pcc\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.price}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"sellnum\">\r\n");
      out.write("                <div class=\"sellnumWrap\">\r\n");
      out.write("                    <!--累计出售-->\r\n");
      out.write("                    <div class=\"sellnumDetail\">\r\n");
      out.write("                        <span style=\"margin-top: 20px;color: #bfbfbf\">累计出售: </span>\r\n");
      out.write("                        <span style=\"color:#5885C7;\">66</span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <!--分割线-->\r\n");
      out.write("                    <div class=\"sellnumborder\"></div>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"sellnumDetail\" style=\"margin-left: 10px;\">\r\n");
      out.write("                        <span style=\"color: #bfbfbf\">累计评价:</span>\r\n");
      out.write("                        <span style=\"color: #5885C7\">666</span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"cartCount\">\r\n");
      out.write("                <span style=\"\">购买数量 : </span>\r\n");
      out.write("                <input type=\"button\" id=\"min\" name=\"min\" value=\"-\" disabled=\"disabled\">\r\n");
      out.write("                <input class=\"textBox\" id=\"text_box\" name=\"textBox\" type=\"text\" value=\"1\">\r\n");
      out.write("                <input id=\"add\" name=\"add\" type=\"button\" value=\"+\">\r\n");
      out.write("                <span class=\"Hgt\">库存（99）</span>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"shop\">\r\n");
      out.write("                <div class=\"btn btn-default cartBtn\" onclick=\"addToCart(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(")\">加入购物车</div>\r\n");
      out.write("                <div class=\"btn btn-success buyBtn\" onclick=\"buy()\">立即购买</div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div style=\"height: 250px\"></div>\r\n");
      out.write("\r\n");
      out.write("<!-- footers start -->\r\n");
      out.write("<footer id=\"footer\" class=\"footers navbar-fixed-bottom\">\r\n");
      out.write("    <div class=\"footer container-fluid footers\">\r\n");
      out.write("        Copy Right @ 2019 BY XIAOMO\r\n");
      out.write("    </div>\r\n");
      out.write("</footer>\r\n");
      out.write("\r\n");
      out.write("<!-- footers end -->\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
