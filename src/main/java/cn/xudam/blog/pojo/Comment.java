package cn.xudam.blog.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 鸣
 * 2020/1/4 16:29
 */
public class Comment {

    private Integer id;
    private String nickName;
    private String email;
    private String content;
    private LocalDateTime createTime;
    private Blog blog;

    private List<Comment> ReplyComments = new ArrayList<>();
    private Comment parentComment;


    public List<Comment> getReplyComments() {
        return ReplyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        ReplyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Comment() {
    }
}
