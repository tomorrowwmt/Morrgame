package cn.pomit.springwork.netty.Monster;


import cn.pomit.springwork.netty.entity.User;

public class Monster {
    //怪兽名字
    private String name;
    //当前生命值
    private int  hp;
    //状态
    public String  isLive;
    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", isLive=" + isLive +
                '}';
    }

    public void bit(User u){
        //做一个伤害
        int injury = (int)(Math.random()*10);
        //获取用户的血量
        int hp = u.getHp();
        hp -= injury;
        //血量为0时
        if(hp>=0) {
            u.setHp(hp);
        }else {
            u.setHp(0);
        }
       //u.setHp((u.getHp()-1));
        System.out.println(u.getUsername()+"被"+name+"进行攻击,剩余血量是"+u.getHp());
        System.out.println("=========================");
    }
}
