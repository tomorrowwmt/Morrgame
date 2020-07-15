package cn.pomit.springwork.netty.Command;

import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@Component
public class CmdOrder {
    public static SessionImpl session;
    public static User user=new User();
    public static void  jiazai() throws Exception {
        System.out.println("请选择一下操作1.login 2.register 3.aoi 4.attack.talk");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (s == null || s.length() == 0) {
                System.out.println("非法输入，请重新选择！");
            }
            String code=s;
            switch (code){
                case "login":
                    System.out.println("请输入账号");
                    String username=sc.nextLine();
                    System.out.println("请输入密码：");
                    String password=sc.nextLine();
                    UserService userService = SpringUtil.getBean(UserService.class);
                    String login = userService.login(session, username, password);
                    System.out.println(login);
                    break;
                case "register":
                    System.out.println("请输入账号：");
                    String username1 = sc.nextLine();
                    System.out.println("请输入密码：");
                    String password1 = sc.nextLine();
                    UserService userService1 = SpringUtil.getBean(UserService.class);
                    String register = userService1.register(username1, password1);
                    break;
                case "aoi":
                   ScenceService scenceService= SpringUtil.getBean(ScenceService.class);
                    String scenceByRole =  scenceService.getScenceByRole(user);
                    break;
                default:
                    System.out.println("错误操作请重新选择");
            }
        }
    }
}
