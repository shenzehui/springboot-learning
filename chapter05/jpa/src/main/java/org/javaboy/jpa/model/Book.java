package org.javaboy.jpa.model;

import javax.persistence.*;

/**
 * @author szh
 */
@Entity(name = "t_book") //指定数据库生成的表名 若不设置name，则默认为类名
public class Book {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 指定主键和生成策略
    private Long id;

    @Column(name = "b_name") //指定数据库字段名
    private String name;

    private String author;

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", author='" + author + '\'' + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
