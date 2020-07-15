package cn.pomit.springwork.netty.User.Service.Impl;

import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.Bag.Enum.BagType;
import cn.pomit.springwork.netty.Mapper.UserMapper;
import cn.pomit.springwork.netty.Monster.Service.BitUserService;
import cn.pomit.springwork.netty.Skills.Enum.SkillType;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.User.Service.BagService;
import cn.pomit.springwork.netty.User.Service.EquipService;
import cn.pomit.springwork.netty.User.Service.HurtService;
import cn.pomit.springwork.netty.Skills.Entity.Skill;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HurtServiceImpl implements HurtService {
    @Override
    public String enterHurtMonster(User user, Monster mas) {
        return user.getUsername()+"进入打怪场景请查看打斗结果。。。。。。。";
    }

    @Override
    //普攻
    public void  bit(User user,Monster mas) {
        Skill skill=new Skill();
        skill.cd=1;
        skill.mp=30;
        mas.setHp((int) (mas.getHp()-Math.random()*10));
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+user.getUsername()+"玩家利用"+ SkillType.Common.getName()
                +"攻击，剩余血量是"+mas.getHp()+"\n"+"技能CD需要"+skill.cd+"秒后恢复");
        System.out.println("==========================================");
        System.out.println("CD恢复");
        skill.setMp(skill.getMp()-5);
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
    public void bitmas(User user, Monster mas) {
        Skill skill=new Skill();
        skill.cd=1;
        skill.mp=30;
        mas.setHp((int) (mas.getHp()-Math.random()*20));
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+user.getUsername()+"玩家利用"+ SkillType.Common1.getName()
                +"攻击，剩余血量是"+mas.getHp()+"\n"+"技能CD需要"+skill.cd+"秒后恢复");
        System.out.println("CD恢复");
        skill.setMp(skill.getMp()-5);
        if(skill.getMp()<=0){
            System.out.println("mp值过低无法使用技能");
        }else{
            System.out.println("mp等待1s自动恢复");
            System.out.println("mp恢复成功");
        }
    }

    @Override
    public void magicAttack(User user, Monster mas) {
        //技能赋值
        Skill skill=new Skill();
        skill.cd=3;
        skill.mp=30;
        mas.setHp((mas.getHp()-30));
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+user.getUsername()+"玩家利用"+ SkillType.Bisha.getName()
                +"攻击，剩余血量是"+mas.getHp()+"\n"+"技能CD需要"+skill.cd+"秒后恢复");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("CD恢复");
        skill.setMp(skill.getMp()-5);
        if(skill.getMp()<=0){
            System.out.println("mp值过低无法使用技能");
        }else{
            System.out.println("mp等待1s自动恢复");
            System.out.println("mp恢复成功");
        }
    }
    //使用药水恢复hp或者mp
    //这里设计用药水的话恢复值不能超过生命值上限
    @Override
    public void drug(User user){
        //设计0.15倍数
        int hp=user.getHp();
        //先去查询缓存玩家目前拥有药水的数量
        UserService userService=SpringUtil.getBean(UserService.class);
        Integer yaoshui = userService.queryAllUser().get(0).getYaoshui();
        yaoshui= (int) Math.round(hp*0.15);
        hp=hp+yaoshui;
        System.out.println("["+user.getUsername()+"使用背包中的物品"+ BagType.Yaoshui.getName() +",增加了血量hp="+yaoshui+"的血量!]");
        System.out.println("喝了药水后迅速恢复hp="+hp);
    }

    @Override
    public String batter(User user,Monster mas, Bag bag) throws Exception {
        String enterHurtMonster = enterHurtMonster(user, mas);
        //拿到相关bean
        BitUserService bitUserService = SpringUtil.getBean(BitUserService.class);
        BagService bagService=SpringUtil.getBean(BagService.class);
        EquipService equipService=SpringUtil.getBean(EquipService.class);
        UserService userService = SpringUtil.getBean(UserService.class);
        //result作为服务端返回客户端的变量
        String result=null;
        user.setUsername("wbl1");
        user.setHp(500);
        user.exp=0;
        user.levelExp=20;
        user.setAtk(70);
        mas.setName("雪域魔王");
        mas.setHp(300);
        mas.sendExp=30;
        while(user.getHp()>0 && mas.getHp()>0){
            //一刀普攻
            bit(user,mas);
            //二刀普攻
            bitmas(user,mas);
            //穿上屠龙刀装备
            Long id = equipService.queryAllEquip().get(3).getId();
            equipService.wearEquip(user,id);
            //穿上装备触发大招
           magicAttack(user,mas);
            if(mas.getHp()<=0){
                System.out.println(mas.Live="怪兽死亡"+user.getUsername()+"玩家胜利");
                //缓存查出场景内玩家
                List<User> users = userService.queryAllUser();
                result="怪兽死亡"+user.getUsername()+"胜利"+"\n"+"通知玩家在场景怪兽死亡了"+users+
                        "\n"+"打怪胜利升级啦啦啦,"+upgrade(user);
                //设计怪兽死亡后，送出相应的经验值
                user.exp+=mas.sendExp;
                //设计死亡后看下经验时候足够升级
                checkUpgrade(user);
            }
            bitUserService.bit(mas,user);
            //做一个设计当hp<100时；利用药水恢复hp
            if(user.getHp()>0){
                if(user.getHp()<=100){
                    drug(user);
                    //吃药后药水减1
                    bagService.useconsumable(user,bag);
                    //可以继续叠加药水
                    bagService.diejia(user,bag);
                }
            }else {
                break;
            }
            if(user.getHp()<=0){
                System.out.println("玩家死亡"+mas.getName()+"胜利");
                 result="玩家死亡"+mas.getName()+"胜利";
            }
        }
        return enterHurtMonster+"\n"+result;
    }
    public void checkUpgrade(User user){
        //检查是否升级，自己的经验是否大于升级所需要的经验
        //如果升级就调用升级方法
        if(user.exp>=user.levelExp){
            upgrade(user);
        }
    }
    //升级的方法
    public String upgrade(User user) {
        String ret=null;
        //升级之后，将所有属性则增加
        System.out.println("终于打赢要升级啦");
        //先查询玩家当前级别
        UserService userService=SpringUtil.getBean(UserService.class);
        List<User> queryuser =userService.queryAllUser();
        //获取玩家当前等级，经验
        int level = queryuser.get(0).getLevel();
        int exp = queryuser.get(0).getExp();
        int levelExp = queryuser.get(0).getLevelExp();
        int atk=queryuser.get(0).getAtk();
        level += 1;
        exp += 30;
        levelExp += 50;
        atk+=70;
        //更新数据库
        user.setUid(queryuser.get(0).getUid());
        user.setExp(exp);
        user.setLevel(level);
        user.setLevelExp(levelExp);
        user.setAtk(atk);
        int update = userService.update(user);
        System.out.println("玩家等级升级为" + level + " " + "玩家经验" + exp + " " + "下一级所需要的经验为" + levelExp);
        return  ret="玩家等级升级为" + level + " " + "玩家经验" + exp + " " + "下一级所需要的经验为" + levelExp+" ";
    }

}
