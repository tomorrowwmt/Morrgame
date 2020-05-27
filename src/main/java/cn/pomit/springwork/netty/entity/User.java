package cn.pomit.springwork.netty.entity;

import java.util.List;

/*
用户
 */
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String  skill;
    private Integer hp;
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

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
                ", skill='" + skill + '\'' +
                ", hp=" + hp +
                '}';
    }

    //普通攻击
    public void Attack(Monster m) {
        int injury = (int)(Math.random()*10+30);
        int hp = m.getHp();
        hp -= injury;
        if(hp>=0) {
            m.setHp(hp);
        }else {
            m.setHp(0);
        }

    }
    //必杀攻击
    public void HugeAttack(Monster m) {
        //做一个伤害
        int injury = (int) (m.getHp() > 50 ? m.getHp()*0.85 : m.getHp()*0.95);

        m.setHp(m.getHp() - injury);
    }
    //魔法
    public void MagicAttack(Monster[] m) {
        for(Monster monster : m) {
            int hp = monster.getHp();
            hp -= 35;
            if(hp>=0) {
                monster.setHp(hp);
            }else {
                monster.setHp(0);
            }
        }
    }
}
