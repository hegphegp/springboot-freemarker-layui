package com.hegp.core.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UrlMappingFilter extends OncePerRequestFilter {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    private Integer contextPathLength = 0;
    private PathMatcher pathMatcher = new AntPathMatcher();
    private Map<String, Object> allUrlMap = new HashMap();
    private Map<String, Object> directUrlMap = new HashMap();

    /**  "/users" 是否也匹配 "/users/".  */
    private boolean useTrailingSlashMatch = true;
    /**  "/users" 是否也匹配 "/users.*"  */
    private boolean useSuffixPatternMatch = false;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        /** 通过 Method和URL定位接口的权限 */
        if (StringUtils.hasText(contextPath)) {
            contextPathLength = contextPath.length();
        }
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();// 就是这个
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            Set<String> set = rmi.getPatternsCondition().getPatterns();
            Set<String> methods = getMethodStr(rmi.getMethodsCondition().getMethods());
            for (String url:set) {
                for (String method:methods) {
                    allUrlMap.put(method+" "+url, null); // null要替换成具体的业务逻辑对象
                    if (pathMatcher.isPattern(url)==false) {
                        directUrlMap.put(method+" "+url, null); // null要替换成具体的业务逻辑对象
                    }
                }
            }
        }
//        System.out.println(allUrlMap);
//        System.out.println(directUrlMap);
    }

    public List<String> getMatchingPatterns(String methodAndRequestURI) {
        List<String> matches = new ArrayList<>();
        for (String pattern :allUrlMap.keySet()) {
            String match = getMatchingPattern(pattern, methodAndRequestURI);
            if (match != null) {
                matches.add(match);
            }
        }
        if (matches.size() > 1) {
            matches.sort(pathMatcher.getPatternComparator(methodAndRequestURI));
        }
        return matches;
    }

    private String getMatchingPattern(String pattern, String methodAndRequestURI) {
        if (pattern.equals(methodAndRequestURI)) {
            return pattern;
        }
        if (pathMatcher.match(pattern, methodAndRequestURI)) {
            return pattern;
        }
        if (this.useTrailingSlashMatch) {
            if (!pattern.endsWith("/") && pathMatcher.match(pattern + "/", methodAndRequestURI)) {
                return pattern + "/";
            }
        }
        return null;
    }

    public Set<String> getMethodStr(Set<RequestMethod> set) {
        return ObjectUtils.isEmpty(set)? new HashSet(Arrays.asList("GET","HEAD","POST","PUT","PATCH","DELETE","OPTIONS","TRACE")):set.stream().map(o->o.name()).collect(Collectors.toSet());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = contextPathLength>0? request.getRequestURI().substring(contextPathLength):request.getRequestURI();
        System.out.println("请求的URL是  "+request.getMethod()+" "+requestURI);
        Object object = directUrlMap.get(request.getMethod()+" "+requestURI);
        if (object!=null) { //对该URL镜像权限拦截
            System.out.println("最优匹配的URL是 "+request.getMethod()+" "+requestURI);
        } else { // 可能是通配符动态URL
            List<String> matches = getMatchingPatterns(request.getMethod()+" "+requestURI);
            if (!ObjectUtils.isEmpty(matches)) {
                System.out.println("匹配到的URL有 "+matches);
                System.out.println("最优匹配的URL是 "+matches.get(0));
            }
        }
        filterChain.doFilter(request, response);
    }

}
