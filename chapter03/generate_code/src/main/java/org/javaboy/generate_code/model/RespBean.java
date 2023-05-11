package org.javaboy.generate_code.model;

/**
 * @author szh
 */
public class RespBean {
    /**
     * 返回的状态值
     */
    private Integer status;
    /**
     * 返回的成功或失败信息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private Object obj;

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }


    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    private RespBean() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
