package cn.xudam.blog.controller.admin;

import cn.xudam.blog.dto.cond.BlogCond;
import cn.xudam.blog.pojo.Blog;
import cn.xudam.blog.pojo.Type;
import cn.xudam.blog.service.BlogService;
import cn.xudam.blog.service.BlogTagRelationService;
import cn.xudam.blog.service.TagService;
import cn.xudam.blog.service.TypeService;
import cn.xudam.blog.util.Commons;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }


    @GetMapping({"/blog", "/blogs", "/blogs.html"})
    public String toBlogs(Model model){
        PageInfo<Blog> pageInfo = blogService.listBlog(1);
        model.addAttribute("types", typeService.listType());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(BlogCond blogCond, Model model){
        PageInfo<Blog> pageInfo = blogService.listBlogByCond(blogCond);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String toBlogInput(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());

        return "admin/blogs-input";
    }

    @PostMapping("/blog/publish")
    public String saveBlog(Blog blog, String tagIds){
        blogService.saveBlog(blog);
        List<Integer> tagId = Commons.stringToList(tagIds);
        blogTagService.saveBlogTagByIds(blog.getId(), tagId);
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/input/{id}")
    public String toBlogUpdate(@PathVariable("id")Integer blogId, Model model){
        model.addAttribute("blog", blogService.getBlogById(blogId));
        return toBlogInput(model);
    }
}
