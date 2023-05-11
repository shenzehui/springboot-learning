package org.javaboy.gson;

import java.util.Date;

/**
 * @author szh
 */
public class User {
    private String userName;
    private Date birthday;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
