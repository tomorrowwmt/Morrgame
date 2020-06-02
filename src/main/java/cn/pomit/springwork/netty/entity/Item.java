package cn.pomit.springwork.netty.entity;
/*
物品的基类
 */
public class Item {
    private Integer id;
    //物品名字
    private  String Iname;
    //物品类型
    private String type;
    //物品描述
    private String desc;
    //物品的容量
    private Integer capacity;

    public Item(Integer id,String Iname,String type,String desc,Integer capacity){
        this.id=id;
        this.Iname=Iname;
        this.type=type;
        this.desc=desc;
        this.capacity=capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIname() {
        return Iname;
    }

    public void setIname(String iname) {
        Iname = iname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
