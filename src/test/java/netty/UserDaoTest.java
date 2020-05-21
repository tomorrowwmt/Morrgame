package netty;
import Dao.BaseTest;
import cn.pomit.springwork.netty.mapper.UserMapper;
import cn.pomit.springwork.netty.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDaoTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void query(){
        List<User> users=userMapper.queryuser();
        System.out.println(users);
    }
    @Test
    public void insert(){
        User user=new User();
        user.setUsername("光光");
        user.setPassword("44444");
        int effNum=userMapper.addUser(user);
    }
    @Test
    public void select(){
        User user=userMapper.getUserById(1);
        System.out.println(user);
    }
}
