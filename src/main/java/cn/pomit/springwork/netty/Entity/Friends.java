package cn.pomit.springwork.netty.Entity;

import lombok.Data;

/*
玩家跟朋友组队
 */
public class Friends {
    private  Long fid;
    private String name;
    private  Long uid;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "fid=" + fid +
                ", name='" + name + '\'' +
                ", uid=" + uid +
                '}';
    }
}
