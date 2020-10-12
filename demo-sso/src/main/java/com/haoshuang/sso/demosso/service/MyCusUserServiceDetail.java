package com.haoshuang.sso.demosso.service;

import com.haoshuang.sso.demosso.Mapper.SysRoleMapper;
import com.haoshuang.sso.demosso.Mapper.SysUserMapper;
import com.haoshuang.sso.demosso.bean.SysRole;
import com.haoshuang.sso.demosso.bean.SysUser;
import com.haoshuang.sso.demosso.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyCusUserServiceDetail implements UserDetailsService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();


        SysUser sysUser =  sysUserMapper.selectByName(s);

//        用户不存在
        if(null == sysUser){
            throw new UsernameNotFoundException("用户不存在");
        }

        List<SysRole> roles = sysRoleMapper.selectByUser(sysUser.getId());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        user.setUsername(sysUser.getName());
        user.setRoles(roles);
        return user;
    }
}
