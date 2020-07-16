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
        System.out.println("\n请选择一下操作1.login 2.register");
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
                    ScenceService scenceService= SpringUtil.getBean(ScenceService.class);
                    String login = userService.login(session, username, password);
                    System.out.println(login+"请进行操作1.aoi 2.move 3.talk 4.attack");
                    String operateType = sc.next();
                    if("aoi".equals(operateType)){
                        Command com = CmdServcieFactory.getCommandSevice(operateType);
                        String  scenceByRole= com.handle(user, "打印实体");
                        System.out.println(scenceByRole);
                    }
                    String operateType1=sc.next();
                    if("move".equals(operateType1)){
                        Command command = CmdServcieFactory.getCommandSevice(operateType1);
                        String movescence = command.handle(user, "移动场景");
                        System.out.println(movescence);
                    }
                    String operateType2=sc.next();
                    if("talk".equals(operateType2)){
                        String talkNpc = scenceService.talkNpc(user);
                        System.out.println(talkNpc);
                    }
                    String operateType3=sc.next();
                    if("attack".equals(operateType3)){
                        Command command = CmdServcieFactory.getCommandSevice(operateType3);
                        String s1 = command.handle(user, "打怪");
                        System.out.println(s1);
                    }
                    break;
                case "register":
                    System.out.println("请输入账号：");
                    String username1 = sc.nextLine();
                    System.out.println("请输入密码：");
                    String password1 = sc.nextLine();
                    System.out.println("请输入性别：");
                    String sex=sc.nextLine();
                    System.out.println("请输入职业：");
                    String profession=sc.nextLine();
                    UserService userService1 = SpringUtil.getBean(UserService.class);
                    String register = userService1.register(username1, password1,sex,profession);
                    break;
                default:
                    System.out.println("非法操作，请重新输入");
            }
        }
    }
}
