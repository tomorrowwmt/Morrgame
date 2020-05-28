package cn.pomit.springwork.netty.entity;

import javax.jws.soap.SOAPBinding;

public class Monster {
    //怪兽名字
    private String name;
    //血量
    private  Integer hp;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Monster(String name,Integer hp){
       this.name=name;
       this.hp=hp;
    }


    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                '}';
    }

    public void Attack(User u) {
        //做一个伤害
        int injury = (int)(Math.random()*10);
        //获取用户的血量
        int hp = u.getHp();
        hp -= injury;
        //血量为0时怎么办
        if(hp>=0) {
            u.setHp(hp);
        }else {
            u.setHp(0);
        }
    }
}
