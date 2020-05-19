package com.springwork.netty.handler;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import io.netty.channel.Channel;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Slf4j
@Service("helloServerHandler")
@Scope("prototype")

@Sharable
//特别注意这个注解@Sharable，默认的4版本不能自动导入匹配的包，需要手动加入
//地址是import io.netty.channel.ChannelHandler.Sharable;

//继承Netty提供的通道传入处理器类，只要复写方法就可以了，简化开发
public class HelloServerHandler extends ChannelInboundHandlerAdapter {
    public static Map<String,String> user = new HashMap<String, String>();
    //获取现有通道，一个通道channel就是一个socket链接在这里
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //有新链接加入，对外发布消息
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        }
        channels.add(ctx.channel());
    }

    //有链接断开，对外发布消息
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        }
        channels.remove(ctx.channel());
    }

    //消息读取有两个方法，channelRead和channelRead0，其中channelRead0可以读取泛型，常用
    //收到消息打印出来，并返还客户端消息

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        String data= (String) msg;
        if("logon".equals(data)){
            ctx.channel().writeAndFlush("nice");
            return;
        }else if(data.contains("wbl1")) {
            String name =data.substring(4);
            user.put(name, "sucess");
            ctx.channel().writeAndFlush("wbl1牛气冲天的用户-" + name + "-登录成功！");
            return;
        }else if(data.contains("wbl2")) {
            ctx.channel().writeAndFlush("wbl2" +"-用户未注册！");
            return ;
        }
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("------Received your message !\n");
    }

    /*
     *
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     *
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}
