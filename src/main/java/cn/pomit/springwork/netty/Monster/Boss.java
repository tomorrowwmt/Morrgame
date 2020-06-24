package cn.pomit.springwork.netty.Monster;

import cn.pomit.springwork.netty.Entity.User;
import lombok.Data;

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

    //个人boss对玩家做的伤害
    public void bit(User u){
        //做一个伤害
        int injury = (int)(Math.random()*20);
        //获取用户的血量
        int hp = u.getHp();
        hp -= injury;
        //血量为0时
        if(hp>=0) {
            u.setHp(hp);
        }else {
            u.setHp(0);
        }
        System.out.println(u.getUsername()+"被"+name+"进行攻击,剩余血量是"+u.getHp());
        System.out.println("=========================");
    }
    //怪兽移动
    public void move(int direction){
        switch (direction){
            case 1:
            System.out.println(this.name+"is moving 1 step NORTH.");
            break;
            case 2:
                System.out.println(this.name + "is moving 1 step EAST.");
                break;
            case 3:
                System.out.println(this.name + "is moving 1 step SOUTH.");
                break;
            default:
                System.out.println(this.name + "is moving 1 step WEST.");
                break;
        }
    }
}
