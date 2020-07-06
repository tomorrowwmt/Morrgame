package cn.pomit.springwork.netty.Monster.Service.Impl;

import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Monster.Service.BitUserService;
import cn.pomit.springwork.netty.User.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class BitUserServiceImpl implements BitUserService {
    @Override
    public void bit(Monster monster,User user) {
        //做一个伤害
        int injury = (int)(Math.random()*10);
        //获取用户的血量
        int hp = user.getHp();
        hp -= injury;
        //血量为0时
        if(hp>=0) {
            user.setHp(hp);
        }else {
            user.setHp(0);
        }
        //u.setHp((u.getHp()-1));
        System.out.println(user.getUsername()+"被"+monster.getName()+"进行攻击,剩余血量是"+user.getHp());
        System.out.println("=========================");
    }
}
