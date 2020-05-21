package cn.pomit.springwork.netty.handler;
import cn.pomit.springwork.netty.mapper.UserMapper;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class HelloClientHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private UserMapper userMapper;
    @Override
    public  void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server say : " + msg);
        //System.out.println("牛牛客户端启动成功！");
        //收到数据
        String data= (String) msg;
        if("登陆成功".equals(data)){
            System.out.println("牛牛客户端启动----\n");
            return;
        }else if(data.contains("wbl2")){
            System.out.println("游戏即将开始-----");
        }else if(data.contains("wbl1")){
            System.out.println("游戏开始请稍后-----");
        }
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
    public static void sendMsg(Channel channel) {
        String str = "login";
        channel.writeAndFlush(str);
    }
}
