package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层
 * @author 鸣
 * 2020/1/4 20:22
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据用户名和密码查询用户信息
     * @param userName 用户名
     * @param password 密码
     * @return User 查询到的用户信息
     */
    User checkUser(String userName, String password);
}
