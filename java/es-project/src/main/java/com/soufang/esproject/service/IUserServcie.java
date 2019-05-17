package com.soufang.esproject.service;

import com.soufang.esproject.entity.User;
import com.soufang.esproject.web.dto.UserDTO;

/**
 * 用户服务
 * Description: es-project
 * Create by liangxifeng on 19-4-20
 */
public interface IUserServcie {
    User findUserByName (String userName);

    ServiceResult<UserDTO> findById(Long userId);

    /**
     * 根据电话号码寻找用户
     * @param telephone
     * @return
     */
    User findUserByTelephone(String telephone);

    /**
     * 通过手机号注册用户
     * @param telehone
     * @return
     */
    User addUserByPhone(String telehone);

    /**
     * 修改指定属性值
     * @param profile
     * @param value
     * @return
     */
    ServiceResult modifyUserProfile(String profile, String value);
}
