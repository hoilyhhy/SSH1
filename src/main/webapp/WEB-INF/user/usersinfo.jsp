<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/jscss/jquery-1.9.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="/jscss/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/jscss/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
<div style="width: 80%;margin-left: 10%">
    <table class="table table-hover">
        <tr>
            <th colspan="7" style="text-align: center">人员信息表</th>
            <td><a <%--onclick="newUser();"--%> data-toggle="modal" data-target="#myModal" data-type="add">新增</a></td>
        </tr>
        <tr>
            <th style="display:none;">id</th>
            <th>编号</th>
            <th>登录名</th>
            <th>用户名</th>
            <th>密码</th>
            <th>用户类型</th>
            <th>最后操作时间</th>
            <th>最后操作者</th>
            <th>操作</th>
        </tr>
        <c:forEach begin="0" end="${fn:length(userEntityList)}" var="userEntity" items="${userEntityList}" varStatus="status">
        <tr>
            <td style="display:none;">${userEntity.id}</td>
            <td>${status.index+1}</td>
            <td>${userEntity.loginname}</td>
            <td>${userEntity.username}</td>
            <td>${userEntity.password}</td>
            <td>${userEntity.type}</td>
            <td>${userEntity.lasthandledatetime}</td>
            <td>${userEntity.lasthandleuser}</td>
            <td><a  data-toggle="modal" data-target="#myModal" data-id="${userEntity.id}" data-type="update">修改</a>
                |<a onclick="deleteUser(${userEntity.id})">删除</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>

<!-- Button trigger modal -->
<%--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    Launch demo modal
</button>--%>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增用户信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" action="/updateuserinfo" id="userinfo">
                    <div class="form-group">
                        <label for="loginname" class="col-sm-2 control-label">登录名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="loginname" name="loginname" onblur="checkloginname()" placeholder="登录名" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="password" name="password" placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">用户类型</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="type" name="type" placeholder="用户类型">
                        </div>
                    </div>
                    <div class="form-group" id="addBuild"><%----%>

                        <%--<div class="col-sm-10" >--%>
                           <%--<input type="checkbox" class="col-sm-2" name="buildings[0].id" value="">--%>
                      <%--  </div>--%>
                    </div>
                   <%-- <input type="hidden" id="id" name="id" value="">--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateOrSaveForm()">保存</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#myModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('id') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);

        if(button.data('type')== "update") {
            $("#addBuild").html("");
            $.ajax({
                url:"/getBuildsJson",
                data:{},
                dataType:"json",
                type:"post",
                async: false,
                success:function(data){
                    var appendhtml = "";
                    for(var i = 0 ;i < data.length ; i++){
                        appendhtml +=  "<input type=\'checkbox\' class=\"col-sm-2\" name=\"buildings["+i+"].id\" value=\""+data[i].id+"\"><span class=\"col-sm-4\">编号:"+data[i].bno+"</span></br>";
                    }
                    $("#addBuild").append(
                        "<label  class=\"col-sm-2 control-label\" >拥有房屋</label><div class=\"col-sm-10\">"+appendhtml+"</div>"
                    )
                },error:function(data){
                    $("#addBuild").append("<div class=\"col-sm-10\">无法加载房屋信息，请联系管理员！</div>");
                }
            })

                modal.find('#id').val(recipient);
                $.ajax({
                    data: {
                        id: recipient
                    },
                    dataType: "json",
                    type: "post",
                    url: "/getUserinfo",
                    async: false,
                    success: function (data) {
                        modal.find('.modal-title').text("更新信息")
                        modal.find('.modal-body #username').val(data.username);
                        $("#loginname").val(data.loginname);
                        modal.find('#password').val(data.password);
                        modal.find('#type').val(data.type);
                        for(var i=0;i<data.bid.length;i++){
                            modal.find("#addBuild input[value='"+data.bid[i]+"']").attr("checked", true);
                            console.log(data.bid[i]);
                        }

                    }
                });
        }else if(button.data("type") == "add"){
            modal.find('#id').val("");
            modal.find('.modal-title').text("新增人员信息")
            modal.find('.modal-body #username').val("");
            $("#loginname").val("");
            modal.find('#password').val("");
            modal.find('#type').val("");
            $("#addBuild").html("");
           $.ajax({
                url:"/getBuildsJson",
                data:{},
                dataType:"json",
                type:"post",
                async: false,
                success:function(data){
                   var appendhtml = "";
                   for(var i = 0 ;i < data.length ; i++){
                       appendhtml +=  "<input type=\'checkbox\' class=\"col-sm-2\" name=\"buildings["+i+"].id\" value=\""+data[i].id+"\"><span class=\"col-sm-4\">编号:"+data[i].bno+"</span></br>";
                   }
                     $("#addBuild").append(
                         "<label  class=\"col-sm-2 control-label\" >拥有房屋</label><div class=\"col-sm-10\">"+appendhtml+"</div>"
                     )
                },error:function(data){
                    $("#addBuild").append("<div class=\"col-sm-10\">无法加载房屋信息，请联系管理员！</div>");
                }
            })
        }
    })


    function updateOrSaveForm(){
        //更新
     /*   if($("#id").val() != 'undefined' && $("#id").val() != ""){
       $.ajax({
            data:{
                "loginname": $("#loginname").val(),
                "password":$("#password").val(),
                "username":$("#username").val(),
                "type":$("#type").val(),
                "id":$("#id").val()
            },
            dataType:"text",
            type:"post",
            url:"/updateuserinfo",
            async:false,
            success:function(data){
                alert("update  --- success");
            },error:function(data){
                //if(data.status == 200){
                    $('#myModal').modal('hide');//关闭模态窗口
                    window.location.reload();//刷新本页面
              //  }

            }
        });
    }else{*///新增
            $("#userinfo").submit();
            /*$.ajax({
                data:{
                    "loginname": $("#loginname").val(),
                    "password":$("#password").val(),
                    "username":$("#username").val(),
                    "type":$("#type").val()
                },
                dataType:"json",
                type:"post",
                url:"/updateuserinfo",
                async:false,
                success:function(data){
                    alert("success");
                },error:function(data){
                 //   if(data.status == 200){
                        $('#myModal').modal('hide');//关闭模态窗口
                        window.location.reload();//刷新本页面
                   // }

                }
            });*/
       /* }*/
    }

    function deleteUser(id){
        $.ajax({
            data:{
                "id":id
            },
            type:"post",
            datatype:"json",
            url:"/deleteUser",
            success:function(data){
                window.location.reload();
            },error:function(data){
                if(data.status == 200){
                    window.location.reload();
                }
            }

        });
    }

    function checkloginname() {
        $.ajax({
            data:{
                "loginname":$("#loginname").val()
            },
            dataType:"json",
            type:"post",
            url:"/checkloginname",
            success:function(data){
                if(data == true){
                    alert("用户名已存在");
                    $("#loginname").val("");
                }
            },error:function(data){
                alert("error");
            }

        });
    }
    function newUser(){
        window.open ('page.html','newwindow','height=100,width=400,top=20%,left=20%,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
    }
</script>
</body>
</html>
