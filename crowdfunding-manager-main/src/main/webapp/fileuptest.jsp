<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2019/12/17
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form id="form" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/file/upfile.do">
    <input type="file" name="file" multiple="multiple" ><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
