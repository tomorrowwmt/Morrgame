package cn.pomit.springwork.netty.Command;

import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.HurtBossService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Component;

@Component
public class Fuben implements Command{
    @Override
    public String handle(User user, String content) throws Exception {
        if(content==null){
            return  content;
        }
        Boss boss=new Boss();
        HurtBossService hurtBossService=SpringUtil.getBean(HurtBossService.class);
        String gongji = hurtBossService.gongji(user, boss);
        return gongji;
    }
}
