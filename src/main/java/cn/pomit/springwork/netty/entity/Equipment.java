package cn.pomit.springwork.netty.entity;
/*
装备类
 */
public class Equipment  {
    private  Integer id;
    private String name;
    //攻击力
    private Integer atk;
    //装备的耐久度
    private  int endurance;
    public Equipment(Integer id,String name,int atk,int endurance){
        this.id=id;
        this.name=name;
        this.atk=atk;
        this.endurance=endurance;
    }

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

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }
}
