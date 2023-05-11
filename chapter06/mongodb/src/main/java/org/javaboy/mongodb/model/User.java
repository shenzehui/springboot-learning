package org.javaboy.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author szh
 */
@Document("users")  // 指定数据库表名
public class User implements Serializable {
    @Id
    private Long id;

    @Field("name") // 指定字段名
    private String username;

    @Field("addr")
    private String address;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", address='" + address + '\'' + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
