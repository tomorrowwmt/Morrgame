package cn.pomit.springwork.netty.Monster.Service;

import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.User.Entity.User;

public interface BossService {
    //副本Boss攻击玩家
    void bit(Boss boss,User user);
    //做一个小设计
    void move(Boss boss,int direction);
}
