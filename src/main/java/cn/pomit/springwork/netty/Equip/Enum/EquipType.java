package cn.pomit.springwork.netty.Equip.Enum;

public enum EquipType {
    /*
    weapon表示武器
    Hujia表示护甲
     */
    Weapon("武器", 1),
    Hujia ("护甲", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private EquipType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (EquipType c : EquipType.values()) {
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
        System.out.println(EquipType.getName(1));
    }

}