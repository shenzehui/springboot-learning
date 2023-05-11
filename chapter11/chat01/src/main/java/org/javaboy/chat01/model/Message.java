package org.javaboy.chat01.model;

/**
 * @Author szh
 * @Date 2022/5/17 14:10
 * @PackageName:org.javaboy.chat01.model
 * @ClassName: Message
 * @Description: TODO
 * @Version 1.0
 */
public class Message {
    /**
     * 消息发出者
     */
    private String name;

    /**
     * 消息的内容
     */
    private String content;

    @Override
    public String toString() {
        return "Message{" + "name='" + name + '\'' + ", content='" + content + '\'' + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
