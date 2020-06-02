package cn.pomit.springwork.netty.Service.Impl;

import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.entity.User;
import cn.pomit.springwork.netty.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * (User)表服务实现类
 * userCache
 */
@Service("UserGuavaCache")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Cacheable(value = "userCache",key="#uid")
    public User queryById(Integer uid) {
        return userMapper.getUserById(uid);
    }
    @Cacheable(value = "userCache")
    public List<User> queryAllUser() {
        return userMapper.queryuser();
    }
    @Cacheable(value = "userCache")
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

}