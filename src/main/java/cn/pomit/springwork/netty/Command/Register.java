package cn.pomit.springwork.netty.Command;

import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Register implements  Command {
    @Override
    public String handle(User user,String content) throws Exception {
        if(content==null){
            return  content;
        }
        //user.setUsername("wbl3");
       // user.setPassword("12345");
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserService userService=ac.getBean(UserService.class);
        //UserService userService=SpringUtil.getBean(UserService.class);
        String register = userService.register(user.getUsername(), user.getPassword());
        return register;
    }
}
