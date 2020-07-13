package cn.pomit.springwork.netty.User.Service.Impl;

import cn.pomit.springwork.netty.BossFuBen.CountDown;
import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.Monster.Service.BossService;
import cn.pomit.springwork.netty.Skills.Entity.Skill;
import cn.pomit.springwork.netty.Skills.Enum.SkillType;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.EquipService;
import cn.pomit.springwork.netty.User.Service.HurtBossService;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Service;

@Service
public class HurtBossServiceImpl implements HurtBossService {
    @Override
    public String copyScenece(User user) {
        return "玩家进入副本场景开启.............";
    }

    @Override
    public void attackboss(User user, Boss boss) {
        Skill skill=new Skill();
        skill.cd=1;
        skill.mp=30;
        boss.setHp(boss.getHp()-30);
        if(boss.getHp()<=0){
            boss.setHp(0);
        }
        System.out.println(boss.getName()+"被"+user.getUsername()+"玩家利用"+ SkillType.Common.getName()
                +"攻击，剩余血量是"+boss.getHp()+"\n"+"技能CD需要"+skill.cd+"秒后恢复");
        System.out.println("==========================================");
        System.out.println("CD恢复");
        skill.setMp(skill.getMp()-10);
        if(skill.getMp()<=0){
            System.out.println("mp值过低无法使用技能");
        }else{
            System.out.println("mp等待1s自动恢复");
            System.out.println("=============");
            System.out.println("mp恢复成功");
            System.out.println("=============");
        }
    }

    @Override
    public void zhongdu(User user, Boss boss) {
        boss.setHp(boss.getHp()-60);
        if(boss.getHp()<=0){
            boss.setHp(0);
        }
        System.out.println(boss.getName()+"被"+user.getUsername()+"玩家用毒刀斩杀中毒"+"血量为"+boss.getHp());
    }
    @Override
    public String gongji(User user, Boss boss) throws Exception {
        String copyScenece = copyScenece(user);
        //CountDown countDown=new CountDown(10);
        //返回变量
        String res=null;
        BossService bossService=SpringUtil.getBean(BossService.class);
        HurtBossService hurtBossService=SpringUtil.getBean(HurtBossService.class);
        EquipService equipService=SpringUtil.getBean(EquipService.class);
        //开始赋值
        user.setUsername("wbl1");
        user.setHp(200);
        user.setMoney(0);
        boss.setName("狂血妖王");
        boss.setHp(150);
        boss.setSendmoney(100);
        while(user.getHp()>0 && boss.getHp()>0){
            bossService.bit(boss,user);
            //设计怪兽打完移动一下
            bossService.move(boss,(int) (Math.random()*4+1));
            System.out.println("移动完成");
            //在继续攻击玩家
            bossService.bit(boss,user);
            if(user.getHp()<=0 ){
                System.out.println("玩家死亡挑战失败"+boss.getName()+"胜利");
                res="玩家死亡挑战失败"+boss.getName()+"胜利";
                huishou(user);
                break;
            }
            hurtBossService.attackboss(user,boss);
            //使用毒刀装备
            Long id = equipService.queryAllEquip().get(1).getId();
            equipService.wearEquip(user,id);
            //用毒刀加大攻击
            hurtBossService.zhongdu(user,boss);
            if(boss.getHp()<=0){
                System.out.println("怪兽死亡"+user.getUsername()+"玩家挑战成功");
                System.out.println("======================");
                res="怪兽死亡"+user.getUsername()+"玩家挑战成功";
                //设计boss死亡后送出金币
                getmoney(user);
                //回收场景
                huishou(user);
                break;
            }
        }
        return copyScenece+"\n"+res+"\n"+"退出副本完成";
    }

    public void getmoney(User user){
        //先缓存查询金币金额
        UserService userService= SpringUtil.getBean(UserService.class);
        Long uid = userService.queryAllUser().get(0).getUid();
        Integer money = userService.queryById(uid).getMoney();
        //更新
        money+=100;
        user.setUid(uid);
        user.setMoney(money);
        int update = userService.update(user);
    }
    //回收场景
    public void huishou(User user) throws Exception {
        String changjing="个人副本boss";
        changjing=null;
        System.out.println("回收场景完成");
    }
}
