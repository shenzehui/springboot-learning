package org.javaboy.redis.model;

import java.io.Serializable;

/**
 * @author szh
 */
public class User implements Serializable {
    private String username;
    private String address;

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", address='" + address + '\'' + '}';
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
