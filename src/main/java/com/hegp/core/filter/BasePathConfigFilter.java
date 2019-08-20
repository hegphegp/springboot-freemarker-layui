package com.hegp.core.filter;

import org.springframework.stereotype.Component;
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
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        System.out.println(request.getRequestURL().toString());
        System.out.println(request.getRequestURI());
        System.out.println(request.getHeader("x-forwarded-uri"));
        System.out.println(request.getHeader("x-forwarded-host"));
        String host = request.getHeader("Host");
        String xForwardedPrefix = request.getHeader("x-forwarded-prefix");
        String scheme = StringUtils.hasText(request.getScheme())? request.getScheme():"http";
        String basePath = scheme+"://"+host+"/"+xForwardedPrefix+"/"+contextPath;
        request.setAttribute("basePath", basePath);
        // 如果用了 nginx作为请求入口，一定要配置
        /**
         *         proxy_set_header X-Real-IP $remote_addr;
         *         proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
         *         proxy_set_header X-Forwarded-Proto $scheme;
         *         proxy_set_header Host $http_host;
         *         proxy_set_header X-Forwarded-Uri $uri;
         */
//        String xForwardedUri = request.getHeader("x-forwarded-uri");
//        String xForwardedHost = request.getHeader("x-forwarded-host");



//        String requestURI = request.getRequestURI();
//        if (StringUtils.hasText(xForwardedUri)) {
//            if (StringUtils.hasText(host) && StringUtils.hasText(requestURI) && requestURI.equals("/") == false) {
//                int index = xForwardedUri.lastIndexOf(requestURI);
//                basePath = index>-1 ? scheme+"://"+host+xForwardedUri.substring(0, index) : basePath;
//            }
//        } else {
//            basePath = StringUtils.hasText(host) ? scheme+"://"+host+request.getHeader("x-forwarded-prefix") : basePath;
//        }
//        basePath += StringUtils.hasText(contextPath)? contextPath:"";
//        if (StringUtils.hasText(basePath)) {
//            request.setAttribute("basePath", basePath);
//        } else if (StringUtils.hasText(host)) {
//            request.setAttribute("basePath", scheme+"://"+host);
//        }
        printAllHeaders(request);
        filterChain.doFilter(request, response);
    }

    public void printAllHeaders(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            System.out.println(headerName + "  ===>>>  " + request.getHeader(headerName));
        }
    }
}