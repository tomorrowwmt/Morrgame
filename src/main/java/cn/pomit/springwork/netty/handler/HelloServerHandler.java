package cn.pomit.springwork.netty.handler;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.Command.*;
import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Monster.Service.BitUserService;
import cn.pomit.springwork.netty.Monster.Service.BossService;
import cn.pomit.springwork.netty.User.Service.HurtBossService;
import cn.pomit.springwork.netty.User.Service.HurtService;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.User.Session.SessionManager;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import com.google.common.collect.ImmutableMap;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("helloServerHandler")
@Scope("prototype")
@Component
@Sharable
//特别注意这个注解@Sharable，默认的4版本不能自动导入匹配的包，需要手动加入
//地址是import io.netty.channel.ChannelHandler.Sharable;

//继承Netty提供的通道传入处理器类，只要复写方法就可以了，简化开发
public class HelloServerHandler extends ChannelInboundHandlerAdapter {
    private IdWorker worker;
    private SessionImpl session;
    public static User user=new User();
    public static Boss boss=new Boss();
    public  static Bag bag=new Bag();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println("\n"+ctx.channel().remoteAddress() + " Say : " + msg);
        UserService userService=SpringUtil.getBean(UserService.class);
        ScenceService scenceService = SpringUtil.getBean(ScenceService.class);
        HurtBossService hurtBossService=SpringUtil.getBean(HurtBossService.class);
        String body= (String) msg;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        //ctx.writeAndFlush(SessionManager.putSession(user.getUid(),session));
        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
        super.channelActive(ctx);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常栈跟踪
        cause.printStackTrace();
        // 关闭该Channel
        ctx.close();
    }
}
