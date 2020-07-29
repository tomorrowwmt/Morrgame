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
         //NioEvent是个线程组，
         EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //启动Nio服务端辅助启动类，降低服务端开发复杂度
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            //作用相当于Reactor模式中的Handler类。主要用于处理网络I/0事件，例如记录日志，对消息进行编码
            b.childHandler(new HelloServerInitializer());
            // 服务器绑定端口监听，用于异步操作通知的回调
            ChannelFuture f = b.bind(portNumber).sync();
            // 监听服务器关闭监听，等待服务端链路关闭之后main函数才退出
            f.channel().closeFuture().sync();
            log.info("###########################################");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}