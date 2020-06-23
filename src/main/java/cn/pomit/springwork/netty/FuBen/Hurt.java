package cn.pomit.springwork.netty.FuBen;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.Entity.User;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Hurt {
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
    /*
    public Hurt(User user) throws Exception {
        qiehuan(user);
    }
     */
    public void gongji(User user) throws Exception {
        //切换场景
        //qiehuan(user);

        user.setUsername("wbl1");
        user.setHp(200);
        Boss boss=new Boss();
        boss.setName("熔岩巨兽");
        boss.setHp(150);
        while(user.getHp()>0 && boss.getHp()>0){
            boss.bit(user);
            if(user.getHp()<=0 ){
                System.out.println("玩家死亡挑战失败"+boss.getName()+"胜利");
                //huishou(user);
                break;
            }
            user.attackboss(boss);
            if(boss.getHp()<=0){
                System.out.println("怪兽死亡"+user.getUsername()+"挑战成功");
                System.out.println("======================");
                //huishou(user);
                break;
            }
        }

    }

    public void  qiehuan(User user)throws Exception{
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> map = excelReader.readXlsx("src\\main\\resources\\Excel\\Ditu.xlsx");
        System.out.println("个人副本开始请输入移动指令:move");
        while (true){
            String cli=read();
            if("move".equals(cli)){
                System.out.println(map.get(0).get(3));
               break;
            }
        }
    }
    //回收场景
    /*
    public void huishou(User user) throws Exception {
        Hurt changjing=new Hurt(user);
        changjing=null;
    }
     */
    public static String read() throws Exception{
        String str = "";
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            str = sc.next();
            break;
        }
        return str;
    }

    public static void main(String[] args) throws Exception {
        User user=new User();
        //new Hurt(user).gongji(user);
    }
}
