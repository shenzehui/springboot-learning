package org.javabot.securitydemo.service;

import org.javabot.securitydemo.mapper.HrMapper;
import org.javabot.securitydemo.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author szh
 * @Date 2022/5/20 14:55
 * @PackageName:org.javabot.securitydemo.service
 * @ClassName: HrService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if(hr == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        hr.setRoles(hrMapper.getRolesByHrId(hr.getId()));
        return hr;
    }
}
