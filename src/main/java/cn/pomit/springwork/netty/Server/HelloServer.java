package cn.pomit.springwork.netty.Server;
import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.handler.HelloServerInitializer;
import com.google.common.collect.ImmutableMap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
//注解方式注入bean，名字是helloServer
@Service("helloServer")
public class HelloServer {
    private static Logger log = Logger.getLogger(HelloServer.class);
    private static IdWorker WORKER=new IdWorker(1,1,1);
    /**
     * 服务端监听的端口地址
     */
    private static final int portNumber = 8099;

    //自动装备变量，spring会根据名字或者类型来装备这个变量，注解方式不需要set get方法了
    @Autowired
    private HelloServerInitializer helloServerInitializer;

    //程序初始方法入口注解，提示spring这个程序先执行这里
    //@PostConstruct
     public static void main(String[] args) throws Exception {
         //启动服务器直接加载所有配置资源
        new HelloServer().jiazai();
         EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            //b.childHandler(helloServerInitializer);
            b.childHandler(new HelloServerInitializer());
            // 服务器绑定端口监听
            ChannelFuture f = b.bind(portNumber).sync();
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();

            log.info("###########################################");
            // 可以简写为
            /* b.bind(portNumber).sync().channel().closeFuture().sync(); */
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public void  jiazai()throws Exception {
        //启动服务器直接加载所有配置资源
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> res = excelReader.readXlsx("src\\main\\resources\\Excel\\Monster.xlsx");
        List<List<String>> map = excelReader.readXlsx("src\\main\\resources\\Excel\\Ditu.xlsx");
        List<List<String>> say = excelReader.readXlsx("src\\main\\resources\\Excel\\NPC.xlsx");
        List<List<String>> skill = excelReader.readXlsx("src\\main\\resources\\Excel\\Skill.xlsx");
        ///利用gauva工具类创建一个immutableMap的不可变map,更改只需要替换即可
        Map<String, Object> immutableMap = new ImmutableMap.Builder<String, Object>()
                .put("Monster", res)
                .put("Ditu", map)
                .put("NPC", say)
                .put("Skill", skill).build();
        System.out.println("所有地图，怪兽，Npc,技能配置资源加载完成:\n" + immutableMap);
    }
}