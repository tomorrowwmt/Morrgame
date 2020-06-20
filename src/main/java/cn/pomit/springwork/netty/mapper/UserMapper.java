package cn.pomit.springwork.netty.mapper;

import cn.pomit.springwork.netty.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    //查询全部用户
    @Select("select * from tb_user")
    List<User> queryuser();
    //通过id查询用户
    @Select("select * from tb_user where uid=#{uid}")
    User getUserById(int uid);
   //添加用户
    @Insert("insert into tb_user(uid,username,password)values(#{uid},#{username},#{password})")
    int  addUser(User user);
    @Update("update tb_user set exp=#{exp},level=#{level},levelExp=#{levelExp} where uid=#{uid}")
    int update(User user);
}
