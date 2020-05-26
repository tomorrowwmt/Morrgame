package cn.pomit.springwork.netty.entity;

public class Monster {
    //怪兽名字
    private String name;
    //角色类型
    private String RoleType;
    //血量
    private  Integer hp;
    //状态1生存，0，死亡
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public String getRoleType() {
        return RoleType;
    }

    public void setRoleType(String roleType) {
        RoleType = roleType;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public void Attack(User u) {
        //做一个伤害
        int injury = (int)(Math.random()*10+10);
        //获取用户的血量
        int hp = u.getHp();
        hp -= injury;
        //血量为0时怎么办
        if(hp>=0) {
            u.setHp(hp);
        }else {
            u.setHp(0);
        }
    }
    /*
    public void setHp(Integer hp) {
        this.hp = hp;
    }
    public Monster(String name,Integer hp){
        super();
        this.name=name;
        this.hp=hp;
    }
    public void Attack(Ultraman u) {
        //做一个伤害
        int injury = (int)(Math.random()*10+10);
        //获取奥特曼的血量
        int hp = u.getHp();
        hp -= injury;
        //血量为0时怎么办
        if(hp>=0) {
            u.setHp(hp);
        }else {
            u.setHp(0);
        }
    }
    */
}
