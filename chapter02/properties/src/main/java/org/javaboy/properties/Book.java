package org.javaboy.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author szh
 */
@Component  // 交给spring处理
@PropertySource(value = "classpath:book.properties") // 导入配置文件
@ConfigurationProperties(prefix = "book") // SpringBoot提供安全的属性注入
public class Book {
    //        @Value("${book.id}")
    private Long id;
    //    @Value("${book.name}")
    private String name;
    //    @Value("${book.author}")
    private String author;

    private String[] tags;

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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", author='" + author + '\'' + ", tags=" + Arrays.toString(tags) + '}';
    }
}
