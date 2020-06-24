package cn.pomit.springwork.netty.Service;


import cn.pomit.springwork.netty.Entity.Shop;
import cn.pomit.springwork.netty.Entity.User;
import org.ehcache.config.builders.UserManagedCacheBuilder;

import java.util.List;

public interface ShopService {
   //购买商品
   void  shopping(User user);
}
