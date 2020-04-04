package com.hegp.aop;

import com.hegp.controller.HttpSessionController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class WebUrlPermissionAspect {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    /**
      @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
          @Autowired
          private HttpSessionController httpSessionController;
          然后 httpSessionController 调用任何方法, 都会触发 within(@org.springframework.web.bind.annotation.RestController *) 这个条件,
          用 this 调用方法, 不会触发 都会触发 within(@org.springframework.web.bind.annotation.RestController *) 这个条件,
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Before("springBeanPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes==null || (requestAttributes instanceof ServletRequestAttributes)==false) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if (request==null || "1".equals(request.getAttribute("has-check-url-permisions"))) {
            return;
        }
        getAllApi();
        // 如果将来排除万难绝对要动态配置URL的权限或者参数时, 再在此处添加逻辑实现
//        System.out.println("000000000000");
    }

    public void getAllApi() {
        Map<RequestMappingInfo, HandlerMethod> map = this.handlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> set = map.keySet();
        for (RequestMappingInfo info : set) {
            HandlerMethod handlerMethod = map.get(info);
//            handlerMethod.getMethodParameters();
            // springmvc的url地址，不包含项目名
            PatternsRequestCondition patternsCondition = info.getPatternsCondition();
            // info.getMethodsCondition().getMethods() 为空, 意味着匹配所有的 @RequestMapping 方法
            System.out.println("请求方式: " + info.getMethodsCondition().getMethods().toString() + "  uri: " + patternsCondition);
        }
    }

    /** 在方法上面获取请求体的参数 */
    public static Object getRequestBodyObjectParamInMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        Annotation[][] paramAnnotationArr = method.getParameterAnnotations();
        for (int i = 0; i < paramAnnotationArr.length; i++) {
            Annotation[] paramAnnotations = paramAnnotationArr[i];
            for (int j = 0; j < paramAnnotations.length; j++) {
                if (paramAnnotations[j] instanceof RequestBody) {
                    return args[i];
                }
            }
        }
        return null;
    }
}
