package cn.pomit.springwork.netty.entity;

import cn.pomit.springwork.netty.mapper.EquipMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.Id;
import java.util.HashMap;

/*
装备类
 */
public class Equipment {
    private Integer id;
    private String name;
    //攻击力
    private Integer atk;
    //类型，武器，护甲
    private String type;
    //装备的耐久度
    private int endurance;
    //是否穿上
    public int loaded;
    //是否删除装备
    public int delete;
    //是否已被锁定
    private int locked;
    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLoaded() {
        return loaded;
    }

    public void setLoaded(int loaded) {
        this.loaded = loaded;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", atk=" + atk +
                ", type='" + type + '\'' +
                ", endurance=" + endurance +
                ", loaded=" + loaded +
                ", delete=" + delete +
                ", locked=" + locked +
                '}';
    }

    /**
     * 判断是否已经被删除
     * @return boolean
     */
    public boolean Removed(){ return this.delete > 0; }

    /**
     * 判断是否是上阵的装备
     * @return boolean
     */
    public boolean Loaded(){
        return this.loaded > 0;
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
