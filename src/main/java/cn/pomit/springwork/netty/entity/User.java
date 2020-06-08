package cn.pomit.springwork.netty.entity;

import cn.pomit.springwork.netty.Monster.Monster;

/*
用户
 */
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String  skill;
    //当前生命值
    private int hp;
    //药水增加的hp或者mp
    public double yaoshui;
    //设置实体的生存死亡状态
    public String isLive;
    //技能mp
    private int mp;

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getYaoshui() {
        return yaoshui;
    }

    public void setYaoshui(double yaoshui) {
        this.yaoshui = yaoshui;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", skill='" + skill + '\'' +
                ", hp=" + hp +
                ", yaoshui=" + yaoshui +
                ", isLive='" + isLive + '\'' +
                ", mp=" + mp +
                '}';
    }
    //普通攻击
    public void bit(Monster mas){
        mas.setHp(mas.getHp()-10);
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+username+"玩家利用普通技能末日风暴和心灵火符"
                +"攻击，剩余血量是"+mas.getHp()+"\n"+"技能CD需要1s后恢复");
        System.out.println("==========================================");
        System.out.println("CD恢复");
        User u=new User();
        u.setMp(30);
        //mp
        u.setMp(u.getMp()-5);
        if(mp<=0){
            System.out.println("mp值过低无法使用技能");
        }else{
            System.out.println("mp等待1s自动恢复");
            System.out.println("=============");
            System.out.println("mp恢复成功");
            System.out.println("=============");
        }
    }
    //使用药水恢复hp或者mp
    //这里设计用药水的话恢复值不能超过生命值上限
    public void drug(){
        //设计0.15倍数
        yaoshui=Math.round(hp*0.15);
        hp= (int) (hp+yaoshui);
        System.out.println("["+username+"使用的药水,增加了血量hp="+yaoshui+"的血量!]");
        System.out.println("喝了药水后迅速恢复hp="+hp);
    }
}
