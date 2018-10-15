package com.xu.srpingbootdubboprovider.dao;

import com.xu.dubbo.dubbointerface.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper/*使该接口成为spring的bean*/
public interface UserMapper {

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
     * 根据id查询用户信息
     * @param user
     * @return
     */
    User selectUser(User user);
    /**
     * 分页查询
     * @param paramPage
     * @return
     */
    List<User> selectUserByPage(Map<String, Object> paramPage);

    /**
     * 分页查询总数
     * @return
     */
    int selectUserByTotal();

}