package com.zhaosen.exception;

@SuppressWarnings("serial")
public class CLException extends RuntimeException {
    private String detail;
    private String time;
    private String msg;
    private String code;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return this.detail == null?"":this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public CLException() {
    }

    public CLException(String arg0) {
        super(arg0);
        this.msg = "未知错误";
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CLException(String code, String desc) {
        this.code = code;
        this.detail = desc;
    }

    public CLException(Exception arg0) {
        super(arg0);
        this.msg = "未知错误";
    }
}
