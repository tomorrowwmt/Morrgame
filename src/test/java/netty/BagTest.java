package netty;

import cn.pomit.springwork.netty.Entity.Bag;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import cn.pomit.springwork.netty.mapper.BagMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

public class BagTest  {
    @Autowired
    private BagMapper bagMapper;
    @Test
    public  void query(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        List<Bag> bags = bagMapper.selectAll();
        //System.out.println(bags);
        System.out.println(bags);
    }
    @Test
    public void insert(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        Bag bag=new Bag();
        IdWorker worker=new IdWorker(1,1,1);
        long l=worker.nextId();
        bag.setId(l);
        bag.setIname("圣域令");
        bag.setCapacity(10);
        bag.setCount(1);
        int insert = bagMapper.insertSelective(bag);
        System.out.println(insert);
    }
    @Test
    public void queryid(){
        Bag bag=new Bag();
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        Bag bag1 = bagMapper.selectByPrimaryKey(1L);
        System.out.println(bag1);
    }

    @Test
    public void update(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        Bag bag=new Bag();
        bag.setId(11L);
        //bag.setIname("hhhh");
        bag.setCapacity(100);
        bag.setCount(90);
        int result=bagMapper.updateByPrimaryKeySelective(bag);
        System.out.println(result);
    }

}
