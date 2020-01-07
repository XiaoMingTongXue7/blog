package cn.xudam.blog;

import cn.xudam.blog.dao.TypeMapper;
import cn.xudam.blog.dao.UserMapper;
import cn.xudam.blog.pojo.Tag;
import cn.xudam.blog.pojo.Type;
import cn.xudam.blog.pojo.User;
import cn.xudam.blog.service.TagService;
import cn.xudam.blog.service.UserService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    TagService tagService;

    @Test
    void contextLoads() {
        User user = userService.checkUser("admin", "123456");
        System.out.println(user);
    }

    @Test
    void daoTest1() {
        Type type = new Type();
        type.setName("SpringBoot");
        Integer java = typeMapper.saveType(type);
        System.out.println(java);
    }

    @Test
    void daoTest2() {
        Collection<Type> types = typeMapper.listType();
        System.out.println(types);
    }

    @Test
    void daoTest3() {
        Integer integer = typeMapper.deleteType(1);
        System.out.println(integer);
    }

    @Test
    void daoTest4() {
        PageInfo<Type> pageInfo = PageHelper.startPage(1, 4, "id desc").doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                typeMapper.listType();
            }
        });
        System.out.println(pageInfo);
        for (Type type : pageInfo.getList()) {
            System.out.println(type);
        }
    }

    @Test
    void daoTest5() {
        PageInfo<Type> pageInfo = PageHelper.startPage(1, 4, "id desc").doSelectPageInfo(() -> typeMapper.listType());
        System.out.println(pageInfo);
        for (Type type : pageInfo.getList()) {
            System.out.println(type);
        }
    }

    @Test
    void daoTest6() {
        Tag tag = new Tag();
        tag.setName("技术");
        tagService.saveTag(tag);
        PageInfo<Tag> pageInfo = tagService.listTag(1);
        for (Tag tag1 : pageInfo.getList()) {
            System.out.println(tag1);
        }
    }

}
