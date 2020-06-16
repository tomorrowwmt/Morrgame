package cn.pomit.springwork.netty.Attack;


import cn.pomit.springwork.netty.entity.Bag;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.entity.Equipment;
import cn.pomit.springwork.netty.entity.User;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class Batter {
   public void attack(User user)throws Exception {
       Bag bag=new Bag();
       Monster mas=new Monster();
       user.setUsername("wbl1");
       user.setHp(200);
       user.exp=0;
       user.levelExp=20;
       user.setAtk(70);
       mas.setName("雪域魔王");
       mas.setHp(300);
       mas.sendExp=30;
        while(user.getHp()>0 && mas.getHp()>0){
            user.bit(mas);
            //穿装备
            user.wearEquip(4);
            //触发必杀技能
            user.MagicAttack(mas);
            if(mas.getHp()<=0){
                System.out.println(mas.Live="怪兽死亡"+user.getUsername()+"胜利");
                System.out.println("======================");
                //设计怪兽死亡后，送出相应的经验值
                user.exp+=mas.sendExp;
                //设计死亡后看下经验时候足够升级
                 checkUpgrade(user);
            }
           mas.bit(user);
            //做一个设计当hp<100时；利用药水恢复hp
            if(user.getHp()>0){
                if(user.getHp()<=150){
                    user.drug(user);
                    //吃药后药水减1
                    bag.useconsumable(bag);
                    //可以继续叠加药水
                    bag.diejia(bag);
                }
            }else{
                break;
            }
            if(user.getHp()<=0){
                System.out.println("玩家死亡"+mas.getName()+"胜利");
                System.out.println("装备丢失");
                user.unlockEquip(4);
            }
        }
    }
    public void checkUpgrade(User user){
       //检查是否升级，自己的经验是否大于升级所需要的经验
       //如果升级就调用升级方法
       if(user.exp>=user.levelExp){
           upgrade(user);
       }
    }
    //升级的方法
    public void upgrade(User user) {
       //升级之后，将所有属性则增加
        System.out.println("终于打赢要升级啦");
        user.level+=1;
        user.exp+=50;
        user.levelExp+=30;
        System.out.println("玩家等级升级为"+user.level+" "+"玩家经验"+user.exp+" "+"下一级所需要的经验为"+user.levelExp);
    }

    public static void main(String[] args) throws Exception{
       User user=new User();
       new Batter().attack(user);
        //System.out.println(user.getUsername());
    }
}
