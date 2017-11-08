<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-30
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="login_container"></div>
</body>
<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<script type="text/javascript">
    var obj = new WxLogin({
        /*
        *   id	是	第三方页面显示二维码的容器id
            appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
            scope	是	应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
            redirect_uri	是	重定向地址，需要进行UrlEncode
            state	否	用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
            style	否	提供"black"、"white"可选，默认为黑色文字描述。详见文档底部FAQ
            href	否	自定义样式链接，第三方可根据实际需求覆盖默认样式。详见文档底部FAQ
        * */
        id:"login_container",

        appid: "",
        scope: "",
        redirect_uri: "",
        state: "",
        style: "",
        href: ""
    });
    $.get(
        "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code",
        function(data){
            alert(data);
            /*
            * {
                "access_token":"ACCESS_TOKEN",
                "expires_in":7200,
                "refresh_token":"REFRESH_TOKEN",
                "openid":"OPENID",
                "scope":"SCOPE"
                }
            * */
        },
        "json"
    );
    $.get(
        "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID",
        function(data){
            /*
            *
            * {
                "openid":"OPENID",
                "nickname":"NICKNAME",
                "sex":1,
                "province":"PROVINCE",
                "city":"CITY",
                "country":"COUNTRY",
                "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
                "privilege":[
                "PRIVILEGE1",
                "PRIVILEGE2"
                ],
                "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"

                }
            * */
        },
        "json"
    );
</script>
</html>
