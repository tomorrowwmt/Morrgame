package cn.pomit.springwork.netty.handler;
import java.net.InetAddress;
import java.util.*;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Login.LoginUtil;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.entity.Ditu;
import cn.pomit.springwork.netty.entity.Monster;
import cn.pomit.springwork.netty.entity.NPC;
import cn.pomit.springwork.netty.entity.User;
import cn.pomit.springwork.netty.mapper.UserMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.netty.channel.Channel;

import io.netty.channel.ChannelFuture;
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
        String body= (String) msg;
        if("login".equals(body)) {
            LoginUtil loginUtil=new LoginUtil();
            loginUtil.longin();
            //System.out.println(56666);
            //System.out.println("恭喜玩家进入屠龙破晓游戏\n游戏开始了请输入相关命令：1.aor 2.move 3.talk 4.exit");
            ctx.channel().writeAndFlush("登录成功\n"+loginUtil.toString());
            return;
        }else if("insert".equals(body)){
            new LoginUtil().zhuce();
            ctx.channel().writeAndFlush("用户-注册成功\n！");
            return;
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

        //System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        System.out.println("有客户端登录："+ ctx.channel().remoteAddress().toString());
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
    public void  Attack() throws Exception {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UserMapper userMapper=ac.getBean(UserMapper.class);
        User user=userMapper.getUserById(1);
        Monster[] monsters = new Monster[1];
        monsters[0] = new Monster("雪域魔王",100);
        int n = 1;
        while(true) {
            System.out.println("=============第"+n+"回合=============");
            if(user.getHp()==0) {
                System.out.println("怪兽胜利");
                break;

            }else if (monsters[0].getHp() == 0 ) {
                System.out.println("玩家胜利");
                List<User> users=userMapper.queryuser();
                System.out.println("怪兽已经处于死亡状态，通知所有玩家\n"+"收到over!!=="+users);
                break;
            }
            else {
                System.out.println(user);
                double key = Math.random();
                if(key >= 0.0 && key <= 0.6) {
                    while(monsters[(int)Math.random()*1].getHp() != 0) {
                        user.Attack(monsters[(int)Math.random()*1]);
                        System.out.println(user.getUsername()+"玩家利用普通技能末日风暴和心灵火符对"
                                +monsters[(int)Math.random()*1].getName()
                                +"怪兽使用了普通攻击\n"+"技能使用完CD,1s后恢复");
                        System.out.println("使用普通技能消耗自身值mp为10");
                        System.out.println("CD技能恢复成功");
                        break;
                    }
                }else if (key > 0.6 && key <= 0.7) {
                    while(monsters[(int)Math.random()*1].getHp() != 0) {
                        user.HugeAttack(monsters[(int)Math.random()*3]);
                        System.out.println(user.getUsername()+"玩家利用技能咸鱼突刺对"+monsters[(int)Math.random()*3].getName()
                                +"怪兽使用了必杀\n"+"技能使用完毕CD,2s后恢复请玩家稍后");
                        System.out.println("使用普通技能消耗自身值mp为20");
                        System.out.println("CD技能恢复成功");
                        break;
                    }
                }else if (key > 0.7 && key < 1.0) {
                    user.MagicAttack(monsters);
                    System.out.println(user.getUsername()+"玩家利用技能冰龙破和唤雷术对所有怪兽使用了魔法攻击\n"+"技能使用完CD,3s后恢复");
                    System.out.println("使用普通技能消耗自身值mp为30");
                    System.out.println("CD技能恢复成功");
                    }
                }
                System.out.println(monsters[0].toString());
                monsters[0].Attack(user);
                System.out.println("----------------");
                System.out.println("怪兽对"+user.getUsername()+"玩家使用了普攻\n");
                n++;
            }
        }
}
