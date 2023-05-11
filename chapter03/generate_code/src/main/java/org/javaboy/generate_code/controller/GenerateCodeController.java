package org.javaboy.generate_code.controller;

import org.javaboy.generate_code.model.RespBean;
import org.javaboy.generate_code.model.TableClass;
import org.javaboy.generate_code.service.GenerateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author szh
 */
@RestController
public class GenerateCodeController {

    @Autowired
    GenerateCodeService generateCodeService;

    @PostMapping("/generateCode")
    public RespBean generateCode(@RequestBody List<TableClass> tableClassList, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/");
        System.out.println("path = " + path);
        return generateCodeService.generateCode(tableClassList, request.getServletContext().getRealPath("/"));
    }
}
