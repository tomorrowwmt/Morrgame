package netty;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import cn.pomit.springwork.netty.mapper.ShopMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //加载spirng配置文件
        ApplicationContext  ac= new ClassPathXmlApplicationContext("spring-netty.xml");
        ShopMapper shopMapper= SpringUtil.getBean(ShopMapper.class);
        //UserService userService = (UserServiceImpl) SpringContextHolder.getBean(UserServiceImpl.class);
        //System.out.println(userServiceImpl);
        //HelloServer helloServer=ac.getBean(HelloServer.class);
        //System.out.println(helloServer);
    // UserMapper userDao= (UserMapper) ac.getBean("queryuser");
     //userDao.queryuser();
    }

}