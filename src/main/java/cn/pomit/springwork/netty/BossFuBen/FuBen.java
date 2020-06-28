package cn.pomit.springwork.netty.BossFuBen;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import cn.pomit.springwork.netty.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class FuBen {
    //设定最大倒计时10s
    private static CountDown countDown;
    static {
        try {
            System.out.println("副本打boss倒计时开始");
            countDown = new CountDown(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void gongji(User user) throws Exception {
        user.setUsername("wbl1");
        user.setHp(200);
        user.setMoney(0);
        Boss boss=new Boss();
        boss.setName("熔岩巨兽");
        boss.setHp(150);
        boss.setSendmoney(100);
        while(user.getHp()>0 && boss.getHp()>0){
            boss.bit(user);
            //设计怪兽打完移动一下
            boss.move((int) (Math.random()*4+1));
            System.out.println("移动完成");
            //在继续攻击玩家
            boss.bit(user);
            if(user.getHp()<=0 ){
                System.out.println("玩家死亡挑战失败"+boss.getName()+"胜利");
                huishou(user);
                break;
            }
            user.attackboss(boss);
            //使用毒刀装备
            user.wearEquip(2);
            //用毒刀加大攻击
            user.zhongdu(boss);
            if(boss.getHp()<=0){
                System.out.println("怪兽死亡"+user.getUsername()+"挑战成功");
                System.out.println("======================");
                //设计boss死亡后送出金币
                getmoney(user);
                //回收场景
                huishou(user);
                break;
            }
        }

    }
    public void getmoney(User user){
        //先查询金币金额
        UserService userService=SpringUtil.getBean(UserService.class);
        Integer money = userService.queryById(1L).getMoney();
        //更新
        money+=100;
        user.setUid(1L);
        user.setMoney(money);
        int update = userService.update(user);
    }
    //回收场景
    public void huishou(User user) throws Exception {
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> ditu= excelReader.readXlsx("src\\main\\resources\\Excel\\Ditu.xlsx");
        String map= ditu.get(0).get(3);
        map=null;
        System.out.println("回收场景完成");
    }
}
