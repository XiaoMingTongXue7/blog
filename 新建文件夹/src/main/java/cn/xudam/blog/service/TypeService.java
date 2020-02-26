package cn.xudam.blog.service;

import cn.xudam.blog.pojo.Type;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/5 21:52
 */
public interface TypeService {

    void saveType(Type type);

    Type getTypeById(Integer id);

    Type getTypeByName(String name);

    Boolean checkTypeName(String name);

    PageInfo<Type> listType(Integer pageNum);

    List<Type> listType();

    List<Type> listTypeTop();

    List<Type> listTypeTop(Integer size);

    PageInfo<Type> listType(Integer pageNum, Boolean isDesc);

    void updateType(Type type);

    void deleteType(Integer id);

}
