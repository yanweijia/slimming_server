package cn.yanweijia.slimming.dao;

import cn.yanweijia.slimming.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDAOTest {

    @Autowired
    private IUserDAO dao;

    @Test
    public void testSelectUser() throws Exception {
        Integer id = 1;
        User user = dao.selectByPrimaryKey(id);
        System.out.println(user.getUsername());
    }

}