package cn.pomit.springwork.netty.Service.Impl;

import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.mapper.UserMapper;
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
    @Resource
    private UserMapper userMapper;
    @Override
    @Cacheable(value = "userCache",key="#uid")
    public User queryById(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }
    @Override
    @Cacheable(value = "userCache")
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }
    /*
    @Override
    @Cacheable(value = "userCache")
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }
     */
}