package cn.pomit.springwork.netty.Login;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Component
public class LoginUtil {
    private  static Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
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
    public void login(String username,String password) throws Exception {
        UserService userService = SpringUtil.getBean(UserService.class);
        userService.login(username,password);
        // 放入一个缓存
        cache.put(username,username);
        System.out.println(cache.getIfPresent(username)+"登陆成功");
    }
    public void aor(User user) throws Exception {
        System.out.println("打印实体");
        UserService userService= SpringUtil.getBean("UserGuavaCache");
        List<User> users = userService.queryAllUser();
        System.out.println(users);
    }
    public void register(String username,String password){
        UserService userService = SpringUtil.getBean(UserService.class);
        userService.registerAndLogin(username,password);
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
       // new LoginUtil().login("wbl1");
        User user=new User();
       //new LoginUtil().zhuce("wbl3");
    }

}
