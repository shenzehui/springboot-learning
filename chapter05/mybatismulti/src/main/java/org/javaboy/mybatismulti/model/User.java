package org.javaboy.mybatismulti.model;

/**
 * @author szh
 */
public class User {
    private Long id;
    private String username;
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
