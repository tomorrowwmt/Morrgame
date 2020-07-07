package cn.pomit.springwork.netty.handler;
import java.net.InetAddress;
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
import cn.pomit.springwork.netty.User.Session.Session;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import com.google.common.collect.ImmutableMap;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
    public static  Monster monster=new Monster();
    public  static Bag bag=new Bag();
    //获取现有通道，一个通道channel就是一个socket链接在这里
    //public static ChannelGroup channel = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        UserService userService=SpringUtil.getBean(UserService.class);
        ScenceService scenceService = SpringUtil.getBean(ScenceService.class);
        //HurtService hurtService=SpringUtil.getBean(HurtService.class);
        HurtBossService hurtBossService=SpringUtil.getBean(HurtBossService.class);
       // BossService bossService=SpringUtil.getBean(BossService.class);
       // BitUserService bitUserService=SpringUtil.getBean(BitUserService.class);
        String body= (String) msg;
        if("wbl1".equals(body)) {
            user.setUsername("wbl1");user.setPassword("12345");
            String user1 = userService.login(session,user.getUsername(),user.getPassword());
            ctx.channel().writeAndFlush(user1+"登录成功,有以下操作1.aoi 2.move 3.talk 4.attack 5.fuben \n");
            return;
        } else if("wbl3".equals(body)){
            user.setUsername("wbl3");user.setPassword("12345");
           String register  = userService.register(user.getUsername(), user.getPassword());
            ctx.channel().writeAndFlush(register+"注册\n");
            return;
        } else if(Command.AOi.contains(body)){
            //String scenceByRole = scenceService.getScenceByRole(user);
            Aoi aoi= SpringUtil.getBean(Aoi.class);
            Command command = CmdServcieFactory.getCommandSevice("aoi");
            String  scenceByRole= command.handle(user, "打印实体");
            ctx.channel().writeAndFlush(scenceByRole + "\n");
            return;
        } else if(Command.MOVE.contains(body)){
           // String s = scenceService.moveScence(user);
            Move move=SpringUtil.getBean(Move.class);
            Command command = CmdServcieFactory.getCommandSevice("move");
            String s = command.handle(user, "移动场景");
             ctx.channel().writeAndFlush( s +"\n");
            return;
        } else if("attack".equals(body)){
            //String batter = hurtService.batter(user,monster,bag);
            Attack attack= SpringUtil.getBean(Attack.class);
            Command command = CmdServcieFactory.getCommandSevice("attack");
            String batter=command.handle(user, "场景打怪");
            ctx.channel().writeAndFlush(batter + "\n");
            return;
        }else if("talk".equals(body)){
            String talkNpc = scenceService.talkNpc(user);
            ctx.channel().writeAndFlush( talkNpc +"\n");
            return;
        }else if("fuben".equals(body)){
            String ret=hurtBossService.gongji(user,boss);
            ctx.channel().writeAndFlush(ret + "\n");
            return;
        }else {
           ctx.channel().writeAndFlush("非法操作");
        }
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("------Received your message !\n");
    }

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
    public static String read() throws Exception{
        String str = "";
        Scanner sc = new Scanner(System.in);
        return str;
    }
}
