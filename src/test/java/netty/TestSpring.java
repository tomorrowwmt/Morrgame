package netty;
import cn.pomit.springwork.netty.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //加载spirng配置文件
        ApplicationContext ac= new ClassPathXmlApplicationContext("spring-netty.xml");
        //HelloServer helloServer=ac.getBean(HelloServer.class);
        //System.out.println(helloServer);
    // UserMapper userDao= (UserMapper) ac.getBean("queryuser");
     //userDao.queryuser();
    }

}