package cn.xudam.blog.controller;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.service.BlogService;
import cn.xudam.blog.service.TypeService;
import cn.xudam.blog.util.MarkdownUtils;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model){
        model.addAttribute("page", blogService.listBlog(1));
        model.addAttribute("types", typeService.listTypeTop(5));
        BlogCond blogCond = new BlogCond();
        blogCond.setRecommend(true);
        model.addAttribute("recommendBlogs", blogService.listBlogByCond(blogCond));
        return "index";
    }

    @PostMapping("/index/{id}")
    public String nextPage(@PathVariable("id")Integer pageNum, Model model){
        model.addAttribute("page", blogService.listBlog(pageNum));
        return "index :: blogList";
    }

    @PostMapping("/search")
    public String search(String query, Model model){
        if(StringUtil.isEmpty(query)){
            query = "";
        }
        BlogCond blogCond = new BlogCond();
        blogCond.setTitle(query);
        blogCond.setDescription(query);
        PageInfo<Blog> pageInfo = blogService.listBlogByCond(blogCond);
        model.addAttribute("page", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id")Integer id, Model model){
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("blogHTML", MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return "blog";
    }

    @GetMapping("/footer/newBlogs")
    public String newBlogList(Model model){
        model.addAttribute("newBlogs", blogService.listRecommendBlogTop(3));
        return "common :: ";
    }

}
