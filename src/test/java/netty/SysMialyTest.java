package netty;

import cn.pomit.springwork.netty.Entity.SysMail;
import cn.pomit.springwork.netty.Entity.UserMail;
import cn.pomit.springwork.netty.mapper.SysmailMapper;
import cn.pomit.springwork.netty.mapper.UsermailMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SysMialyTest {
    @Test
    public void querty(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        SysmailMapper sysmailMapper = ac.getBean(SysmailMapper.class);
        List<SysMail>sysMails = sysmailMapper.selectAll();
        System.out.println(sysMails);
    }
}
