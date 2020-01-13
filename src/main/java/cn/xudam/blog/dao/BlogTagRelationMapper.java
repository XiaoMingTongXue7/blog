package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.BlogTagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客标签持久层
 * @author 鸣
 * 2020/1/9 16:22
 */
@Mapper
@Repository
public interface BlogTagRelationMapper {

    /**
     * 通过List<BlogTagRelation>保存博客标签
     * @param tagId 是一个 BlogTagRelation 类的列表
     * @return
     */
    Integer saveBlogTagByIds(List<BlogTagRelation> tagId);

    /**
     * 通过博客id删除博客标签
     * 会删除blogId的所有有关标签
     * @param blogId 要删除标签的博客id
     * @return
     */
    Integer deleteBlogTagById(Integer blogId);

    /**
     * 通过参数动态的返回一个列表
     * 如果传入的对象中只有blogId，那么返回就是该blog的所有标签的id的列表
     * 如果传入的对象中只有tagId，那么返回就是所有含有该tag的博客的id的列表
     * 如果传入的对象中既有blogId，又有tagId，那么返回的就是一个博客标签信息
     * @param blogTagRelation
     * @return
     */
    List<BlogTagRelation> getBlogTagByBlogTagId(BlogTagRelation blogTagRelation);

}
