package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 分类持久层
 * @author 鸣
 * 2020/1/6 9:16
 */
@Mapper
@Repository
public interface TypeMapper {

    /**
     * 添加一个标签
     * @param type
     * @return
     */
    Integer saveType(Type type);

    /**
     * 通过id获取分类
     * @param id
     * @return
     */
    Type getTypeById(Integer id);

    /**
     * 通过名称获取分类
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 获取所有的分类列表
     * @return
     */
    List<Type> listType();

    /**
     * 更新一个分类
     * @param type
     * @return
     */
    Integer updateType(Type type);

    /**
     * 删除一个分类
     * @param id
     * @return
     */
    Integer deleteType(Integer id);

}
