<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<li style="padding-top:8px;">
    <div class="btn-group">
        <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
            <i class="glyphicon glyphicon-user"></i> ${username.username} <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
            <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
            <li class="divider"></li>
            <li><a href="javascript:doLogout()"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
        </ul>
    </div>
</li>
<li style="margin-left:10px;padding-top:8px;">
    <button type="button" class="btn btn-default btn-danger">
        <span class="glyphicon glyphicon-question-sign"></span> 帮助
    </button>
</li>
<script>
    function doLogout() {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/user/doLogout.do",
            success:function (result) {
                if (result.success) {
                    location.href="${pageContext.request.contextPath}/crowdfunding/to_Login.htm";
                }
            }
        })
    }
</script>