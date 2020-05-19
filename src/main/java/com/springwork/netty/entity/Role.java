package com.springwork.netty.entity;
/*
业务实体类
 */
public class Role {
    private Role rid;
    //村民
    private String village;
    //npc玩家
    private String npc;
    //怪兽
    private String master;
    //状态,0,生，1,死
    private Integer state;

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getNpc() {
        return npc;
    }

    public void setNpc(String npc) {
        this.npc = npc;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Role getRid() {
        return rid;
    }

    public void setRid(Role rid) {
        this.rid = rid;
    }
}
