package netty;

import cn.pomit.springwork.netty.EMail.Mapper.UsermailMapper;
import cn.pomit.springwork.netty.User.Entity.UserMail;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class UserMaliTest {
    @Test
    public void querty(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UsermailMapper usermailMapper = ac.getBean(UsermailMapper.class);
        List<UserMail> userMails = usermailMapper.selectAll();
        System.out.println(userMails);
    }
    @Test
    public void insert(){
        UserMail userMail=new UserMail();
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        UsermailMapper usermailMapper = ac.getBean(UsermailMapper.class);
        IdWorker worker=new IdWorker(1,1,1);
        long l=worker.nextId();
        userMail.setMailId(l);
        userMail.setTitle("sdhdsds");
        userMail.setSendtime(new Date());
        userMail.setStatus(0);
        userMail.setUid(1L);
        int insert = usermailMapper.insert(userMail);
    }
}
