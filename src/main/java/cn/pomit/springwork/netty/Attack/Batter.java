package cn.pomit.springwork.netty.Attack;


import cn.pomit.springwork.netty.entity.Bag;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.entity.Equipment;
import cn.pomit.springwork.netty.entity.User;

import javax.jws.soap.SOAPBinding;
import java.net.URL;

public class Batter {
   public void attack()throws Exception {
       Bag bag=new Bag();
        Monster mas=new Monster();
       Equipment equip=new Equipment();
       equip.setName("屠龙刀");
       equip.setAtk(100);
       equip.setType("武器类");
       equip.setEndurance(70);
       User user=new User();
        user.setUsername("wbl1");
        user.setHp(200);
        user.exp=0;
        user.levelExp=20;
        user.setAtk(70);
        mas.setName("雪域魔王");
        mas.setHp(300);
        //mas.setSendExp(30);
       mas.sendExp=30;
        while(user.getHp()>0&& mas.getHp()>0){
            user.bit(mas);
            //穿一下
            user.wearEquip(4);
            user.MagicAttack(mas);
            if(mas.getHp()<=0){
                System.out.println(mas.Live="怪兽死亡"+user.getUsername()+"胜利");
                System.out.println("======================");
                //设计怪兽死亡后，送出相应的经验值
                user.exp+=mas.sendExp;
                //设计死亡后看下经验时候足够升级
                 checkUpgrade();
            }
           mas.bit(user);
            //做一个设计当hp<100时；利用药水恢复hp
            if(user.getHp()>0){
                if(user.getHp()<=150){
                    user.drug();
                    //吃药后药水减1
                    bag.useconsumable(bag);
                    //可以继续叠加药水
                    bag.diejia();
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
    public void checkUpgrade(){
       //检查是否升级，自己的经验是否大于升级所需要的经验
       User user=new User();
       //如果升级就调用升级方法
       if(user.exp>=user.levelExp){
           upgrade();
       }
    }
    //升级的方法
    public void upgrade() {
       //升级之后，将所有属性则增加
       User user=new User();
        System.out.println("终于打赢要升级啦");
        user.level+=1;
        user.exp+=50;
        user.levelExp+=30;
        System.out.println("玩家等级升级为"+user.level+" "+"玩家经验"+user.exp+" "+"下一级所需要的经验为"+user.levelExp);
    }

    public static void main(String[] args) throws Exception{
       new Batter().attack();
    }
}
