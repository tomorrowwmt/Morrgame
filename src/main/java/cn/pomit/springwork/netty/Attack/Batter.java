package cn.pomit.springwork.netty.Attack;


import cn.pomit.springwork.netty.entity.Monster;
import cn.pomit.springwork.netty.entity.User;

public class Batter {
   public void attack()throws Exception {
        Monster mas=new Monster();
        User user=new User();
        user.setUsername("wbl1");
        user.setHp(200);
        user.setMp(30);
        mas.setName("雪域魔王");
        mas.setHp(200);
        while(user.getHp()>0&&mas.getHp()>0){
            user.bit(mas);
            if(mas.getHp()<=0){
                System.out.println(mas.isLive="怪兽死亡"+user.getUsername()+"胜利");
            }
           mas.bit(user);
            //做一个设计当hp<100时；利用药水恢复hp
            if(user.getHp()>0){
                if(user.getHp()<=100){
                    user.drug();
                }
            }else{
                break;
            }
            if(user.getHp()<=0){
                System.out.println(user.isLive="玩家死亡"+mas.getName()+"胜利");
            }
        }
    }
    public static void main(String[] args) throws Exception{
       new Batter().attack();
    }
}
