package cn.pomit.springwork.netty.entity;

import cn.pomit.springwork.netty.mapper.EquipMapper;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.Id;
import java.util.HashMap;

/*
装备类
 */
@Data
public class Equipment {
    private Long id;
    private String name;
    //攻击力
    private Integer atk;
    //类型，武器，护甲
    //private String type;
    //装备的耐久度
    private int endurance;
    //是否穿上
    public int loaded;
    //是否已被锁定
    private int locked;
    /**
     * 判断是否是上阵的装备
     * @return boolean
     */
    public boolean loaded(){
        return this.loaded >= 0;
    }
    /**
     * 锁定装备
     */
    public void lock(){
        this.locked = 1;
    }

    /**
     * 解锁装备
     */
    public void unlock(){
        this.locked = 0;
    }
}
