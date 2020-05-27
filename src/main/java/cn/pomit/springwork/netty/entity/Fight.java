package cn.pomit.springwork.netty.entity;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Fight {
    @Autowired
    private UserMapper userMapper;
    public void Attack() throws Exception {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
        User user=userMapper.getUserById(14);
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
                System.out.println("用户胜利");
                System.out.println("怪兽全部死亡了");
                break;
            }
            else {
                System.out.println(user.toString());
                double key = Math.random();
                if(key >= 0.0 && key <= 0.6) {
                    while(monsters[(int)Math.random()*3].getHp() != 0) {
                       user.Attack(monsters[(int)Math.random()*3]);
                        System.out.println(user+"\n"+"用户利用技能对"+monsters[(int)Math.random()*3].getName()
                                +"怪兽使用了普通攻击");
                        break;
                    }
                }else if (key > 0.6 && key <= 0.7) {
                    while(monsters[(int)Math.random()*3].getHp() != 0) {
                        user.HugeAttack(monsters[(int)Math.random()*3]);
                        System.out.println(user+"\n"+"用户利用技能对"+monsters[(int)Math.random()*3].getName()
                                +"怪兽使用了必杀");
                        break;
                    }
                }else if (key > 0.7 && key < 1.0) {
                    user.MagicAttack(monsters);
                    System.out.println(user+"\n"+"用户利用技能对所有怪兽使用了魔法攻击");
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
