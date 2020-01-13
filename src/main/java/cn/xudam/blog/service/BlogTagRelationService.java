package cn.xudam.blog.service;

import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.BlogTagRelation;
import cn.xudam.blog.pojo.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客与标签维护关系的服务层
 * @author 鸣
 * 2020/1/9 18:10
 */
@Service
public interface BlogTagRelationService {

    /**
     * 通过博客标签关系的实体类列表保存博客标签<br/>
     * 在保存之前，该方法会先尝试查询，如果不存在的话才会保存
     * 如果存在的话会抛出一个NotFoundException<br/>
     * @param tagId 由博客标签关系的实体类组成的列表
     * @exception NotFoundException 如果要保存的标签已经存在会抛出次异常
     */
    void saveBlogTagByIds(List<BlogTagRelation> tagId);

    /**
     * 通过一个博客id和多个标签id保存博客标签<br/><br/>
     * <p>在本方法中会将博客id和标签id转化为由博客标签关系的实体类组成的列表，然后在调用
     * saveBlogTagByIds(List<BlogTagRelation> tagId)方法<p/>
     * @param blogId 博客标签
     * @param tagIds 标签列表
     * @exception NotFoundException 如果要保存的标签已经存在会抛出次异常
     */
    void saveBlogTagByIds(Integer blogId, List<Integer> tagIds);

    /**
     * 通过博客id删除博客标签
     * 会删除所有和该blogId相关的所有标签
     * @param blogId 博客id
     */
    void deleteBlogTagByBlogId(Integer blogId);

    /**
     * 根据博客id和标签id返回一个博客标签关系对象
     * @param blogTagRelation
     * @return
     */
    BlogTagRelation getBlogTagByBlogTagId(BlogTagRelation blogTagRelation);

    /**
     * 根据博客id返回该博客的所有标签列表
     * @param blogId 博客id
     * @return
     */
    List<Tag> getTagsByBlogId(Integer blogId);

    /**
     * 根据标签id返回所有含有该标签的博客
     * @param tagId
     * @return
     */
    List<Blog> getBlogsByTagId(Integer tagId);

    /**
     * 更新博客的标签<br>
     * 该方法会先删除要更新的博客的所有标签，再添加要更新的标签
     * @param blogId 要更新标签的博客id
     * @param tagIds 要更新的所有标签
     */
    void updateBlogTag(Integer blogId, List<Integer> tagIds);

    /**
     * 通过参数动态的返回一个列表
     * 如果传入的对象中只有blogId，那么返回就是该blog的所有标签的id的列表
     * 如果传入的对象中只有tagId，那么返回就是所有含有该tag的博客的id的列表
     * 如果传入的对象中既有blogId，又有tagId，那么返回的就是一个博客标签信息
     * @param blogTagRelation
     * @return
     */
    List<BlogTagRelation> getBlogTagsByBlogTagId(BlogTagRelation blogTagRelation);

}
