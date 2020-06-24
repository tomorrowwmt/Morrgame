package cn.pomit.springwork.netty.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="tb_shop")
public class Shop {
    @Id
    private  Long sid;
    private String name;
    private  Integer price;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
