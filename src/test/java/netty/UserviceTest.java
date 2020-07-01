package netty;

import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class UserviceTest {
    @Test
    public void test(){
        System.out.println(66666);
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserService userService= (UserService) ac.getBean("UserGuavaCache");
        //UserMapper userMapper = ac.getBean(UserMapper.class);
        //List<User> users = userService.queryAllUser();
        //从数据库查询用户信息
        //Example example=new Example(User.class);
        //example.createCriteria().andEqualTo("name",username);
        //List<User> users = userMapper.selectByExample(example);
        //User user = userService.checkUserByUsername("wbl1");
        //System.out.println("\n"+user );
    }
    /*
    @Test
    public void insert(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserService userService= (UserService) ac.getBean("UserGuavaCache");
        User user=new User();
        IdWorker worker=new IdWorker(1,1,1);
        long l=worker.nextId();
        user.setUid(l);
        user.setUsername("hhhhh");
        user.setPassword("44444");
        user.setHp(1000);
        int i = userService.addUser(user);
        System.out.println(i);
    }
     */
}
