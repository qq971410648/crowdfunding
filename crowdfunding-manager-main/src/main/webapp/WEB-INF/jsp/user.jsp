<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="zh-CN">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>查看用户信息</title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/pagination.css"/>
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
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 用户列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;" id="form" action="#" method="post">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" id="loginacct" placeholder="请输入查询账户"
                                       name="condition">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning" onclick="doGet()"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                        <%--<button type="button" class="btn btn-warning" onclick="select2()"><i class="glyphicon glyphicon-search"></i> 查询 </button>--%>
                        <%--<button type="button" class="btn btn-warning" onclick="$('#form').submit()"><i class="glyphicon glyphicon-search"></i> 查询</button>--%>
                        <%--<button type="submit" class="btn btn-warning" onclick="submit()"><i class="glyphicon glyphicon-search"></i> 查询</button>--%>
                    </form>
                    <button type="button" class="btn btn-danger" onclick="batchDel()"
                            style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="toAdd()"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>

                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <form method="post" id="uform">
                            <table class="table  table-bordered">
                                <thead>
                                <tr>
                                    <th width="30">#</th>
                                    <th width="30"><input type="checkbox"></th>
                                    <th>账号</th>
                                    <th>名称</th>
                                    <th>角色</th>
                                    <th>邮箱地址</th>
                                    <th width="100">操作</th>
                                </tr>
                                </thead>

                                <tbody id="tbody">
                                <%--异步加载数据--%>
                                </tbody>

                                <tfoot>
                                <tr>
                                    <td colspan="6" align="center">
                                        <%--<ul class="pagination">--%>
                                        <%--<li class="disabled"><a href="#">上一页</a></li>--%>
                                        <%--<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a>--%>
                                        <%--</li>--%>
                                        <%--<li><a href="#">2</a></li>--%>
                                        <%--<li><a href="#">3</a></li>--%>
                                        <%--<li><a href="#">4</a></li>--%>
                                        <%--<li><a href="#">5</a></li>--%>
                                        <%--<li><a href="#">下一页</a></li>--%>
                                        <%--</ul>--%>

                                        <%--采用pagenation--%>
                                        <div id="Pagination" class="pagination">
                                            <!-- 这里显示分页 -->
                                        </div>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </form>
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
<script src="${pageContext.request.contextPath}/lib/jquery.pagination.js"></script>
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
        page = 1;
        doGet(page);
    });
    $("tbody .btn-success").click(function () {
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function () {
        window.location.href = "edit.html";
    });

    //分页查询所有用户信息
    function doGet(page) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/user/doGet.do",
            data: {
                "page": page,
                "size": 3,
                "loginacct": $("#loginacct").val()
            },
            success: function (data) {
                if (data.success) {
                    var pageData = data.pageInfo;//得到ajaxJson对象中的pageInfo对象   pageInfo对象里面有PageHelper的各种数据   其中list：表示当前页的数据
                    var pageDataList = pageData.list;//得到当前页的数据，数据：当前页的所有用户
                    var content = "";

                    $.each(pageDataList, function (i, n) {
                        var text = n.roles;
                        content += '<tr>';
                        content += '	<td>' + (i + 1) + '</td>';
                        content += '	<td><input type="checkbox" id="checkbox" name="userIds" value=' + (n.id) + '></td>';
                        content += '	<td>' + (n.loginacct) + '</td>';
                        content += '	<td>' + (n.username) + '</td>';
                        $.each(text, function (j, k) {
                            content +=
                                '<td>' +
                                k.name
                                + '</td>';
                        });
                        content += '	<td>' + (n.email) + '</td>';
                        content += '	<td>';
                        content += '		<button type="button" onclick="toAssignRole(' + n.id + ')"  class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        content += '		<button type="button" onclick="getUserById(' + n.id + ')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        content += '		<button type="button" onclick="deleteUser(' + n.id + ')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                        content += '	</td>';
                        content += '</tr>';
                    });
                    $("#tbody").html(content);

                    var num_entries = pageData.total;  //总记录数
                    var initPagination = function () {
                        // 显示分页导航
                        $("#Pagination").pagination(num_entries, { //调用插件的函数
                            num_edge_entries: 1, //边缘页数
                            num_display_entries: 4, //主体页数(不能小于等于3)
                            callback: pageselectCallback(pageData.pageNum-1),   //页码选择之后的回调操作
                            items_per_page: pageData.pageSize, //每页显示条数
                            current_page: pageData.pageNum-1,//当前页
                            prev_text: "上一页",
                            next_text: "下一页"
                        });
                    }();  //函数后增加小括号,表示执行这个函数.

                    function pageselectCallback(page_index, jq) {
                        //page_index从0 开始
                        page = page_index+1;
                        // doGet(page);
                        return false;
                    }
                } else {
                    layer.alert(data.message)
                }
            }
        });
    }

    function deleteUser(id) {
        layer.confirm("您确认要删除吗", {icon: 2, title: '提示'}, function () {
            $.ajax({
                type: "POST",
                data: {
                    // "id": id   $("#form").serialse()  得到得就是   id:id  name:name这种形式。  键没有""
                    id: id //data里面""可加可不加
                },
                url: "${pageContext.request.contextPath}/user/doDel.do",
                success: function (data) {
                    if (data.success) {
                        layer.alert(data.message, function () {
                            location.href = "${pageContext.request.contextPath}/crowdfunding/to_User.htm";
                        });
                    } else {
                        layer.alert(data.message);
                    }
                }
            })
        })
    }


    //批量删除
    function batchDel() {
        //:checked  用于获取复选框被选中的选项
        //根据id选择器获取id为checkbox的复选框里面被选中的选项
        var checkboxChecked = $("#checkbox:checked");
        var param = "";
        $.each(checkboxChecked, function (i, n) {
            if (i!=0) {
                param += "&";
            }
            param += "id=" + n.value;
        });
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/user/batchDel.do",
            // data: $("#uform").serialize(),
            data: param,
            success: function (data) {
                if (data.success) {
                    layer.alert(data.message, function () {
                        location.href = "${pageContext.request.contextPath}/crowdfunding/to_User.htm";
                    });
                } else {
                    layer.alert(data.message);
                }
            }
        })
    }

    function getUserById(id) {
        location.href = "${pageContext.request.contextPath}/crowdfunding/to_update.htm?id=" + id;
    }

    function toAdd() {
        location.href = "${pageContext.request.contextPath}/crowdfunding/to_Add.htm";
    }

    function toAssignRole(id) {
        location.href = "${pageContext.request.contextPath}/crowdfunding/to_AssignRole.htm?id=" + id;
    }

</script>
</body>
</html>
