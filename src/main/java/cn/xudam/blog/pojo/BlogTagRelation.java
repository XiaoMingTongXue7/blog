package cn.xudam.blog.pojo;

/**
 * @author 鸣
 * 2020/1/9 16:21
 */
public class BlogTagRelation {

    private Integer id;
    private Integer blogId;
    private Integer tagId;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BlogTagRelation{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BlogTagRelation() {
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
