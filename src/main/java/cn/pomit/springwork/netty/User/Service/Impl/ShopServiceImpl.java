package cn.pomit.springwork.netty.User.Service.Impl;
import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.Mapper.UserMapper;
import cn.pomit.springwork.netty.Shopping.Entity.Shop;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.ShopService;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.Mapper.BagMapper;
import cn.pomit.springwork.netty.Mapper.ShopMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ShopServiceImpl implements ShopService {
    private static  Shop shop=new Shop();

    @Override
    public void shopping(User user) {
    }
    /*
    @Override
    public void shopping(User user) {
        //查询商品物品
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        ShopMapper shopMapper=ac.getBean(ShopMapper.class);
        List<Shop> shops = shopMapper.selectAll();
        //查询用户拥有金额
        UserMapper userMapper = ac.getBean(UserMapper.class);
        Integer usermoney = userMapper.selectByPrimaryKey(1L).getMoney();
        //获取商品金额
        Integer price1 = shops.get(0).getPrice();
        if(price1>usermoney){
            System.out.println("购买失败金额不足");
        }
        //假设购买红药水
            Shop goods = shopMapper.selectByPrimaryKey(1L);
            //查询商品名字
            String name = goods.getName();
            //购买成功加入背包
          Bag bag=new Bag();
          BagMapper bagMapper = ac.getBean(BagMapper.class);
          IdWorker worker=new IdWorker(1,1,1);
            long l=worker.nextId();
            bag.setId(l);
            bag.setIname(name);
            bag.setCount(1);
            int insert = bagMapper.insert(bag);
            //购买后钱减
            Integer price = goods.getPrice();
            int userprice=usermoney-price1;
            //更新用户金额
            user.setUid(1L);
            user.setMoney(userprice);
            int result= userMapper.updateByPrimaryKeySelective(user);
            //更新商品物品数量
         Integer count = shopMapper.selectByPrimaryKey(1L).getCount();
         count--;
         shop.setSid(1L);
         shop.setCount(count);
        shopMapper.updateByPrimaryKeySelective(shop);
    }
    */
}
