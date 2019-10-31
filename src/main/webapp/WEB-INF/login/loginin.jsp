<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
    <style>
        .leftdiv{
            height: 10%;
            text-align:center;
        }
        .leftspan{
        font: inherit;
            color:#fff;
            text-align:center;
            position: relative;
            top: 50%;
        }
    </style>

    <!-- Bootstrap -->
    <link href="/jscss/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/jscss/jquery.min.js"></script>
    <script type="text/javascript">
        //点击a标签
        $(document).ready(function(){
            $("a").click(function(){
                $(this).parent().siblings().removeClass("active");//点击的a元素的父元素de所有同级元素，移除class为active
                $(this).parent().attr("class","active");
            });
        });
        function jump(url){

            document.getElementById('mainIframe').src=url;

            // $("#mainIframe").attr("src",url);
        }
    </script>
</head>
<body>
<div style="width: 100%">
    <div style="width: 20%;height:100%;float: left;background-color:rgb(64, 77, 81);">
        <div class="leftdiv" ><span class="leftspan">测试系统</span></div>
        <div class="leftdiv" ><span class="leftspan">${userEntity.username}</span></div>
        <div class="leftdiv" ><span class="leftspan">${msg}</span></div>
        <div class="leftdiv" ><span class="leftspan"><a href="javascript:void(0);" onclick="jump('/usersinfo.do')">修改用户名</a></span></div>
        <ul class="nav nav-pills nav-stacked">
            <li role="presentation" <%--class="active"--%>><a href="#" onclick="jump('/usersinfo.do')" >修改用户名</a></li>
            <li role="presentation" ><a href="#" onclick="jump('/getBuilds')">房屋信息管理</a></li>
            <li role="presentation"><a href="#">Messages</a></li>
        </ul>
    </div>
    <div style="width: 80%;height:100%;float: right;">
        <div style="height: 10%;background-color: rgb(64, 77, 81);color: #fff;">
            <span >右上部</span>
        </div>
        <div style="height: 90%;">
            <iframe id="mainIframe" src="https://www.sogou.com"  style="height: 100%;width: 100%;"></iframe>
        </div>
    </div>
</div>
</body>
</html>
