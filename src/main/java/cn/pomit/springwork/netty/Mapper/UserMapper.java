package cn.pomit.springwork.netty.Mapper;

import cn.pomit.springwork.netty.User.Entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select * from tb_user where username=#{username}")
    User findUserByName(String username);
    //@Select("select * from tb_user where username=#{username} and password=#{password}")
    //User findByUserNameAndPassword(@Param("username")String username, @Param("password")String password);
}
