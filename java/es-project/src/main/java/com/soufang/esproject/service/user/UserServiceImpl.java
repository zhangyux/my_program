package com.soufang.esproject.service.user;

import com.soufang.esproject.entity.Role;
import com.soufang.esproject.entity.User;
import com.soufang.esproject.repository.RoleReposity;
import com.soufang.esproject.repository.UserRepository;
import com.soufang.esproject.service.IUserServcie;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.web.dto.UserDTO;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper = new ModelMapper();

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

    @Override
    public ServiceResult<UserDTO> findById(Long userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return ServiceResult.notFound();
        }
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ServiceResult.of(userDTO);
    }

    @Override
    public User findUserByTelephone(String telephone) {
        return null;
    }

    @Override
    public User addUserByPhone(String telehone) {
        return null;
    }

    @Override
    public ServiceResult modifyUserProfile(String profile, String value) {
        return null;
    }
}
