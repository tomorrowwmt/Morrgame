package cn.pomit.springwork.netty.Skills.Enum;

public enum SkillType {
    /*
    common表示普通攻击
    bisha表示必杀攻击
     */
    Common("末日风暴",1),
    Common1("唤雷术",2),
    Bisha("屠龙大刀", 3);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private SkillType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (SkillType c :SkillType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static void main(String[] args) {
        System.out.println(SkillType.Common.getName());
        //System.out.println(SkillType.Cd.getIndex());
    }
}
