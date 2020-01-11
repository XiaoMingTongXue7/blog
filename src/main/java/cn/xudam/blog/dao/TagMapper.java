package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author 鸣
 * 2020/1/6 9:16
 */
@Mapper
@Repository
public interface TagMapper {

    Integer saveTag(Tag tag);

    Tag getTagById(Integer id);

    List<Tag> listTag();

    Integer updateTag(Tag tag);

    Integer deleteTag(Integer id);

}
