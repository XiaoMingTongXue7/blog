package cn.xudam.blog.service;

import cn.xudam.blog.pojo.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/5 21:52
 */
public interface TagService {

    void saveTag(Tag tag);

    Tag getTagById(Integer id);

    Tag getTagByName(String name);

    Boolean checkTagName(String name);

    PageInfo<Tag> listTag(Integer pageNum);

    PageInfo<Tag> listTag(Integer pageNum, Boolean isDesc);

    List<Tag> listTag();

    List<Tag> listTagTop();

    void updateTag(Tag tag);

    void deleteTag(Integer id);

}
