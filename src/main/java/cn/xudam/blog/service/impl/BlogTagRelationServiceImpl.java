package cn.xudam.blog.service.impl;

import cn.xudam.blog.dao.BlogMapper;
import cn.xudam.blog.dao.BlogTagRelationMapper;
import cn.xudam.blog.dao.TagMapper;
import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.BlogTagRelation;
import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.service.BlogTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 鸣
 * 2020/1/9 18:24
 */
@Service
public class BlogTagRelationServiceImpl implements BlogTagRelationService {

    private TagMapper tagMapper;

    private final BlogTagRelationMapper blogTagRelationMapper;

    private BlogMapper blogMapper;

    @Autowired
    public BlogTagRelationServiceImpl(BlogTagRelationMapper blogTagRelationMapper) {
        this.blogTagRelationMapper = blogTagRelationMapper;
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Autowired
    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void saveBlogTagByIds(List<BlogTagRelation> tagId) {
        for (BlogTagRelation blogTagRelation : tagId) {
            BlogTagRelation blogTagByBlogTagId = getBlogTagByBlogTagId(blogTagRelation);
            if(blogTagByBlogTagId != null){
                throw new NotFoundException("要添加的标签已存在");
            }
        }
        Integer integer = blogTagRelationMapper.saveBlogTagByIds(tagId);
        if(integer != tagId.size()){
            throw new NotFoundException("添加标签失败");
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void saveBlogTagByIds(Integer blogId, List<Integer> tagIds) {
        List<BlogTagRelation> blogTags = new ArrayList<>();
        for (Integer tagId : tagIds) {
            blogTags.add(new BlogTagRelation(blogId, tagId));
        }
        saveBlogTagByIds(blogTags);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void saveBlogTagById(BlogTagRelation blogTagRelation) {
        List<BlogTagRelation> blogTagRelations = new ArrayList<>();
        blogTagRelations.add(blogTagRelation);
        saveBlogTagByIds(blogTagRelations);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void deleteBlogTagByBlogId(Integer blogId) {
        if(blogId == null){
            throw new NotFoundException("blogId 为空");
        }
        blogTagRelationMapper.deleteBlogTagById(blogId);
    }

    @Override
    public BlogTagRelation getBlogTagByBlogTagId(BlogTagRelation blogTagRelation) {
        if(blogTagRelation.getBlogId() == null || blogTagRelation.getTagId() == null){
            throw new NotFoundException("要添加的标签属性不明确");
        }
        List<BlogTagRelation> blogTags = getBlogTagsByBlogTagId(blogTagRelation);
        if(blogTags.size() == 0){
            return null;
        }
        return blogTags.get(0);
    }

    @Override
    public List<Tag> getTagsByBlogId(Integer blogId) {
        if(blogId == null){
            throw new NotFoundException("blogId 为空");
        }
        BlogTagRelation blogTagRelation = new BlogTagRelation();
        blogTagRelation.setBlogId(blogId);
        List<BlogTagRelation> tagIds = getBlogTagsByBlogTagId(blogTagRelation);
        List<Tag> tags = new ArrayList<>();
        for (BlogTagRelation id : tagIds) {
            tags.add(tagMapper.getTagById(id.getTagId()));
        }
        return tags;
    }

    @Override
    public List<Blog> getBlogsByTagId(Integer tagId) {
        if(tagId == null){
            throw new NotFoundException("tagId 为空");
        }
        BlogTagRelation blogTagRelation = new BlogTagRelation();
        blogTagRelation.setTagId(tagId);
        List<BlogTagRelation> blogIds = getBlogTagsByBlogTagId(blogTagRelation);
        List<Blog> blogs = new ArrayList<>();
        for (BlogTagRelation id : blogIds) {
            blogs.add(blogMapper.getBlogById(id.getBlogId()));
        }
        return blogs;
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void updateBlogTag(Integer blogId, List<Integer> tagIds) {
        deleteBlogTagByBlogId(blogId);
        saveBlogTagByIds(blogId, tagIds);
    }

    @Override
    public List<BlogTagRelation> getBlogTagsByBlogTagId(BlogTagRelation blogTagRelation) {
        return blogTagRelationMapper.getBlogTagByBlogTagId(blogTagRelation);
    }
}
