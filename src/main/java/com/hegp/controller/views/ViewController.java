package com.hegp.controller.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/** 每个页面的URL单独配置一个@GetMapping接口，显得很傻逼，用通配符的方式映射 */
@Controller
@RequestMapping("${front-end-pages.prefix:/pages}")
public class ViewController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int contextPathLength = -1;
    @Value("${front-end-pages.prefix:/pages}")
    private String frontEndPagesPrefix;
    private int frontEndPagesPrefixLength = 0;

    @Autowired
    private ServerProperties serverProperties;
    @PostConstruct
    public void init() {
        String contextPath = serverProperties.getServlet().getContextPath();
        contextPathLength = StringUtils.hasText(contextPath)? contextPath.length():0;
        if (StringUtils.hasText(frontEndPagesPrefix)) {
            frontEndPagesPrefixLength = frontEndPagesPrefix.length();
        }
    }

    /**
     * 一个星号通配符只能匹配一层URL，两个星号通配符可以匹配多层URL
     * @param request
     * @return
     */
//    @GetMapping(value={"/back-end/**","/front-end/**","/test-examples/**"})
    @GetMapping(value={"/**"})
    // ModelAndView的路径计算不对，如果计算后还存在/pages前缀，会进入死循环，不断地进入这个方法
    // resources/templates或者resources/static文件下面，首层目录的名称禁止用pages，否则new ModelAndView(requestURI) requestURI以pages开头会进入该方法
    public ModelAndView page(HttpServletRequest request) {
//        ServletUtils.printAllHeaders();
//        ServletUtils.getCurrentReqOriginIp();
        String requestURI = request.getRequestURI();
        if (contextPathLength!=0) {
            requestURI = requestURI.substring(contextPathLength);
        }

        if (requestURI.endsWith(".html")) {
            requestURI = requestURI.substring(frontEndPagesPrefixLength, requestURI.length()-5);
        } else if (requestURI.endsWith(".htm")) {
            requestURI = requestURI.substring(frontEndPagesPrefixLength, requestURI.length()-4);
        } else {
            requestURI = requestURI.substring(frontEndPagesPrefixLength);
        }
        return new ModelAndView(requestURI);
    }

}
