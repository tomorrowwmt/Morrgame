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
    public int time;

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", time=" + time +
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
}
