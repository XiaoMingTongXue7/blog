package cn.xudam.blog.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 博客实体类
 * @author 鸣
 * 2020/1/4 11:49
 */
public class Blog {

    /**
     * 博客主键
     */
    private Integer id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 博客描述
     */
    private String description;

    /**
     * 博客首图链接
     */
    private String firstPic;

    /**
     * 博客标记
     * 默认为‘原创’
     */
    private String flag = "原创";

    /**
     * 博客浏览次数
     * <p>初始为0<p/>
     */
    private Integer views = 0;

    /**
     * 是否开启赞赏<br/>
     * true为开启<br/>
     * false为不开启
     */
    private Boolean appreciation = false;

    /**
     * 是否开启版权<br/>
     * true为开启<br/>
     * false为不开启
     */
    private Boolean copyright = false;

    /**
     * 是否开启评论<br/>
     * true为开启<br/>
     * false为不开启
     */
    private Boolean commentAble = false;

    /**
     * 是否发布<br/>
     * true为发布<br/>
     * false为草稿
     */
    private Boolean publish = false;

    /**
     * 是否开启推荐<br/>
     * true为开启<br/>
     * false为不开启
     */
    private Boolean recommend = false;

    /**
     * 博客的创建时间
     */
    private LocalDateTime createTime;


    /**
     * 博客的更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 博客的评论
     */
    private List<Comment> comments = new ArrayList<>();

    /**
     * 博客的分类
     */
    private Type type;

    /**
     * 博客的标签
     */
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
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
                ", comments=" + comments +
                ", type=" + type +
                ", tags=" + tags +
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
