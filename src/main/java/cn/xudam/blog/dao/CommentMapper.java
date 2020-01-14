package cn.xudam.blog.dao;

import cn.xudam.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/14 10:34
 */
@Mapper
@Repository
public interface CommentMapper {

    Comment getCommentById(Integer id);

    List<Comment> listCommentByBlogId(Integer blogId);

    Integer saveComment(Comment comment);

}
