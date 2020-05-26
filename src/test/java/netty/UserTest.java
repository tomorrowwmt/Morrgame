package netty;

import Dao.BaseTest;
import cn.pomit.springwork.netty.entity.User;
import cn.pomit.springwork.netty.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;
import java.util.List;

public class UserTest {
    private static Logger log = Logger.getLogger(UserTest.class);
    @Test
    public void test(){
        System.out.println(66666);
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
         List<User> users=userMapper.queryuser();
        System.out.println(users);
    }
    @Test
    public void select(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
        User user=userMapper.getUserById(14);
        System.out.println(user);
    }
    @Test
    public void insert(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
        User user=new User();
        user.setUsername("三七互娱");
        user.setPassword("1111");
        int user1 = userMapper.addUser(user);
        System.out.println(user1);
    }
}
