package cn.xudam.blog.dao;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.BlogTagRelation;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客持久层
 * @author 鸣
 * 2020/1/7 20:50
 */
@Mapper
@Repository
public interface BlogMapper {

    /**
     * 根据博客Id查询博客
     * @param id 博客的Id
     * @return Blog 查询到的博客
     */
    Blog getBlogById(Integer id);

    /**
     * 根据博客标题查询博客
     * @param title 博客的标题
     * @return 查询到的博客
     */
    Blog getBlogByName(String title);

    /**
     * 根据条件查询查询博客
     * 默认为第一页，倒叙
     * @param blogCond 查询博客的条件
     * @return List<Blog>查询到的博客
     */
    List<Blog> listBlogByCond(BlogCond blogCond);

    /**
     * 通过博客的Id集合获取响应的博客列表
     * @param blogIds 博客Id集合
     * @return
     */
    List<Blog> listBlogByBlogIds(List<BlogTagRelation> blogIds);

    /**
     * 查询所有博客
     * @return List<Blog>查询到的博客
     */
    List<Blog> listBlog();

    /**
     * 添加博客
     * @param blog 要添加的博客
     * @return 受影响的条数
     */
    Integer saveBlog(Blog blog);

    /**
     * 更新博客
     * @param blog 要添加的博客
     * @return 受影响的条数
     */
    Integer updateBlog(Blog blog);


    /**
     * 浏览次数+1
     * @param blogId 要增加浏览次数的博客
     */
    void updateBlogViewsByBlogId(Integer blogId);

    /**
     * 根据Id删除博客
     * @param id 博客的id
     * @return 影响数据库的条数
     */
    Integer deleteBlogById(@Param("id") Integer id);

    /**
     * 根据创建时间生成博客的创建年份列表
     * @return
     */
    List<String> findGroupYear();

    /**
     * 根据博客的创建年份查询博客信息
     * @param year
     * @return
     */
    List<Blog> listBlogByYear(Integer year);

    /**
     * 获取当前的博客总数
     * @return 博客总数，Integer类型
     */
    Integer countBlog();
}
