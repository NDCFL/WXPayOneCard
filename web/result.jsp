<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<h1>登录成功</h1>
用户名称:${user.name}
<c:if test="${user.gender==0}">
    性别：未定义
</c:if>
<c:if test="${user.gender==1}">
    性别：男
</c:if>
<c:if test="${user.gender==2}">
    性别：女
</c:if>
成员所属部门：${user.department}
职位信息：${user.position}
头像<img src="${user.avatar}"/>
成员手机号:${user.mobile}
邮箱：${user.email}
</body>
</html>
