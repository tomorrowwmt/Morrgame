package cn.pomit.springwork.netty.FuBen;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.entity.User;
import com.google.common.collect.ImmutableMap;
import org.checkerframework.checker.units.qual.min;
import sun.misc.GC;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

public class Hurt {
    public Hurt(String sence)  {
    }
    public void gongji(User user) throws Exception {
        //切换场景
        qiehuan(user);
        //增加countdown计时器
        CountDown cd=new CountDown(9);
        user.setUsername("wbl1");
        user.setHp(200);
        Boss boss=new Boss();
        boss.setName("熔岩巨兽");
        boss.setHp(150);
        while(user.getHp()>0 && boss.getHp()>0){
            boss.bit(user);
            if(user.getHp()<=0){
                System.out.println("玩家死亡挑战失败"+boss.getName()+"胜利");
                huishou();
            }
            user.attackboss(boss);
            if(boss.getHp()<=0){
                System.out.println("怪兽死亡"+user.getUsername()+"挑战成功");
                System.out.println("======================");
                huishou();
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
    public void huishou(){
        Hurt changjing=new Hurt("boss副本");
        changjing=null;
        System.gc();
    }
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
        new Hurt("boss副本").gongji(user);
    }
}
