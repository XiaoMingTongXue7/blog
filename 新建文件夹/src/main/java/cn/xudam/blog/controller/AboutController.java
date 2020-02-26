package cn.xudam.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 鸣
 * 2020/1/22 15:34
 */
@Controller
public class AboutController {

    @RequestMapping("/about")
    public String toAbout(){
        return "about";
    }

}
