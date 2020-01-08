package cn.xudam.blog.dto.cond;

/**
 * @author 鸣
 * 2020/1/8 16:53
 */
public class BlogCond {

    private String title;
    private Integer typeId;
    private Boolean recommend;
    private Integer pageNum = 1;
    private Boolean isDesc = true;

    @Override
    public String toString() {
        return "BlogCond{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                ", recommend=" + recommend +
                ", pageNum=" + pageNum +
                ", isDesc=" + isDesc +
                '}';
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
