package cn.pomit.springwork.netty.Monster.Service;

import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.User.Entity.User;

public interface BitUserService {
    void bit(Monster monster,User user);
}
