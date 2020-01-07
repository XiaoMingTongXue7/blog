package cn.xudam.blog.service;

import cn.xudam.blog.pojo.User;

/**
 * @author 鸣
 * 2020/1/4 20:21
 */
public interface UserService {

    User checkUser(String username, String password);
}
