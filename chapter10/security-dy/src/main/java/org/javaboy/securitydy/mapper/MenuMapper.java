package org.javaboy.securitydy.mapper;

import org.javaboy.securitydy.bean.Menu;

import java.util.List;

/**
 * @author szh
 */
public interface MenuMapper {
    /**
     * 查询所有菜单以及关联的角色
     *
     * @return
     */
    List<Menu> getAllMenus();
}
