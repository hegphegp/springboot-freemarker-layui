/**
 * 01) 按照请求参数名的字母升序排列非空请求参数（空的参数过滤掉，appKey ，appSecret 和 timestamp不排序)
 * 02) 先拼接timestamp，再拼接appKey，最后拼接appSecret
 */
function getSign(params, kAppKey, kAppSecret) {
    var content = "";
    if (typeof params == "string") {
        content += params
    } else if (typeof params == "object") {
        params = sortObject(params);
        var arr = [];
        for (var i in params) {
            if (params[i]!=undefined && params[i]!=null) {
                var str = params[i].toString();
                if (str.length>0) { // 非空对象
                    arr.push(i + "=" + str);
                }
            }

        }
        content += arr.join("&")
    }
    var url = content + "&timestamp=" + new Date().getTime() + "&appKey=" + kAppKey + "&key=" + kAppSecret;
    // console.log("URL======>>>>>>>>>>>"+url)
    // console.log("md5()加密=========>>>>>>>>>>"+md5(url));
    return md5(url);
}

/**
 * @param object 传入要进行属性排序的对象
 * @returns {{}} 将对象进行属性排序(按首字母顺序进行排序)
 */
function sortObject(object) {
    var objectKeys = Object.keys(object).sort();
    var result = {};
    for (var i in objectKeys) {
        result[objectKeys[i]] = object[objectKeys[i]];
    }
    return result;
}

/**
 * @param url 请求的url,应该包含请求参数(url的?后面的参数)
 * @param requestParams 请求参数(POST的JSON参数)
 * @returns {string} 获取签名
 */
function getSignUrl(url, requestParams, kAppKey, kAppSecret) {
    var signString = "";
    var urlParams = parseQueryString(url);
    var allParams = sortObject(mergeObject(urlParams, requestParams));
    return getSign(allParams, kAppKey, kAppSecret);
}

/**
 * @param url 请求的url
 * @returns {{}} 将url中请求参数组装成json对象(url的?后面的参数)
 */
function parseQueryString(url) {
    var urlReg = /^[^\?]+\?([\w\W]+)$/,
        paramReg = /([^&=]+)=([\w\W]*?)(&|$|#)/g,
        urlArray = urlReg.exec(url),
        result = {};
    if (urlArray && urlArray[1]) {
        var paramString = urlArray[1], paramResult;
        while ((paramResult = paramReg.exec(paramString)) != null) {
            result[paramResult[1]] = paramResult[2];
        }
    }
    return result;
}

/**
 * @returns {*} 将两个对象合并成一个
 */
function mergeObject(objectOne, objectTwo) {
    if (objectTwo != null) {
        for (var key in objectTwo) {
            objectOne[key] = objectTwo[key]
        }
    }
    return objectOne;
}

