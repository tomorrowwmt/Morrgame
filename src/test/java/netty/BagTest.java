package netty;

import Dao.BaseTest;
import cn.pomit.springwork.netty.entity.Bag;
import cn.pomit.springwork.netty.mapper.BagMapper;
import org.apache.poi.ss.formula.functions.Count;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

public class BagTest  {
    @Autowired
    private  BagMapper bagMapper;
    @Test
    public  void query(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        List<Bag> bags = bagMapper.queryBag();
        System.out.println(bags);
    }
    @Test
    public void insert(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        Bag bag=new Bag();
        bag.setIname("护膝");
        bag.setType("装备");
        bag.setBesc("保护玩家");
        bag.setCapacity(10);
        int insert = bagMapper.insert(bag);
        System.out.println(insert);
    }
    /*
    @Test
    public void queryid(){
        Bag bag=new Bag();
        bag.setCapacity(10);
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        Bag queryid = bagMapper.queryid(12);
        bag.setCapacity(bag.getCapacity
        4()-1);
        System.out.println(queryid);
    }
     */
    @Test
    public void update(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        Bag bag=new Bag();
        bag.setId(1);
        bag.setCount(9);
        int result=bagMapper.update(bag);
        System.out.println(result);
    }

}
