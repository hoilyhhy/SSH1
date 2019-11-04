<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房屋信息展示</title>
<script src="/jscss/jquery-1.9.1.min.js"></script>
<!-- Bootstrap -->
<link href="/jscss/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="/jscss/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
<div style="width: 80%;margin-left: 10%">
    <table class="table table-hover">
        <tr>
            <th colspan="7" style="text-align: center">房屋信息</th>
            <td><a <%--onclick="newUser();"--%> data-toggle="modal" data-target="#myModal" data-type="add">新增</a></td>
        </tr>
        <tr>
            <th style="display:none;">id</th>
            <th>序号</th>
            <th>房屋编号</th>
            <th>地址</th>
            <th>楼层</th>
            <th>类型</th>
            <th>操作</th>
        </tr>
       <c:forEach var="bd" items="${builds}" varStatus="status">
           <tr>
            <td style="display:none;">${bd.id}</td>
            <td>${status.index+1}</td>
            <td>${bd.bno}</td>
            <td>${bd.addr}</td>
            <td>${bd.floors}</td>
            <td>${bd.type}</td>
            <td><a  data-toggle="modal" data-target="#myModal" data-uid="${bd.id}" data-type="update">修改</a>
               |<a onclick="deleteUser(${bd.id})">删除</a>
           </td>

           </tr>
       </c:forEach>
    </table>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增房屋信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/addBuild" id="buildinfo" name="buildinfo" method="post">
                    <div class="form-group">
                        <label for="bno" class="col-sm-2 control-label">房屋编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="bno" name="bno" onblur="checkbno()" placeholder="房屋编号" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addr" class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="addr" name="addr" placeholder="地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="floors" class="col-sm-2 control-label">楼层</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="floors" name="floors" placeholder="楼层">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="type" name="type" placeholder="类型">
                        </div>
                    </div>
                    <input type="hidden" id="id" name="id" value="">
                    <input type="submit" class="btn btn-primary"  value="保存">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateOrSaveForm()">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function updateOrSaveForm(){
        alert(1);
       // $("#buildinfo").submit();//这里有个问题！！！！为什么不能直接提交？报400，用ajax就可以
        $.ajax({
            url:"/addBuild",
            type:"post",
            datatype:"text",
            data:{
                "bno":$("#bno").val(),
                "addr":$("#addr").val(),
                "floors":$("#floors").val(),
                "type":$("#type").val()
            },success:function(data){
                console.log("success"+data);
                $('#myModal').modal('hide');//关闭模态窗口
                window.location.reload();//刷新本页面
            },error:function(data){
                console.log("error"+data);
            }
        })
    }

    function checkbno(){
       $.ajax({
           url:"checkbno",
           type:"post",
           dataType:"text",
           data:{
               "bno":$("#bno").val()
           },
           success:function(data){
              if(data == "true"){
                  alert("已存在");
                  $("#bno").val("");
              }
           },
           error:function (data) {
               alert("请联系管理员");
           }

       })

    }
</script>
</html>
