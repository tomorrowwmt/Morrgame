package cn.pomit.springwork.netty.Login;

import cn.pomit.springwork.netty.Attack.Batter;
import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Npc.NPC;
import cn.pomit.springwork.netty.entity.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
@DependsOn("springContextHolder")
public class LoginUtil {
    @Autowired
    private  UserService userService;
    public void login() throws Exception {
        //启动服务器直接加载所有配置资源
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> res = excelReader.readXlsx("src\\main\\resources\\Excel\\Monster.xlsx");
        List<List<String>> map = excelReader.readXlsx("src\\main\\resources\\Excel\\Ditu.xlsx");
        List<List<String>> say = excelReader.readXlsx("src\\main\\resources\\Excel\\NPC.xlsx");
        List<List<String>> skill = excelReader.readXlsx("src\\main\\resources\\Excel\\Skill.xlsx");
        ///利用gauva工具类创建一个immutableMap的不可变map,更改只需要替换即可
        Map<String,Object> immutableMap = new ImmutableMap.Builder<String,Object>()
                .put("Monster",res)
                .put("Ditu",map)
                .put("NPC",say)
                .put("Skill",skill).build();
        System.out.println("所有地图，怪兽，Npc,技能配置资源加载完成:\n"+immutableMap);
        System.out.println("==================================================");
        //利用guava工具进行缓存用户数据的操作
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        // 放入一个缓存
        cache.put("username", "wbl1");
        // 获取缓存
        Object username = cache.getIfPresent("username");
        System.out.println(username+"玩家登陆成功");
        System.out.println("恭喜玩家进入屠龙游戏\n游戏开始了请输入相关命令：1.aor 2.move 3.talk 4.attack");
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
                System.out.println(immutableMap.get("Ditu"));
                System.out.println("完成切换地图操作");
            }else if("talk".equals(cli)){
                System.out.println("npc玩家对话场景");
                System.out.println(immutableMap.get("NPC"));
            }else if("attack".equals(cli)){
                Batter batter=new Batter();
                batter.attack();
            } else {
                System.out.println("命令输入错误，请输入相关命令：1.aor 2.move 3.talk 4.attack");
            }
        }
    }
    public void zhuce()throws Exception{
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserService userService= (UserService) ac.getBean("UserGuavaCache");
        User user=new User();
        while(true) {
            String name=read();
            if ("wbl1".equals(name)) {
                System.out.println("用户已存在请换名字注册");
            } else if ("register".equals(name)) {
                user.setUsername("wbl3");
                user.setPassword("44444");
                user.setHp(100);
                int i = userService.addUser(user);
                System.out.println("注册成功");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new LoginUtil().login();
        //new LoginUtil().zhuce();
        //new LoginUtil().zhaungbei();
       // new LoginUtil().start();
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
