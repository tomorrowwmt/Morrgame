package cn.pomit.springwork.netty.User.Entity;
import cn.pomit.springwork.netty.User.Session.Session;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
用户
 */
@Table(name="tb_user")
public class User {
    @Id
    private Long uid;
    @Transient
    private Long mid;
    private String username;
    private String password;
    private String sex;
    private String profession;
    @Transient
    private String map;
    //当前生命值
    private Integer hp;
    //药水增加的hp或者mp
    private Integer yaoshui;
    //设计玩家的经验
    public Integer exp;
   //等级
   public Integer level;
    //下一级所需经验
    public Integer levexp;
    public Integer atk;
    //金钱
    public Integer money;
    //读取系统邮件状态
    private Integer status;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getYaoshui() {
        return yaoshui;
    }

    public void setYaoshui(Integer yaoshui) {
        this.yaoshui = yaoshui;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getAtk() {
        return atk;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Integer getLevexp() {
        return levexp;
    }

    public void setLevexp(Integer levexp) {
        this.levexp = levexp;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", mid=" + mid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", profession='" + profession + '\'' +
                ", hp=" + hp +
                ", yaoshui=" + yaoshui +
                ", exp=" + exp +
                ", level=" + level +
                ", levexp=" + levexp +
                ", atk=" + atk +
                ", money=" + money +
                ", status=" + status +
                '}';
    }
}
