package cn.pomit.springwork.netty.entity;
/*
技能实体类
 */
public class Skill {
    //技能id
    private Integer id;
    //技能名称
    private String name;
    //技能类型
    private  String type;
    //技能cd
    private Integer cd;
    //需要消耗mp
    private Integer mp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getCd() {
        return cd;
    }

    public void setCd(Integer cd) {
        this.cd = cd;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
