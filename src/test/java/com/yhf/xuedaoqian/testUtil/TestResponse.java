package com.yhf.xuedaoqian.testUtil;

import okhttp3.Headers;
import okhttp3.MediaType;

/**
 * @author SuyuZhuang
 * @date 2019/12/2 8:47 下午
 */
public class TestResponse {
    private MediaType responseMediaType;
    private String bodyString;
    private Boolean successful;
    private Headers headers;
    private String message;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MediaType getResponseMediaType() {
        return responseMediaType;
    }

    public void setResponseMediaType(MediaType responseMediaType) {
        this.responseMediaType = responseMediaType;
    }

    public String getBodyString() {
        return bodyString;
    }

    public void setBodyString(String bodyString) {
        this.bodyString = bodyString;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
