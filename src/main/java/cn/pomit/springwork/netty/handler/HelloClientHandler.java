package cn.pomit.springwork.netty.handler;
import cn.pomit.springwork.netty.Dao.CommandDao;
import cn.pomit.springwork.netty.Dao.DituDao;
import cn.pomit.springwork.netty.Dao.UserDao;
import cn.pomit.springwork.netty.entity.User;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Slf4j
@Component
public class HelloClientHandler extends ChannelInboundHandlerAdapter {
    public static String Name = "";
    public static Map<String,String> command=new HashMap<String, String>();
    @Autowired
    private CommandDao commandDao;
    @Autowired
    private DituDao dituDao;
    @Autowired
    private UserDao userDao;
    @Override
    public  void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server say : " + msg);
        //System.out.println("牛牛客户端启动成功！");
        //收到数据
        String data= (String) msg;
        if("登陆成功".equals(data)){
            System.out.println("hhh\n");
        }else if(data.contains("wbl2")||data.contains("wbl3")){
            System.out.println(data.substring(4));
            System.out.println("游戏开始啦啦啦啦啦啦");
        }else if(data.contains("wbl1")){
            //System.out.println(data.substring(4));
            System.out.println("游戏开始");
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
