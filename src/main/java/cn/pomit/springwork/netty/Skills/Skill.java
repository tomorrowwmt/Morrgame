package cn.pomit.springwork.netty.Skills;
/*
技能实体类
 */
public class Skill {
    //技能id
    private int id;
    //技能名字
    public String name;
    //技能类型
    public String type;
    //技能cd时间
    public  int cd;
    //技能mp消耗
    public int mp;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cd=" + cd +
                ", mp=" + mp +
                '}';
    }
}
