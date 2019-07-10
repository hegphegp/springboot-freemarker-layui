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

    /**
     * 加密（需要先加载lib/aes/aes.min.js文件）
     * @param word
     * @returns {*}
     */
    function encrypt(word, key, iv) {
        var key = CryptoJS.enc.Utf8.parse(key);
        var iv = CryptoJS.enc.Utf8.parse(iv);    //十六位十六进制数作为密钥偏移量
        var srcs = CryptoJS.enc.Utf8.parse(word);
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {iv:iv, mode:CryptoJS.mode.CBC, padding:CryptoJS.pad.Pkcs7});
        return encrypted.toString();
    }

    /**
     * 解密
     * @param word
     * @returns {*}
     */
    function decrypt(word, key, iv) {
        var key = CryptoJS.enc.Utf8.parse(key);
        var iv = CryptoJS.enc.Utf8.parse(iv);    //十六位十六进制数作为密钥偏移量
        var decrypt = CryptoJS.AES.decrypt(word, key, {iv:iv, mode:CryptoJS.mode.CBC, padding:CryptoJS.pad.Pkcs7});
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();
    }

    (function() {

        $("#submitform").on("submit", function(ev) {
            var key = "1234123412ABCDEF";
            var iv = "ABCDEF1234123412";
            var username = encrypt($("#username").val(), key, iv);
            console.log(username);
            var password = encrypt($("#password").val(), key, iv);
            console.log(password);
            var jsonData = {"username": username, "password": password};
            $.getJSON({
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                url:'${Request.dynamicProjectPath!""}/v1/crypto/application-json',
                data: JSON.stringify(jsonData),
                type:'post',
                cache:false,
                contentType:"application/json",
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

    })()

</script>

</body>
</html>