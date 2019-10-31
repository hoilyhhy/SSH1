<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/26
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>报500跳转页面</title>
</head>
<body>
错误码： <%=request.getAttribute("javax.servlet.error.status_code")%> <br>
信息： <%=request.getAttribute("javax.servlet.error.message")%> <br>
异常： <%=request.getAttribute("javax.servlet.error.exception_type")%> <br>

</body>
</html>
