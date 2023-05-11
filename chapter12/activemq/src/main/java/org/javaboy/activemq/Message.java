package org.javaboy.activemq;

import java.io.Serializable;
import java.util.Date;

/**
 * @author szh
 */
public class Message implements Serializable {
    private String content;
    private Date sendDate;

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" + "content='" + content + '\'' + ", sendDate=" + sendDate + '}';
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
