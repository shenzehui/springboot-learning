package org.javaboy.validation.controller;

import org.javaboy.validation.model.User;
import org.javaboy.validation.validation.ValidationGroup1;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author szh
 * @Date 2022/5/18 19:50
 * @PackageName:org.javaboy.validation.model.controller
 * @ClassName: UserController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class UserController {

    /**
     * 添加 @Validated 表示校验该参数   BindingResult：表示出错信息  value 属性 ：分组指定
     * 注意：没分组的就不会校验，只有分组属性的校验，若不指定分组，就都会校验
     */
    @PostMapping("/addUser")
    public void addUser(@Validated(ValidationGroup1.class) User user, BindingResult result) {
        // 不为空且有错误信息
        if (result != null && result.hasErrors()) {
            // 获取全部异常信息
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                // 错误的信息
                System.out.println(error.getObjectName() + ":" + error.getDefaultMessage());
            }
        }
    }
}
