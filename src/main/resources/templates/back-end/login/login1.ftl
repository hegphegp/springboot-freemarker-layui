<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <link rel="stylesheet" href="${Request.basePath!""}/plugins/layui/v2.4.5/css/layui.css"/>
    <link rel="stylesheet" href="${Request.basePath!""}/easyweb/css/login.css?v=312">
    <!--[if lt IE 9]>
    <script src="${Request.basePath!""}/plugins/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="${Request.basePath!""}/plugins/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        if (window != top) {
            top.location.replace(location.href);
        }
    </script>
</head>
<body>
<div class="login-wrapper">
    <div class="login-header"> EasyWeb后台开发框架 </div>
    <div class="login-body">
        <div class="layui-card">
            <div class="layui-card-header">
                <i class="layui-icon layui-icon-engine"></i>&nbsp;&nbsp;用户登录
            </div>
            <form class="layui-card-body layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label class="layui-form-label"><i class="layui-icon layui-icon-username"></i></label>
                    <div class="layui-input-block">
                        <input name="username" type="text" placeholder="账号" class="layui-input"
                               lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i></label>
                    <div class="layui-input-block">
                        <input name="password" type="password" placeholder="密码" class="layui-input"
                               lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i class="layui-icon layui-icon-vercode"></i></label>
                    <div class="layui-input-block">
                        <div class="layui-row inline-block">
                            <div class="layui-col-xs7">
                                <input name="code" type="text" placeholder="验证码" class="layui-input"
                                       autocomplete="off" lay-verType="tips" lay-verify="required" required/>
                            </div>
                            <div class="layui-col-xs5" style="padding-left: 6px;">
                                <img class="login-captcha" src="${Request.basePath!""}/captcha">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <a href="javascript:;" class="layui-link">帐号注册</a>
                    <a href="javascript:;" class="layui-link pull-right">忘记密码？</a>
                </div>
                <div class="layui-form-item">
                    <button lay-filter="login-submit" class="layui-btn layui-btn-fluid" lay-submit>登 录</button>
                </div>
                <div class="layui-form-item login-other">
                    <label>第三方登录</label>
                    <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                    <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                    <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
                </div>
            </form>
        </div>
    </div>

    <div class="login-footer">
        <p>© 2019 easyweb.vip 版权所有</p>
        <p>
            <span><a href="https://easyweb.vip" target="_blank">获取授权</a></span>
            <span><a href="https://easyweb.vip/doc/" target="_blank">开发文档</a></span>
            <span><a href="https://demo.easyweb.vip/spa/" target="_blank">单页面版</a></span>
        </p>
    </div>
</div>

<script type="text/javascript" src="${Request.basePath!""}/plugins/layui/v2.4.5/layui.js"></script>
<script>
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;

        // 表单提交
        form.on('submit(login-submit)', function (obj) {
            layer.msg(JSON.stringify(obj.field), {icon: 1,});
            return false;
        });

        // 图形验证码
        $('.login-captcha').click(function () {
            this.src = '${Request.basePath!""}/captcha?t=' + (new Date).getTime();
        });
    });
</script>
</body>
</html>