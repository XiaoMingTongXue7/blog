package cn.xudam.blog.service.impl;

import cn.xudam.blog.dao.TypeMapper;
import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Type;
import cn.xudam.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 鸣
 * 2020/1/6 10:14
 */
@Service
public class TypeServiceImpl implements TypeService {

    private final TypeMapper typeMapper;

    @Autowired
    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public void saveType(Type type) {
        Integer integer = typeMapper.saveType(type);
        if(integer != 1){
            throw new NotFoundException("添加分类失败");
        }
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }

    @Override
    public PageInfo<Type> listType(Integer pageNum) {
        return listType(pageNum, true);
    }

    @Override
    public PageInfo<Type> listType(Integer pageNum, Boolean isDesc) {
        PageInfo<Type> pageInfo = PageHelper.startPage(pageNum, 5, "id" + (isDesc?" desc":"")).doSelectPageInfo(() -> listType());
        return pageInfo;
    }


    @Override
    public void updateType(Type type) {
        Type typeById = typeMapper.getTypeById(type.getId());
        if(typeById == null){
            throw new NotFoundException("要更新的分类不存在");
        }
        Integer integer = typeMapper.updateType(type);
        if(integer != 1){
            throw new NotFoundException("更新分类失败");
        }
    }

    @Override
    public void deleteType(Integer id) {
        Integer integer = typeMapper.deleteType(id);
        if(integer != 1){
            throw new NotFoundException("删除分类失败");
        }
    }
}
