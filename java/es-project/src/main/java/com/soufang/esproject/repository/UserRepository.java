package com.soufang.esproject.repository;

import com.soufang.esproject.entity.User;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by 瓦力.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /*
    User findByName(String userName);

    User findUserByPhoneNumber(String telephone);

    @Modifying
    @Query("update User as user set user.name = :name where id = :id")
    void updateUsername(@Param(value = "id") Long id, @Param(value = "name") String name);

    @Modifying
    @Query("update User as user set user.email = :email where id = :id")
    void updateEmail(@Param(value = "id") Long id, @Param(value = "email") String email);

    @Modifying
    @Query("update User as user set user.password = :password where id = :id")
    void updatePassword(@Param(value = "id") Long id, @Param(value = "password") String password);
    */
}
