package com.hegp.controller;

import com.hegp.utils.ResourcesUtils;
import com.wf.captcha.GifCaptcha;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
//import java.awt.Font;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@RestController
public class CaptchaController {
    private Font font;

    @Value("${font.font.path:classpath:config/UbuntuMonoRegular.ttf}")
    private String fontFilePath;

    @PostConstruct
    public void init() throws IOException, FontFormatException {
        File file = ResourcesUtils.getFileByPath(fontFilePath);
        font = Font.createFont(Font.TRUETYPE_FONT, file);
        font = font.deriveFont(Font.PLAIN+Font.ITALIC, 25);
    }

    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @GetMapping(value="/captcha")
    public void getGifCode1(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);

        // 三个参数分别为宽、高、位数
        GifCaptcha gifCaptcha = new GifCaptcha(120, 40, 4);

        gifCaptcha.setFont(font);  // 有默认字体，可以不用设置
        HttpSession session = request.getSession(true);
        gifCaptcha.out(response.getOutputStream());

        // 验证码存入session
        session.setAttribute("captcha", gifCaptcha.text().toLowerCase());
    }
}
