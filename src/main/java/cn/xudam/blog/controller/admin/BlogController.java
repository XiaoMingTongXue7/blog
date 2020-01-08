package cn.xudam.blog.controller.admin;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.Type;
import cn.xudam.blog.service.BlogService;
import cn.xudam.blog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/5 20:20
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private TypeService typeService;
    private BlogService blogService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }


    @GetMapping({"/blog", "/blogs", "/blogs.html"})
    public String toBlogs(Model model){
        List<Type> types = typeService.listType();
        PageInfo<Blog> pageInfo = blogService.listBlog(1);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(BlogCond blogCond, Model model){
        PageInfo<Blog> pageInfo = blogService.listBlogByCond(blogCond);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

}
