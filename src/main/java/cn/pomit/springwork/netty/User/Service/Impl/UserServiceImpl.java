package cn.pomit.springwork.netty.User.Service.Impl;
import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.Exception.ErrorCodeException;
import cn.pomit.springwork.netty.Mapper.UserMapper;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.User.Session.Session;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.User.Session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 * userCache
 */
@Service("UserGuavaCache")
public class UserServiceImpl implements UserService {
    private static IdWorker WORKER=new IdWorker(1,1,1);
    private SessionImpl session;
    @Resource
    private UserMapper userMapper;
    @Autowired
    UserService userService;
    @Override
    public String register(String username, String password,String sex,String profession) {
        //检测名字;
        User existuser= userMapper.findUserByName(username);
        //玩家名被占用
            if(existuser!=null){
                System.out.println("用户已经存在了"+ResultCode.PLAYER_EXIST);
                return "用户已存在"+existuser;
            } else {//否则创建账号
                User user = new User();
                user.setUid(WORKER.nextId());
                user.setUsername(username);
                user.setPassword(password);
                user.setSex(sex);
                user.setProfession(profession);
                int i = userMapper.insertSelective(user);
                System.out.println("注册完成"+i);
            }
        //login(session,username,password);
        return username;
    }

    @Override
    public String login(Session session , String username, String password) {
            //判断玩家账号不存在问题
            User user = userService.findUserByName(username);
            String ret=null;
            if (user == null) {
               ret= "账号不存在"+ResultCode.PLAYER_NO_EXIST;
            }else if(user.getUsername()!=null && user.getPassword().equals(password)) {
                ret= "登录完成" + ResultCode.SUCCESS;
            }else {
               ret="用户名或者密码错误";
            }
            //判断玩家是否在其他地方登陆过
            boolean onlineUser = SessionManager.isOnlinePlayer(user.getUid());
            if (onlineUser) {
                Session oldSession = SessionManager.removeSession(user.getUid());
                //踢下线
                oldSession.close();
            }
            return ret;
        }

    @Override
    @Cacheable(value = "userCache",key="#uid")
    public User queryById(Long uid) {
        return userMapper.selectByPrimaryKey(uid);
    }
    @Override
    @Cacheable(value = "userCache",key="#username")
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
    @Override
    @Cacheable(value = "userCache")
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }

    @Override
    @Cacheable(value = "userCache")
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
    @Override
    @Cacheable(value = "userCache")
    public List<User> findUserBymap(Long mid) {
        return userMapper.findUserBymap(mid);
    }
}