<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="zh-CN">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>许可信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/doc.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/zTreeStyle.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 许可维护</a></div>
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
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree">

                        <%--&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&--%>
                        <%--树--%>


                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>没有默认类</h4>
                    <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>没有默认类</h4>
                    <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/script/docs.min.js"></script>
<script src="${pageContext.request.contextPath}/ztree/jquery.ztree.all-3.5.min.js"></script>


<%--权限那个树--%>
<script type="text/javascript">

    // 这个权限树    需要三洋东西     1.指定要渲染的dom   2. setting   3. zNodes(json数据)
    $(function () {
        //这个东西：开关左侧菜单那个
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

        var setting = {
            view: {
                addDiyDom: function (treeId, treeNode) {
                    var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                    //显示树形图标
                    if (treeNode.icon) {
                        icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background", "");
                    }
                },

                //鼠标悬停事件实现  addHoverDom必须与removeHoverDom 同时使用
                addHoverDom: function (treeId, treeNode) {   //设置自定义按钮组,在节点后面悬停显示增删改按钮组.
                    var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                    aObj.attr("href", "javascript:;"); // 取消当前链接事件.
                    if (treeNode.editNameFlag || $("#btnGroup" + treeNode.tId).length > 0) return;
                    var s = '<span id="btnGroup' + treeNode.tId + '">';
                    if (treeNode.level == 0) { //根节点
                        s += '<button class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id=' + treeNode.id + '\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></button>';
                    } else if (treeNode.level == 1) { //分支节点
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" onclick="window.location.href=\'${APP_PATH}/permission/toUpdate.htm?id=' + treeNode.id + '\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        if (treeNode.children.length == 0) {
                            s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission(' + treeNode.id + ',' + treeNode.name + ')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                        }
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id=' + treeNode.id + '\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if (treeNode.level == 2) { //叶子节点
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#"  onclick="window.location.href=\'${APP_PATH}/permission/toUpdate.htm?id=' + treeNode.id + '\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission(' + treeNode.id + ',' + treeNode.name + ')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                    }

                    s += '</span>';
                    aObj.after(s);
                },
                removeHoverDom: function (treeId, treeNode) {
                    $("#btnGroup" + treeNode.tId).remove();
                }
            }
        };

        $.ajax({
            url: "${pageContext.request.contextPath}/permission/doPermission.do",
            type: "post",
            success: function (result) {
                if (result.success) {
                    var zNodes = result.data;
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                } else {
                    alert("加载数据失败...");
                }
            }

        });

        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
</script>
<%--权限那个树--%>


</body>
</html>
