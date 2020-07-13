package cn.pomit.springwork.netty.Shopping.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tb_shop")
public class Shop {
    @Id
    private  Long sid;
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
    @Override
    public String toString() {
        return "Shop{" +
                "sid=" + sid +
                ", count=" + count +
                '}';
    }
}
