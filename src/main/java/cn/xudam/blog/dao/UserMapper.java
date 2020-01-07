package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 鸣
 * 2020/1/4 20:22
 */
@Mapper
@Repository
public interface UserMapper {

    User checkUser(String userName, String password);
}
