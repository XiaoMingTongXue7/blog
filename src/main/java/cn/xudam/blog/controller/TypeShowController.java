package cn.xudam.blog.controller;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Type;
import cn.xudam.blog.service.BlogService;
import cn.xudam.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/16 9:11
 */
@Controller
public class TypeShowController {

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

    @RequestMapping("/type/{id}")
    public String type(@PathVariable("id")Integer id, Model model){
        List<Type> types = typeService.listTypeTop();
        if(id == -1){
            id = types.get(0).getId();
        }
        BlogCond blogCond = new BlogCond();
        blogCond.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlogByCond(blogCond));
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}
