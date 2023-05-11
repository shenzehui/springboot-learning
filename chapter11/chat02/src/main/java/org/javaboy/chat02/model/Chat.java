package org.javaboy.chat02.model;

/**
 * @Author szh
 * @Date 2022/5/17 15:31
 * @PackageName:org.javaboy.chat01.model
 * @ClassName: Chat
 * @Description: 单聊类
 * @Version 1.0
 */
public class Chat {

    private String to;

    private String from;

    private String content;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
