package cn.pomit.springwork.netty.handler;
import cn.pomit.springwork.netty.mapper.UserMapper;
import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@Service("helloServerInitializer")
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    @Qualifier("helloServerHandler")
    @Autowired
    private HelloServerHandler helloServerHandler;
    @Autowired
    private  UserMapper userMapper;
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 以("\n")为结尾分割的 解码器
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 字符串解码 和 编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        // 自己的逻辑Handler
        //pipeline.addLast("handler", helloServerHandler);
        pipeline.addLast("handler",new HelloServerHandler());
    }
}