<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>form表单提交会跳转URL，此乃大忌。用ajax提交表单数据，并阻止表单提交</title>
    <script src="${Request.basePath!""}/plugins/jquery/v1.12.4/jquery.min.js"></script>
    <script src="${Request.basePath!""}/js/sign/md5.min.js"></script>
    <script src="${Request.basePath!""}/js/sign/sign.js"></script>
    <style type="text/css">
        *{padding:0;margin:0}
        button{padding:10px;font-size:14px;margin:10px;margin-right: 0}
        #text{background:rgb(0,0,0);padding:10px;color:rgb(255,255,255);border-radius:10px;margin:10px;line-height:24px;display: none;}
    </style>
</head>

<body>
<button onclick="getData_1()">GET方法，data为String</button>
<button onclick="getData_2()">GET方法，data为Object</button>
<button onclick="getData_3()">POST方法，application/json</button>
<button onclick="getData_4()">POST方法，application/x-www-form-urlencoded</button>
<div id="text">
    <p></p>
    <p></p>
    <p></p>
</div>
<script type="text/javascript">
    var pageSize = 10,
        pageNo = 1,
        type = 1,
        xid = "91040b4bc9415b28a6e0";

    var kAppKey = 'appKey';
    var kAppSecret = 'appSecret';

    function getData_1() {
        var params = "xid=" + xid + "&type=" + type + "&pageSize=" + pageSize + "&pageNo=" + pageNo;
        var sign = getSign(params, kAppKey, kAppSecret);
        alert("签名后的sign====>>>"+sign)
    }

    function getData_2() {
        var paramsObj = { xid: xid, pageSize: pageSize, type: type, pageNo: pageNo };
        var url = "https://www.baidu.com?aa=aa&bb=bb&cc=cc&dd=&ee="
        var sign = getSignUrl(url, undefined, kAppKey, kAppSecret);
        alert("签名后的sign====>>>"+sign)
    }

    function getData_3() {
        var paramsObj = { xid: xid, pageSize: pageSize, type: type, pageNo: pageNo };
        var sign = getSign(paramsObj, kAppKey, kAppSecret);
        alert("签名后的sign====>>>"+sign)
    }

    function getData_4() {
        var params = "xid=" + xid + "&type=" + type + "&pageSize=" + pageSize + "&pageNo=" + pageNo;
        var sign = getSign(params, kAppKey, kAppSecret);
        alert("签名后的sign====>>>"+sign)
    }

    function insertHtml(contentArray) {
        for (var i = 0; i < contentArray.length; i++) {
            $("#text p").eq(i).html(contentArray[i]);
        }
        $("#text").show();
    }
</script>

</body>
</html>