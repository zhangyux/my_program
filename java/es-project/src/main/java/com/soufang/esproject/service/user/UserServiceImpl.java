package com.soufang.esproject.service.user;

import com.soufang.esproject.entity.Role;
import com.soufang.esproject.entity.User;
import com.soufang.esproject.repository.RoleReposity;
import com.soufang.esproject.repository.UserRepository;
import com.soufang.esproject.service.IUserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: es-project
 * Create by liangxifeng on 19-4-20
 */
@Service
public class UserServiceImpl implements IUserServcie {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleReposity roleReposity;

    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByName(userName);
        if( user == null ){
            return null;
        }
        List<Role> roles = roleReposity.findRolesByUserId(user.getId());
        if( roles == null || roles.isEmpty() ){
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        //java8的流式方法, 使用lavb表达式
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }
}
