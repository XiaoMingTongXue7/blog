package cn.xudam.blog.service;

import cn.xudam.blog.pojo.User;

/**
 * @author 鸣
 * 2020/1/4 20:21
 */
public interface UserService {

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User checkUser(String username, String password);
}
