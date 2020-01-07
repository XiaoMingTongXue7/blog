package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author 鸣
 * 2020/1/6 9:16
 */
@Mapper
@Repository
public interface TypeMapper {

    Integer saveType(Type type);

    Type getTypeById(Integer id);

    List<Type> listType();

    Integer updateType(Type type);

    Integer deleteType(Integer id);

}
