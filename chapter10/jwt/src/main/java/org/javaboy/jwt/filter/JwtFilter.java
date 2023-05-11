package org.javaboy.jwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author szh
 * @Date 2022/5/17 11:49
 * @PackageName:org.javaboy.jwt.filter
 * @ClassName: JwtFilter
 * @Description: 过滤器：用户每次访问接口都会携带 token，这个过滤器就会检验token是否正确 Y:请求继续往下走 N:拦截
 * @Version 1.0
 */
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // 从请求头中获取token头名称自定义
        String jwtToken = req.getHeader("Authorization");
        // 解析token
        Jws<Claims> jws = Jwts.parser().setSigningKey("javaboy@123")
                // 去除Token默认会添加的头 Bearer
                .parseClaimsJws(jwtToken.replace("Bearer", ""));
        // 获取jwt的body
        Claims claims = jws.getBody();
        // 从body 中 获取主题（前面设置的是用户名为主题）
        String username = claims.getSubject();
        // 获取角色信息
//        Object roles = claims.get("authorities");
        // 这里我们可以通过工具类自动将字符串转为对象格式(工具类仅限于用逗号隔开)
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
        /**
         * 作用：校验token
         * 参数说明：
         * principal：用户名
         * credentials：密码
         * authorities：角色
         */
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // 重新设值
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
}
