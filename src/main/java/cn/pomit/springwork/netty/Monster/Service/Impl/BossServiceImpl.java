package cn.pomit.springwork.netty.Monster.Service.Impl;

import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.Monster.Service.BossService;
import cn.pomit.springwork.netty.User.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class BossServiceImpl implements BossService {
    @Override
    public void bit(Boss boss, User user) {
        //做一个伤害
        int injury = (int)(Math.random()*20);
        //获取用户的血量
        int hp = user.getHp();
        hp -= injury;
        //血量为0时
        if(hp>=0) {
            user.setHp(hp);
        }else {
            user.setHp(0);
        }
        System.out.println(user.getUsername()+"被"+boss.getName()+"进行攻击,剩余血量是"+user.getHp());
        System.out.println("=========================");
    }

    @Override
    public void move(Boss boss, int direction) {
        switch (direction){
            case 1:
                System.out.println(boss.getName()+"is moving 1 step NORTH.");
                break;
            case 2:
                System.out.println(boss.getName() + "is moving 1 step EAST.");
                break;
            case 3:
                System.out.println(boss.getName() + "is moving 1 step SOUTH.");
                break;
            default:
                System.out.println(boss.getName() + "is moving 1 step WEST.");
                break;
        }
    }
}
