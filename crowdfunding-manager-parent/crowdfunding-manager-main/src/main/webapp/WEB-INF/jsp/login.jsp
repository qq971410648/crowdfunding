<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="zh-CN">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <title>用户登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <style>

    </style>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form class="form-signin" role="form" id="loginForm">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" name="loginacct" id="loginacct" placeholder="请输入登录账号" value="zhangsan" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" name="userpswd" id="userpswd" placeholder="请输入登录密码" value="123" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="checkbox">
            <label>
                <input id="checkbox" type="checkbox" name="remember" value="1"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
        <div align="center">
            <h4 style="color: red">${login_error}</h4>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery/layer/layer.js"></script>
<script>
    function dologin() {
            $.ajax({
                type:"post",
                data:{
                    "loginacct":$("#loginacct").val(),
                    "userpswd":$("#userpswd").val(),
                    "remember":$("#checkbox:checked").val()
                },
                url:"${pageContext.request.contextPath}/user/doLogin.do",
                success:function (result) {
                    if (result.success) {
                        location.href = "${pageContext.request.contextPath}/crowdfunding/to_main.htm";
                    } else {
                        layer.msg(result.message,{time:1500,icon:5,shift:6})
                    }
                }
            })
    }
</script>
</body>
</html>