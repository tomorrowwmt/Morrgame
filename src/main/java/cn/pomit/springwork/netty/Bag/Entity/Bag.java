package cn.pomit.springwork.netty.Bag.Entity;

import cn.pomit.springwork.netty.Mapper.BagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

/*
背包栏d
 */
@Table(name="tb_bag")
public class Bag implements Serializable {
    @Autowired
    private BagMapper bagMapper;
    @Id
    private Long id;
    //物品名字
    //@Transient
    private String Iname;
    //背包的容量
    private Integer capacity;
    //物品数量
    private  Integer count;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getIname() {
        return Iname;
    }

    public void setIname(String iname) {
        Iname = iname;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "id=" + id +
                ", Iname='" + Iname + '\'' +
                ", capacity=" + capacity +
                ", count=" + count +
                '}';
    }
    //消耗物品
    public  void useconsumable(Bag bag){
        //先做非空判断
        if(bag==null){
            return;
        }
        //查询数据库背包是否有药水物品
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        List<Bag> bags = bagMapper.selectAll();
        //根据查询结果获取药水count
        Integer yaoshui = bags.get(0).getCount();
        //每次使用药水减1，执行更新count进入数据库
        yaoshui--;
        bag.setId(1L);
        bag.setCount(yaoshui);
        int result=bagMapper.updateByPrimaryKeySelective(bag);
    }
    //药水叠加方法
    public void diejia(Bag bag){
        //药水可叠加，装备不能叠加,key表示位置，value表示数量
        Map<Integer,Integer> map=new HashMap<>();
        //直接添加
        map.put(1, 99);
        //查询数据药水物品
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
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
    public static void main(String[] args) throws Exception {
        Bag bag=new Bag();
        bag.useconsumable(bag);
        //bag.diejia(bag);
    }
}

