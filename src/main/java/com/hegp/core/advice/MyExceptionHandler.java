package com.hegp.core.advice;

import com.hegp.core.domain.RequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@ControllerAdvice
//@RestControllerAdvice
public class MyExceptionHandler implements Serializable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();
        return RequestResponse.build(500, e.getMessage());
    }
}