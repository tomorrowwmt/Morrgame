package cn.pomit.springwork.netty.handler;
import java.net.InetAddress;
import java.sql.SQLOutput;
import java.util.*;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.entity.Ditu;
import cn.pomit.springwork.netty.entity.NPC;
import cn.pomit.springwork.netty.entity.User;
import cn.pomit.springwork.netty.mapper.UserMapper;
import io.netty.channel.Channel;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;

@Slf4j
@Service("helloServerHandler")
@Scope("prototype")
@Component
@Sharable
//特别注意这个注解@Sharable，默认的4版本不能自动导入匹配的包，需要手动加入
//地址是import io.netty.channel.ChannelHandler.Sharable;

//继承Netty提供的通道传入处理器类，只要复写方法就可以了，简化开发
public class HelloServerHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private  UserMapper userMapper;
    private ExcelReader reader;
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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        User us=new User();
        String body= (String) msg;
        if("login".equals(body)){
            ctx.channel().writeAndFlush("登陆成功");
            return;
        }else if("wbl1".equals(body)) {
            System.out.println("怪兽即将到来---");
            ExcelReader excelReader = new ExcelReader();
            List<List<String>> res = excelReader.readXlsx("D:\\test\\Monster.xlsx");
            ctx.channel().writeAndFlush("用户登录成功怪兽已生成\n"+"怪兽来了==="+res);
            return;
        }else if("insert".equals(body)){
            System.out.println("用户注册正在执行请稍等---");
            ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
            UserMapper userMapper=ac.getBean(UserMapper.class);
            User user=new User();
            user.setUsername("wbl4");
            user.setPassword("1111");
            user.setHp(1000);
            int user1 = userMapper.addUser(user);
            ctx.channel().writeAndFlush("用户-注册成功\n！"+user);
            return;
        } else if("aor".equals(body)){
            System.out.println("打印实体开始");
            ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
            UserMapper userMapper=ac.getBean(UserMapper.class);
            List<User> users=userMapper.queryuser();
            ctx.channel().writeAndFlush("打印成功\n"+users.toString());
        }else if("move".equals(body)){
            System.out.println("移动相邻地图");
            ExcelReader excelReader = new ExcelReader();
            List<List<String>> result = excelReader.readXlsx("D:\\test\\ditu.xlsx");
            ctx.channel().writeAndFlush("移动成功\n"+result.toString());
        }else if("talk".equals(body)){
            System.out.println("talk开始");
            ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
            UserMapper userMapper=ac.getBean(UserMapper.class);
            String username = userMapper.getUserById(14).getUsername();
            ExcelReader excelReader = new ExcelReader();
            List<List<String>> result1 = excelReader.readXlsx("D:\\test\\NPC.xlsx");
            NPC npc=new NPC(""+username," "+result1.get(0));
            ctx.channel().writeAndFlush("欢迎来到超炫酷的游戏\n"+npc);
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
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常栈跟踪
        cause.printStackTrace();
        // 关闭该Channel
        ctx.close();
    }
}
