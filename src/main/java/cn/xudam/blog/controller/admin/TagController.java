package cn.xudam.blog.controller.admin;

import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.pojo.Type;
import cn.xudam.blog.service.TagService;
import cn.xudam.blog.service.TypeService;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author 鸣
 * 2020/1/6 10:04
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public String toTags(Model model){
        PageInfo<Tag> pageInfo = tagService.listTag(1);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/{index}")
    public String toTags(@PathVariable("index") Integer id, Model model){
        PageInfo<Tag> pageInfo = tagService.listTag(id);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String toTagsInput(){
        return "admin/tags-save";
    }

    @PostMapping("/tags")
    public String saveTag(@NotNull Tag tag, RedirectAttributes attributes){
        if(tag.getName()==null){
            return "redirect:/admin/tags";
        }
        tagService.saveTag(tag);
        attributes.addFlashAttribute("message", "添加成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/update/{id}")
    public String toTagsUpdate(@PathVariable("id") Integer id, Model model){
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        return "admin/tags-update";
    }

    @PostMapping("/tags/update")
    public String updateTag(@NotNull Tag tag, RedirectAttributes attributes){
        if(tag.getId()==null || tag.getName()==null){
            return "redirect:/admin/tags";
        }
        tagService.updateTag(tag);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/tags";
    }

}
