package cn.pomit.springwork.netty.Command;

import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class Aoi implements  Command{
    @Override
    public String handle(User user,String content) throws Exception {
        if(content==null){
            return  content;
        }
        ScenceService scenceService = SpringUtil.getBean(ScenceService.class);
       String scenceByRole = scenceService.getScenceByRole(user);
        return scenceByRole;
    }
}
