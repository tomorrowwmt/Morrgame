package cn.pomit.springwork.netty.PK;

import cn.pomit.springwork.netty.BossFuBen.CountDown;
import cn.pomit.springwork.netty.Entity.User;

public class PkGongji {
    //设定最大倒计时10s
    private static CountDown countDown;
    static {
        try {
            System.out.println("玩家进入竞技场进行PK战斗");
            countDown = new CountDown(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void pk(User user1,User user2){
        System.out.println("比赛开始了啦啦啦");
        user1.setUsername("wbl1");
        user1.setHp(200);
        user2.setUsername("詹姆斯");
        user2.setHp(300);
        while (user1.getHp()>0 && user2.getHp()>0){
            beat(user1,user2);
            if(user1.getHp()<=0){
                System.out.println("玩家"+user2.getUsername()+"胜利");
            }else if(user2.getHp()<=0){
                System.out.println("玩家"+user1.getUsername()+"胜利");
            }
        }
    }
    public void beat( User user1,User user2){
        user1.setHp((int) (user1.getHp()-(Math.random()*30+50)));
        user2.setHp((int) (user2.getHp()-(Math.random()*10+30)));
        if(user2.getHp()<=0){
            user2.setHp(0);
        }else if (user1.getHp()<=0){
            user1.setHp(0);
        }
        System.out.println(user2.getUsername()+"被"+user1.getUsername()+"攻击了"+"血量为"+user2.getHp());
        System.out.println(user1.getUsername()+"被"+user2.getUsername()+"攻击了"+"血量为"+user1.getHp());
    }

    public static void main(String[] args) {
        User user1=new User();
        User user2=new User();
        new PkGongji().pk(user1,user2);
    }
}
