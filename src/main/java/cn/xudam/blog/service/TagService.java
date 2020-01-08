package cn.xudam.blog.service;

import cn.xudam.blog.pojo.Tag;
import com.github.pagehelper.PageInfo;

/**
 * @author 鸣
 * 2020/1/5 21:52
 */
public interface TagService {

    void saveTag(Tag Tag);

    Tag getTagById(Integer id);

    PageInfo<Tag> listTag(Integer pageNum);

    PageInfo<Tag> listTag(Integer pageNum, Boolean isDesc);

    void updateTag(Tag Tag);

    void deleteTag(Integer id);

}
