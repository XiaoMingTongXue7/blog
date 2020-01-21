package cn.xudam.blog.service.impl;

import cn.xudam.blog.constant.WebConst;
import cn.xudam.blog.dao.BlogMapper;
import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.pojo.Type;
import cn.xudam.blog.service.BlogService;
import cn.xudam.blog.service.BlogTagRelationService;
import cn.xudam.blog.util.Commons;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 鸣
 * 2020/1/7 20:49
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogMapper blogMapper;

    public BlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    private BlogTagRelationService blogTagService;

    @Autowired
    public void setBlogTagService(BlogTagRelationService blogTagRelationService) {
        this.blogTagService = blogTagRelationService;
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public Blog getBlogById(Integer id) {
        if (id == null){
            throw new NotFoundException("输入的id不正确");
        }
        Blog blog = blogMapper.getBlogById(id);
        blog.setTags(blogTagService.getTagsByBlogId(blog.getId()));
        blogMapper.updateBlogViewsByBlogId(id);
        return blog;
    }

    @Override
    public Blog getBlogByName(String title) {
        return blogMapper.getBlogByName(title);
    }

    @Override
    public Boolean checkBlogName(String title) {
        Blog blog = getBlogByName(title);
        return blog != null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>(years.size());
        for (String year : years) {
            map.put(year, blogMapper.listBlogByYear(Integer.parseInt(year)));
        }
        return map;
    }

    @Override
    public Integer countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public PageInfo<Blog> listBlog(Integer pageNum) {
        return listBlog(pageNum, true);
    }

    @Override
    public PageInfo<Blog> listBlogByCond(BlogCond blogCond) {
        PageHelper.startPage(blogCond.getPageNum(), 5, "updateTime" + (blogCond.getDesc()?" desc":""));
        List<Blog> blogs = blogMapper.listBlogByCond(blogCond);
        return new PageInfo<Blog>(blogs);
    }

    @Override
    public List<Blog> listBlog() {
        return blogMapper.listBlog();
    }

    @Override
    public List<Blog> listBlogByType(Type type) {
        BlogCond blogCond = new BlogCond();
        blogCond.setTypeId(type.getId());
        return blogMapper.listBlogByCond(blogCond);
    }

    @Override
    public List<Blog> listBlogByTag(Tag tag) {
        if(tag.getId() == null){
            throw new NotFoundException("没有正确的标签");
        }
        return blogTagService.getBlogsByTagId(tag.getId());
    }

    @Override
    public PageInfo<Blog> listBlog(Integer pageNum, Boolean isDesc) {
        BlogCond blogCond = new BlogCond();
        blogCond.setPageNum(pageNum);
        blogCond.setDesc(isDesc);
        return listBlogByCond(blogCond);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void saveBlog(Blog blog, String tagIds) {
        checkBlog(blog);
        List<Integer> tagId = Commons.stringToList(tagIds);
        Integer integer;
        if(blog.getId() == null){
            blog.setUpdateTime(LocalDateTime.now());
            blog.setCreateTime(LocalDateTime.now());
            integer = blogMapper.saveBlog(blog);
            blogTagService.saveBlogTagByIds(blog.getId(), tagId);
        } else {
            blog.setUpdateTime(LocalDateTime.now());
            integer = blogMapper.updateBlog(blog);
            blogTagService.updateBlogTag(blog.getId(), tagId);
        }
        if(integer != 1){
            throw new NotFoundException("添加博客失败");
        }
    }

    private void checkBlog(Blog blog) {
        if(blog.getTitle()==null || blog.getTitle().length()> WebConst.MAX_BLOG_TITLE){
            throw new NotFoundException("添加博客失败，博客标题过长");
        }
        if(blog.getContent()==null || blog.getTitle().length()> WebConst.MAX_BLOG_CONTENT){
            throw new NotFoundException("添加博客失败，博客内容过长");
        }
        if(blog.getDescription()==null || blog.getTitle().length()> WebConst.MAX_BLOG_DESCRIPTION){
            throw new NotFoundException("添加博客失败，博客描述过长");
        }
        if(blog.getFirstPic()==null || blog.getTitle().length()> WebConst.MAX_BLOG_FIRST_PIC){
            throw new NotFoundException("添加博客失败，博客首图链接过长");
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void deleteBlog(Integer id) {
        if(id == null){
            throw new NotFoundException("删除博客失败");
        }
        blogTagService.deleteBlogTagByBlogId(id);
        Integer integer = blogMapper.deleteBlogById(id);
        if(integer != 1){
            throw new NotFoundException("删除博客失败");
        }
    }
}
