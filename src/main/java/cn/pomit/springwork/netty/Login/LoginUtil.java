package cn.pomit.springwork.netty.Login;

import cn.pomit.springwork.netty.Attack.Batter;
import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Service.Impl.UserServiceImpl;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Spring.SpringContextHolder;
import cn.pomit.springwork.netty.entity.Bag;
import cn.pomit.springwork.netty.entity.NPC;
import cn.pomit.springwork.netty.entity.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
@DependsOn("springContextHolder")
public class LoginUtil {
    @Autowired
    private  UserService userService;
    public void longin() throws Exception {
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> res = excelReader.readXlsx("D:\\test\\Monster.xlsx");
        List<List<String>> map = excelReader.readXlsx("D:\\test\\Ditu.xlsx");
        List<List<String>> say = excelReader.readXlsx("D:\\test\\NPC.xlsx");
        List<List<String>> beibao = excelReader.readXlsx("D:\\test\\Beibao.xlsx");
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        // 放入一个缓存
        cache.put("username", "wbl1");
        cache.put("Monster", res);
        cache.put("Ditu",map);
        cache.put("Npc",say);
        cache.put("Beibao",beibao);
        // 获取缓存
        Object username = cache.getIfPresent("username");
        Object monster = cache.getIfPresent("Monster");
        System.out.println(username+"玩家登陆成功\n"+"怪兽加载完成=="+monster);
        System.out.println("恭喜玩家进入屠龙游戏\n游戏开始了请输入相关命令：1.aor 2.move 3.talk 4.attack 5.query");
        while(true){
            String cli=read();
            if("aor".equals(cli)){
                System.out.println("打印实体");
                ApplicationContext ac = new ClassPathXmlApplicationContext("spring-netty.xml");
                UserService userService = (UserService) ac.getBean("UserGuavaCache");
                List<User> users = userService.queryAllUser();
                System.out.println(users);
            }else if("move".equals(cli)){
                System.out.println("切换地图开启->");
                Object ditu = cache.getIfPresent("Ditu");
                System.out.println(ditu.toString());
                System.out.println("完成切换地图操作");
            }else if("talk".equals(cli)){
                ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
                UserService userService= (UserService) ac.getBean("UserGuavaCache");
                String us = userService.queryById(1).getUsername();
                Object say1 = cache.getIfPresent("Npc");
                NPC npc=new NPC(""+us,"与NPC对话\n");
                System.out.println(npc+say1.toString());
            }else if("attack".equals(cli)){
                Batter batter=new Batter();
                batter.attack();
            }else if("query".equals(cli)){
                System.out.println("查看背包里面的道具");
                Object bag = cache.getIfPresent("Beibao");
                System.out.println(bag);
            }
            else {
                System.out.println("命令输入错误，请输入相关命令：1.aor 2.move 3.talk 4.attack 5.query");
            }
        }
    }
    public void zhuce()throws Exception{
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
       UserService userService= (UserService) ac.getBean("UserGuavaCache");
        User user=new User();
        user.setUsername("wbl2");
        user.setPassword("44444");
        user.setHp(100);
        int i = userService.addUser(user);
        System.out.println(i);
    }

    public static void main(String[] args) throws Exception {
        new LoginUtil().longin();
        //new LoginUtil().zhuce();
        //new LoginUtil().zhaungbei();
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
