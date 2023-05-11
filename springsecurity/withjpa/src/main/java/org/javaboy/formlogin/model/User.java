package org.javaboy.formlogin.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author szh
 * @Date 2022/5/19 19:27
 * @PackageName:org.javaboy.formlogin.model
 * @ClassName: User
 * @Description: TODO
 * @Version 1.0
 */
@Entity(name = "t_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<Role> roles;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public Long getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    /*返回用户的角色*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /*返回用户密码*/
    @Override
    public String getPassword() {
        return password;
    }
    /*返回用户名*/
    @Override
    public String getUsername() {
        return username;
    }

    /*账号是否未过期*/
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /*账户是否未锁定*/
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /*密码是否未过期*/
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /*账户是否可用*/
    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
