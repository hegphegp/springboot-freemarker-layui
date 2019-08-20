package com.hegp.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 给request配置用户浏览器访问路径，然后动态设置项目的css和js的全路径
 */
@Component
public class BasePathConfigFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * zuul网关会自动封装 x-forwarded-host 参数
         * zuul网关不会自动封装 x-forwarded-uri 参数,要手动写代码补上去
         */
        String contextPath = request.getContextPath();
        String basePath = StringUtils.hasText(request.getHeader("x-forwarded-host"))? assemblyWhenXForwardedHostExists(request):"";
        basePath += StringUtils.hasText(contextPath)? contextPath:"";
        request.setAttribute("basePath", basePath);
        // 如果用了 nginx作为请求入口，一定要配置
        /**
         *  proxy_set_header X-Real-IP $remote_addr;
         *  proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
         *  proxy_set_header X-Forwarded-Proto $scheme;
         *  proxy_set_header Host $http_host;
         *  proxy_set_header X-Forwarded-Uri $uri;
         */
        printAllHeaders(request);
        filterChain.doFilter(request, response);
    }

    private String assemblyWhenXForwardedHostExists(HttpServletRequest request) {
        String xForwardedUri = request.getHeader("x-forwarded-uri");
        Assert.isTrue(StringUtils.hasText(xForwardedUri), "请配置请求头的 x-forwarded-uri 参数");
        String requestURI = request.getRequestURI();
        String dynamicPath = null;
        if ("/".equals(requestURI)) {
            dynamicPath = xForwardedUri.endsWith("/")? xForwardedUri.substring(0, xForwardedUri.length()-1):xForwardedUri;
        } else {
            dynamicPath = xForwardedUri.substring(0, xForwardedUri.lastIndexOf(requestURI));
        }
        String scheme = StringUtils.hasText(request.getScheme())? request.getScheme():"http";
        return scheme+"://"+request.getHeader("x-forwarded-host")+dynamicPath;
    }

    public void printAllHeaders(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            logger.debug(headerName + "  ===>>>  " + request.getHeader(headerName));
        }
    }
}
