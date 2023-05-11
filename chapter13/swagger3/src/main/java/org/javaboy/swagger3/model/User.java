package org.javaboy.swagger3.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author szh
 * @Date 2022/5/18 19:34
 * @PackageName:org.javaboy.swagger3.model
 * @ClassName: User
 * @Description: TODO
 * @Version 1.0
 */
@ApiModel(value = "用户实体类", description = "这个类定义了用户的所有属性") // 对类的描述
public class User {
    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户地址")
    private String address;

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
