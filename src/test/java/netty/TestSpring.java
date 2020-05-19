package netty;
import com.springwork.netty.server.HelloServer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;

public class TestSpring {

    private static Logger log = Logger.getLogger(TestSpring.class);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //加载spirng配置文件
        ApplicationContext ac= new ClassPathXmlApplicationContext("spring-netty.xml");
        //HelloServer helloServer=ac.getBean(HelloServer.class);
        //System.out.println(helloServer);
    }

}