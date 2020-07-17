package cn.pomit.springwork.netty.Command;

import cn.pomit.springwork.netty.User.Entity.User;

/*
指令操作
 */
public interface Command {
  String handle(User user,String content) throws Exception;
}
