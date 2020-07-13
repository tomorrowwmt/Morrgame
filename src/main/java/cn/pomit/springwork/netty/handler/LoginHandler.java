package cn.pomit.springwork.netty.handler;

import cn.pomit.springwork.netty.Command.CmdServcieFactory;
import cn.pomit.springwork.netty.Command.Command;
import cn.pomit.springwork.netty.User.Entity.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import io.netty.channel.ChannelHandler.Sharable;
@Slf4j
@Service("loginHandler")
@Scope("prototype")
@Component
@Sharable
public class LoginHandler extends ChannelInboundHandlerAdapter {
    public static User user=new User();
    public void channelRead(ChannelHandlerContext ctx, String msg) throws Exception {
        String body= msg;
        if("login".equals(body)){
            user.setUsername("wbl1");
            user.setPassword("12345");
            Command command = CmdServcieFactory.getCommandSevice("login");
            String user1= command.handle(user,"登录");
            ctx.channel().writeAndFlush(user1+"登录成功,有以下操作1.aoi 2.move 3.talk 4.attack\n");
        }
    }
}
