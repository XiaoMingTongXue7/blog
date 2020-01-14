package cn.xudam.blog.service;

import cn.xudam.blog.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/14 10:25
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Integer blogId);

    Comment saveComment(Comment comment);
}
