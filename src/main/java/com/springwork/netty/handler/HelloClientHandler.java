package com.springwork.netty.handler;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
@Slf4j
public class HelloClientHandler extends ChannelInboundHandlerAdapter {
    public static String Name = "";
    //@Override
    public  void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server say : " + msg);
        System.out.println(777777);
        //收到数据
        String data= (String) msg;
        if("nice".equals(data)){
            System.out.println("牛牛客户端启动成功！");
            System.out.println("请选择序号:1.登录2.注册");
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
            System.out.println("请选择序号:1.登录 2.注册");
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
        }else if(data.contains("wbl1")){
            //System.out.println(data.substring(4));
            System.out.println("游戏开始！请输入命令序号：aor,move,exit\n");
            while(true){
                String cli = read();
                if("aor".equals(cli)){
                    ctx.channel().writeAndFlush("收到打印消息请稍后----");
                    System.out.println("怪兽");
                    break;
                }else if("move".equals(cli)){
                    ctx.channel().writeAndFlush("收到移动消息请稍后----");
                    System.out.println("兽");
                    break;
                }else if("exit".equals(cli)){
                    ctx.channel().writeAndFlush("收到退出消息请稍后");
                    System.out.println("客户端正常登出，"+Name+"账号非常安全！");
                    System.exit(1);
                }else{
                    System.out.println("指令输入有误，请输入命令序号：aor,move,exit");
                }
            }
            //开始返回结果
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
        String str = "logon";
        channel.writeAndFlush(str);
    }
}
