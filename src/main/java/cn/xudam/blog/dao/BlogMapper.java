package cn.xudam.blog.dao;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/7 20:50
 */
@Mapper
@Repository
public interface BlogMapper {

    Blog getBlogById(Integer id);

    List<Blog> listBlogByCond(BlogCond blogCond);

    List<Blog> listBlog();

    Integer saveBlog(Blog blog);

    Integer updateBlog(Blog blog);

    Integer deleteBlogById(Integer id);

}
