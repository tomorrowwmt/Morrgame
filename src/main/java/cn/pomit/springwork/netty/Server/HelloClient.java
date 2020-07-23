package cn.pomit.springwork.netty.Server;
import cn.pomit.springwork.netty.Command.CmdOrder;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import cn.pomit.springwork.netty.handler.HelloClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

import org.apache.ibatis.annotations.Case;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class HelloClient {
    public static String host = "127.0.0.1";
    public static int port = 8099;
    public static Channel channel;
    public static User user=new User();
    public static SessionImpl session;
    public static void main(String[] args) throws InterruptedException, IOException {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        final Logger logger = LoggerFactory.getLogger(HelloClient.class);
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HelloClientInitializer());

            // 连接服务端
            Channel ch = b.connect(host, port).sync().channel();
            // 控制台输入
            //CmdOrder.jiazai();
            //也可以用while循环
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //Scanner sc=new Scanner(System.in);
           // CmdOrder.jiazai();
            System.out.println("\n请选择一下操作1.login 2.register");
            while (true) {
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                if (s == null || s.length() == 0) {
                    System.out.println("非法输入，请重新选择！");
                    break;
                }
                switch (Objects.requireNonNull(s)){
                    case "login":
                        System.out.println("请输入账号");
                        String username=sc.nextLine();
                        System.out.println("请输入密码：");
                        String password=sc.nextLine();
                        UserService userService = SpringUtil.getBean(UserService.class);
                        String login = userService.login(session, username, password);
                        ch.writeAndFlush(login+"哈哈\r\n");
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
                        ch.writeAndFlush(register+"\r\n");
                        break;
                    default:
                        System.out.println("非法操作，请重新输入");
                }
            }
            //for(;;){
                //ch.writeAndFlush(sc.nextLine()+"\r\n");
           // }
               //Scanner sc = new Scanner(System.in);
               //String line = sc.nextLine();
                /*
                 * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
                 * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
                 * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
                 * */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
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