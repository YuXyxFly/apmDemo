package cn.fly.result;

import cn.hutool.http.HttpStatus;

import java.io.Serializable;


public class AjaxResult<T> implements Serializable {

    private static final long serialVersionUID = -6048394392232029305L;
    private Integer code;

    private String msg;

    private T data;

    public AjaxResult() {
    }

    public static <T> AjaxResult<T> build(Integer code, String msg, T data) {
        return new AjaxResult<>(code, msg, data);
    }
    public static <T> AjaxResult<T> build(Integer code, String msg) {
        return build(code, msg, null);
    }
    /**
     * 返回code 为 200
     */
    public static <T> AjaxResult<T> success() {
        return AjaxResult.success("操作成功");
    }

    public static <T> AjaxResult<T> success(T data) {
        return AjaxResult.success("操作成功", data);
    }

    public static <T> AjaxResult<T> success(String msg) {
        return AjaxResult.success(msg, null);
    }

    public static <T> AjaxResult<T> success(String msg, T data) {
        return build(HttpStatus.HTTP_OK, msg, data);
    }

    /**
     * 返回code 为 500
     */
    public static <T> AjaxResult<T> error() {
        return AjaxResult.error("系统异常");
    }

    public static <T> AjaxResult<T> error(String msg) {
        return AjaxResult.error(msg, null);
    }

    public static <T> AjaxResult<T> error(String msg, T data) {
        return build(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
    }
    public static <T> AjaxResult<T> error(Integer code, String msg) {
        return build(code, msg);
    }
    /**
     * 返回code 为 500
     */
    public static <T> AjaxResult<T> failed() {
        return AjaxResult.failed("操作失败");
    }

    public static <T> AjaxResult<T> failed(String msg) {
        return AjaxResult.failed(msg, null);
    }

    public static <T> AjaxResult<T> failed(String msg, T data) {
        return build(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
    }
    public static <T> AjaxResult<T> failed(Integer code, String msg) {
        return build(code, msg);
    }

    public AjaxResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public AjaxResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
