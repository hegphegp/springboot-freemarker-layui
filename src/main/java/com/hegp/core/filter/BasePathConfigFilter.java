package com.hegp.core.filter;

import com.hegp.core.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String basePath = StringUtils.hasText(request.getHeader("x-forwarded-host"))? ServletUtils.getBasePathWhenRequestIsForwarded():"";
        basePath += StringUtils.hasText(request.getContextPath())? request.getContextPath():"";
        request.setAttribute("basePath", basePath);
        // 如果用了 nginx作为请求入口，一定要配置
        /**
         *  proxy_set_header Host $http_host;
         *  proxy_set_header X-Forwarded-Uri $uri;
         *  proxy_set_header X-Real-IP $remote_addr;
         *  proxy_set_header X-Forwarded-Proto $scheme;
         *  proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
         */
        ServletUtils.printAllHeaders();
        filterChain.doFilter(request, response);
    }

}
