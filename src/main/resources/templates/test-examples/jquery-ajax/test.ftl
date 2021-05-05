<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>form表单提交会跳转URL，此乃大忌。用ajax提交表单数据，并阻止表单提交</title>
    <script src="${Request.basePath!""}/plugins/jquery/v1.12.4/jquery.min.js"></script>
    <script src="${Request.basePath!""}/plugins/layui/v2.4.5/layui.all.js"></script>
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

        // jQuery.ajaxSetup 非常难用，里面加不了判断逻辑，想获取xhr的头部是否有Content-Type参数，动态加Content-Type都加不了，太难用了。之前对ajax二次封装的，比 jQuery.ajaxSetup 好用很多倍，看来官方封装得不好
        jQuery.ajaxSetup({
            async: true, //异步加载
            crossDomain: true,
            type: "post",
            dataType: "json",
            headers: {
                // 'Authorization': "000000000000",
                // 'Content-Type': 'application/json',
                'Accept': '*/*'
            },
            cache: false, // 不缓存数据
            complete: function (xhr) {
                // 如果httpStatus封装都返回200的话，接口返回的异常统一在complete捕获
                if (401==xhr.status) { // && location.pathname != "/views/login.html") {
                    layer.msg('请先登录系统', {shift: -1, time: 2000}, function () {
                        setTimeout(function() {
                            window.parent.location.reload(); //刷新父页面
                            window.parent.location.href = "./views/login.html";
                        }, 2000);
                    });
                    return;
                }

                // 是否是演示模式
                // var userType = xhr.getResponseHeader("userType"); //通过XMLHttpRequest取得响应头,sessionState
                // if (userType == 'temp') {
                //     layer.msg("演示模式，不允许操作！");
                // }

                // httpStatus大于400的抛错
                if (xhr.status>=400) {
                    var responseJSON = xhr.responseJSON;
                    if (responseJSON==null || responseJSON==undefined) {
                        console.log(xhr.responseText);
                        layui.layer.msg("服务内部错误， "+xhr.responseText);
                    } else {
                        var msg = responseJSON["msg"];
                        if (responseJSON==null || responseJSON==undefined) {
                            layui.layer.msg("服务内部错误");
                        } else {
                            layui.layer.msg(msg);
                        }
                    }
                }
                layui.data("aaaaaaaaaaa", {
                    key: "token",
                    value: xhr.responseText
                });
                console.log(layui.data("aaaaaaaaaaa"));
                console.log(layui.data("aaaaaaaaaaa")["token"]);
                layui.data('aaaaaaaaaaa', null); //删除aaaaaaaaaaa表
            },
            error: function(xhr, textStatus, errorThrown){
                // layui.layer.msg("请求对象XMLHttpRequest: "+XMLHttpRequest+"错误类型textStatus: "+textStatus+"异常对象errorThrown: "+errorThrown);
                // console.log("请求对象XMLHttpRequest: "+XMLHttpRequest+"错误类型textStatus: "+textStatus+"异常对象errorThrown: "+errorThrown);
                console.log(xhr);
                // console.log(textStatus);
                // console.log(errorThrown);
            },
            beforeSend: function(xhr) {
                // console.log(xhr);
                console.log(xhr);
                console.log("==>>   "+JSON.stringify(xhr));
                // console.log(xhr.ajaxSettings.headers['Content-Type']);
                // ;
                // var contentType = xhr.getRequestHeader.propertyName['Content-Type'];
                // if (contentType==null || contentType==undefined || contentType.trim().length==0) {
                //     console.log("========>>>>>>>>>>>")
                //     xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
                // }
                // 在这里校验本地有没有token
            }
        });


        $("#submitform").on("submit", function(ev) {
            $.ajax({
                url:'${Request.basePath!""}/v1/jquery-ajax/x-www-form-urlencoded',
                // data: {username:$("#username").val(), password:$("#password").val()},
                data: $('#submitform').serialize(),                   // data冒号后面接的是json对象
                type:'post',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8", // 设置请求参数以什么方式传输，如application/json，application/x-www-form-urlencoded，application/form-data
                success:function(data) {
                    if(data.code == 200 ) {
                        layui.layer.msg("修改成功！"+JSON.stringify(data));
                    } else {
                        layui.layer.msg("修改失败！"+JSON.stringify(data));
                    }
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
                success:function(data) {
                    if(data.code == 200 ) {
                        var layer = layui.layer;
                        layer.msg("修改成功！"+JSON.stringify(data));
                    } else {
                        var layer = layui.layer;
                        layer.msg("修改失败！"+JSON.stringify(data));
                    }
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