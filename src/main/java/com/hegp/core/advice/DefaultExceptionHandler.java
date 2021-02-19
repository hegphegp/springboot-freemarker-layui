package com.hegp.core.advice;

import com.hegp.core.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@ControllerAdvice
@RestControllerAdvice
public class DefaultExceptionHandler implements Serializable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        response.setStatus(500);
        logger.error(e.getLocalizedMessage(), e);
        return Result.build(500, getMessage(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage());
            break;
        }
        logger.error(sb.toString(), e);
        return handle(HttpStatus.BAD_REQUEST, 999, sb.toString(), request, response);
    }

    protected Result handle(HttpStatus status, int code, String message, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(status.value());
        Result result = Result.build(message, code, message);
        logger.error("[" + request.getMethod() + "]" + request.getRequestURI() + " 请求失败", message);
        return result;
    }

    protected Result handle(HttpStatus status, int code, Throwable cause, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(status.value());
        String message = getMessage(cause);
        Result result = Result.build(message, code, message);
        logger.error("[" + request.getMethod() + "]" + request.getRequestURI() + " 请求失败", cause);
        return result;
    }

    protected String getMessage(Throwable cause) {
        if (cause == null) {
            return "null";
        } else if (cause.getCause() == null) {
            return cause.getMessage();
        } else {
            return getMessage(cause.getCause());
        }
    }

}