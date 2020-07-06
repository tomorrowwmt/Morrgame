package cn.pomit.springwork.netty.User.Service;

import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Monster.Monster;

public interface HurtService {
    //普通攻击怪兽
    void bit(User user,Monster mas);
    //必杀
    void MagicAttack(User user,Monster mas);
    //增加药水
    void drug(User user);
    //进行跟master打怪
    String batter(User user)throws Exception ;
}
