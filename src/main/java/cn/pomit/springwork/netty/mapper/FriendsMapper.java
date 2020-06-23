package cn.pomit.springwork.netty.mapper;

import cn.pomit.springwork.netty.Entity.Friends;
import cn.pomit.springwork.netty.Entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FriendsMapper {
    //查询朋友
    @Select("select  * from tb_friend f where f.uid=#{uid}")
    List<Friends> queryfriendbyid(int uid);
}
