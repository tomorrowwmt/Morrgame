package cn.pomit.springwork.netty.User.Service;

import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.User.Entity.User;

public interface BagService {
    //消耗药品;
    void useconsumable(User user,Bag bag);
    //叠加药水
    void diejia(User user,Bag bag);
}
