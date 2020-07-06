package cn.pomit.springwork.netty.Attack;
import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.Mapper.UserMapper;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
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
            //bitService.bit(mas);
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
        //先查询玩家当前级别
        UserMapper userMapper= SpringUtil.getBean(UserMapper.class);
        List<User> queryuser = userMapper.selectAll();
        //获取玩家当前等级，经验
        int level = queryuser.get(0).getLevel();
        int exp = queryuser.get(0).getExp();
        int levelExp = queryuser.get(0).getLevelExp();
        level+=1;
        exp+=30;
        levelExp+=50;
        //更新数据库
        user.setUid(1L);
        user.setExp(exp);
        user.setLevel(level);
        user.setLevelExp(levelExp);
        int update = userMapper.updateByPrimaryKeySelective(user);
        System.out.println("玩家等级升级为"+level+" "+"玩家经验"+exp+" "+"下一级所需要的经验为"+levelExp);
    }
}
