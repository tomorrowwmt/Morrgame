package cn.pomit.springwork.netty.Login;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.entity.NPC;
import cn.pomit.springwork.netty.entity.User;
import cn.pomit.springwork.netty.mapper.UserMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class LoginUtil {
    @Autowired
    private  UserService userService;
    public void longin() throws Exception {
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> res = excelReader.readXlsx("D:\\test\\Monster.xlsx");
        List<List<String>> map = excelReader.readXlsx("D:\\test\\Ditu.xlsx");
        List<List<String>> say = excelReader.readXlsx("D:\\test\\NPC.xlsx");
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        // 放入一个缓存
        cache.put("username", "wbl1");
        cache.put("Monster", res);
        cache.put("Ditu",map);
        cache.put("Npc",say);
        // 获取缓存
        Object username = cache.getIfPresent("username");
        Object monster = cache.getIfPresent("Monster");
        System.out.println(username+"玩家登陆成功\n"+"怪兽=="+monster);
        System.out.println("恭喜玩家进入屠龙游戏\n游戏开始了请输入相关命令：1.aor 2.move 3.talk 4.exit");
        while(true){
            String cli=read();
            if("aor".equals(cli)){
                System.out.println("打印实体");
                ApplicationContext ac = new ClassPathXmlApplicationContext("spring-netty.xml");
                UserService userService = (UserService) ac.getBean("UserGuavaCache");
                List<User> users = userService.queryAllUser();
                System.out.println(users);
            }else if("move".equals(cli)){
                System.out.println("移动相邻地图");
                Object ditu = cache.getIfPresent("Ditu");
                System.out.println(ditu.toString());
            }else if("talk".equals(cli)){
                System.out.println("对话进行当中");
                ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
                UserService userService= (UserService) ac.getBean("UserGuavaCache");
                String us = userService.queryById(1).getUsername();
                Object say1 = cache.getIfPresent("Npc");
                NPC npc=new NPC(""+us,"\n"+say1);
                System.out.println(npc.toString());
            }else if("exit".equals(cli)){
                System.out.println("客户端登出");
                System.exit(1);
            }else {
                System.out.println("命令输入错误，请输入相关命令：1.aor 2.move 3.talk 4.exit");
            }
        }

    }
    public void zhuce()throws Exception{
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserService userService= (UserService) ac.getBean("UserGuavaCache");
        User user=new User();
        user.setUsername("hhhhh");
        user.setPassword("44444");
        user.setHp(100);
        int i = userService.addUser(user);
        System.out.println(i);
    }

    public static void main(String[] args) throws Exception {
        new LoginUtil().longin();
        //new LoginUtil().zhuce();
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

}
