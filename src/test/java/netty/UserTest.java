package netty;

import cn.pomit.springwork.netty.Entity.Bag;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.mapper.BagMapper;
import cn.pomit.springwork.netty.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserTest {
    private static Logger log = Logger.getLogger(UserTest.class);
    @Test
    public void test(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
         List<User> users=userMapper.selectAll();
        System.out.println(users);
    }
    @Test
    public void select(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
        User user=userMapper.selectByPrimaryKey(1L);
        System.out.println(user);
    }
    @Test
    public void insert(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
        User user=new User();
        IdWorker worker=new IdWorker(1,1,1);
        long l=worker.nextId();
        user.setUid(l);
        user.setUsername("tgtretrgtgr");
        user.setPassword("1111");
        int user1 = userMapper.insertSelective(user);
        System.out.println(user1);
    }
    @Test
    public void update(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
       UserMapper userMapper=ac.getBean(UserMapper.class);
       User user=new User();
       user.setUid(1L);
        //bag.setIname("hhhh");
        user.setLevel(10);
        int result=userMapper.updateByPrimaryKeySelective(user);
        System.out.println(result);
    }
}
