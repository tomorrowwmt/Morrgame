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
    @Autowired
    private BagMapper bagMapper;
    private Integer id;
    //物品名字
    private String Iname;
    //物品类型
    private String type;
    //物品描述
    private String besc;
    //背包的容量
    private Integer capacity;
    private  Integer count;

    public Integer getId() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                ", type='" + type + '\'' +
                ", besc='" + besc + '\'' +
                ", capacity=" + capacity +
                '}';
    }
    //消耗物品
    public  void useconsumable(Bag bag){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        BagMapper bagMapper=ac.getBean(BagMapper.class);
        Bag queryid = bagMapper.queryid(11);
        int ct=queryid.getCount();
        ct--;
        System.out.println("使用药水后背包剩下药水数量="+ct);
    }
    public void diejia(){
        List<String> str=new ArrayList<String>();
        //药水可叠加，装备不能叠加
        str.add(0,"药水");
        str.add(1,"药水");
        str.add(2,"药水");
        str.add(3,"药水");
        str.add(4,"装备");
        Map<String,Object> map=new HashMap<String, Object>();
        for(String obj:str){
            if(map.containsKey(obj)){
                map.put(obj, ((Integer)map.get(obj)).intValue() + 1);
            }else{
                map.put(obj,1);
            }
            System.out.println("叠加完成");
        }
    }
    public static void main(String[] args) throws Exception {
        Bag bag=new Bag();
        bag.diejia();
    }
}

