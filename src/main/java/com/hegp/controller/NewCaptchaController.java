package com.hegp.controller;

import com.wf.captcha.GifCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import java.awt.Font;
import java.io.IOException;

@RestController
public class NewCaptchaController {
    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @GetMapping(value="/captcha-new")
    public void getGifCode1(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);

        // 三个参数分别为宽、高、位数
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);

//        gifCaptcha.setFont(new Font("黑体", Font.BOLD+Font.ITALIC, 28 ));  // 有默认字体，可以不用设置
        HttpSession session = request.getSession(true);
        gifCaptcha.out(response.getOutputStream());

        // 验证码存入session
        session.setAttribute("captcha", gifCaptcha.text().toLowerCase());
    }
}
