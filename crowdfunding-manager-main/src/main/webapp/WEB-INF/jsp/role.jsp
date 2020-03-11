<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="zh-CN">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>查看角色信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <jsp:include page="/WEB-INF/jsp/common/top.jsp"/>
                <li style="margin-left:10px;padding-top:8px;">
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 角色列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="select" name="select" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" onclick="getAllRole()" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='${pageContext.request.contextPath}/crowdfunding/to_RAdd.htm'">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>


                            <tbody id="tbody">

                            <%--需要渲染的数据--%>

                            </tbody>


                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li class="disabled"><a href="#">上一页</a></li>
                                        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">下一页</a></li>
                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
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
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });

        getAllRole();
    });

    $("tbody .btn-success").click(function () {
        window.location.href = "assignPermission.html";
    });

    function getAllRole() {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/role/getAllRole.do",
            data: {
                "page": 1,
                "size": 20,
                "name": $("#select").val()
            },
            success: function (result) {
                var pageData = result.pageInfo;//得到ajaxJson对象中的pageInfo对象   pageInfo对象里面有PageHelper的各种数据
                var pageDataList = pageData.list;//得到每页的数据
                var content = '';
                $.each(pageDataList, function (i, n) {
                    content += '<tr>';
                    content += '<td>' + (i + 1) + '</td>';
                    content += '<td><input type="checkbox"></td>';
                    content += '<td>' + (n.name) + '</td>';
                    content += '<td>';
                    content += '<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                    content += '<button type="button" onclick="deUpdate(' + n.id + ')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                    content += '<button type="button" onclick="doDel(' + n.id + ')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                    content += '</td>';
                    content += '</tr>';
                });
                if (result.success) {
                    $("#tbody").html(content)
                }
            }
        });
    }

    function deUpdate(id) {
        location.href = "${pageContext.request.contextPath}/crowdfunding/to_RUpdate.htm?id=" + id;
    }

    function doDel(id) {
        layer.confirm("确认要删除吗", {icon: 2, title: '提示'}, function (cindex) {
            $.ajax({
                type: "post",
                data: {
                    "id": id
                },
                url: "${pageContext.request.contextPath}/role/doDel.do",
                success: function (data) {
                    if (data.success) {
                        layer.msg(data.message, {time: 1000, icon: 5, shift: 6});
                        location.href = "${pageContext.request.contextPath}/crowdfunding/to_Role.do";
                    } else {
                        layer.msg(data.message, {time: 1000, icon: 5, shift: 6});
                    }
                }
            })

        })
    }
</script>
</body>
</html>
