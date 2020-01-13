package cn.xudam.blog.service.impl;

import cn.xudam.blog.dao.UserMapper;
import cn.xudam.blog.pojo.User;
import cn.xudam.blog.service.UserService;
import cn.xudam.blog.util.Md5Utils;
import org.springframework.stereotype.Service;

/**
 * @author 鸣
 * 2020/1/4 20:24
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.checkUser(username, Md5Utils.code(password));
        return user;
    }
}
