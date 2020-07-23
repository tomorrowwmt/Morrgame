package cn.pomit.springwork.netty.Monster;


import cn.pomit.springwork.netty.User.Entity.User;

import javax.sql.rowset.BaseRowSet;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "id",
        "name",
        "hp",
        "sendExp"
})
@XmlRootElement(name="Monster")
public class Monster {
    private  int  id;
    //怪兽名字
    private String name;
    //当前生命值
    private int  hp;
   //送经验值
    public  int sendExp;

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
    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", sendExp=" + sendExp +
                '}';
    }
}
