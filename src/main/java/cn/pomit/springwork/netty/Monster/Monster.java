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

    public void bit(User u){
        //做一个伤害
        int injury = (int)(Math.random()*10);
        //获取用户的血量
        int hp = u.getHp();
        hp -= injury;
        //血量为0时
        if(hp>=0) {
            u.setHp(hp);
        }else {
            u.setHp(0);
        }
       //u.setHp((u.getHp()-1));
        System.out.println(u.getUsername()+"被"+name+"进行攻击,剩余血量是"+u.getHp());
        System.out.println("=========================");
    }
}
