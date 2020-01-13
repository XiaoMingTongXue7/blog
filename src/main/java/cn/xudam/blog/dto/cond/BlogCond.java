package cn.xudam.blog.dto.cond;

/**
 * 博客查询条件
 * @author 鸣
 * 2020/1/8 16:53
 */
public class BlogCond {

    /**
     * 要查询的标题
     * 可以模糊查询
     */
    private String title;

    /**
     * 要查询的描述
     * 可以模糊查询
     */
    private String description;

    /**
     * 要查询的分类
     */
    private Integer typeId;

    /**
     * 是否推荐
     * true : 推荐
     * false : 不推荐
     */
    private Boolean recommend;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 是否倒叙排列
     * true : 倒叙
     * false : 顺序
     */
    private Boolean isDesc = true;

    @Override
    public String toString() {
        return "BlogCond{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", typeId=" + typeId +
                ", recommend=" + recommend +
                ", pageNum=" + pageNum +
                ", isDesc=" + isDesc +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDesc() {
        return isDesc;
    }

    public void setDesc(Boolean desc) {
        isDesc = desc;
    }

    public BlogCond() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
