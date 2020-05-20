package cn.pomit.springwork.netty.entity;

/*
用户
 */
public class User {
    private Integer uid;
    private String username;
    private String password;
    //用户所在地点
    private Ditu mid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ditu getMid() {
        return mid;
    }

    public void setMid(Ditu mid) {
        this.mid = mid;
    }
}
