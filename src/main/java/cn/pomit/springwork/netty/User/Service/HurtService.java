package cn.pomit.springwork.netty.User.Service;

import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Monster.Monster;

public interface HurtService {
    //一加普通攻击怪兽
    void bit(User user,Monster mas) throws Exception;
    //二加普通攻击
    void bitmas(User user ,Monster mas) throws Exception;
    //必杀
    void magicAttack(User user, Monster mas) throws Exception;
    //增加药水
    void drug(User user);
    //进行跟master打怪
    String batter(User user,Monster mas, Bag bag)throws Exception ;
}
