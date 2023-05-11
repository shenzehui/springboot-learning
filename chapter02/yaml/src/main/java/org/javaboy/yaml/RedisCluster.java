package org.javaboy.yaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 内省:注入实质是根据get和set方法的名称注入的，即使属性名与properties不同也没有影响，只要get和set方法名称和properties方法名称保持一致
 *
 * @author szh
 */
@Component
@ConfigurationProperties("redis")
public class RedisCluster {

    private Integer port;

    private List<String> hosts;

    private List<Redis> redisList;

    public List<Redis> getRedisList() {
        return redisList;
    }

    public void setRedisList(List<Redis> redisList) {
        this.redisList = redisList;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    @Override
    public String toString() {
        return "RedisCluster{" + "port=" + port + ", hosts=" + hosts + ", redisList=" + redisList + '}';
    }
}
