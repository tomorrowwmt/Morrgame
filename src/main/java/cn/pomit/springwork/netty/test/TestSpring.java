package cn.pomit.springwork.netty.test;
import cn.pomit.springwork.netty.Dao.UserDao;
import cn.pomit.springwork.netty.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestSpring {
    @Autowired
    private UserDao userDao;
    private static Logger log = Logger.getLogger(TestSpring.class);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //加载spirng配置文件
        ApplicationContext ac= new ClassPathXmlApplicationContext("spring-netty.xml");
        //HelloServer helloServer=ac.getBean(HelloServer.class);
        //System.out.println(helloServer);
     UserDao userDao= (UserDao) ac.getBean("queryuser");
     userDao.queryuser();
    }

}