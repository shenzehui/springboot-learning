package org.javabot.securitydemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javabot.securitydemo.model.Hr;
import org.javabot.securitydemo.model.Role;

import java.util.List;

/**
 * @Author szh
 * @Date 2022/5/20 14:55
 * @PackageName:org.javabot.securitydemo.mappser
 * @ClassName: HrMapper
 * @Description: TODO
 * @Version 1.0
 */
@Mapper
public interface HrMapper {
    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Integer id);
}
