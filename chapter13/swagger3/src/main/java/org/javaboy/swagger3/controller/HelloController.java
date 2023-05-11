package org.javaboy.swagger3.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.javaboy.swagger3.model.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author szh
 * @Date 2022/5/18 18:57
 * @PackageName:org.javaboy.swagger3.controller
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    @ApiIgnore // 忽略接口
    public String hello() {
        return "hello swagger3";
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户", notes = "根据Id查询用户") // notes 详细信息  value 说明接口
// 新版注解：@Operation(summary = "查询用户",description = "根据Id查询用户")
    /*
     * paramType：参数类型
     *   -query   是以key，value形式传递过来
     *   -path:   参数放在地址栏中，与@PathVariable配合使用
     *   -body:   放在请求体中，与@RequestBody使用
     * value：    参数中文解释
     * required： 参数是否必须
     * */
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true)
    // 设置不同响应码对应的中文含义
    @ApiResponses({@ApiResponse(responseCode = "200", description = "请求成功"), @ApiResponse(responseCode = "500", description = "请求失败")})
    // 多参
//    @ApiImplicitParams({
//            @ApiImplicitParam
//    })
    public String getUserById(@PathVariable(required = false) String id) {
        return "id = " + id;
    }

    @PostMapping("/addUser")
    @Parameter(name = "user")
    public String addUser(@RequestBody User user) {
        return user.toString();
    }
}
