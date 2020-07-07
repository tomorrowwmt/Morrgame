package cn.pomit.springwork.netty.Monster;

import cn.pomit.springwork.netty.User.Entity.User;

public class Boss {
    //id
    private int id;
    //boss名字
    private  String name;
    //boss血量
    private int hp;
    //送出金钱
    private int sendmoney;

    public int getSendmoney() {
        return sendmoney;
    }

    public void setSendmoney(int sendmoney) {
        this.sendmoney = sendmoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", sendmoney=" + sendmoney +
                '}';
    }
}
