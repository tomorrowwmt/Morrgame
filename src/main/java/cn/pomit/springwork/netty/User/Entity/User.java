package cn.pomit.springwork.netty.User.Entity;
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
    private String username;
    private String password;
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
    public Integer levelExp;
    public Integer atk;
    //金钱
    public Integer money;
    //读取系统邮件状态
    private Integer status;

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

    public Integer getLevelExp() {
        return levelExp;
    }

    public void setLevelExp(Integer levelExp) {
        this.levelExp = levelExp;
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

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", map='起始之地"  + '\'' +
                ", hp=" + hp +
                ", yaoshui=" + yaoshui +
                ", exp=" + exp +
                ", level=" + level +
                ", levelExp=" + levelExp +
                ", atk=" + atk +
                ", money=" + money +
                ", status=" + status +
                '}';
    }
}