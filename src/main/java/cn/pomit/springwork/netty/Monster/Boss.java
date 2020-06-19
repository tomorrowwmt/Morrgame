package cn.pomit.springwork.netty.Monster;

import cn.pomit.springwork.netty.entity.User;
import lombok.Data;

@Data
public class Boss {
    //id
    private int id;
    //boss名字
    private  String name;
    //boss血量
    private int hp;
    public int time;
    //个人boss对玩家做的伤害
    public void bit(User u){
        //做一个伤害
        int injury = (int)(Math.random()*20);
        //获取用户的血量
        int hp = u.getHp();
        hp -= injury;
        //血量为0时
        if(hp>=0) {
            u.setHp(hp);
        }else {
            u.setHp(0);
        }
        System.out.println(u.getUsername()+"被"+name+"进行攻击,剩余血量是"+u.getHp());
        System.out.println("=========================");
    }
}
