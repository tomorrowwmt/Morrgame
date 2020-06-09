package cn.pomit.springwork.netty.entity;

import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Skills.Skill;

/*
用户
 */
public class User {
    private Integer uid;
    private String username;
    private String password;
    //当前生命值
    private int hp;
    //药水增加的hp或者mp
    public int yaoshui;
    //设计玩家的经验
    public  int exp;
   //等级
    public  int level;
    //下一级所需经验
    public  int levelExp;

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelExp() {
        return levelExp;
    }

    public void setLevelExp(int levelExp) {
        this.levelExp = levelExp;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getYaoshui() {
        return yaoshui;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hp=" + hp +
                ", yaoshui=" + yaoshui +
                ", exp=" + exp +
                ", level=" + level +
                ", levelExp=" + levelExp +
                '}';
    }

    public void setYaoshui(int yaoshui) {
        this.yaoshui = yaoshui;
    }

    //攻击
    public void bit(Monster mas){
        Skill skill=new Skill();
        skill.name="末日风暴";
        skill.type="普通攻击";
        skill.cd=1;
        skill.mp=30;
        mas.setHp(mas.getHp()-10);
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+username+"玩家利用"+skill.type+skill.name
                +"攻击，剩余血量是"+mas.getHp()+"\n"+"技能CD需要"+skill.cd+"秒后恢复");
        System.out.println("==========================================");
        System.out.println("CD恢复");
        //User u=new User();
        //u.setMp(30);
        //mp
        //u.setMp(u.getMp()-5);
        skill.setMp(skill.getMp()-5);
        if(skill.getMp()<=0){
            System.out.println("mp值过低无法使用技能");
        }else{
            System.out.println("mp等待1s自动恢复");
            System.out.println("=============");
            System.out.println("mp恢复成功");
            System.out.println("=============");
        }
    }
    //必杀攻击
    public void HugeAttack(Monster m) {
        //做一个伤害
        int injury = (int) (m.getHp() > 50 ? m.getHp()*0.85 : m.getHp()*0.95);
        m.setHp(m.getHp() - injury);
    }
    //使用药水恢复hp或者mp
    //这里设计用药水的话恢复值不能超过生命值上限
    public void drug(){
        //设计0.15倍数
        yaoshui= (int) Math.round(hp*0.15);
        hp=hp+yaoshui;
        System.out.println("["+username+"使用的药水,增加了血量hp="+yaoshui+"的血量!]");
        System.out.println("喝了药水后迅速恢复hp="+hp);
    }
}
