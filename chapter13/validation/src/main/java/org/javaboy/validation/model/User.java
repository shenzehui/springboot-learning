package org.javaboy.validation.model;

import org.javaboy.validation.validation.ValidationGroup1;
import org.javaboy.validation.validation.ValidationGroup2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.*;

/**
 * @Author szh
 * @Date 2022/5/18 19:49
 * @PackageName:org.javaboy.validation.model
 * @ClassName: User
 * @Description: TODO
 * @Version 1.0
 */
public class User {
    private Long id;
    /*分组*/
    @Size(min = 5,max = 8,message = "{user.username.size}",groups = ValidationGroup1.class)
    private String username;

    @NotNull(message = "{user.address.notnull}",groups = ValidationGroup2.class)
    private String address;
    @DecimalMin(value = "1",message = "{user.age.min}",groups = {ValidationGroup1.class,ValidationGroup2.class})
    @DecimalMax(value = "200",message = "{user.age.max}")
    private Integer age;
    @NotNull(message = "{user.email.notnull}")
    /*校验邮箱地址*/
    @Email(message = "{user.email.pattern}")
    private String email;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
