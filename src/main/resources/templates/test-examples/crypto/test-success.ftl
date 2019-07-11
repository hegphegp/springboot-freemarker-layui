<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>只有这一种方式,能力有限,没必要浪费生命去研究,如果想浪费生命,直接去S</title>
    <script src="${Request.dynamicProjectPath!""}/plugins/jquery/v1.12.4/jquery.min.js"></script>
    <script src="${Request.dynamicProjectPath!""}/plugins/crypto-js/3.1.9-1/crypto-js.min.js"></script>
    <script src="${Request.dynamicProjectPath!""}/plugins/layui/v2.4.5/layui.all.js"></script>
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

    function encryptAesCBC(word, key, iv) {
        var key = CryptoJS.enc.Utf8.parse(key);
        var iv = CryptoJS.enc.Utf8.parse(iv);
        var srcs = CryptoJS.enc.Utf8.parse(word);
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {iv:iv, mode:CryptoJS.mode.CBC, padding:CryptoJS.pad.Pkcs7});
        return encrypted.toString();
    }

    function decryptAESCBC(word, key, iv) {
        var key = CryptoJS.enc.Utf8.parse(key);
        var iv = CryptoJS.enc.Utf8.parse(iv);
        var decrypt = CryptoJS.AES.decrypt(word, key, {iv:iv, mode:CryptoJS.mode.CBC, padding:CryptoJS.pad.Pkcs7});
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();
    }

    function encryptAesECB(word, key) {
        var key = CryptoJS.enc.Utf8.parse(key);
        var srcs = CryptoJS.enc.Utf8.parse(word);
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB, padding:CryptoJS.pad.Pkcs7});
        return encrypted.toString();
    }

    function decryptAesECB(word, key) {
        var key = CryptoJS.enc.Utf8.parse(key);
        var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB, padding:CryptoJS.pad.Pkcs7});
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();
    }

    (function() {

        $("#submitform").on("submit", function(ev) {
            var key = "1234123412ABCDEF";
            var iv = "ABCDEF1234123412";
            var username = encryptAesCBC($("#username").val(), key, iv);
            var password = encryptAesCBC($("#password").val(), key, iv);
            console.log(username);
            console.log(password);
            var jsonData = {"username": username, "password": password};
            $.getJSON({
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                url:'${Request.dynamicProjectPath!""}/v1/crypto/aes-cbc-iv',
                data: JSON.stringify(jsonData),
                type:'post',
                cache:false,
                contentType:"application/json",
                success:function(data) {
                    if(data.code == 200 ) {
                        layui.layer.msg("返回的username==>> "+decryptAESCBC(data.data.username, key, iv)+"  返回的password==>>  "+decryptAESCBC(data.data.password, key, iv));
                    } else {
                        layui.layer.msg("修改失败！"+JSON.stringify(data));
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    layui.layer.msg("请求对象XMLHttpRequest: "+XMLHttpRequest+"错误类型textStatus: "+textStatus+"异常对象errorThrown: "+errorThrown);
                    console.log(errorThrown);
                }
            });

            var newUsername = encryptAesECB($("#username").val(), key);
            var newPassword = encryptAesECB($("#password").val(), key);
            console.log(newUsername);
            console.log(newPassword);
            var newJsonData = {"username": newUsername, "password": newPassword};
            $.getJSON({
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                url:'${Request.dynamicProjectPath!""}/v1/crypto/aes-ecb-no-iv',
                data: JSON.stringify(newJsonData),
                type:'post',
                cache:false,
                contentType:"application/json",
                success:function(data) {
                    if(data.code == 200 ) {
                        layui.layer.msg("返回的username==>> "+decryptAesECB(data.data.username, key)+"  返回的password==>>  "+decryptAesECB(data.data.password, key));
                    } else {
                        layui.layer.msg("修改失败！"+JSON.stringify(data));
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    layui.layer.msg("请求对象XMLHttpRequest: "+XMLHttpRequest+"错误类型textStatus: "+textStatus+"异常对象errorThrown: "+errorThrown);
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