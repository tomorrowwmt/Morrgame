package cn.pomit.springwork.netty.Ditu;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
地图
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "mid",
        "mname",
        "neighbor",
        "monsterStr"
})
@XmlRootElement(name="Ditu")
public class Ditu {
    private Integer mid;
    //地图名称m
    private String mname;
    private String monsterStr;
    //临近的地图
    private String neighbor;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }

    public String getMonsterStr() {
        return monsterStr;
    }

    public void setMonsterStr(String monsterStr) {
        this.monsterStr = monsterStr;
    }

    @Override
    public String toString() {
        return "Ditu{" +
                "mid=" + mid +
                ", mname='" + mname + '\'' +
                ", monsterStr='" + monsterStr + '\'' +
                ", neighbor='" + neighbor + '\'' +
                '}';
    }
}
