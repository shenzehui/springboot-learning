package org.javaboy.securitydb.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 实现UserDetails ，统一规范处理
 *
 * @author szh
 */
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private List<Role> roles;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账户是否未过期  :这里数据库没有 就写成没有过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定 数据库有字段，放回属性，注意：要删除重复的get方法
     */
    @Override
    public boolean isAccountNonLocked() {
        // 取反 0 代表没有锁定  1 代表锁定
        return !locked;
    }

    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否可用 删除 get 方法
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 返回用户的所有角色
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            // spring security 角色是要以 'ROLE_' 开头 ，也可以在数据库添加
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            // 这里因为数据库配置了前缀，所以不加了
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
