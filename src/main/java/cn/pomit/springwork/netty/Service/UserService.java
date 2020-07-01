package cn.pomit.springwork.netty.Service;

import cn.pomit.springwork.netty.Entity.User;

import java.util.List;

/**
 */
public interface UserService {
    /**
     * 登录注册用户
     * @return
     */
   void  registerAndLogin(String username, String password);
    //ResultCode registerAndLogin(String username, String password);
    /**
     * 登录
     * @return
     */
   void  login( String username, String password);
    /*
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
   User queryById(Long uid);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<User> queryAllUser();
    /*
    更新数据
     */
    int update(User user);
}