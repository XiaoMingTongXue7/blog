package cn.xudam.blog.service;

import cn.xudam.blog.pojo.Type;
import com.github.pagehelper.PageInfo;

/**
 * @author 鸣
 * 2020/1/5 21:52
 */
public interface TypeService {

    void saveType(Type type);

    Type getTypeById(Integer id);

    PageInfo<Type> listType(Integer id);

    PageInfo<Type> listType(Integer id, Boolean isDesc);

    void updateType(Type type);

    void deleteType(Integer id);

}
