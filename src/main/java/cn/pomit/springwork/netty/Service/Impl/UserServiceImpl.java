package cn.pomit.springwork.netty.Service.Impl;

import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.Exception.ErrorCodeException;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.mapper.UserMapper;
import cn.pomit.springwork.netty.session.Session;
import cn.pomit.springwork.netty.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.datasource.DataSourceUtils;
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
    public void registerAndLogin(String username, String password) {
        //检测名字
        User existuser=userMapper.finduserByname(username);
        //玩家名被占用
        while(true){
            if(existuser!=null){
                System.out.println("用户已经存在了"+ResultCode.PLAYER_EXIST);
                break;
            }else {//否则创建账号
                User user = new User();
                user.setUid(WORKER.nextId());
                user.setUsername(username);
                user.setPassword(password);
                int i = userMapper.insertSelective(user);
                //顺便登陆
                login(username, password);
                break;
            }
        }
    }

    @Override
    public void  login(String username, String password) {
        //判断当前会话是否已经登陆了
        //if(session.getAttachment()!=null){
           // throw  new ErrorCodeException(ResultCode.HAS_LOGIN);
        //}
        //玩家账号不存在问题
        User user=userMapper.finduserByname(username);
        if(user==null){
            throw  new ErrorCodeException(ResultCode.PLAYER_NO_EXIST);
        }
        //判断玩家是否在其他地方登陆过
        boolean onlineUser= SessionManager.isOnlinePlayer(user.getUid());
        if(onlineUser){
            Session oldSession= SessionManager.removeSession(user.getUid());
            //踢下线
            oldSession.close();
        }
    }

    @Override
    @Cacheable(value = "userCache",key="#uid")
    public User queryById(Long uid) {
        return userMapper.selectByPrimaryKey(uid);
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