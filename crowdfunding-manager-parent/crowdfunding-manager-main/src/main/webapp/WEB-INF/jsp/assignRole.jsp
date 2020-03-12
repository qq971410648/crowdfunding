<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="zh-CN">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>分配角色</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/doc.min.css">
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
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label>未分配角色列表</label><br>
                            <select id="leftRoleList" class="form-control" multiple size="10" style="width:180px;overflow-y:auto;">
                                <%--动态渲染--%>
                            </select>
                        </div>
                        <div class="form-group">


                            <%--*****************************这就是那个   左右分配权限的按钮*****************************--%>
                            <ul>
                                <li id="leftToRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="rightToLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"></li>
                            </ul>
                            <%--*****************************这就是那个   左右分配权限的按钮*****************************--%>


                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label>已分配角色列表</label><br>
                            <select id="rightRoleList" class="form-control" multiple size="10"  style="width:180px;overflow-y:auto;">
                                <%--动态渲染--%>
                            </select>
                        </div>
                    </form>
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

        checkAssignedRoleById();
        checkNoAssignedRoleById();
        leftToRightBtn();
        rightToLeftBtn()
    });

    //用户从用户页面来到本页面，应该是携带了用户id的。一跳转过来，静态页面一加载完成我就发送ajax请求
    //根据用户id查看***********************已分配*****************************的角色信息
    function checkAssignedRoleById() {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/user/checkAssignedRole.do",
            data: {
                "id":${param.id}
            },
            success: function (result) {
                //已经分配的角色
                var assignedRoleList = result.list;
                var content = "";
                $.each(assignedRoleList, function (i, n) {
                    content += '<option value="' + (n.id) + '">' + (n.name) + '</option>';
                });
                if (result.success) {
                    //渲染动态数据,将已分配的角色渲染出来
                    $("#rightRoleList").html(content)
                } else {
                    alert(result.message)
                }
            }
        })
    }

    //根据用户id查看未分配的角色信息
    //根据用户id查看***********************未分配*****************************的角色信息
    function checkNoAssignedRoleById() {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/user/checkNoAssignedRole.do",
            data: {
                "id":${param.id}
            },
            success: function (result) {
                //未分配的角色
                var assignedRoleList = result.list;
                var content = "";
                $.each(assignedRoleList, function (i, n) {
                    content += '<option value="' + (n.id) + '">' + (n.name) + '</option>';
                });
                if (result.success) {
                    //渲染动态数据,将已分配的角色渲染出来
                    $("#leftRoleList").html(content)
                } else {
                    alert(result.message)
                }
            }
        })
    }

    //当用户点击 -> **************************************************************************************
    function leftToRightBtn() {
        $("#leftToRightBtn").click(function () {
            //获取未分配角色列表中被选中的角色
            var selectedOptions = $("#leftRoleList option:selected");

            var jsonObj = {
                userid: "${param.id}"
            };

            $.each(selectedOptions, function (i, n) {
                jsonObj["assignedRoleId[" + i + "]"] = this.value;
            });

            // alert(JSON.stringify(jsonObj));//{"userid":"1","assignedRoleId[0]":"2"}

            //发送ajax请求：用于实现分配角色的功能
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/leftToRight.do",
                data: jsonObj,//这样一来    我就得到了用户id   和一堆被希望分配的角色id，我需要做的就是  插入到t_user_role表
                success: function (result) {
                    if (result.success) {
                        $("#rightRoleList").append(selectedOptions);
                    }
                }
            })
        })
    }

    //当用户点击 <- **************************************************************************************
    function rightToLeftBtn() {
        $("#rightToLeftBtn").click(function () {
            //获取已分配角色列表中被选中的角色
            var selectedOptions = $("#rightRoleList option:selected");

            var jsonObj = {
                userid: "${param.id}"
            };

            $.each(selectedOptions, function (i, n) {
                jsonObj["assignedRoleId[" + i + "]"] = this.value;
            });

            //发送ajax请求：用于实现分配角色的功能
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/rightToLeft.do",
                data: jsonObj,//这样依赖    我就得到了用户id   和一堆被希望分配的角色id，我需要做的就是  插入到t_user_role表
                success: function (result) {
                    if (result.success) {
                        $("#leftRoleList").append(selectedOptions);
                    }
                }
            })
        })
    }

</script>
</body>
</html>
