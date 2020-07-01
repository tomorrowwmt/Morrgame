package cn.pomit.springwork.netty.handler;
import java.net.InetAddress;
import java.util.List;

import cn.pomit.springwork.netty.Attack.Batter;
import cn.pomit.springwork.netty.BossFuBen.FuBen;
import cn.pomit.springwork.netty.Entity.Shop;
import cn.pomit.springwork.netty.Entity.SysMail;
import cn.pomit.springwork.netty.Entity.UserMail;
import cn.pomit.springwork.netty.GoShopping.Shopping;
import cn.pomit.springwork.netty.Login.LoginUtil;
import cn.pomit.springwork.netty.PK.PkGongji;
import cn.pomit.springwork.netty.ReviceMail.Mail;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.mapper.ShopMapper;
import cn.pomit.springwork.netty.mapper.SysmailMapper;
import cn.pomit.springwork.netty.mapper.UserMapper;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import cn.pomit.springwork.netty.session.Session;
import io.netty.channel.Channel;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static User user=new User();
    //获取现有通道，一个通道channel就是一个socket链接在这里
    //public static ChannelGroup channel = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        String body= (String) msg;
        if("wbl1".equals(body)) {
            LoginUtil loginUtil=SpringUtil.getBean(LoginUtil.class);
            loginUtil.login("wbl1","12345");
            ctx.channel().writeAndFlush("登录成功\n");
            return;
        }else if("aor".equals(body)){
            LoginUtil loginUtil=SpringUtil.getBean(LoginUtil.class);
             loginUtil.aor(user);
            ctx.channel().writeAndFlush("打印成功\n");
            return;
        }else if("attack".equals(body)){
           Batter batter=SpringUtil.getBean(Batter.class);
            batter.attack(user);
            //new Batter().attack(user);
            //通知所有玩家
            UserService userService = (UserService) SpringUtil.getBean("UserGuavaCache");
            List<User> users = userService.queryAllUser();
            ctx.channel().writeAndFlush(users+"\n"+"战斗完成，通知玩家怪兽死亡\n");
            return;
        }else if("qiehuan".equals(body)){
            LoginUtil loginUtil=SpringUtil.getBean(LoginUtil.class);
             loginUtil.qiehuan(user);
            ctx.channel().writeAndFlush("场景切换完成并与npc谈话\n");
            return;
        }else if("zhuce".equals(body)){
            LoginUtil loginUtil=SpringUtil.getBean(LoginUtil.class);
            loginUtil.register("wbl3","12345");
            ctx.channel().writeAndFlush("注册成功\n");
            return;
        }else if("fuben".equals(body)){
           new FuBen().gongji(user);
            ctx.channel().writeAndFlush("退出副本完成\n");
            return;
        }
        else{
            ctx.channel().writeAndFlush("非法操作请输入登陆指令");
        }
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("------Received your message !\n");
    }

    /*
    消息处理
     */

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
    /*
    else if("shop".equals(body)){
        System.out.println("用户来到商店");
        new Shopping().shopping(user);
        //查询商品物品
        ShopMapper shopMapper= SpringUtil.getBean(ShopMapper.class);
        List<Shop> shops = shopMapper.selectAll();
        //查询用户拥有金额
        UserMapper userMapper =  SpringUtil.getBean(UserMapper.class);
        Integer usermoney = userMapper.selectByPrimaryKey(1L).getMoney();
        //获取商品金额
        Integer productprice= shops.get(0).getPrice();
        if(usermoney<productprice){
            ctx.channel().writeAndFlush("购买失败金额不足请充值\n");
        }else{
            System.out.println("用户成功购买");
            ctx.channel().writeAndFlush("购买完成\n");
            return;
        }
    }else if("sendmail".equals(body)){
        System.out.println("邮件正在发送");
        //发送个人邮件
        UserMail userMail=new UserMail();
        new Mail().revicepersonMail(user,userMail);
        //发送系统邮件
        SysMail sysMail=new SysMail();
        new Mail().SystemMail(user,sysMail);
        //查询sysMial邮件title
        SysmailMapper sysmailMapper=SpringUtil.getBean(SysmailMapper.class);
        List<SysMail> sysMails = sysmailMapper.selectAll();
        ctx.channel().writeAndFlush("邮件发送完成\n"+
                "系统邮件标题："+sysMails.get(0).getTitle()+",邮件内容:"+sysMails.get(0).getContect()+"\n"+
                "系统邮件标题："+sysMails.get(1).getTitle()+ ",系统邮件内容:"+sysMails.get(1).getContect()+"\n");
        return;
    }else if("pk".equals(body)){
        User user1=new User();
        User user2=new User();
        new PkGongji().pk(user1,user2);
        ctx.channel().writeAndFlush("玩家詹姆斯胜利\n");
        return;
    }
    */
}
