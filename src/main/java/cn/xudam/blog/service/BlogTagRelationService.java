package cn.xudam.blog.service;

import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.BlogTagRelation;
import cn.xudam.blog.pojo.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/9 18:10
 */
@Service
public interface BlogTagRelationService {

    void saveBlogTagByIds(List<BlogTagRelation> tagId);

    void saveBlogTagByIds(Integer blogId, List<Integer> tagIds);

    void saveBlogTagById(BlogTagRelation blogTagRelation);

    void deleteBlogTag(BlogTagRelation blogTagRelation);

    BlogTagRelation getBlogTagByBlogTagId(BlogTagRelation blogTagRelation);

    List<Tag> getTagsByBlogId(Integer blogId);

    List<Blog> getBlogsByTagId(Integer tagId);

    void updateBlogTag(Integer blogId, List<Integer> tagIds);

    List<BlogTagRelation> getBlogTagsByBlogTagId(BlogTagRelation blogTagRelation);

}
