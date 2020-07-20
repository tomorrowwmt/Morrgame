package cn.pomit.springwork.netty.Equip;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
装备类
 */
@Table(name="tb_equip")
public class Equipment {
    @Id
    private Long id;
    private String name;
    //攻击力
    private Integer atk;
    //类型，武器，护甲
    //private String type;
    //装备的耐久度
    private Integer endurance;
    //是否穿上
    @Transient
    public int loaded;
    //是否已被锁定
    @Transient
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    public int getLoaded() {
        return loaded;
    }

    public void setLoaded(int loaded) {
        this.loaded = loaded;
    }

    public int getLocked() {
        return locked;
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
                ", endurance=" + endurance +
                '}';
    }
}
