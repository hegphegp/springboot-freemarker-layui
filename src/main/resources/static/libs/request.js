/*
* 该工具依赖了layui
* type              请求的方式  默认为get
* url               发送请求的地址
* data              发送请求的参数
* isShowLoader      是否显示loader动画  默认为false
* dataType          返回JSON数据  默认为JSON格式数据
* callBack          请求的回调函数
*/
function AjaxReq(opts) {
    this.type                 = opts.type || "get";
    this.url                  = opts.url;
    this.data                 = opts.data || {};
    this.async                = (opts.async==null || opts.async==undefined)? true:opts.async; //异步加载
    this.crossDomain          = (opts.crossDomain==null || opts.crossDomain==undefined)? true:opts.crossDomain;
    this.dataType             = opts.dataType || "json";
    this.success              = opts.success || complete;
    this.error                = opts.success || error;
    this.complete             = opts.complete || complete;
    this.beforeSend           = opts.beforeSend || beforeSend;
    this.noLoginJumpLoginPage = (opts.noLoginJumpLoginPage==null || opts.noLoginJumpLoginPage==undefined)? true:opts.noLoginJumpLoginPage;
    this.checkTokenExists     = (opts.checkTokenExists==null || opts.checkTokenExists==undefined)? true:opts.checkTokenExists;
    this.contentType          = opts.contentType || 'application/json; charset=UTF-8';
    this.headers              = opts.headers || {'accept': 'application/json, text/javascript, text/html, application/xml'};
    this.init();
}

function complete(xhr) {
    dealErr(xhr);
}

function dealErr(xhr) {
    if (401==xhr.status) {
        layer.msg('请先登录系统', {time: 2000}, function () {
            if (this.noLoginJumpLoginPage) {
                window.parent.location.href = "./views/login.html";
            }
        });
        return;
    }

    if (xhr.status>=400) {
        var responseJSON = xhr.responseJSON;
        if (responseJSON==null || responseJSON==undefined) {
            console.log(xhr.responseText);
            layui.layer.msg("服务内部错误， "+xhr.responseText);
        } else {
            var msg = responseJSON["msg"];
            layui.layer.msg((responseJSON==null || responseJSON==undefined)? "服务内部错误":msg);
        }
    }
}

function error(xhr, textStatus, errorThrown) {
    dealErr(xhr);
}

function beforeSend(xhr) {
    if (this.checkToken) {
        xhr.setRequestHeader('token', layui.data("userInfo")["token"]);
    }
}

AjaxReq.prototype = {
    init: function() {
        if (this.checkTokenExists) {
            var token = layui.data("userInfo")["token"];
            if (token==null || token==undefined) {
                layer.msg('请先登录系统', {time: 2000}, function () {
                    if (this.noLoginJumpLoginPage) {
                        window.parent.location.href = "./views/login.html";
                    }
                });
                return;
            }
        }
        this.sendRequest();
    },
    sendRequest: function() {
        $.ajax({
            url: this.url,
            type: this.type,
            data: this.data,
            async: this.async,
            dataType: this.dataType,
            contentType: this.contentType,
            crossDomain: this.crossDomain,
            headers: this.headers,
            beforeSend: this.beforeSend,
            success: this.success,
            error: this.error,
            complete: this.complete,
            checkTokenExists: this.checkTokenExists
        });
    }
};
