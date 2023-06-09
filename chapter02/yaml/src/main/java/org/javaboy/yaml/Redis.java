package org.javaboy.yaml;

/**
 * @author szh
 */
public class Redis {

    private Integer port;

    private String host;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Redis{" + "port=" + port + ", host='" + host + '\'' + '}';
    }
}
