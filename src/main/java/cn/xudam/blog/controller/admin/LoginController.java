package cn.xudam.blog.controller.admin;

import cn.xudam.blog.pojo.User;
import cn.xudam.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author 鸣
 * 2020/1/4 20:33
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String toLogin(HttpSession session){
        if(session.getAttribute("user") == null){
            return "admin/login";
        }
        return "redirect:/admin/index.html";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes, Model model){
        User user = userService.checkUser(username, password);
        if(user == null){
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        } else {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "redirect:/admin/index.html";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
