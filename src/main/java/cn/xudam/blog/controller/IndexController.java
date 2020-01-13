package cn.xudam.blog.controller;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.service.BlogService;
import cn.xudam.blog.service.TagService;
import cn.xudam.blog.service.TypeService;
import cn.xudam.blog.util.MarkdownUtils;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.sun.xml.internal.ws.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/3 21:24
 */
@Controller
public class IndexController {

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    private BlogService blogService;
    private TypeService typeService;
    private TagService tagService;

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model){
        model.addAttribute("page", blogService.listBlog(1));
        model.addAttribute("types", typeService.listTypeTop());
        model.addAttribute("tags", tagService.listTagTop());
        BlogCond blogCond = new BlogCond();
        blogCond.setRecommend(true);
        model.addAttribute("recommendBlogs", blogService.listBlogByCond(blogCond));
        return "index";
    }

    @PostMapping("/search")
    public String search(String query, Model model){
        if(StringUtil.isNotEmpty(query)){
            BlogCond blogCond = new BlogCond();
            blogCond.setTitle(query);
            blogCond.setDescription(query);
            PageInfo<Blog> pageInfo = blogService.listBlogByCond(blogCond);
            model.addAttribute("page", pageInfo);
            model.addAttribute("query", query);
        }
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id")Integer id, Model model){
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("blogHTML", MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return "blog";
    }

}
