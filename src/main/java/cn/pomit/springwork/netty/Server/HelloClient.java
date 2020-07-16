package cn.pomit.springwork.netty.Server;
import cn.pomit.springwork.netty.Command.CmdOrder;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.handler.HelloClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.ibatis.annotations.Case;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class HelloClient {
    public static String host = "127.0.0.1";
    public static int port = 8099;
    public static Channel channel;
    public static User user=new User();
    public static SessionImpl session;
    private static IdWorker WORKER=new IdWorker(1,1,1);
    public static void main(String[] args) throws InterruptedException, IOException {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        final Logger logger = LoggerFactory.getLogger(HelloClient.class);
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HelloClientInitializer());

            // 连接服务端
            Channel ch = b.connect(host, port).sync().channel();
            // 控制台输入
            CmdOrder.jiazai();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            //也可以用while循环
          for (;;) {
             String line = in.readLine();
               if (line == null) {
                   continue;
              }
               /*
                * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
                * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
                * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
                * */
               ch.writeAndFlush(line + "\r\n");
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}