package cn.pomit.springwork.netty.Command;

import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Move  implements  Command{
    @Override
    public String handle(User user,String content) throws Exception {
        if(content==null){
            return null;
        }
       ScenceService scenceService= SpringUtil.getBean(ScenceService.class);
        String moveScence = scenceService.moveScence();
        return moveScence;
    }

}
