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
    <title>一卡通充值支付</title>
    <link href="<%=path %>/js/sweet-alert/sweet-alert.css" rel="stylesheet"/>
</head>
<body>
</body>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/sweet-alert/sweet-alert.min.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script>
    $(function() {
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
            onBridgeReady();
        }
    });

    function onBridgeReady() {
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId": "${appId}",
                "timeStamp": "${timeStamp}",
                "nonceStr": "${nonceStr}",
                "package": "${packages}",
                "signType": "MD5",
                "paySign": "${paySign}"
            }, function (res) {
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                    swal("支付成功", "", "success");
                } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
                    swal("取消支付", "", "warning");
                } else if (res.err_msg == "get_brand_wcpay_request:fail") {
                    swal("支付失败", "", "error");
                }
            });
    }
</script>
</html>
