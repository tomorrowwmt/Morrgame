package cn.pomit.springwork.netty.Monster;


import cn.pomit.springwork.netty.User.Entity.User;

import javax.sql.rowset.BaseRowSet;

public class Monster extends BaseRowSet {
    private  int  id;
    //怪兽名字
    private String name;
    //当前生命值
    private int  hp;
    //状态
   public String  Live;
   //送经验值
    public  int sendExp;
    //送出金币
    public int sendmoney;

    public int getSendExp() {
        return sendExp;
    }

    public void setSendExp(int sendExp) {
        this.sendExp = sendExp;
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

    public String getLive() {
        return Live;
    }

    public void setLive(String live) {
        Live = live;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", Live='" + Live + '\'' +
                ", sendExp=" + sendExp +
                '}';
    }
}
