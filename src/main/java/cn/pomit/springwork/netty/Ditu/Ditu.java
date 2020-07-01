package cn.pomit.springwork.netty.Ditu;

import lombok.Data;

/*
地图
 */
public class Ditu {
    private Integer mid;
    //地图名称m
    private String mname;
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

    @Override
    public String toString() {
        return "Ditu{" +
                "mid=" + mid +
                ", mname='" + mname + '\'' +
                ", neighbor='" + neighbor + '\'' +
                '}';
    }
}
