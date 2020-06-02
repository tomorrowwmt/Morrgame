package cn.pomit.springwork.netty.entity;
/*
装备类
 */
public class Equipment extends Item {
    //增加力量攻击属性
    private int Strength;
    //增加伤害
    private  int Ack;

    public Equipment(Integer id, String Iname, String type, String desc, Integer capacity) {
        super(id, Iname, type, desc, capacity);
    }

    public int getStrength() {
        return Strength;
    }

    public void setStrength(int strength) {
        Strength = strength;
    }

    public int getAck() {
        return Ack;
    }

    public void setAck(int ack) {
        Ack = ack;
    }
}
