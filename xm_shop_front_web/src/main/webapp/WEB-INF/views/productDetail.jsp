<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xmstyle.css">
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/animate.css/3.7.0/animate.min.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/zshop.js"></script>
    <title>商品详情页</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/site-logo.png"
          media="screen"/>

    <script>
        $(function () {
            var t = $('#text_box');
            $('#min').attr('disabled', true);

            $('#add').click(function () {
                t.val(parseInt(t.val()) + 1);
                $('#min').attr('disabled', parseInt(t.val()) === 1);
            });

            $('#min').click(function () {
                t.val(parseInt(t.val()) - 1);
                $('#min').attr('disabled', parseInt(t.val()) === 1);
            });
            //调整footer 的位置
            autoFooterHeight();
        });

        //TODO: 设置固定在底部的footer 可以自适应高度
        function autoFooterHeight() {
            // 获取内容的高度
            var bodyHeight = $("body").height();
            // 获取底部导航的高度
            var navHeight = $(".footer").height();
            // 获取显示屏的高度
            var iHeight = document.documentElement.clientHeight || document.body.clientHeight;
            // 如果内容的高度大于（窗口的高度 - 导航的高度）,移除属性样式
            if (bodyHeight >= (iHeight - navHeight)) {
                $("#footer").removeClass("navbar-fixed-bottom");
            }
        }

        //添加商品到购物车
        function addToCart(id) {
            $.post(
                '${pageContext.request.contextPath}/front/cart/addToCart',
                {'id': id, 'textBox': $('#text_box').val()},
                function (result) {
                    if (result.status == 1) {
                        //商品成功添加购物车
                        layer.msg(result.message, {
                            time: 1200,
                            skin: 'successMsg'
                        });
                    } else if (result.status == 3) {
                        //表示用户未登录
                        layer.msg(result.message, {
                            time: 2000,
                            skin: 'warningMsg'
                        });
                    } else {
                        //商品添加失败
                        layer.msg(result.message, {
                            time: 1500,
                            skin: 'errorMsg'
                        });
                    }
                }
            );
        }

        //TODO:直接购买功能未实现
        function buy() {
            var count = $('#text_box').val();
            <%--var price = parseFloat(${product.price});--%>
            // var totalPrice = count * price;
            var productId = '${product.id}';

            // console.log(count);
            // console.log(price);
            // console.log(totalPrice);
            // console.log(productId);

            //发送一个ajax 请求，到后端直接放入购物车中
            $.post('${pageContext.request.contextPath}/front/order/redirectBuyToCart',
                {'count': count, 'productId': productId},
                function (result) {
                    if (result.status === 1) {
                        //表示商品成功放入购物车中
                        console.log(result);
                        location.replace('${pageContext.request.contextPath}/front/order/redirectConfirmOrder?cartId=' + result.data);
                    } else {
                        layer.msg(result.message, {
                            time: 1500,
                            skin: 'errorMsg'
                        });
                    }
                });
        }
    </script>

</head>
<body class="animated fadeIn">
<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="5"/>
</jsp:include>
<!-- 导航栏 end -->

<!--banner start-->
<%--<div class="row">
    <div class="container">
        <img src="${banner}" class="banner" alt="">
    </div>
</div>--%>
<!--banner end-->

<div class="row" style="margin-top: 12%">
    <div class="container">
        <div class="col-sm-6" style="padding: 0">
            <div class="imgShow">
                <img style="width: 400px;height: 400px" src="${product.image}" alt="">
            </div>
        </div>
        <div class="col-sm-6">
            <span class="title">${product.name}</span>
            <span class="productDesc">${product.info}</span>
            <div class="pic">
                <span style="margin-left: 30px;">价格:</span>
                <span class="priceIcon">￥</span>
                <span class="priceIcon" class="pcc">${product.price}</span>
            </div>
            <div class="sellnum">
                <div class="sellnumWrap">
                    <!--累计出售-->
                    <div class="sellnumDetail">
                        <span style="margin-top: 20px;color: #bfbfbf">累计出售: </span>
                        <span style="color:#5885C7;">17</span>
                    </div>
                    <!--分割线-->
                    <div class="sellnumborder"></div>

                    <div class="sellnumDetail" style="margin-left: 10px;">
                        <span style="color: #bfbfbf">累计评价:</span>
                        <span style="color: #5885C7">1541</span>
                    </div>
                </div>
            </div>
            <div class="cartCount">
                <span style="">购买数量 : </span>
                <input type="button" id="min" name="min" value="-" disabled="disabled">
                <input class="textBox" id="text_box" name="textBox" type="text" value="1">
                <input id="add" name="add" type="button" value="+">
                <span class="Hgt">库存（248）</span>
            </div>

            <div class="shop">
                <div class="btn btn-default cartBtn" onclick="addToCart(${product.id})">加入购物车</div>
                <c:if test="${customer != null}">
                    <div class="btn btn-success buyBtn" onclick="buy()">立即购买</div>
                </c:if>
            </div>
        </div>
    </div>
</div>

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
