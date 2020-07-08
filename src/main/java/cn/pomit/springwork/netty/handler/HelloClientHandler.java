package cn.pomit.springwork.netty.handler;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.UserService;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class HelloClientHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private UserService userService;
    public static User user=new User();
    @Override
    public  void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server say : " + msg);
        if (null == msg) {
            return;
        }
        //收到数据
        String data = (String) msg;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常栈跟踪
        cause.printStackTrace();

        // 关闭该Channel
        ctx.close();
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
