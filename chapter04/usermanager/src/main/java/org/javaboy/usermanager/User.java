package org.javaboy.usermanager;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author szh
 */
public class User {
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", birthday=" + birthday +
                '}';
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
}
