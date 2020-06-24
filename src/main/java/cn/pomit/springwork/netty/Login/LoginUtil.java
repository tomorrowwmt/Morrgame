package cn.pomit.springwork.netty.Login;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.mapper.UserMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Component
public class LoginUtil {
    @Autowired
    private  UserService userService;
    //@Autowired
    private IdWorker worker;
   @Autowired
    UserMapper userMapper;
    //定义idworker
    private static IdWorker WORKER=new IdWorker(1,1,1);
    public void  qiehuan(User user)throws Exception{
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> map = excelReader.readXlsx("src\\main\\resources\\Excel\\Ditu.xlsx");
        List<List<String>> say = excelReader.readXlsx("src\\main\\resources\\Excel\\NPC.xlsx");
        ///利用gauva工具类创建一个immutableMap的不可变map,更改只需要替换即可
        Map<String,Object> immutableMap = new ImmutableMap.Builder<String,Object>()
                .put("Ditu",map)
                .put("NPC",say).build();
        System.out.println("请输入相关命令:1.move 3.talk");
        while (true){
            String cli=read();
            if("move".equals(cli)){
                System.out.println("切换地图开启->");
                System.out.println(immutableMap.get("Ditu"));
                System.out.println("完成切换地图操作");
            }else if("talk".equals(cli)){
                System.out.println("npc玩家对话场景");
                System.out.println(immutableMap.get("NPC"));
                break;
            }
        }
    }
    public void login() throws Exception {
        //利用guava工具进行缓存用户数据的操作
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        // 放入一个缓存
        cache.put("username", "wbl1");
        // 获取缓存
        Object username = cache.getIfPresent("username");
        System.out.println("\n"+username+"玩家登陆成功");
    }
    public void aor(User user) throws Exception {
        System.out.println("打印实体");
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-netty.xml");
        UserService userService = (UserService) ac.getBean("UserGuavaCache");
        List<User> users = userService.queryAllUser();
        System.out.println(users);
    }
    public void zhuce()throws Exception{
        User user=new User();
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        //UserService userService= (UserService) ac.getBean("UserGuavaCache");
        UserMapper userMapper = ac.getBean(UserMapper.class);
        while(true) {
            String name=read();
            if ("wbl1".equals(name)) {
                System.out.println("用户已存在请换名字注册");
            } else if ("wbl2".equals(name)) {
                user.setUid(WORKER.nextId());
                user.setUsername("wbl2");
                user.setPassword("44444");
                user.setHp(100);
                int i = userMapper.insertSelective(user);
                System.out.println("注册成功");
            }
        }
    }

    public static void main(String[] args) throws Exception {
       // new LoginUtil().login();
        new LoginUtil().zhuce();
        // new LoginUtil().peizhi();
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
