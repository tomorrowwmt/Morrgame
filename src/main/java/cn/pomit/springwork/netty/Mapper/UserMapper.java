package cn.pomit.springwork.netty.Mapper;

import cn.pomit.springwork.netty.User.Entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    @Select("select * from tb_user where username=#{username}")
    User findUserByName(String username);
    @Select("select * from tb_user where mid=#{mid}")
    List<User> findUserBymap(Long mid);
}
