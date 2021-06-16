<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>我的购物车</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/site-logo.png"
          media="screen"/>
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/animate.css/3.7.0/animate.min.css"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body class="animated fadeIn">

<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="6"/>
</jsp:include>
<!-- 导航栏 end -->

<!-- content start -->
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>我的购物车</h3>
            </div>
        </div>
    </div>
    <div>
        <h3 class="text-success" style="text-align: center;margin-top: 180px">
            购物车空空如也，<a class="btn-link" href="${pageContext.request.contextPath}/front/product/searchAllProducts">前往购物~</a>
        </h3>
    </div>
</div>
<!-- content end-->
<div style="height: 250px"></div>

<!-- footers start -->
<footer id="footer" class="footers navbar-fixed-bottom">
    <div class="footer container-fluid footers">
        Copy Right @ 2021 BY TuiXiaoMi
    </div>
</footer>
<!-- footers end -->
</body>

</html>
