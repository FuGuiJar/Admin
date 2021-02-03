package top.fuguijar.vo;

import java.io.Serializable;

/**
 * @author fuguijar.top
 * @date 2021-01-26
 */
public class Result implements Serializable {
    private int code;
    private int count;
    private String msg;
    private Object data;


    public Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
