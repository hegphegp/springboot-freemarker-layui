package com.hegp.controller;

import com.hegp.core.captcha.Captcha;
import com.hegp.core.captcha.GifCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class CaptchaController {
    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @GetMapping(value="/captcha")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");
        /**
         * gif格式动画验证码
         * 宽，高，位数。
         */
        Captcha captcha = new GifCaptcha(146,33,4);
        HttpSession session = request.getSession(true);
        //输出
        captcha.out(response.getOutputStream());
        //存入Session
        session.setAttribute("_code",captcha.text().toLowerCase());
    }
}
