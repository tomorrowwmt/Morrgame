package cn.pomit.springwork.netty.Service;

import cn.pomit.springwork.netty.Entity.User;

import java.util.List;

/**
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
   User queryById(Integer uid);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<User> queryAllUser();
}