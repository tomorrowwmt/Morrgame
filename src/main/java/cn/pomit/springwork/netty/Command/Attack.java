package cn.pomit.springwork.netty.Command;

import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.HurtService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Component;

@Component
public class Attack implements Command {
    @Override
    public String handle(User user, String content) throws Exception {
        if(content==null){
            return null;
        }
        Monster monster=new Monster();
        Bag bag=new Bag();
        HurtService hurtService= SpringUtil.getBean(HurtService.class);
        String batter = hurtService.batter(user, monster, bag);
        return batter;
    }
}
