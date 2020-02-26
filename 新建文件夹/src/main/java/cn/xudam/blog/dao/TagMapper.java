package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * 标签持久层
 * @author 鸣
 * 2020/1/6 9:16
 */
@Mapper
@Repository
public interface TagMapper {

    /**
     * 添加标签
     * @param tag 要添加的标签
     * @return
     */
    Integer saveTag(Tag tag);

    /**
     * 通过id获取标签
     * @param id 标签的id
     * @return
     */
    Tag getTagById(Integer id);

    /**
     * 通过标签名称获取标签
     * @param name 标签名称
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 获取所有的标签
     * @return
     */
    List<Tag> listTag();

    /**
     * 更新一个标签
     * @param tag 要更新的标签
     * @return
     */
    Integer updateTag(Tag tag);

    /**
     * 删除一个标签
     * @param id
     * @return
     */
    Integer deleteTag(Integer id);

}
