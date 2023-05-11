package org.javaboy.test;

import org.javaboy.test.dao.UserDao;
import org.javaboy.test.model.User;
import org.javaboy.test.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @SpringBootTest
 * webEnvironment：指定 web 应用环境：
 *  -MOCK：提供了模拟的Servlet环境
 *  -RANDOM_PORT：随机端口：提供了真实的Servlet环境，端口随机的 事务不会回滚
 *  -DEFINED_PORT:端口指定的真实Servlet环境                  事务不会回滚
 *  -NONE
 *
 *  classes:指定应用的启动类
 */
@SpringBootTest(classes = Application.class)
class ApplicationTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    /*对controller的测试*/
    @BeforeEach
    /*初始化mockMvc*/
    void setup(){
        /*方式一 从上下文种获取controller  推荐 */
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        /*方式二 */
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }
    @Test
    void contextLoads() throws Exception {
        /*返回JSON数据*/
        mockMvc.perform(MockMvcRequestBuilders.get("/user/99").accept(MediaType.APPLICATION_JSON_UTF8))
                /*期望的状态码*/
                .andExpect(MockMvcResultMatchers.status().isOk())
                /*期望返回的Json内容*/
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":99,\"username\":\"javaboy\"}"))
                .andDo(MockMvcResultHandlers.print());
    }

    /*----------------------------------------------------------------------------------------------*/

    @MockBean //操作不会涉及到数据库
    UserDao userDao;

    @Autowired
    UserService userService;

    @Test
    void test1(){
        /*真实返回的数据对象 mock出来的 ，而不是userDao返回的*/
        User user = new User();
        user.setId(99L);
        user.setUsername("javaboy");
        Mockito.when(userDao.getUserById(99L)).thenReturn(user);

        User u = userService.getUserById(99L);
        Assertions.assertEquals(99L, u.getId());
        Assertions.assertEquals("javaboy", u.getUsername());
    }















}
