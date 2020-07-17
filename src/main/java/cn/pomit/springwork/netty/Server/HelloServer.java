package cn.pomit.springwork.netty.Server;
import cn.pomit.springwork.netty.handler.HelloServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloServer {
    private static final Logger log = Logger.getLogger(HelloServer.class);
    /**
     * 服务端监听的端口地址
     */
    private static final int portNumber = 8099;
    //程序初始方法入口注解，提示spring这个程序先执行这里
    //@PostConstruct
    public static void main(String[] args) throws Exception {
         ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
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