<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/23
  Time: 10:49
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


    <title>瑞蓝酒店管理 - 500错误</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=path%>/static/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=path%>/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="<%=path%>/static/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/style.min.css?v=4.0.0" rel="stylesheet">
    <base target="_blank">

</head>

<body class="gray-bg">
<div class="middle-box text-center animated fadeInDown">
    <h1>500</h1>
    <h3 class="font-bold">服务器内部错误</h3>

    <div class="error-desc">
        服务器好像出错了...
        <br/>您可以返回主页看看
        <br/><a href="index.html" class="btn btn-primary m-t">主页</a>
    </div>
</div>
<script src="<%=path%>/static/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/static/js/bootstrap.min.js?v=3.3.5"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>
