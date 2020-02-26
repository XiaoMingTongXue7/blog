package cn.xudam.blog.controller;

import cn.xudam.blog.constant.WebConst;
import cn.xudam.blog.pojo.Comment;
import cn.xudam.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


/**
 * @author 鸣
 * 2020/1/14 10:15
 */
@Controller
public class CommentController {

    private CommentService commentService;

    @Value("${comment.avatar}")
    private String avatar;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId")Integer blogId, Model model){
        model.addAttribute("comments", commentService.listParentCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String saveComment(Comment comment, HttpSession session){
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY) != null){
            comment.setAdmin(true);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlogId();
    }
}
