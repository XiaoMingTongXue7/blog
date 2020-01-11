package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.BlogTagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/9 16:22
 */
@Mapper
@Repository
public interface BlogTagRelationMapper {

    /**
     *
     * @param tagId
     * @return
     */
    Integer saveBlogTagByIds(List<BlogTagRelation> tagId);

    Integer deleteBlogTag(BlogTagRelation blogTagRelation);

    Integer deleteBlogTagById(Integer blogId);

    List<BlogTagRelation> getBlogTagByBlogTagId(BlogTagRelation blogTagRelation);

}
