package cn.pomit.springwork.netty.mapper;

import cn.pomit.springwork.netty.Entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}
