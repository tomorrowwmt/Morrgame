package cn.pomit.springwork.netty.User.Service.Impl;
import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.Mapper.BagMapper;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.BagService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("BagGuavaCache")
public class BagServiceImpl implements BagService {
    @Autowired
    private  BagMapper bagMapper;
    @Autowired
    private  BagService bagService;
    @Override
    public void useconsumable(User user, Bag bag) {
        //先做非空判断
        if(bag==null){
            return;
        }
        UserService userService = SpringUtil.getBean(UserService.class);
        User queryById = userService.queryById(1L);
        //查询缓存数据库背包是否有药水物品
        //  Integer yaoshui根据查询结果获取药水count;
        Integer yaoshui=bagService.queryBagId(1L).getCount();
        //每次使用药水减1，执行更新count进入数据库
        yaoshui--;
        bag.setId(1L);
        bag.setCount(yaoshui);
        user.setUid(1L);
        user.setYaoshui(yaoshui);
        //BagMapper bagMapper=ac.getBean(BagMapper.class);
        int result=bagMapper.updateByPrimaryKeySelective(bag);
        int ret=userService.update(user);
    }

    @Override
    public void diejia(Bag bag) {
        //判断背包容量是否超过了最大限度
        Integer bagcapacity = bagService.queryBagId(1L).getCapacity();
        if(bagcapacity>100){
            return;
        }
        //药水可叠加，装备不能叠加,key表示位置，value表示数量
        Map<Integer,Integer> map=new HashMap<>();
        //直接添加
        map.put(1, 10);
        //查询数据药水物品
        Bag bags = bagService.queryBagId(1L);
        //根据查询结果获取药水count
        Integer ys= bags.getCount();
        //获取map里的value
        int count= map.get(1);
        //叠加插入更新数据库
        count+=ys;
        //更新操作
        bag.setId(1L);
        bag.setCount(count);
        int result=bagMapper.updateByPrimaryKeySelective(bag);
    }
    @Override
    @Cacheable(value = "bagCache")
    public List<Bag> queryAllBag() {
        return bagMapper.selectAll();
    }
    @Override
    @Cacheable(value = "bagCache",key="#id")
    public Bag queryBagId(Long id) {
        return bagMapper.selectByPrimaryKey(id);
    }

}
