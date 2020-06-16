package cn.pomit.springwork.netty.entity;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.mapper.BagMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/*
背包栏
 */
public class Bag {
    private Integer id;
    //物品名字
    private String Iname;
    //物品类型
    //private String type;
    //物品描述
    private String besc;
    //背包的容量
    private Integer capacity;
    private  Integer count;

    public Integer getId(int i) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIname() {
        return Iname;
    }

    public void setIname(String iname) {
        Iname = iname;
    }

    public String getBesc() {
        return besc;
    }

    public void setBesc(String besc) {
        this.besc = besc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "id=" + id +
                ", Iname='" + Iname + '\'' +
                ", besc='" + besc + '\'' +
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
        List<Bag> bags = bagMapper.queryBag();
        //根据查询结果获取药水count
        Integer yaoshui = bags.get(0).getCount();
        //每次使用药水减1，执行更新count进入数据库
        yaoshui--;
        bag.setId(1);
        bag.setCount(yaoshui);
        int result=bagMapper.update(bag);
    }
    public void diejia(Bag bag){
        //药水可叠加，装备不能叠加,key表示位置，value表示数量
        Map<Integer,Integer> map=new HashMap<>();
        //直接添加
        map.put(1, 10);
        //查询数据药水物品
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        List<Bag> bags = bagMapper.queryBag();
        //根据查询结果获取药水count
        Integer ys= bags.get(0).getCount();
        //获取map里的value
        int count= map.get(1);
        //叠加插入更新数据库
        count+=ys;
        //更新操作
        bag.setId(1);
        bag.setCount(count);
        int result=bagMapper.update(bag);
    }
    public static void main(String[] args) throws Exception {
        Bag bag=new Bag();
        //bag.useconsumable(bag);
        bag.diejia(bag);
    }
}

