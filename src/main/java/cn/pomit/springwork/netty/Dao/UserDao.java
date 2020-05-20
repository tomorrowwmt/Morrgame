package cn.pomit.springwork.netty.Dao;

import cn.pomit.springwork.netty.entity.User;

import java.util.List;

public interface UserDao {
    //查询全部用户
   // @Select("select * from tb_user")
    List<User> queryuser();
    //通过id查询用户
   // @Select("select * from tbuser where id=#{id}")
    User getUserById(int uid);
   //添加用户
   // @Insert("insert into tb_user(username, password) values(#{name},#{pwd})")
    int  addUser(User user);
}
