package cn.pomit.springwork.netty.Ditu;

import lombok.Data;

/*
地图
 */
public class Ditu {
    private Integer mid;
    //地图名称m
    private String mname;
    private String desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
                ", desc='" + desc + '\'' +
                ", monsterStr='" + monsterStr + '\'' +
                ", neighbor='" + neighbor + '\'' +
                '}';
    }
}
