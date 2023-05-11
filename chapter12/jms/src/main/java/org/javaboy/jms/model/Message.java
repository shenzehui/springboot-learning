package org.javaboy.jms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author szh
 * @Date 2022/5/17 17:25
 * @PackageName:org.javaboy.model
 * @ClassName: Message
 * @Description: TODO
 * @Version 1.0
 */
public class Message implements Serializable {
    private String content;
    private Date date;

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
