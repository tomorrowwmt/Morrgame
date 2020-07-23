package cn.pomit.springwork.netty.handler;

import cn.pomit.springwork.netty.Command.CmdServcieFactory;
import cn.pomit.springwork.netty.Command.Command;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public  void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UserService userService= SpringUtil.getBean(UserService.class);
        ScenceService scenceService = SpringUtil.getBean(ScenceService.class);
        System.out.println("\n"+ctx.channel().remoteAddress() + " Say : " + msg);
        // HurtBossService hurtBossService=SpringUtil.getBean(HurtBossService.class);
        String data= (String) msg;
        if("login".equals(data)) {
            ctx.writeAndFlush("登陆操作开始\n");
            //Command ca = CmdServcieFactory.getCommandSevice("attack");
            //String s1 = ca.handle(user, "打怪");
            //ctx.writeAndFlush(s1+"\n");
            return;
        }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String nowTime = sdf.format(new Date());
        String msg = nowTime + " " + " 上线了!";
        channelGroup.writeAndFlush(msg);
        channelGroup.add(channel);
        System.out.println("客户端: " + msg);
    }
}
