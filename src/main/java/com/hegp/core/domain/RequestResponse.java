package com.hegp.core.domain;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class RequestResponse<T extends Object> implements Serializable {

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

    public RequestResponse() {
        super();
    }

    /**
     * 构建 成功的响应对象
     *
     * @param data 响应数据
     * @return tech.ascs.citywork.domain.net.RequestResponse
     */
    public static RequestResponse build(Object data) {
        RequestResponse response = new RequestResponse();
        response.data = data;
        response.code = HttpStatus.OK.value();
        return response;
    }

    /**
     * 构建 成功的响应对象
     *
     * @param code 状态码
     * @param msg 服务端提示信息
     * @return tech.ascs.citywork.domain.net.RequestResponse
     */
    public static RequestResponse build(int code, String msg) {
        RequestResponse response = new RequestResponse();
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
     * @return tech.ascs.citywork.domain.net.RequestResponse
     */
    public static RequestResponse build(Object data, int code, String msg) {
        RequestResponse response = new RequestResponse();
        response.data = data;
        response.code = code;
        response.msg = msg;
        return response;
    }

    /**
     * 构建 响应对象
     *
     * @param data 响应数据
     * @param code 提示信息
     * @return tech.ascs.citywork.domain.net.RequestResponse
     */
    public static RequestResponse build(Object data, int code) {
        RequestResponse response = new RequestResponse();
        response.data = data;
        response.code = code;
        return response;
    }

    public RequestResponse(HttpStatus code) {
        this.code = code.value();
    }

    public RequestResponse(int code) {
        this.code = code;
    }

    public RequestResponse(HttpStatus code, String msg) {
        this.code = code.value();
        this.msg = msg;
    }

    public RequestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RequestResponse(HttpStatus code, String msg, T data) {
        this.code = code.value();
        this.msg = msg;
        this.data = data;
    }

    public RequestResponse(int code, String msg, T data) {
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
        return "RequestResponse{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}