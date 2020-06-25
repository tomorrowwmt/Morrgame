package cn.pomit.springwork.netty.GoShopping;

import cn.pomit.springwork.netty.Entity.Bag;
import cn.pomit.springwork.netty.Entity.Shop;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.mapper.BagMapper;
import cn.pomit.springwork.netty.mapper.ShopMapper;
import cn.pomit.springwork.netty.mapper.UserMapper;
import cn.pomit.springwork.netty.spring.SpringUtil;

import java.util.List;

public class Shopping {
    private static Shop shop=new Shop();
    private static Bag bag=new Bag();
    public void shopping(User user) throws Exception {
        //查询商品物品
        //ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        ShopMapper shopMapper= SpringUtil.getBean(ShopMapper.class);
        List<Shop> shops = shopMapper.selectAll();
        //查询用户拥有金额
        UserMapper userMapper =  SpringUtil.getBean(UserMapper.class);
        Integer usermoney = userMapper.selectByPrimaryKey(1L).getMoney();
        //获取商品金额
        Integer productprice= shops.get(0).getPrice();
        //查询背包
        //做物品数量判断，若一旦购买数量<=0直接输出，否则出现负数
        if(shops.get(0).getCount()<=0) {
            System.out.println("库存不足无法购买");
            return;
        }else if(usermoney>productprice){
            //假设购买红药水
            Shop product= shopMapper.selectByPrimaryKey(1L);
            //查询商品名字
            String name =  product.getName();
            //购买加入背包
            BagMapper bagMapper =  SpringUtil.getBean(BagMapper.class);
            //在购买之前需查看背包是否有存在这个东西，这里我判断ct只要>1就说明之前有买过
            Integer ct = bagMapper.selectByPrimaryKey(12L).getCount();
            if(ct>1){
                shoppingagin(user);
            }else{
                IdWorker worker = new IdWorker(1, 1, 1);
                long l = worker.nextId();
                bag.setId(l);
                bag.setIname(name);
                bag.setCount(1);
                int insert = bagMapper.insert(bag);
            }
            //购买后钱减
            Integer price =product.getPrice();
            int userprice = usermoney -productprice;
            //更新用户金额
            user.setUid(1L);
            user.setMoney(userprice);
            int result = userMapper.updateByPrimaryKeySelective(user);
            //更新商品物品数量
            Integer count = shopMapper.selectByPrimaryKey(1L).getCount();
            count--;
            shop.setSid(1L);
            shop.setCount(count);
            shopMapper.updateByPrimaryKeySelective(shop);
        }else{
            System.out.println("购买失败金额不足请充值");
        }
    }
    public void shoppingagin(User user){
        //在一次购买更新count
        //ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper= SpringUtil.getBean(BagMapper.class);
        List<Bag> bags =bagMapper.selectAll();
        Integer count = bags.get(3).getCount();
        count++;
        bag.setId(12L);
        bag.setCount(count);
        int i = bagMapper.updateByPrimaryKeySelective(bag);

    }
}
