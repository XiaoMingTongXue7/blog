package cn.xudam.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 鸣
 * 2020/1/3 21:24
 */
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index(){
        return "index";
    }

}
