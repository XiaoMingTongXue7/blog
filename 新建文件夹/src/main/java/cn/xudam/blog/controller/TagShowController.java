package cn.xudam.blog.controller;

import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.service.BlogTagRelationService;
import cn.xudam.blog.service.TagService;
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
public class TagShowController {

    private TagService tagService;
    private BlogTagRelationService blogTagService;

    @Autowired
    public void setBlogTagService(BlogTagRelationService blogTagRelationService) {
        this.blogTagService = blogTagRelationService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping("/tag/{id}")
    public String tag(@PathVariable("id")Integer id, Model model){
        List<Tag> tags = tagService.listTagTop();
        if(id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogTagService.listBlogsByTagId(1, id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }

}
