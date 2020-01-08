package cn.xudam.blog.service;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/7 20:43
 */
public interface BlogService {

    Blog getBlog(Integer id);

    PageInfo<Blog> listBlog(Integer pageNum);

    PageInfo<Blog> listBlogByCond(BlogCond blogCond);

    List<Blog> listBlog();

    PageInfo<Blog> listBlog(Integer pageNum, Boolean isDesc);

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Integer id);
}
