package cn.xudam.blog;

import cn.xudam.blog.dao.BlogMapper;
import cn.xudam.blog.dao.TypeMapper;
import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.Type;
import cn.xudam.blog.service.BlogService;
import cn.xudam.blog.service.TagService;
import cn.xudam.blog.service.TypeService;
import cn.xudam.blog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
            System.out.println(blog);
        }
    }

    @Test
    void contextLoads5() {
        BlogCond blogCond = new BlogCond();
        blogCond.setTypeId(6);
        PageInfo<Blog> pageInfo = blogService.listBlogByCond(blogCond);
        System.out.println(pageInfo);
        for (Blog blog : pageInfo.getList()) {
            System.out.println(blog);
        }
    }

}
