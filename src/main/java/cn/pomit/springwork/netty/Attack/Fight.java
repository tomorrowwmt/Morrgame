package cn.pomit.springwork.netty.Attack;

import cn.pomit.springwork.netty.entity.Monster;
import cn.pomit.springwork.netty.entity.User;
import cn.pomit.springwork.netty.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Fight {
    @Autowired
    private UserMapper userMapper;
    public void  Attack() throws Exception {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
        User user=userMapper.getUserById(1);
        Monster[] monsters = new Monster[3];
        monsters[0] = new Monster("雪域魔王",100);
        monsters[1] = new Monster("九天狐王",80);
        monsters[2] = new Monster("红眼魔君",200);
        int n = 1;
        while(true) {
            System.out.println("=============第"+n+"回合=============");
            if(user.getHp()==0) {
                System.out.println("怪兽胜利");
                break;
            }else if (monsters[0].getHp() == 0 && monsters[1].getHp() == 0 && monsters[2].getHp() == 0) {
                System.out.println("玩家胜利");
                List<User> users=userMapper.queryuser();
                System.out.println("怪兽全部死亡了通知各全部玩家\n"+"收到over!!"+users);

                break;
            }
            else {
                System.out.println(user);
                double key = Math.random();
                if(key >= 0.0 && key <= 0.6) {
                    while(monsters[(int)Math.random()*3].getHp() != 0) {
                       user.Attack(monsters[(int)Math.random()*3]);
                        System.out.println(user.getUsername()+"玩家利用普通技能末日风暴和心灵火符对"
                                +monsters[(int)Math.random()*3].getName()
                                +"怪兽使用了普通攻击\n"+"技能使用完CD,1s后恢复");
                        System.out.println("使用普通技能消耗自身值mp为10");
                        try {
                            Thread.sleep(1000);
                            System.out.println("CD技能恢复成功");
                        }catch (Exception e){
                            e.getMessage();
                        }
                        break;
                    }
                }else if (key > 0.6 && key <= 0.7) {
                    while(monsters[(int)Math.random()*3].getHp() != 0) {
                        user.HugeAttack(monsters[(int)Math.random()*3]);
                        System.out.println(user.getUsername()+"玩家利用技能咸鱼突刺对"+monsters[(int)Math.random()*3].getName()
                                +"怪兽使用了必杀\n"+"技能使用完毕CD,2s后恢复请玩家稍后");
                        System.out.println("使用普通技能消耗自身值mp为20");
                        try {
                            Thread.sleep(1000);
                            System.out.println("CD技能恢复成功");
                        }catch (Exception e){
                            e.getMessage();
                        }
                        break;
                    }
                }else if (key > 0.7 && key < 1.0) {
                    user.MagicAttack(monsters);
                    System.out.println(user.getUsername()+"玩家利用技能冰龙破和唤雷术对所有怪兽使用了魔法攻击\n"+"技能使用完CD,3s后恢复");
                    System.out.println("使用普通技能消耗自身值mp为30");
                    try {
                        Thread.sleep(3000);
                        System.out.println("CD技能恢复成功");
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
                System.out.println(monsters[0].toString());
                System.out.println(monsters[1].toString());
                System.out.println(monsters[2].toString());

                monsters[0].Attack(user);
                monsters[1].Attack(user);
                monsters[2].Attack(user);
                System.out.println("怪兽对用户使用了普攻\n");
                n++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Fight().Attack();
    }

}
