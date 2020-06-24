package cn.pomit.springwork.netty.mapper;

import cn.pomit.springwork.netty.Entity.Friends;
import cn.pomit.springwork.netty.Entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface FriendsMapper extends Mapper<Friends> {
}
