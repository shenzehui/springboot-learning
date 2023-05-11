package org.javaboy.actuator.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author szh
 * @Date 2022/6/1 16:51
 * @PackageName:org.javaboy.actuator.config
 * @ClassName: AppInfo
 * @Description: 自定义应用信息（Java代码实现）
 * @Version 1.0
 */
@Component
public class AppInfo implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> link = new HashMap<>();
        link.put("site", "www.javaboy.org");
        link.put("site-2", "www.ithub.com");
        builder.withDetail("Link", link);
    }
}
