package org.javaboy.securityjson.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author szh
 * @Date 2022/5/17 10:37
 * @PackageName:org.javaboy.securityjson.filter
 * @ClassName: MyAuthenticationFilter
 * @Description: 登录以 json 格式传递（增强功能 既能以json格式传递，也能以key value形式传递）
 * @Version 1.0
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 若用户以json格式传递的参数
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            // 从 request 中提取JSON数据
            String username = null;
            String password = null;
            try {
                // 从流中获取 JSON 数据，要求请求体（body）中要有数据，get 和 delete 请求中就没有流
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                username = map.get("username");
                password = map.get("password");
            } catch (IOException e) {
                e.printStackTrace();
            }

            username = (username != null) ? username : "";
            username = username.trim();

            password = (password != null) ? password : "";
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        // 父类默认是调用 key value 形式传递的参数
        return super.attemptAuthentication(request, response);
    }
}
