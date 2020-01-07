package cn.xudam.blog.service;

import cn.xudam.blog.dao.UserMapper;
import cn.xudam.blog.pojo.User;
import cn.xudam.blog.util.MD5Utils;
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
        User user = userMapper.checkUser(username, MD5Utils.code(password));
        return user;
    }
}
