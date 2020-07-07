package cn.pomit.springwork.netty.User.Service;

import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.User.Entity.User;

public interface HurtBossService {
    //攻击副本boss
    void attackboss(User user,Boss boss);
    //穿上毒刀直接让怪兽中毒减大量的血
    void zhongdu(User user ,Boss boss);
    //进入副本
    String gongji(User user,Boss boss) throws Exception;
}
