package cn.pomit.springwork.netty.User.Service.Impl;
import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.Exception.ErrorCodeException;
import cn.pomit.springwork.netty.Mapper.UserMapper;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.User.Session.Session;
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
    @Resource
    private UserMapper userMapper;
    @Autowired
    UserService userService;
    @Override
    public String register(String username, String password) {
        //查询缓存检测名字
        User existuser=userService.FindUserByName(username);
        //玩家名被占用
        while(true){
            if(existuser!=null){
                //System.out.println("用户已经存在了"+ResultCode.PLAYER_EXIST);
                return "用户已存在"+existuser;
            }else {//否则创建账号
                User user = new User();
                user.setUid(WORKER.nextId());
                user.setUsername(username);
                user.setPassword(password);
                int i = userMapper.insertSelective(user);
                break;
            }
        }
        return username;
    }

    @Override
    public String login(Session session , String username, String password) {
            //从缓存判断玩家账号不存在问题
            User user = userService.FindUserByName(username);
            if (user == null) {
               return "账号不存在"+ResultCode.PLAYER_NO_EXIST;
            }else if(user.getUsername()!=null){
                System.out.println("登录完成"+ResultCode.SUCCESS);
            }
            //判断玩家是否在其他地方登陆过
            boolean onlineUser = SessionManager.isOnlinePlayer(user.getUid());
            if (onlineUser) {
                Session oldSession = SessionManager.removeSession(user.getUid());
                //踢下线
                oldSession.close();
            }
            return user.getUsername();
        }

    @Override
    @Cacheable(value = "userCache",key="#uid")
    public User queryById(Long uid) {
        return userMapper.selectByPrimaryKey(uid);
    }
    @Cacheable(value = "userCache",key="#username")
    @Override
    public User FindUserByName(String username) {
        return userMapper.FindUserByName(username);
    }
    @Override
    @Cacheable(value = "userCache")
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}