package org.javaboy.mail.model;

/**
 * @Author szh
 * @Date 2022/5/18 13:16
 * @PackageName:org.javaboy.mail.model
 * @ClassName: User
 * @Description: TODO
 * @Version 1.0
 */
public class User {
    private String username;
    private Double salary;
    private String position;
    private String company;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
