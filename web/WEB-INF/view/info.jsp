<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-30
  Time: 13:39
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
    <title>一卡通</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=path%>/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=path%>/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path%>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row" >
        <div class="col-sm-4">
            <div class="contact-box" style="height: 500px">
                <a href="javascript:void(0);">
                    <div class="col-sm-4">
                        <div class="text-center">
                            <img alt="image" class="img-circle m-t-xs img-responsive" style="width:100px;height:100px;margin-left: 100px" src="${userVo.headIcon}">
                        </div>
                    </div>
                </a>
                <div class="col-sm-8">
                    <address style="text-align: center;font-size: larger">
                        <br/>
                        <b>${userVo.userName}</b><br/><br/>
                        <b>${userVo.phone}</b><br/><br/>
                        <b>${userVo.userCard}<a data-toggle="modal" href="form_basic.html#checkCardId"><i class="glyphicon glyphicon-pencil"></i></a></b>
                    </address>
                    <div style="float: left">
                        <a data-toggle="modal" class="btn btn-primary" href="form_basic.html#addMoney" style="color: white;margin-left: 50px">我要充值</a>
                    </div>
                    <div style="float: right">
                        <a class="btn btn-primary"  href="<%=path%>/items/itemsPage/${userVo.userCard}" style="color: white;margin-right: 50px">账单列表</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="checkCardId" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 b-r">
                        <form role="form" id="form" action="<%=path%>/login/updateCard">
                            <input  type="hidden" name="id" id="id" value="${userVo.id}"/>
                            <div class="form-group">
                                <label>姓名：</label>
                                <input type="text" name="userName" id="userName" value="${userVo.userName}" placeholder="请输入姓名" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>卡号：</label>
                                <input type="number" name="userCard" id="userCard" placeholder="请输入卡号" class="form-control">
                            </div>
                            <div>
                                <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>确认修改</strong></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="addMoney" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 b-r">
                        <form role="form" id="money" action="<%=path%>/pay/index">
                            <input  type="hidden" name="id"  value="${userVo.id}"/>
                            <div class="form-group">
                                <label>充值金额：</label>
                                <input type="number" name="userMoney"  placeholder="请输入充值金额" class="form-control">
                            </div>
                            <div>
                                <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>确认充值</strong></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="modal-form" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 b-r">
                        <h3 class="m-t-none m-b">我要绑卡</h3>
                        <form role="addCard"  id="addCard" method="post" action="<%=path%>/login/addCard">
                            <input  type="hidden" name="id" value="${userVo.id}" />
                            <div class="form-group">
                                <label>姓名：</label>
                                <input type="text" placeholder="请输入你的姓名" id="addUserName" class="form-control" name="userName">
                            </div>
                            <div class="form-group">
                                <label>卡号：</label>
                                <input type="number" placeholder="请输入卡号" id="addUserCard" class="form-control" name="userCard">
                            </div>
                            <div>
                                <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>绑定</strong></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=path%>/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/js/bootstrap.min.js?v=3.3.5"></script>
<script src="<%=path%>/js/bootstrapValidator.js"></script>
<script>
    var card = "${userVo.userCard}";
    if(card=='暂缺'){
        $('#modal-form').modal({
            keyboard: true
        })
    }
</script>
<script>
    $('#form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userName: {
                message: '姓名验证失败',
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '姓名长度必须为2-20位'
                    }
                }
            },
            userCard: {
                message: '卡号验证失败',
                validators: {
                    notEmpty: {
                        message: '卡号不能为空'
                    },
                    stringLength: {
                        min: 18,
                        max: 18,
                        message: '卡号长度必须为18位'
                    }
                }
            }
        }
    });
    $('#money').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userMoney: {
                message: '金额验证失败',
                validators: {
                    notEmpty: {
                        message: '金额不能为空'
                    }
                }
            }
        }
    });
    $('#addCard').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userName: {
                message: '姓名验证失败',
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '姓名长度必须为2-20位'
                    }
                }
            },
            userCard: {
                message: '卡号验证失败',
                validators: {
                    notEmpty: {
                        message: '卡号不能为空'
                    },
                    stringLength: {
                        min: 18,
                        max: 18,
                        message: '卡号长度必须为18位'
                    }
                }
            }
        }
    });
</script>
</body>
</html>
