package com.hegp.core.domain;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class Result<T extends Object> implements Serializable {

    /**
     * 数据 依据具体业务 - 实现范型自动转换
     */
    private T data;

    /**
     * 请求状态码 - 国际化处理
     */
    private int code;

    /**
     * 响应信息 - 服务端提示信息
     */
    private String msg;

    public Result() {
        super();
    }

    /**
     * 构建 成功的响应对象
     *
     * @param data 响应数据
     */
    public static Result success(Object data) {
        Result response = new Result();
        response.data = data;
        response.code = HttpStatus.OK.value();
        return response;
    }

    /**
     * 构建 成功的响应对象
     *
     * @param code 状态码
     * @param msg 服务端提示信息
     */
    public static Result build(int code, String msg) {
        Result response = new Result();
        response.code = code;
        response.msg = msg;
        return response;
    }

    /**
     * 构建 响应对象
     *
     * @param data 响应数据
     * @param code 提示信息
     * @param msg 服务端提示信息
     */
    public static Result build(Object data, int code, String msg) {
        Result response = new Result();
        response.data = data;
        response.code = code;
        response.msg = msg;
        return response;
    }

    public Result(HttpStatus code) {
        this.code = code.value();
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(HttpStatus code, String msg) {
        this.code = code.value();
        this.msg = msg;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(HttpStatus code, String msg, T data) {
        this.code = code.value();
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}