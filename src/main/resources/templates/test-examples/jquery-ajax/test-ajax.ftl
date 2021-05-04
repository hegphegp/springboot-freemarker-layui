<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>form表单提交会跳转URL，此乃大忌。用ajax提交表单数据，并阻止表单提交</title>
    <script src="${Request.basePath!""}/plugins/jquery/v1.12.4/jquery.min.js"></script>
    <script src="${Request.basePath!""}/plugins/layui/v2.4.5/layui.all.js"></script>
    <script src="${Request.basePath!""}/libs/request.min.js"></script>
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
    $("#submitform").on("submit", function(ev) {
        new AjaxReq({
            type: "post",
            checkTokenExists: false,
            noLoginJumpLoginPage: false,
            url: '${Request.basePath!""}/v1/jquery-ajax/x-www-form-urlencoded',
            data: $('#submitform').serialize(),                   // data冒号后面接的是json对象
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", // 设置请求参数以什么方式传输，如application/json，application/x-www-form-urlencoded，application/form-data
            success:function(data) {
                if(data.code == 200 ) {
                    layui.layer.msg("修改成功！"+JSON.stringify(data));
                } else {
                    layui.layer.msg("修改失败！"+JSON.stringify(data));
                }
            }
        });

        new AjaxReq({
            type: "get",
            url: '${Request.basePath!""}/v1/jquery-ajax/get',
            checkTokenExists: false,
            noLoginJumpLoginPage: false,
            data: $('#submitform').serialize(),                   // data冒号后面接的是json对象
            success:function(data) {
                if(data.code == 200 ) {
                    layui.layer.msg("修改成功！"+JSON.stringify(data));
                } else {
                    layui.layer.msg("修改失败！"+JSON.stringify(data));
                }
            }
        });

        ev.preventDefault();
        // 或者return false
    });

</script>

</body>
</html>