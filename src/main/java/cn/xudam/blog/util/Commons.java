package cn.xudam.blog.util;

import cn.xudam.blog.exception.NotFoundException;
import cn.xudam.blog.pojo.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鸣
 * 2020/1/9 11:24
 */
public class Commons {

    public static List<Integer> string_list(String tagIds){
        List<Integer> list = new ArrayList<>();
        if("".equals(tagIds) && tagIds != null){
            String[] ids = tagIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                list.add(new Integer(ids[i]));
            }
        }
        return list;
    }

    public static StringBuffer list_string(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            for (Tag tag : tags) {
                ids.append(tag.getId());
                ids.append(",");
            }
            ids.deleteCharAt(ids.length()-1);
            return ids;
        }
        return null;
    }
}
