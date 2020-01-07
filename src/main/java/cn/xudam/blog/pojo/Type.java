package cn.xudam.blog.pojo;


import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鸣
 * 2020/1/4 16:27
 */
public class Type {
    private Integer id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type() {
    }
}
