package org.javaboy.model;

import java.util.Date;

/**
 * @author szh
 */
public class User {

    private String name;
    private Date birthday;

    public User(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
