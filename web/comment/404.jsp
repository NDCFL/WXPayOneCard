<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/23
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>一卡通 - 404 页面</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=path%>/static/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=path%>/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="<%=path%>/static/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

</head>

<body class="gray-bg">
<div class="middle-box text-center animated fadeInDown">
    <h1>404</h1>
    <h3 class="font-bold">页面未找到！</h3>

    <div class="error-desc">
        抱歉，页面好像去火星了~
        <form class="form-inline m-t" role="form">
            <div class="form-group">
                <input type="email" class="form-control" placeholder="请输入您需要查找的内容 …">
            </div>
            <button type="submit" class="btn btn-primary">搜索</button>
        </form>
    </div>
</div>
<script src="<%=path%>/static/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/static/js/bootstrap.min.js?v=3.3.5"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>