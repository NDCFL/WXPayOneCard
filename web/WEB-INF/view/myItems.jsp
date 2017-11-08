<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/19
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一卡通充值记录列表</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=path%>/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=path%>/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path%>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%=path%>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/css/style.min.css?v=4.0.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>${userVo.userName}的一卡通充值记录列表</h5>
        </div>
        <div class="ibox-content">
            <table id="mytab" name="mytab" class="table table-hover"></table>
            <div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
                <%--<button id="btn_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#webAdd">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>充值
                </button>--%>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="webAdd" tabindex="-1" role="dialog" aria-labelledby="webAddLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="webAddTitle">
                    充值
                </h4>
            </div>
            <form class="form-horizontal" method="post" id="formadd">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">卡号：</label>
                        <div class="col-sm-8">
                            <input  name="title" minlength="2" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">金额：</label>
                        <div class="col-sm-8">
                            <input  name="number" minlength="2" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="add" class="btn btn-primary" data-dismiss="modal">
                        充值
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=path%>/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/js/bootstrap.min.js?v=3.3.5"></script>
<script src="<%=path%>/js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=path%>/js/plugins/validate/messages_zh.min.js"></script>
<script src="<%=path%>/js/demo/form-validate-demo.min.js"></script>
<script src="<%=path%>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=path%>/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="<%=path%>/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path%>/js/plugins/layer/layer.js"></script>
<script src="<%=path%>/js/jquery.formautoFill.js"></script>
</body>
<script>
    //生成用户数据
    $('#mytab').bootstrapTable({
        method: 'post',
        contentType: "application/x-www-form-urlencoded",//必须要有！！！！
        url:"/items/myItems/${cardId}",//要请求数据的文件路径
        striped: true, //是否显示行间隔色
        dataField: "res",
        sortable: true, //是否启用排序 sortOrder: "ID asc",
        sortOrder: "ID asc",
        pagination:true,//是否分页
        queryParamsType:'limit',//查询参数组织方式
        queryParams:queryParams,//请求服务器时所传的参数
        sidePagination:'server',//指定服务器端分页
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize:10,//单页记录数
        pageList:[10,20,30,40,50,60,70,80,90,100],//分页步进值
        clickToSelect: true,//是否启用点击选中行
        toolbarAlign:'right',//工具栏对齐方式
        buttonsAlign:'right',//按钮对齐方式
        toolbar:'#toolbar',//指定工作栏
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showExport: true,
        exportDataType: 'all',
        columns:[
            {
                title:'全选',
                field:'select',
                //复选框
                checkbox:true,
                width:25,
                align:'center',
                valign:'middle'
            },
            {
                title:'金额',
                field:'money',
                align:'center',
                sortable:true
            },
            {
                title:'充值时间',
                field:'createTime',
                align:'center',
                sortable:true,
                formatter : function(value){
                    var date = new Date(value);
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    var h = date.getHours();
                    var mi = date.getMinutes();
                    var ss = date.getSeconds();
                    return y + '-' +m + '-' + d+' '+h+':'+mi+':'+ss ;
                }
            }
            ,
            {
                title:'充值状态',
                field:'status',
                align:'center',
                formatter: function (value, row, index) {
                    if(value==0){
                        //表示激活状态
                        return '<i class="btn btn-primary">充值成功</i>';
                    }else{
                        //表示激活状态
                        return '<i class="btn btn-danger">充值失败</i>';
                    }
                }
            }
        ],
        locale:'zh-CN',//中文支持,
        responseHandler:function(res){
            if (res) {
                return {
                    "res" : res.rows,
                    "total" : res.total
                };
            } else {
                return {
                    "rows" : [],
                    "total" : 0
                };
            }
        }
    })
    //请求服务数据时所传参数
    function queryParams(params){
        return{
            //每页多少条数据
            pageSize: this.pageSize,
            //请求第几页
            pageIndex:this.pageNumber
        }
    }
</script>
</html>
