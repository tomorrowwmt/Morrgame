package com.springwork.netty.handler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
@Slf4j
public class HelloClientHandler extends SimpleChannelInboundHandler<String> {
    public static String Name = "";
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Server say : " + msg);
        if (null == msg) {
            return;
        }
        //收到数
        String data= msg;
        if("Morr游戏客户端登陆成功".equals(data)){
            System.out.println("牛牛客户端启动成功！");
            System.out.println("请选择序号:1.登录   2.注册");
            String cli = read();
            if("1".equals(cli)){
                System.out.println("请输入用户名：");
                Name = read();
                ctx.channel().writeAndFlush("wbl1"+Name);
            }else if("2".equals(cli)){
                System.out.println("请输入新用户名：");
                Name = read();
                ctx.channel().writeAndFlush("wbl2"+Name);
            }else{
                System.out.println("指令输入有误，请重新输入:1.登录   2.注册");
                Name = read();
                ctx.channel().writeAndFlush("wbl2"+Name);
            }
        }else if(data.contains("wbl2")||data.contains("wbl3")){
            System.out.println(data.substring(4));
            System.out.println("请选择序号:1.登录   2.注册");
            while(true){
                String cli = read();
                if("1".equals(cli)){
                    System.out.println("请输入用户名：");
                    Name = read();
                    ctx.channel().writeAndFlush("wbl1"+Name);
                    break;
                }else if("2".equals(cli)){
                    System.out.println("请输入新用户名：");
                    Name = read();
                    ctx.channel().writeAndFlush("wbl2"+Name);
                    break;
                }else{
                    System.out.println("指令输入有误，请重新输入:1.登录   2.注册");
                }
            }
        }else if(data.contains("wbl1")||data.contains("wbl4")){
            System.out.println(data.substring(4));
            System.out.println("游戏开始！请输入命令序号：1打印 2.移动  3.退出");
            while(true){
                String cli = read();
                if("1".equals(cli)){
                    ctx.channel().writeAndFlush("wbl3"+Name);
                    break;
                }else if("2".equals(cli)){
                    ctx.channel().writeAndFlush("wbl4"+Name);
                    break;
                }else if("3".equals(cli)){
                    ctx.channel().writeAndFlush("wbl5"+Name);
                    System.out.println("客户端正常登出，"+Name+"账号非常安全！");
                    System.exit(1);
                }else{
                    System.out.println("指令输入有误，请重新输入:打印 2.移动  3.退出");
                }
            }
            //开始返回结果
        }else if(data.contains("wbl5") || data.contains("wbl6")){
            System.out.println(data.substring(4));
            while(true){
                System.out.println("\n\n游戏开始！请输入命令序号：1打印 2.移动  3.退出");
                String cli = read();
                if("1".equals(cli)){
                    ctx.channel().writeAndFlush("wbl3"+Name);
                    break;
                }else if("2".equals(cli)){
                    ctx.channel().writeAndFlush("wbl4"+Name);
                    break;
                }else if("3".equals(cli)){
                    System.out.println("客户端正常登出，"+Name+"账号非常安全！");
                    ctx.channel().writeAndFlush("wbl5"+Name);
                    System.exit(1);
                }else{
                    System.out.println("指令输入有误，请重新输入:1打印 2.移动  3.退出");
                }
            }
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
