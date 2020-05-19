package com.springwork.netty.entity;

public class Command {
    private Integer cid;
    //操作命令名称
    private  String name;
    //操作指令描述
    private String cdesc;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }
}
