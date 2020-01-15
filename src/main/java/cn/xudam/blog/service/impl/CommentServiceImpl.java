package cn.xudam.blog.service.impl;

import cn.xudam.blog.constant.WebConst;
import cn.xudam.blog.dao.CommentMapper;
import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Comment;
import cn.xudam.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 鸣
 * 2020/1/14 10:45
 */
@Service
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapper;

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> listParentCommentByBlogId(Integer blogId) {
        List<Comment> comments = commentMapper.listParentCommentByBlogId(blogId);
        getReplyComments(comments);
        return eachComment(comments);
    }

    private void getReplyComments(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> comments1 = commentMapper.listCommentByParentId(comment.getId());
            if(comments1.size()>0){
                comment.setReplyComments(comments1);
                getReplyComments(comments1);
            }
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public Comment saveComment(Comment comment) {
        if(comment.getNickName()==null || comment.getNickName().length()>WebConst.MAX_COMMENT_NICKNAME){
            throw new NotFoundException("添加博客失败，博客标题过长");
        }
        if(comment.getContent()==null || comment.getContent().length()> WebConst.MAX_COMMENT_CONTENT){
            throw new NotFoundException("添加博客失败，博客内容过长");
        }
        if(comment.getEmail()==null || comment.getEmail().length()> WebConst.MAX_COMMENT_EMAIL){
            throw new NotFoundException("添加博客失败，博客描述过长");
        }
        if(comment.getParentComment().getId() == -1){
            comment.getParentComment().setId(null);
        }
        comment.setCreateTime(LocalDateTime.now());
        commentMapper.saveComment(comment);
        return null;
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;


    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            if(replys1.size()>0){
                for(Comment reply1 : replys1) {
                    //循环迭代，找出子代，存放在tempReplys中
                    recursively(reply1);
                }
            }

            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
