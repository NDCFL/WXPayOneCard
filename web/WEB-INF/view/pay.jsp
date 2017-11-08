<%--
  Created by IntelliJ IDEA.
  User: Wang Genshen
  Date: 2017-07-04
  Time: 22:25
  To change this template use File | Settinfz | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>微信充值</title>
    <link href="<%=path %>/js/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=path %>/js/sweet-alert/sweet-alert.css" rel="stylesheet"/>
</head>
<body>
<div class="row none-box">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 none-box">
        <img src="<%=path %>/images/a5.jpg" class="img-responsive"/>
    </div>
</div>
<div class="row none-box">
        <form id="buyForm" method="post" action="<%=path%>/pay/srcPay">
        <div class="form-group">
            <input type="number" class="form-control" id="money" name="money" onkeydown="money(this.value);" placeholder="请输入你要充值的金额" value="1"/>
        </div>
        <p>您共需支付<span id="totalCost" style="font-weight:bold;color:red;">0</span>元</p>
        <input type="submit" class="btn btn-primary col-xs-12 " value="确认支付" onclick="return pay();"/>
    </form>
</div>

</body>
<script src="<%=path %>/plugins/jquery-3.2.1.min.js"></script>
<script src="<%=path %>/plugins/bootstrap/bootstrap.min.js"></script>
<script src="<%=path %>/plugins/sweet-alert/sweet-alert.min.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script>
    function  money(value) {
        $("#totalCost").html(value);
    }
</script>
</html>
