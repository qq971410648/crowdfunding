<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="zh-CN">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>广告管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 广告管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <jsp:include page="/WEB-INF/jsp/common/top.jsp"/>
                <li style="margin-left:10px;padding-top:8px;">
                    <button type="button" class="btn btn-default btn-danger">
                        <span class="glyphicon glyphicon-question-sign"></span> 帮助
                    </button>
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
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="condition" name="name" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" onclick="getAllAdvertisement()" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${pageContext.request.contextPath}/crowdfunding/to_AAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th>广告描述</th>
                                <th>状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>



                            <tbody id="tbody">

                                            <%--动态渲染--%>


                            </tbody>



                            <tfoot>
                            <tr >
                                <td colspan="4" align="center">
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
        getAllAdvertisement()
    });


    function getAllAdvertisement() {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/advertisement/getAllAdvertisement.do",
            data:{
                name:$("#condition").val()
            },
            success:function (result) {
                var pageData = result.pageInfo;
                var pageDataList = pageData.list;
                var content = "";
                $.each(pageDataList,function (i, n) {
                    content+='<tr>';
                    content+='<td>' + (i + 1) + '</td>';
                    content+='<td>'+(n.name)+'</td>';
                    content+='<td>'+(n.status)+'</td>';
                    content+='<td>';
                    content+='<button type="button" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-pencil"></i></button>';
                    content+='<button type="button" onclick="delAdvertisementById(' + n.id + ')" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i></button>';
                    content+='</td>';
                    content+='</tr>';
                });
                if (result) {
                    $("#tbody").html(content)
                }
            }
        })
    }

    function delAdvertisementById(id) {
        layer.confirm("您确定要删除吗",{icon:3,title:"提示"},function () {
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/advertisement/delAdvertisementById.do",
                data:{
                    id:id
                },
                success:function (res) {
                    if (res.success) {
                        layer.alert(res.message, function () {
                            location.href = "${pageContext.request.contextPath}/crowdfunding/to_Advertisement.htm";
                        });
                    }
                }
            })
        })
    }
</script>
</body>
</html>
