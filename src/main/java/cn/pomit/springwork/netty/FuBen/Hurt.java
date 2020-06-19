package cn.pomit.springwork.netty.FuBen;

import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.entity.User;
import org.checkerframework.checker.units.qual.min;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Hurt {
    public void gongji(User user) throws InterruptedException {
        user.setUsername("wbl1");
        user.setHp(200);
        Time time=new Time();
        Boss boss=new Boss();
        boss.setName("熔岩巨兽");
        boss.setHp(150);
       FuScence fuScence=new FuScence();
        //fuScence.setScence("个人boss副本场景");
        while(user.getHp()>0 && boss.getHp()>0){
            boss.bit(user);
            if(!checktime(user)){
                System.out.println("玩家死亡挑战失败"+boss.getName()+"胜利");
            }
            user.attackboss(boss);
            if(!checktime(user)){
                System.out.println("怪兽死亡"+user.getUsername()+"挑战成功");
                System.out.println("======================");
            }
        }

    }

    private boolean checktime(User user) {
        if(user!=null && user.getUid()!=null && user.getTime()!=null){
            long l = System.currentTimeMillis();
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        User user=new User();
        new Hurt().gongji(user);
    }
}
