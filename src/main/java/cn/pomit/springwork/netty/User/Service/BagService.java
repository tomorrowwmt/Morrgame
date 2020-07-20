package cn.pomit.springwork.netty.User.Service;

import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.User.Entity.User;

import java.util.List;

public interface BagService {
    //消耗药品;
    void useconsumable(User user,Bag bag);
    //叠加药水
    void diejia(Bag bag);
    //把背包数据放入guava缓存
    List<Bag> queryAllBag();
    //查询某一个背包物品
    Bag queryBagId(Long id);
}
