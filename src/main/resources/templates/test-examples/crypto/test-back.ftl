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

    //解密方法
    function aesDecrypt(word, key, iv) {
        key = CryptoJS.enc.Utf8.parse(key);  //十六位十六进制数作为密钥
        iv = CryptoJS.enc.Utf8.parse(iv);    //十六位十六进制数作为密钥偏移量
        var encryptedHexStr = CryptoJS.enc.Hex.parse(word);
        var srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr);
        var decrypt = CryptoJS.AES.decrypt(srcs, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 });
        var decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
        return decryptedStr.toString();
    }

    //加密方法
    function aesEncrypt(word, key, iv) {
        key = CryptoJS.enc.Utf8.parse(key);  //十六位十六进制数作为密钥
        iv = CryptoJS.enc.Utf8.parse(iv);    //十六位十六进制数作为密钥偏移量
        var ciphertext = CryptoJS.enc.Utf8.parse(word);
        var options = CryptoJS.AES.encrypt(ciphertext, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 });
        // var encrypted = CryptoJS[way].encrypt(ciphertext, key, options);
        return options.toString();

        // key = CryptoJS.enc.Utf8.parse(key);  //十六位十六进制数作为密钥
        // iv = CryptoJS.enc.Utf8.parse(iv);    //十六位十六进制数作为密钥偏移量
        // var encryptedHexStr = CryptoJS.enc.Hex.parse(word);
        // var srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr);
        // var encrypted = CryptoJS.AES.encrypt(srcs, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 });
        // return encrypted.toString();


        // var hexStr = encrypted.ciphertext.toString().toUpperCase(); //hexStr是16进制
        // var base64Str = CryptoJS.enc.Base64.stringify(hexStr);
        // return base64Str;
    }

    /**
     * 加密（需要先加载lib/aes/aes.min.js文件）
     * @param word
     * @returns {*}
     */
    function encrypt(word){
        var key = CryptoJS.enc.Utf8.parse("abcdefgabcdefg12");
        var srcs = CryptoJS.enc.Utf8.parse(word);
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
        return encrypted.toString();
    }

    /**
     * 解密
     * @param word
     * @returns {*}
     */
    function decrypt(word){
        var key = CryptoJS.enc.Utf8.parse("abcdefgabcdefg12");
        var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();
    }

    (function() {

        $("#submitform").on("submit", function(ev) {
            var key = "1234123412ABCDEF";
            var iv = "ABCDEF1234123412";
            // var username = aesEncrypt($("#username").val(), key, iv);
            var username = encrypt($("#username").val());
            debugger;
            console.log(username);
            // var password = aesEncrypt($("#password").val(), key, iv);
            var password = encrypt($("#password").val());
            console.log(password);
            var jsonData = {"username": username, "password": password};
            alert(jsonData);
            $.ajax({
                url:'${Request.dynamicProjectPath!""}/v1/crypto/application-json',
                // data: {username:username, password:password},                   // data冒号后面接 {username:username, password:password} 是字符串,不是json对象
                data: JSON.stringify(jsonData),
                type:'post',
                cache:false,
                contentType:"application/json",
                dataType: "json",
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