package com.soufang.esproject.service;

import com.soufang.esproject.entity.User;

/**
 * 用户服务
 * Description: es-project
 * Create by liangxifeng on 19-4-20
 */
public interface IUserServcie {
    User findUserByName (String userName);
}
