package com.xu.dubbo.dubbointerface.service;

import com.xu.dubbo.dubbointerface.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 添加用户
     * @param user
     */
    int insertUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户信息
     * @param user
     */
    int deleteUser(User user);

    /**
     * 根据ID查询用户信息
     * @param user
     * @return
     */
    User selectUser(User user);
    /**
     * 分页查询
     * @param paramMap
     * @return
     */
    List<User> getUserGetPage(Map<String, Object> paramMap);

    /**
     * 分页查询总数
     * @return
     */
    int getUserTotal();
}
