<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>form表单提交会调整URL，此乃大忌。用ajax提交表单数据，并阻止表单提交</title>
    <script src="${Request.basePath!""}/plugins/jquery/v1.12.4/jquery.min.js"></script>
    <script src="${Request.basePath!""}/plugins/layui/v2.4.5/layui.all.js"></script>
</head>
</head>
<body>

<form action="" id='submitform'>
    username: <br>
    <input type="text" id="username" name="username" value="" required="required"> <br>
    password: <br>
    <input type="text" id="password" name="password" value="" required="required"> <br><br>
    <input type="submit" value="Submit">
</form>

<script type="text/javascript" language="javascript">
    (function() {
        $("#submitform").on("submit", function(ev) {
            $.ajax({
                url:'${Request.basePath!""}/v1/jquery-ajax/x-www-form-urlencoded',
                // data: {username:$("#username").val(), password:$("#password").val()},
                data: $('#submitform').serialize(),                   // data冒号后面接的是json对象
                type:'post',
                cache:false,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8", // 设置请求参数以什么方式传输，如application/json，application/x-www-form-urlencoded，application/form-data
                success:function(data) {
                    if(data.code == 200 ) {
                        layui.layer.msg("修改成功！"+JSON.stringify(data));
                    } else {
                        layui.layer.msg("修改失败！"+JSON.stringify(data));
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    layui.layer.msg("请求对象XMLHttpRequest: "+XMLHttpRequest+"错误类型textStatus: "+textStatus+"异常对象errorThrown: "+errorThrown);
                    console.log(errorThrown);
                }
            });
            // 阻止submit表单提交
            ev.preventDefault();
            // 或者return false
            // return false;
        });

        // get请求
        $("#submitform").on("submit", function(ev) {
            $.ajax({
                url:'${Request.basePath!""}/v1/jquery-ajax/get',
                // data: {username:$("#username").val(), password:$("#password").val()},
                data: $('#submitform').serialize(),                   // data冒号后面接的是json对象
                type:'get',
                cache:false,
                success:function(data) {
                    if(data.code == 200 ) {
                        var layer = layui.layer;
                        layer.msg("修改成功！"+JSON.stringify(data));
                    } else {
                        var layer = layui.layer;
                        layer.msg("修改失败！"+JSON.stringify(data));
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert("请求对象XMLHttpRequest: "+XMLHttpRequest);
                    alert("错误类型textStatus: "+textStatus);
                    alert("异常对象errorThrown: "+errorThrown);
                    console.log(errorThrown);
                }
            });
            // 阻止submit表单提交
            ev.preventDefault();
            // 或者return false
            // return false;
        });
    })()

</script>

</body>
</html>