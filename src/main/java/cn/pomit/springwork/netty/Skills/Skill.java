package cn.pomit.springwork.netty.Skills;

import lombok.Data;

/*
技能实体类
 */
@Data
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
}
