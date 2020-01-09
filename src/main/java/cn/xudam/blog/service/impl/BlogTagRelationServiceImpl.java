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

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鸣
 * 2020/1/9 18:24
 */
@Service
public class BlogTagRelationServiceImpl implements BlogTagRelationService {

    TagMapper tagMapper;

    private final BlogTagRelationMapper blogTagRelationMapper;

    BlogMapper blogMapper;

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

    @Override
    public void saveBlogTagByIds(List<BlogTagRelation> tagId) {
        for (BlogTagRelation blogTagRelation : tagId) {
            if(blogTagRelation.getBlogId() == null || blogTagRelation.getTagId() == null){
                throw new NotFoundException("要添加的标签属性不明确");
            }
            List<BlogTagRelation> blogTagByBlogTagId = blogTagRelationMapper.getBlogTagByBlogTagId(blogTagRelation);
            if(blogTagByBlogTagId.size() == 0){
                throw new NotFoundException("要添加的标签不存在");
            }
        }
        Integer integer = blogTagRelationMapper.saveBlogTagByIds(tagId);
        if(!(integer == tagId.size())){
            throw new NotFoundException("添加标签失败");
        }
    }

    @Override
    public void saveBlogTagById(BlogTagRelation blogTagRelation) {
        List<BlogTagRelation> blogTagRelations = new ArrayList<>();
        blogTagRelations.add(blogTagRelation);
        saveBlogTagByIds(blogTagRelations);
    }

    @Override
    public void deleteBlogTag(BlogTagRelation blogTagRelation) {
        if(blogTagRelation.getBlogId() == null || blogTagRelation.getTagId() == null){
            throw new NotFoundException("要删除的标签属性不明确");
        }
        BlogTagRelation blogTagByBlogTagId = getBlogTagByBlogTagId(blogTagRelation);
        if(blogTagByBlogTagId == null){
            blogTagRelationMapper.deleteBlogTag(blogTagRelation);
        }
        throw new NotFoundException("要删除的标签不存在");
    }

    @Override
    public BlogTagRelation getBlogTagByBlogTagId(BlogTagRelation blogTagRelation) {
        if(blogTagRelation.getBlogId() == null || blogTagRelation.getTagId() == null){
            throw new NotFoundException("要添加的标签属性不明确");
        }
        List<BlogTagRelation> blogTags = getBlogTagsByBlogTagId(blogTagRelation);
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

    @Override
    public List<BlogTagRelation> getBlogTagsByBlogTagId(BlogTagRelation blogTagRelation) {
        return blogTagRelationMapper.getBlogTagByBlogTagId(blogTagRelation);
    }
}
