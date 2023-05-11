package org.javaboy.json;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

/**
 * @author szh
 */
//@JsonIgnoreProperties({"birthday","address"})  // 批量忽略字段
public class User {
    /**
     * 指定属性序列化和反序列化时的名称，默认名称就是属性名  index是优先级，越大显示越后面
     */
    @JsonProperty(value = "aaaaage", index = 99)
    private Integer age;

    @JsonProperty(index = 98)
    private String username;

    /**
     * 日期格式化，注意时区问题
     */
    //    @JsonProperty(index = 97)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")

    /**
     * 类似于 @JsonProperty 中的 index
     */
    @JsonPropertyOrder
    private Date birthday;

    /**
     * 在序列化/反序列化忽略某一个字段
     */
//    @JsonIgnore
//    @JsonProperty(index = 96)
    private String address;

    @Override
    public String toString() {
        return "User{" + "age=" + age + ", username='" + username + '\'' + ", birthday=" + birthday + ", address='" + address + '\'' + '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
