package cn.xudam.blog.service.impl;

import cn.xudam.blog.dao.BlogMapper;
import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

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

    @Override
    public Blog getBlog(Integer id) {
        if (id == null){
            throw new NotFoundException("输入的id不正确");
        }
        return blogMapper.getBlogById(id);
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
        if(blog == null){
            throw new NotFoundException("添加博客失败");
        }
        Integer integer = blogMapper.saveBlog(blog);
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
