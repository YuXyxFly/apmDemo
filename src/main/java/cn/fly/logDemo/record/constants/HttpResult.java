package cn.fly.logDemo.record.constants;

import cn.hutool.http.HttpStatus;

/**
 * @author fly
 * @date 2023/3/2
 * @description
 */

public enum HttpResult {

    SUCCESS(1, "success"),

    FAILED(0, "failed");

    private int code;

    private String msg;


    HttpResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public static class HttpResultBuilder {
        public static HttpResult getByCode(int code) {
            switch (code){
                case HttpStatus.HTTP_OK:
                    return SUCCESS;
                default:
                    return FAILED;
            }
        }
    }
}
