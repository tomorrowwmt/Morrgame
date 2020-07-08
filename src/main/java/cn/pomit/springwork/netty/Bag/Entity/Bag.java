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
}

