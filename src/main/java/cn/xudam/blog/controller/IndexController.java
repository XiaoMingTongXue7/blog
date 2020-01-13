package cn.xudam.blog.controller;

import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping({"/", "/index"})
    public String index(Model model){
        PageInfo<Blog> pageInfo = blogService.listBlog(1);
        model.addAttribute("blogs", pageInfo);
        return "index";
    }

}
