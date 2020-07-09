package cn.pomit.springwork.netty.Server;
import cn.pomit.springwork.netty.Excel.PeiZhi;
import cn.pomit.springwork.netty.handler.HelloServerInitializer;
import com.google.common.collect.ImmutableMap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import java.util.Map;
public class HelloServer {
    private static Logger log = Logger.getLogger(HelloServer.class);
    /**
     * 服务端监听的端口地址
     */
    private static final int portNumber = 8099;
    //程序初始方法入口注解，提示spring这个程序先执行这里
    //@PostConstruct
    public static void main(String[] args) throws Exception {
         ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
         //启动服务器直接加载所有配置资源
        //PeiZhi.jiazai();
         EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new HelloServerInitializer());
            // 服务器绑定端口监听
            ChannelFuture f = b.bind(portNumber).sync();
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
            log.info("###########################################");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}