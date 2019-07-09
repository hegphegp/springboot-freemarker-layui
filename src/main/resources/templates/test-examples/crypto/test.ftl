<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crypto加解密</title>
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

    var key = CryptoJS.enc.Utf8.parse("1234123412ABCDEF");  //十六位十六进制数作为密钥
    var iv = CryptoJS.enc.Utf8.parse('ABCDEF1234123412');   //十六位十六进制数作为密钥偏移量

    //解密方法
    function aesDecrypt(word) {
        var encryptedHexStr = CryptoJS.enc.Hex.parse(word);
        var srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr);
        var decrypt = CryptoJS.AES.decrypt(srcs, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 });
        var decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
        return decryptedStr.toString();
    }

    //加密方法
    function aesEncrypt(word) {
        var srcs = CryptoJS.enc.Utf8.parse(word);
        var encrypted = CryptoJS.AES.encrypt(srcs, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 });
        return encrypted.ciphertext.toString();
    }

    (function() {
        $("#submitform").on("submit", function(ev) {
            var username = aesEncrypt($("#username").val());
            console.log(username);
            var password = aesEncrypt($("#password").val());
            console.log(password);
            $.ajax({
                url:'${Request.dynamicProjectPath!""}/v1/crypto/x-www-form-urlencoded',
                // data: {username:$("#username").val(), password:$("#password").val()},
                data: {username:username, password:password},                   // data冒号后面接的是json对象
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

        // get请求
        $("#submitform").on("submit", function(ev) {
            $.ajax({
                url:'${Request.dynamicProjectPath!""}/v1/jquery-ajax/get',
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