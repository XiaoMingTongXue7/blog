package cn.xudam.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 鸣
 * 2020/1/5 20:20
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping({"/blog", "/blogs", "/blogs.html"})
    public String toBlogs(){
        return "admin/blogs";
    }

}
