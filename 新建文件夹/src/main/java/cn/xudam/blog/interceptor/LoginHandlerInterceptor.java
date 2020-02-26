package cn.xudam.blog.interceptor;

import cn.xudam.blog.constant.WebConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 鸣
 * 2020/1/5 20:36
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY) == null){
            request.setAttribute("message", "没有权限，请先登录");
            request.getRequestDispatcher("/admin").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
