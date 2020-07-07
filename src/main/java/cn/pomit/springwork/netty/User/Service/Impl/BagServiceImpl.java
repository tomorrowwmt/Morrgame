package cn.pomit.springwork.netty.User.Service.Impl;

import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.Mapper.BagMapper;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.BagService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class BagServiceImpl implements BagService {
    @Override
    public void useconsumable(User user, Bag bag) {
        //先做非空判断
        if(bag==null){
            return;
        }
        //查询数据库背包是否有药水物品
        BagMapper bagMapper = SpringUtil.getBean(BagMapper.class);
        List<Bag> bags = bagMapper.selectAll();
        //根据查询结果获取药水count
        Integer yaoshui = bags.get(0).getCount();
        //每次使用药水减1，执行更新count进入数据库
        yaoshui--;
        bag.setId(1L);
        bag.setCount(yaoshui);
        int result=bagMapper.updateByPrimaryKeySelective(bag);
    }

    @Override
    public void diejia(User user, Bag bag) {
        //药水可叠加，装备不能叠加,key表示位置，value表示数量
        Map<Integer,Integer> map=new HashMap<>();
        //直接添加
        map.put(1, 10);
        //查询数据药水物品
        //查询数据库背包是否有药水物品
        BagMapper bagMapper = SpringUtil.getBean(BagMapper.class);
        List<Bag> bags = bagMapper.selectAll();
        //根据查询结果获取药水count
        Integer ys= bags.get(0).getCount();
        //获取map里的value
        int count= map.get(1);
        //叠加插入更新数据库
        count+=ys;
        //更新操作
        bag.setId(1L);
        bag.setCount(count);
        int result=bagMapper.updateByPrimaryKeySelective(bag);
    }
}
