package cn.pomit.springwork.netty.entity;

import java.util.List;

/*
用户
 */
public class User {
    private Integer uid;
    private String username;
    private String password;
    private Integer hp;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hp=" + hp +
                '}';
    }
    //普通攻击
    public void Attack(Monster m){
        //做一个伤害
        int injury= (int) (Math.random()*10+20);
        //获取用户的血量
        //获取怪兽的血量
        int hp=m.getHp();
        hp-=injury;
        //血量为0
        if(hp>=0){
            m.setHp(hp);
        }else{
            m.setHp(0);
        }
    }
    //魔法攻击
    public void MagicAttack(Monster[] ret) {
        for(Monster ms :ret) {
            int hp = ms.getHp();
            hp-= 40;
            //血量为0时怎么办
            if(hp>=0) {
                ms.setHp(hp);
            }else {
                ms.setHp(0);
            }
        }
    }
}
