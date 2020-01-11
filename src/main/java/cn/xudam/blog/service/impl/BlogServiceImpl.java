package cn.xudam.blog.service.impl;

import cn.xudam.blog.dao.BlogMapper;
import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.service.BlogService;
import cn.xudam.blog.service.BlogTagRelationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public Blog getBlogById(Integer id) {
        if (id == null){
            throw new NotFoundException("输入的id不正确");
        }
        Blog blog = blogMapper.getBlogById(id);
        blog.setTags(blogTagService.getTagsByBlogId(blog.getId()));
        return blog;
    }

    @Override
    public PageInfo<Blog> listBlog(Integer pageNum) {
        return listBlog(pageNum, true);
    }

    @Override
    public PageInfo<Blog> listBlogByCond(BlogCond blogCond) {
        PageHelper.startPage(blogCond.getPageNum(), 5, "createTime" + (blogCond.getDesc()?" desc":""));
        List<Blog> blogs = blogMapper.listBlogByCond(blogCond);
        return new PageInfo<Blog>(blogs);
    }

    @Override
    public List<Blog> listBlog() {
        return blogMapper.listBlog();
    }

    @Override
    public PageInfo<Blog> listBlog(Integer pageNum, Boolean isDesc) {
        BlogCond blogCond = new BlogCond();
        blogCond.setPageNum(pageNum);
        blogCond.setDesc(isDesc);
        return listBlogByCond(blogCond);
    }

    @Override
    public void saveBlog(Blog blog) {
        if(blog.getTitle()==null || blog.getContent()==null || blog.getType()==null || blog.getFlag()==null){
            throw new NotFoundException("博客数据不完整，添加失败");
        }
        Integer integer;
        if(blog.getId() == null){
            blog.setUpdateTime(LocalDateTime.now());
            blog.setCreateTime(LocalDateTime.now());
            integer = blogMapper.saveBlog(blog);
        } else {
            blog.setUpdateTime(LocalDateTime.now());
            integer = blogMapper.updateBlog(blog);
        }

        if(integer != 1){
            throw new NotFoundException("添加博客失败");
        }
    }

    @Override
    public void updateBlog(Blog blog) {
        Blog blogById = blogMapper.getBlogById(blog.getId());
        if(blogById == null){
            throw new NotFoundException("要更新的博客不存在");
        }
        Integer integer = blogMapper.updateBlog(blog);
        if(integer != 1){
            throw new NotFoundException("更新博客失败");
        }
    }

    @Override
    public void deleteBlog(Integer id) {
        if(id == null){
            throw new NotFoundException("删除博客失败");
        }
        Integer integer = blogMapper.deleteBlogById(id);
        if(integer != 1){
            throw new NotFoundException("删除博客失败");
        }
    }
}
