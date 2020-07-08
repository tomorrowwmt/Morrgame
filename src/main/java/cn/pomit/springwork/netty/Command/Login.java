package cn.pomit.springwork.netty.Command;

import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.User.Session.Session;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Login implements Command{
    private SessionImpl session;
    @Override
    public String handle(User user,String content) throws Exception {
        if(content==null){
            return  content;
        }
        user.setUsername("wbl1");
        user.setPassword("12345");
        UserService userService=SpringUtil.getBean(UserService.class);
        String loginname = userService.login(session, user.getUsername(), user.getPassword());
        return loginname;
    }
}
