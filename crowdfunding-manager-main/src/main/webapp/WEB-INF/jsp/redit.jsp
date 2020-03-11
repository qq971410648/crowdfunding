<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="zh-CN">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>更新角色信息</title>

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
    <script src="${pageContext.request.contextPath}/jquery/layer/layer.js"></script>
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
                    <form role="form" id="form" action="${pageContext.request.contextPath}/user/doUpdate.do" method="post">

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
            url:"${pageContext.request.contextPath}/role/getRoleById.do",
            data:{
                "id":${param.id}
            },
            success:function (result) {
                var updateUser = result.data;//回显信息
                var content = "";
                content+='<div class="form-group">';
                content+='<input type="hidden" name="id" id="id" value="'+updateUser.id+'">';
                content+='  <label for="name">登陆账号</label>';
                content+='  <input type="text" class="form-control" name="name" id="name" value="'+updateUser.name+'">';
                content+='</div>';
                content+='<button type="button" onclick="Doupdate()" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>';
                content+='<button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>';
                if (result.success) {
                    $("#form").html(content)
                }
            }
        })
    }


    function Doupdate() {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/role/doUpdate.do",
            data:$("#form").serialize(),
            success:function (result) {
                if (result.success) {
                    location.href="${pageContext.request.contextPath}/crowdfunding/to_Role.htm";
                }
            }
        })
    }
</script>
</body>
</html>
