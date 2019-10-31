<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>登录页面</title>

  <!-- Bootstrap -->
  <link href="/jscss/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    body {
      padding-top: 40px;
      padding-bottom: 40px;
      background-color: #eee;
    }
    .form-signin {
      max-width: 356px;
      padding: 15px;
      margin: 0 auto;
    }
  </style>
</head>
<body>
<form class="form-signin" action="/login.do" method="post">
  <h2 class="form-signin-heading">Sign in</h2>
  <div class="form-group">
    <%--<label for="exampleInputEmail1">Email address</label>--%>

        ${param.msg}     <%--等价于：<%=request.getParameter("msg")%>--%>
    <input type="text" class="form-control" name="loginname" placeholder="用户名">
  </div>
  <div class="form-group">
   <%-- <label for="exampleInputPassword1">Password</label>--%>
    <input type="password" class="form-control" name="password" placeholder="密码">
  </div>
 <%-- <div class="form-group">
    <label for="exampleInputFile">File input</label>
    <input type="file" id="exampleInputFile">
    <p class="help-block">Example block-level help text here.</p>
  </div>--%>
  <div class="checkbox">
    <label>
      <input type="checkbox"> remember me
    </label>
  </div>
  <button type="submit" class="btn btn-lg btn-primary btn-block">登录</button>
</form>


</body>
</html>
