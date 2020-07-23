package cn.pomit.springwork.netty.handler;

import cn.pomit.springwork.netty.Command.CmdOrder;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

@Component
public class LoginClientHandler extends ChannelInboundHandlerAdapter {
    private SessionImpl session;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server say : " + msg);
        //收到数据;
        String body= (String) msg;
        if ("登陆操作开始".equals(body)) {
            Scanner sc=new Scanner(System.in);
            System.out.print("请输入用户名:");
            String username =sc.nextLine();
            //Scanner sc2=new Scanner(System.in);
            //s=sc.nextLine();
            //String  password=s;
            sc.nextLine();
            System.out.print("请输入密码:");
           String  password = sc.nextLine();
            //String username="wbl1";
            // password= "12345";
            UserService userService = SpringUtil.getBean(UserService.class);
            ScenceService scenceService = SpringUtil.getBean(ScenceService.class);
            String login = userService.login(session, username, password);
            ctx.writeAndFlush(login + "登陆\n");
        }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("\nClient active ");
    }
}
