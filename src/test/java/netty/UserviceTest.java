package netty;

import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserviceTest {
    @Test
    public void test(){
        System.out.println(66666);
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserService userService= (UserService) ac.getBean("UserGuavaCache");
        List<User> users = userService.queryAllUser();
        System.out.println(users);
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
