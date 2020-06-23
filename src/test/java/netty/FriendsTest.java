package netty;

import cn.pomit.springwork.netty.Entity.Equipment;
import cn.pomit.springwork.netty.Entity.Friends;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.mapper.EquipMapper;
import cn.pomit.springwork.netty.mapper.FriendsMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class FriendsTest {
    @Test
    public void  query(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        FriendsMapper friendsMapper=ac.getBean(FriendsMapper.class);
        List<Friends> friends = friendsMapper.queryfriendbyid(1);
        for(Friends fd:friends){
            System.out.println(fd);
        }
    }
}
