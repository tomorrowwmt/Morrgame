package cn.pomit.springwork.netty.entity;
/*
消耗物品类 HP/MP
 */
public class Consumable extends Item {
    private  Integer hp;
    private Integer mp;
    public Consumable(Integer id,String Iname,String type,String desc,Integer capacity){
       super(id,Iname,type,desc,capacity);
    }
    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }
}
