package cn.xudam.blog.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 鸣
 * 2020/1/4 11:49
 */
public class Blog {

    private Integer id;
    //标题
    private String title;
    //内容
    private String content;
    //首图
    private String firstPic;
    //标记
    private String flag;
    //浏览次数
    private Integer views;
    //赞赏开启
    private Boolean appreciation;
    //版权开启
    private Boolean copyright;
    //评论开启
    private Boolean commentAble;
    //是否发布
    private Boolean publish;
    //是否推荐
    private Boolean recommend;
    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

    private List<Comment> comments = new ArrayList<>();

    private Type type;

    private List<Tag> tags = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPic='" + firstPic + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", copyright=" + copyright +
                ", commentAble=" + commentAble +
                ", publish=" + publish +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public Boolean getCommentAble() {
        return commentAble;
    }

    public void setCommentAble(Boolean commentAble) {
        this.commentAble = commentAble;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Blog() {
    }
}
