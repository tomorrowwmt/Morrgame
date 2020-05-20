package netty;
import Dao.BaseTest;
import cn.pomit.springwork.netty.Dao.UserDao;
import cn.pomit.springwork.netty.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDaoTest extends BaseTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void query(){
        List<User> users=userDao.queryuser();
        System.out.println(users);
    }
    @Test
    public void insert(){
        User user=new User();
        user.setUsername("光光");
        user.setPassword("44444");
        int effNum=userDao.addUser(user);
    }
    @Test
    public void select(){
        User user=userDao.getUserById(1);
        System.out.println(user);
    }
}
