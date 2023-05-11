package org.javaboy.generate_code.model;


/**
 * @author szh
 */
public class Db {
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 地址
     */
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
