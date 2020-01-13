package cn.xudam.blog.service.impl;

import cn.xudam.blog.dao.TagMapper;
import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.service.TagService;
import cn.xudam.blog.util.Commons;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void saveTag(Tag tag) {
        Integer integer = tagMapper.saveTag(tag);
        if(integer != 1){
            throw new NotFoundException("添加分类失败");
        }
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public Boolean checkTagName(String name) {
        Tag tag = getTagByName(name);
        if(tag==null){
            return false;
        } else {
            return true;
        }
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
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void updateTag(Tag tag) {
        Tag tagById = tagMapper.getTagById(tag.getId());
        if(tagById == null){
            throw new NotFoundException("要更新的分类不存在");
        }
        Integer integer = tagMapper.updateTag(tag);
        if(integer != 1){
            throw new NotFoundException("更新分类失败");
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void deleteTag(Integer id) {
        Integer integer = tagMapper.deleteTag(id);
        if(integer != 1){
            throw new NotFoundException("删除分类失败");
        }
    }
}
