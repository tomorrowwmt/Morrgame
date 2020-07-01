package cn.pomit.springwork.netty.mapper;

import cn.pomit.springwork.netty.Entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    @Select("select * from tb_user where username=#{username}")
    User finduserByname(String username);
    //@Select("select * from tb_user where username=#{username} and password=#{password}")
    //User findByUserNameAndPassword(@Param("username")String username, @Param("password")String password);
}
