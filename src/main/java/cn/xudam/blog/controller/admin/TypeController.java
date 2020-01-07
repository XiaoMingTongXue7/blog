package cn.xudam.blog.controller.admin;

import cn.xudam.blog.pojo.Type;
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
public class TypeController {

    private TypeService typeService;

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/types")
    public String toTypes(Model model){
        PageInfo<Type> pageInfo = typeService.listType(1);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/{index}")
    public String toTypes(@PathVariable("index") Integer id, Model model){
        PageInfo<Type> pageInfo = typeService.listType(id);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toTypesInput(){
        return "admin/types-save";
    }

    @PostMapping("/types")
    public String saveType(@NotNull Type type, RedirectAttributes attributes){
        if(type.getName()==null){
            return "redirect:/admin/types";
        }
        typeService.saveType(type);
        attributes.addFlashAttribute("message", "添加成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/delete/{id}")
    public String deleteType(@PathVariable("id") Integer id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/update/{id}")
    public String toTypesUpdate(@PathVariable("id") Integer id, Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type", type);
        return "admin/types-update";
    }

    @PostMapping("/types/update")
    public String updateType(@NotNull Type type, RedirectAttributes attributes){
        if(type.getId()==null || type.getName()==null){
            return "redirect:/admin/types";
        }
        typeService.updateType(type);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/types";
    }

}
