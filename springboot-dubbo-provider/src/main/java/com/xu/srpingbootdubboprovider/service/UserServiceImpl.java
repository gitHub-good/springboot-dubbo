package com.xu.srpingbootdubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.xu.dubbo.dubbointerface.model.User;
import com.xu.dubbo.dubbointerface.service.UserService;
import com.xu.srpingbootdubboprovider.dao.UserMapper;
import com.xu.srpingbootdubboprovider.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component/*注入spring 容器的bean*/
@Service(version = "1.0.0",timeout = 10000) /*dubbo注解==dubbo:service 相当于把UserService注册到zookeeper中*/
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserGetPage(Map<String, Object> paramMap) {
        return userMapper.selectUserByPage(paramMap);
    }

    @Override
    public int insertUser(User user) {
        user.setId(Integer.valueOf(RandomUtil.getRandom(5,RandomUtil.TYPE.NUMBER)));
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    @Override
    public User selectUser(User user) {
        return userMapper.selectUser(user);
    }

    @Override
    public int getUserTotal() {
        return userMapper.selectUserByTotal();
    }
}
