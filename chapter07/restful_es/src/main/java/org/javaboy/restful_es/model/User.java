package org.javaboy.restful_es.model;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author szh
 * @Document指出索引的名字 类似于数据库中的数据表
 */
@Document(indexName = "us")
public class User {
    private Long id;
    private String address;
    private String username;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", address='" + address + '\'' + ", username='" + username + '\'' + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
