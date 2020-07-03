package cn.pomit.springwork.netty.handler;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import cn.pomit.springwork.netty.Attack.Batter;
import cn.pomit.springwork.netty.BossFuBen.FuBen;
import cn.pomit.springwork.netty.Command.MapReplace;
import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.Ditu.Ditu;
import cn.pomit.springwork.netty.InVoker.Invoker;
import cn.pomit.springwork.netty.InVoker.InvokerManager;
import cn.pomit.springwork.netty.Module.Module;
import cn.pomit.springwork.netty.Npc.NPC;
import cn.pomit.springwork.netty.Service.ScenceService;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;

import cn.pomit.springwork.netty.request.Request;
import cn.pomit.springwork.netty.request.Response;
import cn.pomit.springwork.netty.session.SessionImpl;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
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
    private SessionImpl session;
    public static User user=new User();
    //获取现有通道，一个通道channel就是一个socket链接在这里
    //public static ChannelGroup channel = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        UserService userService=SpringUtil.getBean(UserService.class);
        ScenceService scenceService = SpringUtil.getBean(ScenceService.class);
        String body= (String) msg;
        if("login".equals(body)) {
           String user1 = userService.login(session, "wbl1", "12345");
            ctx.channel().writeAndFlush(user1+"登录成功,有以下操作1.aoi 2.move 3.talk 4.attack 5.bag\"\n");
            return;
        }else if("zhuce".equals(body)){
            String register= userService.register("wbl3", "12345");
            ctx.channel().writeAndFlush(register+"注册\n");
            return;
        } else if(body.contains("aoi")) {
            Map scenceByRole = scenceService.getScenceByRole(user);
            ctx.channel().writeAndFlush(scenceByRole + "\n");
            return;
        }else if("move".equals(body)){
            String s = scenceService.moveScence(user);
            ctx.channel().writeAndFlush( s +"\n");
            return;
        }else if("talk".equals(body)){
            String talkNpc = scenceService.talkNpc(user);
            ctx.channel().writeAndFlush( talkNpc +"\n");
            return;
        }
        else{
           ctx.channel().writeAndFlush("非法操作请输入登陆指令");
        }
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("------Received your message !\n");
    }
    /*
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        handlerMessage(new SessionImpl(ctx.channel()), request);
        ctx.channel().writeAndFlush(request);
    }
    private void handlerMessage(SessionImpl session, Request request) {
        Response response = new Response(request);
        System.out.println("module:" + request.getModule() + "   " + "cmd：" + request.getCommand());
        Invoker invoker = InvokerManager.getInvoker(request.getModule(),request.getCommand());
        if(invoker!=null){
            try{
                ResultCode result=null;
                if(request.getModule()== Module.Auth){
                    result= (ResultCode) invoker.invoke(session,request.getData());
                }else{
                    Object attachment = session.getAttachment();
                    User user= (User) attachment;
                    result= (ResultCode) invoker.invoke(user.getUid(),request.getData());
                }
                session.write(response);
                return;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
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
