package org.javaboy.securitydy;

import org.javaboy.securitydy.bean.Menu;
import org.javaboy.securitydy.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    MenuService menuService;
    @Test
    void contextLoads() {
        List<Menu> list = menuService.getAllMenus();
        for (Menu menu : list) {
            System.out.println(menu);
        }
    }

}
