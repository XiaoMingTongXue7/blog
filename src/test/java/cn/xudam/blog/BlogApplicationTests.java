package cn.xudam.blog;

import cn.xudam.blog.dao.BlogMapper;
import cn.xudam.blog.dao.TypeMapper;
import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.*;
import cn.xudam.blog.service.*;
import cn.xudam.blog.util.Commons;
import cn.xudam.blog.util.MarkdownUtils;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogTagRelationService blogTagRelationService;

    @Autowired
    CommentService commentService;

    @Test
    void contextLoads() {
        Type type = typeMapper.getTypeById(6);
        Blog blog = new Blog();
        blog.setTitle("测试");
        blog.setContent("dwa");
        blog.setFirstPic("dw");
        blog.setFlag("aa");
        blog.setViews(1);
        blog.setAppreciation(true);
        blog.setCopyright(true);
        blog.setCommentAble(true);
        blog.setPublish(true);
        blog.setRecommend(true);
        blog.setUpdateTime(LocalDateTime.now());
        blog.setCreateTime(LocalDateTime.now());
        blog.setType(type);
        blogMapper.saveBlog(blog);
    }

    @Test
    void contextLoads1() {
        Blog blog = blogMapper.getBlogById(7);
        System.out.println(blog);
    }

    @Test
    void contextLoads2() {
        Blog blog = new Blog();
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        LocalDateTime parse = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);
        blog.setCreateTime(parse);
        System.out.println(blog);
    }

    @Test
    void contextLoads3() {
        Type type = new Type();
        type.setName("dwa");
        typeMapper.saveType(type);
    }

    @Test
    void contextLoads4() {
        PageInfo<Blog> pageInfo = blogService.listBlog(1);
        System.out.println(pageInfo);
        List<Blog> list = pageInfo.getList();
        for (Blog blog : list) {
            System.out.println(blog.getCreateTime().getMonthValue());
            System.out.println(blog);
        }
    }

    @Test
    void contextLoads5() {
        List<Tag> tags = tagService.listTagTop();
        for (Tag tag : tags) {
            System.out.println(tag.getBlogs().size());
        }
    }

    @Test
    void contextLoads15() {
        List<Type> types = typeService.listTypeTop();
        types.forEach(System.out::println);
    }

    @Test
    void contextLoads6() {
        BlogTagRelation blogTagRelation = new BlogTagRelation();
        blogTagRelation.setTagId(9);
        List<BlogTagRelation> blogIds = blogTagRelationService.getBlogTagsByBlogTagId(blogTagRelation);
        List<Blog> blogs = blogMapper.listBlogByBlogIds(blogIds);
        blogs.forEach(System.out::println);
    }

    @Test
    void contextLoads7() {
        Map<String, List<Blog>> map = blogService.archiveBlog();
        System.out.println(map);
        Integer integer = blogService.countBlog();
        System.out.println(integer);
    }


}
