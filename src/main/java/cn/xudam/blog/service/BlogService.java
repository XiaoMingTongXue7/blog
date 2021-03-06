package cn.xudam.blog.service;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.pojo.Type;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 博客服务层
 * @author 鸣
 * 2020/1/7 20:43
 */
public interface BlogService {

    /**
     * 通过博客Id查询博客
     * @param id 博客的Id
     * @return 查询到的博客
     */
    Blog getBlogById(Integer id);

    /**
     * 通过博客标题查询博客
     * @param title 要查询的博客标题
     * @return 查询到的博客
     */
    Blog getBlogByName(String title);

    /**
     * 通过博客标题查询博客是否存在
     * @param title 要查询的博客标题
     * @return true:存在, false:不存在
     */
    Boolean checkBlogName(String title);

    /**
     * 获取当前所有的归档信息
     * @return Map类型 其中key为年份，value为当前年份的所有博客，按创建时间倒叙
     */
    Map<String, List<Blog>> archiveBlog();

    /**
     * 获取当前的博客总数
     * @return 博客总数，Integer类型
     */
    Integer countBlog();

    /**
     * 通过页码获取博客列表
     * @param pageNum 页码
     * @return 对应页的博客列表
     */
    PageInfo<Blog> listBlog(Integer pageNum);

    /**
     * 通过条件获取博客列表
     * @param blogCond 想要查询的博客条件
     * @return 对应条件的博客列表
     */
    PageInfo<Blog> listBlogByCond(BlogCond blogCond);

    /**
     * 获取推荐博客的前三条
     * @param num 获取到博客的数目
     * @return 博客列表
     */
    List<Blog> listRecommendBlogTop(Integer num);

    /**
     * 查询所有的博客
     * @return 所有的博客列表
     */
    List<Blog> listBlog();

    /**
     * 通过分类获取该分类的所有博客
     * @return
     */
    List<Blog> listBlogByType(Type type);

    /**
     * 通过标签获取该标签的所有博客
     * @return
     */
    List<Blog> listBlogByTag(Tag tag);

    /**
     * 通过页码和排序方式查询博客
     * @param pageNum 页码
     * @param isDesc 排序方式，true为倒叙，false为正序
     * @return 博客列表
     */
    PageInfo<Blog> listBlog(Integer pageNum, Boolean isDesc);

    /**
     * 保存博客
     * @param blog 要保存的博客信息
     * @param tagIds 要保存的博客标签字符串：应以 "1,2,3" 的形式
     */
    void saveBlog(Blog blog, String tagIds);

    /**
     * 根据Id删除博客
     * @param id 要删除的博客Id
     */
    void deleteBlog(Integer id);
}
