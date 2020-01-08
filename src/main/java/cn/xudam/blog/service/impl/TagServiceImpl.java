package cn.xudam.blog.service.impl;

import cn.xudam.blog.dao.TagMapper;
import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 鸣
 * 2020/1/7 13:24
 */
@Service
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public void saveTag(Tag Tag) {
        Integer integer = tagMapper.saveTag(Tag);
        if(integer != 1){
            throw new NotFoundException("添加分类失败");
        }
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public PageInfo<Tag> listTag(Integer id) {
        return listTag(id, true);
    }

    @Override
    public PageInfo<Tag> listTag(Integer id, Boolean isDesc) {
        PageInfo<Tag> pageInfo = PageHelper.startPage(id, 5, "id" + (isDesc?" desc":"")).doSelectPageInfo(() -> tagMapper.listTag());
        return pageInfo;
    }


    @Override
    public void updateTag(Tag Tag) {
        Tag TagById = tagMapper.getTagById(Tag.getId());
        if(TagById == null){
            throw new NotFoundException("要更新的分类不存在");
        }
        Integer integer = tagMapper.updateTag(Tag);
        if(integer != 1){
            throw new NotFoundException("更新分类失败");
        }
    }

    @Override
    public void deleteTag(Integer id) {
        Integer integer = tagMapper.deleteTag(id);
        if(integer != 1){
            throw new NotFoundException("删除分类失败");
        }
    }
}
