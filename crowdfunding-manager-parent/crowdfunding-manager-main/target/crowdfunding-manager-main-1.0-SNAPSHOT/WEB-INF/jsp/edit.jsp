<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="zh-CN">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>修改用户信息</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <jsp:include page="/WEB-INF/jsp/common/top.jsp"/>
                <li style="margin-left:10px;padding-top:8px;">
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
                <jsp:include page="/WEB-INF/jsp/common/aside.jsp"/>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">

                    <%--表单数据 *********************************************************************--%>
                        <form role="form" id="form"  method="post">
                            <%--需要动态渲染的数据   ——————回显———————  --%>
                        </form>
                    <%--表单数据 *********************************************************************--%>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                </div>
                <div class="bs-callout bs-callout-info">
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/script/docs.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery/layer/layer.js"></script>

<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });

        doUpdate();
    });

    function doUpdate() {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/user/getUserById.do",
            data:{
                //id从页面得到
                "id":${param.id}
            },
            success:function (result) {
                var updateUser = result.data;//回显信息
                var content = "";
                content+='<div class="form-group">';
                content+='<input type="hidden" name="id" id="id" value="'+updateUser.id+'">';
                content+='<input type="hidden" name="createtime" id="createtime" value="'+updateUser.createtime+'">';
                content+='<input type="hidden" name="userpswd" id="userpswd" value="'+updateUser.userpswd+'">';
                content+='  <label for="loginacct">登陆账号</label>';
                content+='  <input type="text" class="form-control" name="loginacct" id="loginacct" value="'+updateUser.loginacct+'">';
                content+='</div>';
                content+='<div class="form-group">';
                content+='  <label for="username">用户名称</label>';
                content+='  <input type="text" class="form-control" name="username" id="username" value="'+updateUser.username+'">';
                content+='</div>';
                content+='<div class="form-group">';
                content+='  <label for="exampleInputEmail1">邮箱地址</label>';
                content+='  <input type="email" class="form-control" name="email" id="exampleInputEmail1" value="'+updateUser.email+'">';
                content+='  <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>';
                content+='</div>'
                content+='<button type="button" onclick="Doupdate()" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>';
                content+='<button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>';
                if (result.success) {
                    $("#form").html(content)
                } else {
                    layer.msg(result.message, {time: 1500, icon: 5, shift: 6});
                }
            }
        })
    }


    function Doupdate() {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/user/doUpdate.do",
            data:$("#form").serialize(),
            success:function (result) {
                if (result.success) {
                    layer.alert(result.message, function () {
                        location.href = "${pageContext.request.contextPath}/crowdfunding/to_User.htm";
                    });
                } else {
                    layer.msg(result.message,{time:1500,icon:5,shift:6})
                }
            }
        })
    }
</script>
</body>
</html>
