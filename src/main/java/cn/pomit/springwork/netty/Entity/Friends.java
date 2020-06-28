package cn.pomit.springwork.netty.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
与玩家关联的朋友
 */
@Table(name="tb_friend")
public class Friends {
    @Id
    private  Long fid;
    private String name;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Friends{" +
                "fid=" + fid +
                ", name='" + name + '\'' +
                '}';
    }
}
