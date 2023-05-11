package org.javaboy.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.javaboy.jwt.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author szh
 * @Date 2022/5/17 11:24
 * @PackageName:org.javaboy.jwt.filter
 * @ClassName: JwtLoginFilter
 * @Description: 登录后生成token, 继承 AbstractAuthenticationProcessingFilter 类
 * @Version 1.0
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 构造方法
     */
    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        // 属性赋值
        setAuthenticationManager(authenticationManager);
    }

    /**
     * JSON格式登录
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 获取请求传递的参数并将其转为对象
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        // 这里相当于执行登录操作
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    /**
     * 登录成功后生成token并返回给前端
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult 用户信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 获取登录用户的角色
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer sb = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            // 获取角色名称并添加(用,隔开)
            sb.append(authority.getAuthority()).append(",");
        }
        // 生成JWT
        String jwt = Jwts.builder().claim("authorities", sb)
                // 设置主题：一般用用户名
                .setSubject(authResult.getName())
                // 过期时间 60 分钟
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                // 设置签名和加密算法
                .signWith(SignatureAlgorithm.HS512, "javaboy@123").compact();
        // 封装数据返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        map.put("msg", "登录成功");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 返回json数据
        out.write(new ObjectMapper().writeValueAsString(map));
        out.close();
    }

    /**
     * 登录失败
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> map = new HashMap<>();
        map.put("msg", "登录失败");
        // 这里也可以对异常的类型进行判别再返回响应的信息
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 返回json数据
        out.write(new ObjectMapper().writeValueAsString(map));
        out.close();
    }
}
