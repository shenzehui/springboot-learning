package org.javaboy.securitydy.service;

import org.javaboy.securitydy.bean.Menu;
import org.javaboy.securitydy.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author szh
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }
}
